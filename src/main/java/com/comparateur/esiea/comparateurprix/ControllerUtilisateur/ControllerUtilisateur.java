package com.comparateur.esiea.comparateurprix.ControllerUtilisateur;

import com.comparateur.esiea.comparateurprix.DAO.UtilisateurRepository;
import com.comparateur.esiea.comparateurprix.Entity.APIClient;
import com.comparateur.esiea.comparateurprix.Entity.AfficheTrajet;
import com.comparateur.esiea.comparateurprix.Entity.Authentification;
import com.comparateur.esiea.comparateurprix.Entity.Utilisateur;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(value = "/Utilisateur")
public class ControllerUtilisateur {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @RequestMapping(value = "Index")
    public String afficherExemple(){
        return "utilisateur";
    }


    @RequestMapping(value = "formulaire",method = RequestMethod.GET)
    public String saveUtilisateur(){
        return "homePage";
    }
    @RequestMapping(value = "homepage")
    public String pageOfficielle(Model model){
        model.addAttribute("Utilisateur",new Utilisateur("","",""));
        return "register";
    }
    @RequestMapping(value = "pageLogin")
    public String pageConnection(Model model){
        model.addAttribute("Utilisateur",new Utilisateur("","",""));
        return "login";
    }

    @RequestMapping(value = "SaveUtilisateur",method = RequestMethod.POST)
    public String saveUtilisateur(Utilisateur utilisateur){
        String Nom  = utilisateur.getNom();
        String Email = utilisateur.getEmail();
        String Password = Authentification.hashPassword(utilisateur.getMotDePasse());
        Utilisateur utilisateur1 = new Utilisateur(Nom,Email,Password);
        utilisateurRepository.save(utilisateur1);
        return "login";
    }
    @RequestMapping(value = "ConnectUtilisateur",method = RequestMethod.POST)
    public String connectUtilisateur(@RequestParam String email, @RequestParam String motDePasse, Model model,String error){
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        boolean connection = Authentification.Connection(utilisateurList, email, motDePasse);
        if(connection == true)return "redirect:Index";
        return "login";
    }

    @RequestMapping(value = "trajetvoyageLink", method = RequestMethod.POST)
    public String departureArrival(@RequestParam String departureCity,@RequestParam String arrivalCity, @RequestParam String departureDate,
                                   Model model){
        List<AfficheTrajet> afficheTrajetList = APIClient.getTrajetVoyage(departureCity,arrivalCity, departureDate);
        afficheTrajetList.sort(Comparator.comparingDouble(AfficheTrajet::getPrix));
        model.addAttribute("afficheTrajetList",afficheTrajetList);
        return "trajetvoyage";
    }

}

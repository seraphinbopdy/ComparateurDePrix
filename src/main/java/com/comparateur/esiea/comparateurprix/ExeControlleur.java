package com.comparateur.esiea.comparateurprix;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExeControlleur {

    @RequestMapping("/Exemple")
    public String afficherExemple(Model model) {
        model.addAttribute("message", "Bienvenue sur notre exemple Thymeleaf !");
        return "Exemple";
    }





}

package com.comparateur.esiea.comparateurprix;

import com.comparateur.esiea.comparateurprix.DAO.UtilisateurRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ComparateurPrixApplication {

    private static String hashPassword(String maman) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(maman, salt);
        return hashedPassword;
    }

    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ComparateurPrixApplication.class, args);
        UtilisateurRepository utilisateurRepository = context.getBean(UtilisateurRepository.class);
        //utilisateurRepository.save(new Utilisateur("Andre","andre@yahoo.fr",hashPassword("jake")));
        //utilisateurRepository.save(new Utilisateur("JEFF","JEFF@yahoo.fr",hashPassword("Alfred")));


        //Page<Utilisateur> util = utilisateurRepository.findAll( PageRequest.of(0, 5));
        //util.forEach(e->System.out.println(e.getNom()));

        /*
        String val = "https://public-api.blablacar.com";
        System.out.println("valeur de l'URL" + val);

       boolean val_ = checkPassword("Alfred",hashPassword("Alfred"));

       if(val_ == true){
           System.out.println("Mot de passe correct !");
       }else {
           System.out.println("Mot de passe incorrect !");
       }
*/

    }

}


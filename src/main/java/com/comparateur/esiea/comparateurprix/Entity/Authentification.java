package com.comparateur.esiea.comparateurprix.Entity;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class Authentification {


    public static boolean Connection(List<Utilisateur> userList, String enteredEmail, String enteredPassword) {
        for (Utilisateur user : userList) {
            String userEmail = user.getEmail();
            String userPassword = user.getMotDePasse();

            if (checkPassword(enteredPassword,userPassword)) {
                // L'utilisateur a été authentifié avec succès
                return true;
            }
        }
        // Aucune correspondance trouvée, l'authentification échoue
        return false;
    }


    public static String hashPassword(String maman) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(maman, salt);
        return hashedPassword;
    }

    public static boolean checkPassword(String enteredPassword, String hashedPassword) {
        return BCrypt.checkpw(enteredPassword, hashedPassword);
    }


}

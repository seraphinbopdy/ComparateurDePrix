package com.comparateur.esiea.comparateurprix.DAO;

import com.comparateur.esiea.comparateurprix.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}

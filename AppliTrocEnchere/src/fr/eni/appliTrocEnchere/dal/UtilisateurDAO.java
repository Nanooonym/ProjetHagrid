package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface UtilisateurDAO {

	List<Utilisateur> selectUtilisateurs() throws BusinessException;
	void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;
	Utilisateur selectUtilisateurByLogin(String pseudo, String motDePasse) throws BusinessException;
}


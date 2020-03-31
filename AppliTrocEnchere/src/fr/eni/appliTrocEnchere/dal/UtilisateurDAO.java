package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface UtilisateurDAO {

	List<Utilisateur> selectUtilisateurs() throws BusinessException;
	void insertUtilisateur(Utilisateur utilisateur) throws BusinessException;

	Utilisateur selectUtilisateurByLogin(String pseudo, String motDePasse) throws BusinessException;

	Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;

	void updateUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	void updateCreditUtilisateur (int credit, int noUtilisateur) throws BusinessException;
	
	void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException;
	
	Utilisateur selectUtilisateurByEmail(String email) throws BusinessException;
}


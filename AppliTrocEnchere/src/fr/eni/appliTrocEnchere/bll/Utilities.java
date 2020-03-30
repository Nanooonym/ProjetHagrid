package fr.eni.appliTrocEnchere.bll;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.ihm.CodesResultatIHM;

public abstract class Utilities {

	//Classe regroupant des fonctionnalités communes à plusieurs classes du projet
	
public static void confirmationMotDePasse(String motDePasse, String confirmation) throws BusinessException {
		
		//Vérifie si le mot de passe et la confirmation entrés par un utilisateurs sont similaires
		if(!confirmation.equals(motDePasse)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.MOT_DE_PASSE_CONFIRMATION_DIFFERENTS);
			throw be;
		}
	}


public static void validationMotDePasse(Utilisateur utilisateur, String motDePasse) throws BusinessException {
	
	//Vérifie si le mot de passe et la confirmation entrés par un utilisateurs sont similaires
	if(!motDePasse.equals(utilisateur.getMotDePasse())) {
		BusinessException be = new BusinessException();
		be.ajouterErreur(CodesResultatIHM.MOT_DE_PASSE_INCORRECT);
		throw be;
	}
}
	
}

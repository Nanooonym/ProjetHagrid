package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface EnchereDAO {

	List<Enchere> afficherEncheres() throws BusinessException;
	void ajouterEnchere(Enchere enchere) throws BusinessException;
	void supprimerEnchere(int id) throws BusinessException;
	List<Enchere> afficherDetailEnchere() throws BusinessException;


}

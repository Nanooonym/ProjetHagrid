package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class EnchereManager {
	
	private EnchereDAO enchereDAO ;
	
	
	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	public List<Enchere> afficherEncheres(String categorie, String article) throws BusinessException {
		int noCategorie; 
		if(categorie==null) {
			noCategorie=0;
		}else {
		noCategorie = Integer.parseInt(categorie);
		}
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere =	enchereDAO.afficherEncheres(noCategorie,article);
		return listeEnchere;
	}
	
	public List<Enchere> afficherDetailEnchere() {
	
	List<Enchere> listeDetailEnchere = new ArrayList<Enchere>();
		
	return listeDetailEnchere;
		
	}
	
}


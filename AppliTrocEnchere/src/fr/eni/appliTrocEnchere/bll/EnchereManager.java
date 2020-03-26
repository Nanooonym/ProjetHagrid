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
	
	public List<Enchere> afficherEncheres() throws BusinessException {
		
	List<Enchere> listeEnchere = new ArrayList<Enchere>();
	
	listeEnchere =	enchereDAO.afficherEncheres();
		
		return listeEnchere;
		
	}
	
}

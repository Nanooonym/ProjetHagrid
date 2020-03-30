package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	BusinessException be;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}
	
	
	 public void ajouterEnchere(Enchere enchere) throws BusinessException {
		 be = new BusinessException();
			if(!be.hasErreurs()) {
				enchereDAO.ajouterEnchere(enchere);
			}else {
				throw be;
			}
	    }

	public List<Enchere> afficherEncheres() throws BusinessException {

		List<Enchere> listeEnchere = new ArrayList<Enchere>();

		listeEnchere = enchereDAO.afficherEncheres();

		return listeEnchere;
	}
	
	
	


}

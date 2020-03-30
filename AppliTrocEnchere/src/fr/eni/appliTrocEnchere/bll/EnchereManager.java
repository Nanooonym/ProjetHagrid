package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
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
		public List<Enchere> afficherEncheresOuvertes() throws BusinessException {
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere =	enchereDAO.afficherEncheresOuvertes();
		return listeEnchere;
	}
	
	public List<Enchere> afficherEncheresEnCours(Utilisateur utilisateur) throws BusinessException {
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere =	enchereDAO.afficherEncheresEnCours(utilisateur);
		return listeEnchere;
	}
	
	public List<Enchere> afficherEncheresRemportees(Utilisateur utilisateur) throws BusinessException {
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		listeEnchere =	enchereDAO.afficherEncheresRemportees(utilisateur);
		return listeEnchere;
	}
}
	


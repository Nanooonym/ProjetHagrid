package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.UtilisateurDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();

	}

	public List<Utilisateur> selectUtilisateurs() throws BusinessException{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs = utilisateurDAO.selectUtilisateurs();
		return utilisateurs;
	}
	
}

package fr.eni.appliTrocEnchere.dal;

import fr.eni.appliTrocEnchere.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		return utilisateurDAO;
	}
}

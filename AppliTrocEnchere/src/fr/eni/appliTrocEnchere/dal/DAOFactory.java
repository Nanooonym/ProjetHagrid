package fr.eni.appliTrocEnchere.dal;

import fr.eni.appliTrocEnchere.dal.jdbc.ArticleVenduDAOJdbcImpl;
import fr.eni.appliTrocEnchere.dal.jdbc.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOJdbcImpl();
		return utilisateurDAO;
	}
	
	public static ArticleVenduDAO getArticleVenduDAO() {
		ArticleVenduDAO articleVenduDAO = new ArticleVenduDAOJdbcImpl();
		return articleVenduDAO;
	}
}

package fr.eni.appliTrocEnchere.bll;

import java.time.LocalDate;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduManager {

	private ArticleVenduDAO articleVenduDao;

	public ArticleVenduManager() {

		articleVenduDao = DAOFactory.getArticleVenduDAO();
	}

	public ArticleVendu ajouterArticleVendu(int noArticle, String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, String etatVente,
			Utilisateur utilisateur, Categorie categorie) throws BusinessException {

		BusinessException businessException = new BusinessException();
		ArticleVendu articleVendu = null;

		if (!businessException.hasErreurs()) {
			articleVendu = new ArticleVendu();
			articleVendu.setNoArticle(noArticle);
			articleVendu.setNomArticle(nomArticle);
			articleVendu.setNomArticle(nomArticle);
			articleVendu.setDescription(description);
			articleVendu.setDateDebutEncheres(dateDebutEncheres);
			articleVendu.setDateFinEncheres(dateFinEncheres);
			articleVendu.setMiseAPrix(miseAPrix);
			articleVendu.setEtatVente(etatVente);
			articleVendu.setUtilisateur(utilisateur);

			this.articleVenduDao.addArticle(articleVendu);
		} else {
			throw businessException;
		}

		return articleVendu;
	}

}
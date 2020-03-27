package fr.eni.appliTrocEnchere.bll;

import java.time.LocalDate;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduManager {

	private ArticleVenduDAO articleVenduDao;

	public ArticleVenduManager() {

		articleVenduDao = DAOFactory.getArticleVenduDAO();
	}

	public ArticleVendu ajouterArticleVendu(ArticleVendu article) throws BusinessException {

		BusinessException businessException = new BusinessException();
		this.validerDateDebut(article.getDateDebutEncheres(), businessException);
		this.validerDateFin(article.getDateFinEncheres(), article.getDateDebutEncheres(), businessException);
		ArticleVendu articleVendu = null;

		if (!businessException.hasErreurs()) {
			articleVendu = new ArticleVendu();
			articleVendu.setNomArticle(article.getNomArticle());
			articleVendu.setDescription(article.getDescription());
			articleVendu.setDateDebutEncheres(article.getDateDebutEncheres());
			articleVendu.setDateFinEncheres(article.getDateFinEncheres());
			articleVendu.setMiseAPrix(article.getMiseAPrix());
			articleVendu.setEtatVente(article.getEtatVente());
			articleVendu.setUtilisateur(article.getUtilisateur());
			articleVendu.setCategorie(article.getCategorie());

			this.articleVenduDao.addArticle(articleVendu);
		} else {
			throw businessException;
		}

		return articleVendu;
	}

	private void validerDateDebut(LocalDate date, BusinessException businessException) {
		if (date == null || date.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_ERREUR);
		}

	}
	
	private void validerDateFin(LocalDate dateFin, LocalDate dateDebut,  BusinessException businessException) {
		if (dateFin==null || dateFin.isBefore(dateDebut) || dateFin.isBefore(LocalDate.now())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_FIN_ERREUR);
			
		}
	}
	
	@SuppressWarnings("unused")
	private void validerLesChamps (ArticleVendu articleVendu, BusinessException businessException) {
		String nomArticle = articleVendu.getNomArticle();
		if(nomArticle == null || nomArticle.equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.NOM_ARTICLE_ERREUR);
		}
		
		String description = articleVendu.getDescription();
		if(description == null || description.equals("")) {
			businessException.ajouterErreur(CodesResultatBLL.DESCRIPTION_ARTICLE_ERREUR);
		}
		
		Categorie categorie = articleVendu.getCategorie();
		if(categorie == null) {
			businessException.ajouterErreur(CodesResultatBLL.CATEGORIE_ARTICLE_ERREUR);
		}
		
		int miseAPrix = articleVendu.getMiseAPrix();
		if(articleVendu == null) {
			businessException.ajouterErreur(CodesResultatBLL.MISE_A_PRIX_ARTICLE_ERREUR);
		}
		
	}

}
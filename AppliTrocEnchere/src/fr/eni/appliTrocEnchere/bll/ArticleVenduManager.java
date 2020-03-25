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

	public ArticleVendu ajouterArticleVendu(String nomArticle, String description,
			LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, String etatVente,
			Utilisateur utilisateur, Categorie categorie) throws BusinessException {

		BusinessException businessException = new BusinessException();
		this.validerDateDebut(dateDebutEncheres, businessException);
		this.validerDateFin(dateFinEncheres, dateDebutEncheres, businessException);
		ArticleVendu articleVendu = null;

		if (!businessException.hasErreurs()) {
			articleVendu = new ArticleVendu();
			articleVendu.setNomArticle(nomArticle);
			articleVendu.setDescription(description);
			articleVendu.setDateDebutEncheres(dateDebutEncheres);
			articleVendu.setDateFinEncheres(dateFinEncheres);
			articleVendu.setMiseAPrix(miseAPrix);
			articleVendu.setEtatVente(etatVente);
			articleVendu.setUtilisateur(utilisateur);
			articleVendu.setCategorie(categorie);

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
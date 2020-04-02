package fr.eni.appliTrocEnchere.bll;

import java.time.LocalDate;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.CategorieDAO;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduManager {

	private ArticleVenduDAO articleVenduDAO;
	private CategorieDAO categorieDAO;
	BusinessException be;

	public ArticleVenduManager() {

		articleVenduDAO = DAOFactory.getArticleVenduDAO();
		categorieDAO = DAOFactory.getCategorieDAO();
	}

	public Retrait ajouterArticleVendu(Retrait retrait) throws BusinessException {

		try {
			be = new BusinessException();
			validerLesChamps(retrait, be);
			articleVenduDAO.addArticle(retrait);
		} catch (Exception e) {
			throw be;
		}
		return retrait;
	}
	
	public Retrait selectArticleById(int idArticle) throws BusinessException {
		Retrait retrait = new Retrait();
		
		retrait = articleVenduDAO.selectArticleById(idArticle);
		
		return retrait ;
		}
	
	public Categorie selectCategorieById(int noCategorie) throws BusinessException{
		Categorie categorie = new Categorie();
		categorie = categorieDAO.selectCategorieByNumeroCategorie(noCategorie);
		return categorie;
	}
	
	 public void deleteVente(int noArticle) throws BusinessException {
	        articleVenduDAO.deleteVente(noArticle);
	    }
	 
	 public Retrait modifierVente(Retrait retrait) throws BusinessException {

			try {
				be = new BusinessException();
				validerLesChamps(retrait, be);
				articleVenduDAO.modifierVente(retrait);
			} catch (Exception e) {
				throw be;
			}
			return retrait;
		}

	private void validerLesChamps(Retrait retrait, BusinessException be) throws BusinessException {
		String nomArticle = retrait.getArticle().getNomArticle();
		if (nomArticle == null || nomArticle.equals("")) {
			be.ajouterErreur(CodesResultatBLL.NOM_ARTICLE_ERREUR);
		}

		String description = retrait.getArticle().getDescription();
		if (description == null || description.equals("")) {
			be.ajouterErreur(CodesResultatBLL.DESCRIPTION_ARTICLE_ERREUR);
		}

		Categorie categorie = retrait.getArticle().getCategorie();
		if (categorie == null) {
			be.ajouterErreur(CodesResultatBLL.CATEGORIE_ARTICLE_ERREUR);
		}

		int miseAPrix = retrait.getArticle().getMiseAPrix();
		if (miseAPrix < 0) {
			be.ajouterErreur(CodesResultatBLL.MISE_A_PRIX_ARTICLE_ERREUR);
		}

		LocalDate dateDebut = retrait.getArticle().getDateDebutEncheres();
		if (dateDebut == null || dateDebut.isBefore(LocalDate.now())) {
			be.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_ERREUR);
		}
		LocalDate dateFin = retrait.getArticle().getDateFinEncheres();
		if (dateFin == null || dateFin.isBefore(dateDebut) || dateFin.isBefore(LocalDate.now())) {
			be.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_FIN_ERREUR);

		}
	}

}
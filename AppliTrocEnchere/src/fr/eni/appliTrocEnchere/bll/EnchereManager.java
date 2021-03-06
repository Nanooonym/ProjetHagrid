package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	BusinessException be;

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
	}

	public void encherir(int ancienneEnchere, int nouvelleEnchere, Utilisateur utilisateur, Utilisateur utilisateurMax,
			int noArticle) throws BusinessException {
		try {
			enchereDAO.encherir(ancienneEnchere, nouvelleEnchere, utilisateur, utilisateurMax, noArticle);

		} catch (BusinessException be) {
			throw be;
		}

	}

	public List<Enchere> afficherEncheres(String categorie, String article) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherEncheres(parseCategorie(categorie), article);
			if (listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}

	public List<Enchere> afficherDetailEnchere() {
		List<Enchere> listeDetailEnchere = new ArrayList<Enchere>();
		return listeDetailEnchere;
	}

	public List<Enchere> afficherEncheresOuvertes(String categorie, String article) throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherEncheresOuvertes(parseCategorie(categorie), article);
			if (listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}

	public List<Enchere> afficherEncheresEnCours(Utilisateur utilisateur, String categorie, String article)
			throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherEncheresEnCours(utilisateur, parseCategorie(categorie), article);
			if (listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}

	public List<Enchere> afficherEncheresRemportees(Utilisateur utilisateur, String categorie, String article)
			throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherEncheresRemportees(utilisateur, parseCategorie(categorie), article);
			if (listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}
	
	public List<Enchere> afficherMesVentesEnCours(Utilisateur utilisateur, String categorie, String article)
			throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherMesVentesEnCours(utilisateur, parseCategorie(categorie), article);
			if(listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}
	
	public List<Enchere> afficherMesVentesNonDebutees(Utilisateur utilisateur, String categorie, String article)
			throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherMesVentesNonDebutees(utilisateur, parseCategorie(categorie), article);
			if(listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}
	
	public List<Enchere> afficherMesVentesTerminees(Utilisateur utilisateur, String categorie, String article)
			throws BusinessException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		try {
			listeEnchere = enchereDAO.afficherMesVentesTerminees(utilisateur, parseCategorie(categorie), article);
			if(listeEnchere.isEmpty()) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
				throw be;
			}
		} catch (BusinessException e) {
			throw e;
		}
		return listeEnchere;
	}

	public int parseCategorie(String categorie) {
		int noCategorie;
		if (categorie == null) {
			noCategorie = 0;
		} else {
			noCategorie = Integer.parseInt(categorie);
		}
		return noCategorie;
	}

	public void testListeVide(List<Enchere> listeEnchere) throws BusinessException {
		if (listeEnchere.isEmpty()) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.AUCUN_RESULTAT);
			throw be;
		}
	}
}

package fr.eni.appliTrocEnchere.dal;

import java.sql.Connection;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public abstract interface EnchereDAO {

	List<Enchere> afficherEncheresOuvertes(int categorie, String article) throws BusinessException;
	List<Enchere> afficherEncheresEnCours(Utilisateur utilisateur, int categorie, String article) throws BusinessException;
	List<Enchere> afficherEncheresRemportees(Utilisateur utilisateur, int categorie, String article) throws BusinessException;
	List<Enchere> afficherMesVentesEnCours(Utilisateur utilisateur, int categorie, String article) throws BusinessException;
	List<Enchere> afficherMesVentesNonDebutees(Utilisateur utilisateur, int categorie, String article) throws BusinessException;
	List<Enchere> afficherMesVentesTerminees(Utilisateur utilisateur, int categorie, String article) throws BusinessException;
	void supprimerEnchere(int id) throws BusinessException;
	List<Enchere> afficherEncheres(int categorie, String article) throws BusinessException;
	void encherir(int ancienneEnchere, int nouvelleEnchere, Utilisateur utilisateur, Utilisateur utilisateurMax,
			int noArticle) throws BusinessException;
	void updatePrixDeVente(int proposition, int noArticle,Connection cnx) throws BusinessException;
	void ajouterEnchere(int noArticle, Utilisateur utilisateur, int proposition,Connection cnx) throws BusinessException;
	void updateEnchere(int noArticle, Utilisateur utilisateur, int proposition,Connection cnx) throws BusinessException;
	int verifEnchereExistante(int noArticle, Utilisateur utilisateur,Connection cnx) throws BusinessException;

}

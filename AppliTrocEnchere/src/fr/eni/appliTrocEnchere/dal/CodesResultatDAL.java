package fr.eni.appliTrocEnchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {

	/**
	 * Echec général quand erreur à l'insertion
	 */
	public static final int SELECT_UTILISATEURS_ECHEC = 10000;
	public static final int INSERT_UTILISATEUR_ECHEC = 10001;
	public static final int INSERT_NOUVEL_ARTICLE_ECHEC = 10002;
	public static final int SELECT_AFFICHER_ENCHERES_ECHEC = 10003;
	public static final int AJOUTER_ENCHERE_ECHEC = 10004;
	public static final int SUPPRIMER_ENCHERE_ECHEC = 10005;
}
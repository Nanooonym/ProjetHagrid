package fr.eni.appliTrocEnchere.dal;

/**
 * Les codes disponibles sont entre 10000 et 19999
 */
public abstract class CodesResultatDAL {

	/**
	 * Listes des codes erreurs possibles dans la DAL
	 */
	public static final int SELECT_UTILISATEURS_ECHEC = 10000;
	public static final int INSERT_UTILISATEUR_ECHEC = 10001;
	public static final int INSERT_NOUVEL_ARTICLE_ECHEC = 10002;
	public static final int SELECT_UTILISATEUR_BY_ID_ECHEC = 10003;
	public static final int AFFICHER_ENCHERES_ECHEC = 10004;
	public static final int SUPPRIMER_ENCHERE_ECHEC = 10005;
	public static final int AJOUTER_ENCHERE_ECHEC = 10007;
	public static final int INSERT_NOUVEAU_LIEU_RETRAIT_ECHEC = 10008;
	public static final int UPDATE_LIEU_RETRAIT_ECHEC = 10009;
	public static final int SELECT_ALL_LIEU_RETRAIT_ECHEC = 10010;
	public static final int DELETE_UTILISATEUR_ECHEC = 10011;
	public static final int UPDATE_UTILISATEUR_ECHEC = 12000;
	public static final int SELECT_UTILISATEUR_BY_PSEUDO_ECHEC = 12001;
	public static final int SELECT_UTILISATEUR_BY_EMAIL_ECHEC = 12002;
	public static final int INSERT_RETRAIT_ECHEC = 13001;
	public static final int SELECT_CATEGORIE_ECHEC = 14012;
	public static final int SELECT_CATEGORIE_BY_NUMERO_ECHEC = 14013;
	public static final int DELETE_ARTICLE_ECHEC = 14014;
	public static final int UPDATE_PRIX_VENTE_ERREUR = 14015;
	public static final int UPDATE_ARTICLE_ECHEC = 14016;
	public static final int SELECT_UTILISATEUR_BY_ENCHERE_MAX=14017;
	public static final int UPDATE_CREDIT_ECHEC=14018;
	public static final int UPDATE_RETRAIT_ECHEC = 13002;
	public static final int UPDATE_VENTE_ECHEC = 13003;
}
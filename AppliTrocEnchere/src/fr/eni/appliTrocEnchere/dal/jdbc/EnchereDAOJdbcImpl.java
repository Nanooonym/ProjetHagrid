package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private final static String AFFICHER_ENCHERES = "SELECT a.no_article, a.nom_article, a.description, a.prix_vente, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur";
	private static final String AJOUTER_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";

	private static final String SUPPRIMER_ENCHERE = "DELETE * FROM ENCHERES WHERE no_enchere=?";
	private static final String AFFICHER_ENCHERES_OUVERTES = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS AS a INNER JOIN UTILISATEURS AS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE a.etat_vente LIKE 'En cours'";
	private static final String AFFICHER_ENCHERES_EN_COURS = "SELECT a.no_article, a.etat_vente, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS AS a INNER JOIN UTILISATEURS AS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie RIGHT JOIN ENCHERES e ON e.no_utilisateur = a.no_utilisateur WHERE a.etat_vente LIKE 'En cours' AND e.no_utilisateur = ?";
	private static final String AFFICHER_ENCHERES_REMPORTEES = "SELECT a.no_article, a.etat_vente, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS AS a INNER JOIN UTILISATEURS AS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie RIGHT JOIN ENCHERES e ON e.no_utilisateur = a.no_utilisateur WHERE a.etat_vente LIKE 'Terminée' AND e.montant_enchere = a.prix_vente AND a.no_utilisateur = ?";
	private static final String FILTRE_CATEGORIES = "a.no_categorie = ?";
	private static final String FILTRE_ARTICLES = "a.nom_article LIKE ?";
	private static final String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	private static final String UPDATE_CREDITER_UTILISATEUR = "UPDATE UTILISATEURS SET credit =? where no_utilisateur=?";
	private static final String UPDATE_DEBITER_UTILISATEUR = "UPDATE UTILISATEURS SET credit =? where no_utilisateur=?";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET montant_enchere=? date_enchere = GETDATE() WHERE no_utilisateur = ? AND no_article = ?";
	private static final String VERIF_ENCHERE_EXISTANTE = "SELECT no_utilisateur,no_article WHERE no_utilisateur = ? AND no_article = ?";

	public List<Enchere> afficherEncheres(int categorie, String article) throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		ResultSet rs = null;
		PreparedStatement psmt;
		try {
			Connection cnx = ConnectionProvider.getConnection();

			String[] requete = filtresRequetes(categorie, article, AFFICHER_ENCHERES);

			switch (requete[1]) {
			case "Pas de Filtre":
				Statement smt = cnx.createStatement();
				rs = smt.executeQuery(requete[0]);
				break;
			case "Filtre Article":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setString(1, "%" + article + "%");
				rs = psmt.executeQuery();
				break;
			case "Filtre Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, categorie);
				rs = psmt.executeQuery();
				break;
			case "Filtre Article ET Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, categorie);
				psmt.setString(2, "%" + article + "%");
				rs = psmt.executeQuery();
				break;
			}

			while (rs.next()) {
				listeEncheres.add(mappingEnchere(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AFFICHER_ENCHERES_ECHEC);
			throw be;
		}

		return listeEncheres;
	}

	@Override
	public List<Enchere> afficherEncheresOuvertes(int categorie, String article) throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		ResultSet rs = null;
		PreparedStatement psmt;
		try {
			Connection cnx = ConnectionProvider.getConnection();

			String[] requete = filtresRequetes(categorie, article, AFFICHER_ENCHERES_OUVERTES);

			switch (requete[1]) {
			case "Pas de Filtre":
				Statement smt = cnx.createStatement();
				rs = smt.executeQuery(requete[0]);
				break;
			case "Filtre Article":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setString(1, "%" + article + "%");
				rs = psmt.executeQuery();
				break;
			case "Filtre Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, categorie);
				rs = psmt.executeQuery();
				break;
			case "Filtre Article ET Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, categorie);
				psmt.setString(2, "%" + article + "%");
				rs = psmt.executeQuery();
				break;
			}
			while (rs.next()) {
				listeEncheres.add(mappingEnchere(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AFFICHER_ENCHERES_ECHEC);
			throw be;
		}
		return listeEncheres;

	}

	public List<Enchere> afficherEncheresEnCours(Utilisateur utilisateur, int categorie, String article)
			throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		ResultSet rs = null;
		PreparedStatement psmt = null;

		try {
			Connection cnx = ConnectionProvider.getConnection();
			String[] requete = filtresRequetes(categorie, article, AFFICHER_ENCHERES_EN_COURS);

			switch (requete[1]) {
			case "Pas de Filtre":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				break;
			case "Filtre Article":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setString(2, "%" + article + "%");
				break;
			case "Filtre Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setInt(2, categorie);
				break;
			case "Filtre Article ET Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setInt(2, categorie);
				psmt.setString(3, "%" + article + "%");
				break;
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				listeEncheres.add(mappingEnchere(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AFFICHER_ENCHERES_ECHEC);
			throw be;
		}
		return listeEncheres;

	}

	public List<Enchere> afficherEncheresRemportees(Utilisateur utilisateur, int categorie, String article)
			throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		ResultSet rs = null;
		PreparedStatement psmt = null;

		try {
			Connection cnx = ConnectionProvider.getConnection();
			String[] requete = filtresRequetes(categorie, article, AFFICHER_ENCHERES_REMPORTEES);

			switch (requete[1]) {
			case "Pas de Filtre":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				break;
			case "Filtre Article":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setString(2, "%" + article + "%");
				break;
			case "Filtre Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setInt(2, categorie);
				break;
			case "Filtre Article ET Categorie":
				psmt = cnx.prepareStatement(requete[0]);
				psmt.setInt(1, utilisateur.getNoUtilisateur());
				psmt.setInt(2, categorie);
				psmt.setString(3, "%" + article + "%");
				break;
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				listeEncheres.add(mappingEnchere(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AFFICHER_ENCHERES_ECHEC);
			throw be;
		}
		return listeEncheres;
	}

	// Mapping d'une enchere

	public static Enchere mappingEnchere(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		ArticleVendu articleVendu = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		Retrait retrait = new Retrait();
		Categorie categorie = new Categorie();

		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		articleVendu.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
		articleVendu.setMiseAPrix(rs.getInt("prix_initial"));
		// categorie.setNoCategorie(rs.getInt("no_categorie"));
		// categorie.setLibelle(rs.getString("libelle"));

		enchere.setArticleVendu(articleVendu);
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_Article"));
		articleVendu.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));

		utilisateur.setPseudo(rs.getString("pseudo"));
		enchere.setUtilisateur(utilisateur);

		// retrait.setRue(rs.getString("rue"));
		// retrait.setCodePostal(rs.getString("code_postal"));
		// retrait.setVille(rs.getString("ville"));

		return enchere;
	}

	public String[] filtresRequetes(int categorie, String article, String requeteAchatVentes) {

		// Ajout les contraintes WHERE de la requete SQL en paramètres en fonction des
		// valerus de Catégorie et Article (filtres demandés par l'utilisateur)
		String[] requete = new String[2];
		if (categorie == 0 && (article == null || article.equals(""))) {
			requete[0] = requeteAchatVentes;
			requete[1] = "Pas de Filtre";
		} else if (categorie == 0 && article != null) {
			requete[0] = requeteAchatVentes + " AND " + FILTRE_ARTICLES;
			requete[1] = "Filtre Article";
		} else if (categorie != 0 && article == null) {
			requete[0] = requeteAchatVentes + " AND " + FILTRE_CATEGORIES;
			requete[1] = "Filtre Categorie";
		} else if (categorie != 0 && article != null) {
			requete[0] = requeteAchatVentes + " AND " + FILTRE_CATEGORIES + " AND " + FILTRE_ARTICLES;
			requete[1] = "Filtre Article ET Categorie";
		}
		return requete;
	}

	// Methode pour creer une enchere
	@Override
	public void ajouterEnchere(int noArticle, Utilisateur utilisateur, int proposition, Connection cnx)
			throws BusinessException {

		try {
			PreparedStatement smt = cnx.prepareStatement(AJOUTER_ENCHERE);

			smt.setInt(1, utilisateur.getNoUtilisateur());
			smt.setInt(2, noArticle);
			smt.setInt(3, proposition);

			smt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AJOUTER_ENCHERE_ECHEC);
			throw be;
		}
	}

	@Override
	public void encherir(int ancienneEnchere, int nouvelleEnchere, Utilisateur utilisateur, Utilisateur utilisateurMax,
			int noArticle) throws BusinessException {

		Connection cnx = null;
		BusinessException be = new BusinessException();
		PreparedStatement psmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			psmt = cnx.prepareStatement(UPDATE_CREDITER_UTILISATEUR);

			psmt.setInt(1, utilisateurMax.getCredit() + ancienneEnchere);
			psmt.setInt(2, utilisateurMax.getNoUtilisateur());
			int nombreUtilisateurModifie = psmt.executeUpdate();

			if (nombreUtilisateurModifie != 1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
				throw be;
			}

			else {
				psmt.close();
				cnx.close();
			}

			psmt = cnx.prepareStatement(UPDATE_DEBITER_UTILISATEUR);

			psmt.setInt(1, utilisateur.getCredit() - nouvelleEnchere);
			psmt.setInt(2, utilisateur.getNoUtilisateur());
			nombreUtilisateurModifie = psmt.executeUpdate();

			if (nombreUtilisateurModifie != 1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
				throw be;
			}

			else {
				psmt.close();
				cnx.close();

			}
			updatePrixDeVente(nouvelleEnchere, noArticle,cnx);
			int existant = verifEnchereExistante(noArticle,utilisateur,cnx);
			if (existant==1) {
				updateEnchere(noArticle, utilisateur, nouvelleEnchere, cnx);
			}
			else {
				ajouterEnchere(noArticle, utilisateur, nouvelleEnchere, cnx);
			}
			

		} catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
			}
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_CREDIT_ECHEC);
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}
	}

	// Methode pour supprimer une enchere
	@Override
	public void supprimerEnchere(int id) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {
			{

				PreparedStatement pst = cnx.prepareStatement(SUPPRIMER_ENCHERE);
				pst.setInt(1, id);
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SUPPRIMER_ENCHERE_ECHEC);
			throw be;
		}
	}

	// Methode pour afficher une enchere ****CA MARCHE PAS*****
	@Override
	public List<Enchere> afficherDetailEnchere() throws BusinessException {
		List<Enchere> listeDetailEnchere = new ArrayList<Enchere>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {
			ResultSet rs = smt.executeQuery(AFFICHER_ENCHERES);
			Enchere enchereCourant = new Enchere();
			while (rs.next()) {
				if (rs.getInt("no_article") != enchereCourant.getNoArticle()) {
					enchereCourant = mappingEnchere(rs);
					listeDetailEnchere.add(enchereCourant);
				}
			}
			return listeDetailEnchere;

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AFFICHER_ENCHERES_ECHEC);
			throw be;
		}
	}

	@Override
	public void updatePrixDeVente(int proposition, int noArticle, Connection cnx) throws BusinessException {

		try {
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_PRIX_VENTE);
			stmt.setInt(1, proposition);
			stmt.setInt(2, noArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.UPDATE_PRIX_VENTE_ERREUR);
			throw be;

		}

	}

	@Override
	public List<Enchere> afficherEncheres() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEnchere(int noArticle, Utilisateur utilisateur, int proposition, Connection cnx)
			throws BusinessException {
		try {
			PreparedStatement smt = cnx.prepareStatement(UPDATE_ENCHERE);
			smt.setInt(1, proposition);
			smt.setInt(2, utilisateur.getNoUtilisateur());
			smt.setInt(3, noArticle);

			smt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AJOUTER_ENCHERE_ECHEC);
			throw be;
		}

	}

	@Override
	public int verifEnchereExistante(int noArticle, Utilisateur utilisateur, Connection cnx)
			throws BusinessException {
		try {
			PreparedStatement smt = cnx.prepareStatement(VERIF_ENCHERE_EXISTANTE);
			smt.setInt(1, utilisateur.getNoUtilisateur());
			smt.setInt(2, noArticle);

			ResultSet rs= smt.executeQuery();
			if(rs.next()) {
				return 1;
				
			}
			else {
				return 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.AJOUTER_ENCHERE_ECHEC);
			throw be;
		}

		
	}
}
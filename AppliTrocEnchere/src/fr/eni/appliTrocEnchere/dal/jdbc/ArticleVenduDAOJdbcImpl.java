package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_NOUVEL_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description,etat_vente, date_debut_encheres,date_fin_encheres, prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS set nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=? no_categorie=? where no_article=?";
	private static final String SELECT_ALL = "SELECT no_article, nom_article,description, prix_vente, date_fin_encheres, pseudo"
			+ "FROM ARTICLES_VENDUS INNER JOIN utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";
	private static final String SELECT_ARTICLE_BY_ID = "SELECT a.no_article, a.nom_article, a.description, a.prix_initial, a.prix_vente, a.date_debut_encheres, a.date_fin_encheres, r.rue, r.code_postal, r.ville, u.pseudo, u.telephone, c.libelle, c.no_categorie FROM ARTICLES_VENDUS a INNER JOIN RETRAITS r ON r.no_article = a.no_article INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE a.no_article=?";
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ? ";
	private static final String UPDATE_PRIX_VENTE = "UPDATE ARTICLES_VENDUS SET prix_vente= (SELECT MAX(montant_enchere) as montant_enchere from ENCHERES where no_article=?) where no_article=?;";
	private static final String DELETE_RETRAIT = "DELETE FROM RETRAITS WHERE no_article = ?";
	private static final String UPDATE_RETRAIT = "UPDATE_RETRAITS SET rue=?, code_postal = ?, ville = ? WHERE no_article = ?";

	
	@Override
	public void addArticle(Retrait retrait) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		PreparedStatement psmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			// pr�paration de la requ�te avec r�cup�ration de l'id correspondant �
			// l'insertion
			ArticleVendu article = new ArticleVendu();
			article = retrait.getArticle();
			psmt = cnx.prepareStatement(INSERT_NOUVEL_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setString(3, article.getEtatVente());
			psmt.setDate(4, Date.valueOf(article.getDateDebutEncheres()));
			psmt.setDate(5, Date.valueOf(article.getDateFinEncheres()));
			psmt.setInt(6, article.getMiseAPrix());
			psmt.setInt(7, article.getPrixVente());
			psmt.setInt(8, article.getUtilisateur().getNoUtilisateur());
			psmt.setInt(9, article.getCategorie().getNoCategorie());

			int nombreArticleInsere = psmt.executeUpdate();

			if (nombreArticleInsere != 1) {
				be.ajouterErreur(CodesResultatDAL.INSERT_NOUVEL_ARTICLE_ECHEC);
				throw be;
			} else {
				ResultSet rs = psmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
				rs.close();
				psmt.close();
			}

			psmt = cnx.prepareStatement(INSERT_RETRAIT);
			psmt.setInt(1, article.getNoArticle());
			psmt.setString(2, retrait.getRue());
			psmt.setString(3, retrait.getCodePostal());
			psmt.setString(4, retrait.getVille());

			
			int nombreArticleInsere2 = psmt.executeUpdate();

			if (nombreArticleInsere2 != 1) {
				be.ajouterErreur(CodesResultatDAL.INSERT_RETRAIT_ECHEC);
				throw be;
			}
			psmt.close();
			cnx.commit();

		} catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {
				be.ajouterErreur(CodesResultatDAL.INSERT_NOUVEL_ARTICLE_ECHEC);
			}
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				be.ajouterErreur(CodesResultatDAL.INSERT_NOUVEL_ARTICLE_ECHEC);
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}
	}
	
	@Override
	public void modifierVente(Retrait retrait) throws BusinessException {
		Connection cnx = null;
		BusinessException be = new BusinessException();
		PreparedStatement psmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			cnx.setAutoCommit(false);
			// pr�paration de la requ�te avec r�cup�ration de l'id correspondant �
			// l'insertion
			ArticleVendu article = new ArticleVendu();
			article = retrait.getArticle();
			psmt = cnx.prepareStatement(UPDATE_ARTICLE);
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			psmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getMiseAPrix());
			psmt.setInt(6, article.getCategorie().getNoCategorie());
			psmt.setInt(7, article.getNoArticle());
			
			int nombreArticleInsere = psmt.executeUpdate();

			if (nombreArticleInsere != 1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
				throw be;
			} else {
				psmt.close();
			}

			psmt = cnx.prepareStatement(UPDATE_RETRAIT);
			psmt.setString(1, retrait.getRue());
			psmt.setString(2, retrait.getCodePostal());
			psmt.setString(3, retrait.getVille());
			psmt.setInt(4, article.getNoArticle());
			
			nombreArticleInsere = psmt.executeUpdate();

			if (nombreArticleInsere != 1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_RETRAIT_ECHEC);
				throw be;
			}
			psmt.close();
			cnx.commit();

		} catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_VENTE_ECHEC);
			}
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				be.ajouterErreur(CodesResultatDAL.UPDATE_VENTE_ECHEC);
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}
	}

	@Override
	public List<ArticleVendu> selectArticleVendu() throws BusinessException {
		List<ArticleVendu> listeArticles = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {
			ResultSet rs = smt.executeQuery(SELECT_ALL);
			ArticleVendu articleCourant = new ArticleVendu();
			while (rs.next()) {
				if (rs.getInt("no_article") != articleCourant.getNoArticle()) {
					articleCourant = mappingArticle(rs);
					listeArticles.add(articleCourant);
				}

			}

			return listeArticles;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_ALL_LIEU_RETRAIT_ECHEC);
			throw be;
		}
	}

	@Override
	public void updateArticle(ArticleVendu article) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);){
			pstmt.setString(1, article.getNomArticle());
			pstmt.setString(2, article.getDescription());
			pstmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			pstmt.setInt(5, article.getMiseAPrix());
			pstmt.setInt(6, article.getCategorie().getNoCategorie());
			pstmt.setInt(7, article.getNoArticle());
			pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw be;
		}

	}

	private ArticleVendu mappingArticle(ResultSet rs) throws SQLException {
		ArticleVendu newArticle = new ArticleVendu();
		
		newArticle.setNoArticle(rs.getInt("no_article"));
		newArticle.setNomArticle(rs.getString("nom_article"));
		newArticle.setDescription(rs.getString("description"));
		newArticle.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		newArticle.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		newArticle.setMiseAPrix(rs.getInt("prix_initial"));
		newArticle.setPrixVente(rs.getInt("prix_vente"));
		
		Retrait retrait = new Retrait();
		retrait.setVille(rs.getString("ville"));
		retrait.setCodePostal(rs.getString("code_postal"));
		retrait.setRue(rs.getString("rue"));
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setTelephone(rs.getString("telephone"));
		
		newArticle.setUtilisateur(utilisateur);
		retrait.setArticle(newArticle);
		
		return newArticle;
	}
	
	private Retrait mappingRetrait(ResultSet rs) throws SQLException {
		ArticleVendu newArticle = new ArticleVendu();
		
		newArticle.setNoArticle(rs.getInt("no_article"));
		newArticle.setNomArticle(rs.getString("nom_article"));
		newArticle.setDescription(rs.getString("description"));
		newArticle.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		newArticle.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		newArticle.setMiseAPrix(rs.getInt("prix_initial"));
		newArticle.setPrixVente(rs.getInt("prix_vente"));
		
		Retrait retrait = new Retrait();
		retrait.setVille(rs.getString("ville"));
		retrait.setCodePostal(rs.getString("code_postal"));
		retrait.setRue(rs.getString("rue"));
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setTelephone(rs.getString("telephone"));
		
		Categorie categorie = new Categorie();
		categorie.setLibelle(rs.getString("libelle"));
		categorie.setNoCategorie(rs.getInt("no_categorie"));
		
		newArticle.setUtilisateur(utilisateur);
		newArticle.setCategorie(categorie);
		
		retrait.setArticle(newArticle);
		
		return retrait;
	}

	@Override
	public Retrait selectArticleById(int noArticle) throws BusinessException {
		Retrait retrait = new Retrait();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);) {
			smt.setInt(1, noArticle);
			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				retrait = mappingRetrait(rs);
				
			}
			return retrait;

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_ECHEC);
			throw be;
		}
	}

	public int deleteArticle(int noArticle, Connection cnx) throws BusinessException {
	
		try (PreparedStatement stmt = cnx.prepareStatement(DELETE_ARTICLE);) {
			stmt.setInt(1, noArticle);
			int nbRetraitSupprime = stmt.executeUpdate();
			stmt.close();
			return nbRetraitSupprime;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			throw be;
		}

	}

	public int deleteRetrait(int noArticle, Connection cnx) throws BusinessException {
	
		try (PreparedStatement stmt = cnx.prepareStatement(DELETE_RETRAIT);) {
			stmt.setInt(1, noArticle);
			int nbRetraitSupprime = stmt.executeUpdate();
			stmt.close();
			return nbRetraitSupprime;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			throw be;
		}
	
	}
	
	public void deleteVente(int noArticle) throws BusinessException{
		Connection cnx = null;
		BusinessException be = new BusinessException();
		try {
			cnx = ConnectionProvider.getConnection();
			int check = deleteArticle(noArticle, cnx);
			if(check != 1) {
				be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
				throw be;
			}
			check = deleteRetrait(noArticle, cnx);
			if(check != 1) {
				be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
				throw be;
			}
			cnx.commit();
		}catch (Exception e) {

			try {
				cnx.rollback();
			} catch (SQLException e1) {
				be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			}
		} finally {
			try {
				cnx.close();
			} catch (SQLException e) {
				be.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			}
			if (be.hasErreurs()) {
				throw be;
			}
		}

	}

	
	@Override
	public void updatePrixDeVente(int proposition, int noArticle) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(UPDATE_PRIX_VENTE);) {
			stmt.setInt(1, noArticle);
			stmt.setInt(2, noArticle);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.UPDATE_PRIX_VENTE_ERREUR);
			throw be;

		}

	}

}

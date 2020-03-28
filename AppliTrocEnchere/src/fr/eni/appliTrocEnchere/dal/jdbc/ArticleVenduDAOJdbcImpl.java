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
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_NOUVEL_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description,etat_vente, date_debut_encheres,date_fin_encheres, prix_initial,prix_vente,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS set nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_vente=? no_categorie=? where no_article=?; ";
	private static final String SELECT_ALL = "SELECT no_article, nom_article,description, prix_vente, date_fin_encheres, pseudo"
			+ "FROM ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES (?,?,?,?)";

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
			
			nombreArticleInsere = psmt.executeUpdate();

			if (nombreArticleInsere != 1) {
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

	}

	private ArticleVendu mappingArticle(ResultSet rs) throws SQLException {
		ArticleVendu newArticle = new ArticleVendu();
		newArticle.setNoArticle(rs.getInt("no_article"));
		newArticle.setNomArticle(rs.getString("nom"));
		newArticle.setDescription(rs.getString("description"));
		newArticle.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
		newArticle.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
		return newArticle;
	}

}

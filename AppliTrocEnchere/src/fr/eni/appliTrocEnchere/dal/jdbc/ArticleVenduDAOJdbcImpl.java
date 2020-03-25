package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.dal.ArticleVenduDAO;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO {

	private static final String INSERT_NOUVEL_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article,"
			+ "description,date_debut_encheres,date_fin_encheres,"
			+ "prix_initial,prix_vente,no_utilisateur,no_categorie) " + "VALUES (?,?,?,?,?,?,?,?);";

	@Override
	public void addArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(INSERT_NOUVEL_ARTICLE);) {
			psmt.setString(1, article.getNomArticle());
			psmt.setString(2, article.getDescription());
			psmt.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
			psmt.setDate(4, Date.valueOf(article.getDateFinEncheres()));
			psmt.setInt(5, article.getMiseAPrix());
			psmt.setInt(6, article.getPrixVente());
			psmt.setInt(7, article.getUtilisateur().getNoUtilisateur());
			psmt.setInt(8, article.getCategorie().getNoCategorie());

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatDAL.INSERT_NOUVEL_ARTICLE_ECHEC);

		}

	}

	@Override
	public List<ArticleVendu> selectArticleVendu() throws BusinessException {
		return null;
	}

}

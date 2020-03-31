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
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;


public class EnchereDAOJdbcImpl implements EnchereDAO{

	private final static String AFFICHER_ENCHERES = "SELECT a.nom_article, a.no_article, a.date_fin_encheres, e.montant_enchere, u.pseudo FROM ARTICLES_VENDUS AS a INNER JOIN ENCHERES e ON a.no_utilisateur = e.no_utilisateur INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur";
	private static final String AJOUTER_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";											
	private static final String SUPPRIMER_ENCHERE = "DELETE * FROM ENCHERES WHERE no_enchere=?";		
	
	
	
	//Methode pour afficher toutes les encheres en page d'accueil
	@Override
	public  List<Enchere> afficherEncheres() throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		
		try (Connection cnx = ConnectionProvider.getConnection();
				Statement smt = cnx.createStatement();) {
			
			ResultSet rs = smt.executeQuery(AFFICHER_ENCHERES);
			
			while(rs.next())
			{
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
	
	//Mapping d'une enchere
	public static Enchere mappingEnchere(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		ArticleVendu articleVendu = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		
		enchere.setArticleVendu(articleVendu);
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_Article"));
		articleVendu.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
		
		utilisateur.setPseudo(rs.getString("pseudo"));
		enchere.setUtilisateur(utilisateur);
		enchere.setMontantEnchere(rs.getInt("montant_enchere"));
		
		return enchere;
	}
	
	
	//Methode pour creer une enchere
	@Override
	public void ajouterEnchere(Enchere enchere) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(AJOUTER_ENCHERE);) {
				
			smt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			smt.setInt(2, enchere.getArticleVendu().getNoArticle());
			//smt.setDate(3, enchere.getDateEnchere());
			smt.setFloat(4, enchere.getMontantEnchere());

			smt.executeUpdate();
		
		} catch (SQLException e) {
		e.printStackTrace();
		BusinessException be = new BusinessException();
		be.ajouterErreur(CodesResultatDAL.AJOUTER_ENCHERE_ECHEC);
		throw be;
		}
	}	
	
	
	//Methode pour supprimer une enchere
	@Override
	public void supprimerEnchere(int id) throws BusinessException {
		

		try (Connection cnx = ConnectionProvider.getConnection();
				Statement smt = cnx.createStatement();) {
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

	
	
	
	
	
	
	

}
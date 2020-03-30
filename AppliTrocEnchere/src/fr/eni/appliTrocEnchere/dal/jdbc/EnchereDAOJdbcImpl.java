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
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.dal.EnchereDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;



public class EnchereDAOJdbcImpl implements EnchereDAO{

	private final static String AFFICHER_ENCHERES = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, e.montant_enchere "
			+ ", a.prix_vente, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS AS a "
			+ "INNER JOIN ENCHERES e ON a.no_utilisateur = e.no_utilisateur INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie ";
	private static final String AJOUTER_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";											
	private static final String SUPPRIMER_ENCHERE = "DELETE * FROM ENCHERES WHERE no_enchere=?";		
	private final static String SELECT_ARTICLES_BY_CATEGORIES = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente,"
			+ " a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur"
			+ " INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE a.no_categorie = ?";
	private final static String SELECT_ARTICLES_NOM_LIKE = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial,"
			+ " a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE a.nom_article LIKE %?%";
	private final static String SELECT_ARTICLES_NOM_LIKE_BY_CAT = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, "
			+ "u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = "
			+ "a.no_categorie WHERE a.nom_article LIKE %?% AND a.no_categorie = ?";
	
	
	
	
	
	
	

	
	//Methode pour afficher toutes les encheres
	@Override
	public  List<Enchere> afficherEncheres(String categorie, String article) throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		ArticleVendu articleVendu = new ArticleVendu();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				Statement smt = cnx.createStatement();) {
		
			
			if(articleVendu==null){
				
			ResultSet rs = smt.executeQuery(AFFICHER_ENCHERES);
			
			}else {
			PreparedStatement prd = prd.prepareStatement(SELECT_ARTICLES_BY_CATEGORIES);
			ResultSet rs = smt.executeQuery(SELECT_ARTICLES_BY_CATEGORIES);
			
			}else {
			
			ResultSet rs = smt.executeQuery(SELECT_ARTICLES_NOM_LIKE);
			
			}else {
			
			ResultSet rs = smt.executeQuery(SELECT_ARTICLES_NOM_LIKE_BY_CAT);
			
			}
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**	//Methode pour afficher toutes les encheres
	@Override
	public  List<Enchere> afficherEncheres(String categorie, String article) throws BusinessException {

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
	**/
	
	
	//Mapping d'une enchere
	public static Enchere mappingEnchere(ResultSet rs) throws SQLException {
		Enchere enchere = new Enchere();
		ArticleVendu articleVendu = new ArticleVendu();
		Utilisateur utilisateur = new Utilisateur();
		Retrait retrait = new Retrait();
		
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_article"));
		articleVendu.setDescription(rs.getString("description"));
		articleVendu.setPrixVente(rs.getInt("prix_Vente"));
		articleVendu.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
		//categorie.setLibelle(rs.getString("libelle"));
		
		enchere.setArticleVendu(articleVendu);
		utilisateur.setNoUtilisateur(rs.getInt("no_Utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		enchere.setUtilisateur(utilisateur);
		enchere.setMontantEnchere(rs.getInt("montant_enchere"));
		
		retrait.setRue(rs.getString("rue"));
		retrait.setCodePostal(rs.getString("code_postal"));
		retrait.setVille(rs.getString("ville"));
		
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


	//Methode pour afficher une enchere ****CA MARCHE PAS*****
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
	public List<Enchere> afficherEncheres() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
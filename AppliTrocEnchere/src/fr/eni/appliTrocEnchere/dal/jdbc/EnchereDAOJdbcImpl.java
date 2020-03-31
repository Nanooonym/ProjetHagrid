package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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

	private final static String AFFICHER_ENCHERES = "SELECT a.no_article, a.nom_article, a.description, a.prix_vente, a.date_fin_encheres, a.prix_initial, u.pseudo, u.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur";
	private static final String AJOUTER_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";											
	private static final String SUPPRIMER_ENCHERE = "DELETE * FROM ENCHERES WHERE no_enchere=?";
	private static final String AFFICHER_ENCHERES_OUVERTES = "SELECT a.no_article, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie WHERE a.etat_vente LIKE '%En cours%'";
	private static final String AFFICHER_ENCHERES_EN_COURS = "SELECT a.no_article, a.etat_vente, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie RIGHT JOIN ENCHERES e ON e.no_utilisateur = a.no_utilisateur WHERE a.etat_vente LIKE 'En cours' AND e.no_utilisateur = ?";
	private static final String AFFICHER_ENCHERES_REMPORTEES = "SELECT a.no_article, a.etat_vente, a.nom_article, a.description, c.libelle, a.date_fin_encheres, a.prix_vente, a.prix_initial, a.date_fin_encheres, u.rue, u.code_postal, u.ville, u.pseudo, a.no_utilisateur, e.date_enchere, e.montant_enchere FROM ARTICLES_VENDUS a INNER JOIN UTILISATEURS u ON u.no_utilisateur = a.no_utilisateur INNER JOIN CATEGORIES c ON c.no_categorie = a.no_categorie RIGHT JOIN ENCHERES e ON e.no_utilisateur = a.no_utilisateur WHERE a.etat_vente LIKE 'Terminée' AND e.montant_enchere = a.prix_vente AND a.no_utilisateur = ?";
	
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
	
	public  List<Enchere> afficherEncheresEnCours(Utilisateur utilisateur) throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		
		try (Connection cnx = ConnectionProvider.getConnection();	

				PreparedStatement psmt = cnx.prepareStatement(AFFICHER_ENCHERES_EN_COURS);) {
			psmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = psmt.executeQuery();
			
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
	
	@Override
	public  List<Enchere> afficherEncheresOuvertes() throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();

		
		try (Connection cnx = ConnectionProvider.getConnection();	
				
				Statement smt = cnx.createStatement();) {
			
			ResultSet rs = smt.executeQuery(AFFICHER_ENCHERES_OUVERTES);
			
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
	
	public  List<Enchere> afficherEncheresRemportees(Utilisateur utilisateur) throws BusinessException {

		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try (Connection cnx = ConnectionProvider.getConnection();	

				PreparedStatement psmt = cnx.prepareStatement(AFFICHER_ENCHERES_REMPORTEES);) {
			psmt.setInt(1, utilisateur.getNoUtilisateur());
			ResultSet rs = psmt.executeQuery();
			
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
		
		articleVendu.setNoArticle(rs.getInt("no_article"));
		articleVendu.setNomArticle(rs.getString("nom_Article"));
		articleVendu.setDateFinEncheres(LocalDate.parse(rs.getString("date_fin_encheres")));
		articleVendu.setPrixVente(rs.getInt("prix_vente"));
		enchere.setArticleVendu(articleVendu);
		
		utilisateur.setPseudo(rs.getString("pseudo"));
		enchere.setUtilisateur(utilisateur);
		
		return enchere;
	}
	
	
	//Methode pour creer une enchere
	@Override
	public void ajouterEnchere(Enchere enchere) throws BusinessException {
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(AJOUTER_ENCHERE);) {
				
			smt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			smt.setInt(2, enchere.getArticleVendu().getNoArticle());
			smt.setTime(3, Time.valueOf(enchere.getDateEnchere()));
			smt.setInt(4, enchere.getMontantEnchere());

			smt.executeUpdate();
			cnx.close();
			
		
		
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
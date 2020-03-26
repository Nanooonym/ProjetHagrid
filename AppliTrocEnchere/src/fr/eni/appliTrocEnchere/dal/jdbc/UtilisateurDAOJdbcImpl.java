package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.dal.UtilisateurDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_UTILISATEURS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur  FROM UTILISATEURS";
//	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS VALUES ?,test,test,test,test,test,test,test,test,0,0";
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,0,0)";
	private static final String SELECT_UTILISATEUR_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	
	
	public Utilisateur selectUtilisateurById( int noUtilisateur) throws BusinessException{
			Utilisateur utilisateurCourant;
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);){
			psmt.setInt(1, noUtilisateur);
			ResultSet rs = psmt.executeQuery();
			
			 utilisateurCourant = new Utilisateur();
			
			while (rs.next()) {
				
					utilisateurCourant = mappingUtilisateur(rs);
				
		} 
			return utilisateurCourant;
			} catch (SQLException e) {
			
			e.printStackTrace();
			
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_ECHEC);
			throw be;
			
		}
		
		
		
	}
	
	
	
	@Override
	public List<Utilisateur> selectUtilisateurs() throws BusinessException {

		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		

		try (Connection cnx = ConnectionProvider.getConnection();
				Statement smt = cnx.createStatement();) {
			
			ResultSet rs = smt.executeQuery(SELECT_UTILISATEURS);
			
			Utilisateur utilisateurCourant = new Utilisateur();
			
			while (rs.next()) {
				if (rs.getInt("no_utilisateur") != utilisateurCourant.getNoUtilisateur()) {
					utilisateurCourant = mappingUtilisateur(rs);
					listeUtilisateurs.add(utilisateurCourant);
				}
			}
			
			return listeUtilisateurs;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEURS_ECHEC);
			throw be;
		}

	}
	
	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException  {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(INSERT_UTILISATEUR);) {
			
			smt.setString(1, utilisateur.getPseudo());
			smt.setString(2, utilisateur.getNom());
			smt.setString(3, utilisateur.getPrenom());
			smt.setString(4, utilisateur.getEmail());
			smt.setString(5, utilisateur.getTelephone());
			smt.setString(6, utilisateur.getRue());
			smt.setString(7, utilisateur.getCodePostal());
			smt.setString(8, utilisateur.getVille());
			smt.setString(9, utilisateur.getMotDePasse());
			
//			int nombreEnregistrementInsere = smt.executeUpdate();
			smt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw be;
		}

	}
	
	public Utilisateur mappingUtilisateur(ResultSet rs) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone(rs.getString("telephone"));
		utilisateur.setRue(rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("code_postal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
		utilisateur.setCredit(rs.getInt("credit"));
		
		int check = rs.getByte("administrateur");
		if(check == 1) {
			utilisateur.setAdministrateur(true);
		}else {
			utilisateur.setAdministrateur(false);
		}

		return utilisateur;
	}





}

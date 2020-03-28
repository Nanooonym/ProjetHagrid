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
	private static final String INSERT_UTILISATEUR = "INSERT INTO UTILISATEURS VALUES (?,?,?,?,?,?,?,?,?,0,0)";
	private static final String SELECT_UTILISATEUR_BY_USER_PASS = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo LIKE ? AND mot_de_passe LIKE ?";
	private static final String SELECT_UTILISATEUR_BY_PSEUDO = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo LIKE ? ";
	private static final String SELECT_UTILISATEUR_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE email LIKE ?" ;
	private static final String SELECT_UTILISATEUR_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String DELETE_UTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String UPDATE_UTILISATEUR = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?";
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {
		Utilisateur utilisateurCourant;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_ID);) {
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

		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {

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
	public void insertUtilisateur(Utilisateur utilisateur) throws BusinessException {
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

			int nbEnregistrement = smt.executeUpdate();
			if(nbEnregistrement == 0) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
				throw be;
			}

		} catch (SQLException e) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw be;
		}

	}

	@Override
	public Utilisateur selectUtilisateurByLogin(String pseudo, String motDePasse) throws BusinessException {
		Utilisateur utilisateur = new Utilisateur();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_USER_PASS);) {
			smt.setString(1, pseudo);
			smt.setString(2, motDePasse);
			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				utilisateur = mappingUtilisateur(rs);
			}
			return utilisateur;

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_ID_ECHEC);
			throw be;
		}

	}
	
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO);) {
			smt.setString(1, pseudo);
			
			ResultSet rs = smt.executeQuery();

			while (rs.next()) {
				utilisateur = mappingUtilisateur(rs);
			}
			return utilisateur;

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_ECHEC);
			throw be;
		}
	}
		
		public Utilisateur selectUtilisateurByEmail(String email) throws BusinessException{
			Utilisateur utilisateur = new Utilisateur();
			
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement smt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_EMAIL);) {
				smt.setString(1, email);
				
				ResultSet rs = smt.executeQuery();

				while (rs.next()) {
					utilisateur = mappingUtilisateur(rs);
				}
				return utilisateur;

			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_EMAIL_ECHEC);
				throw be;
		}	
	}
	
	public void deleteUtilisateur(Utilisateur utilisateur) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(DELETE_UTILISATEUR);) {

			System.out.println(utilisateur.getNoUtilisateur());
			smt.setInt(1, utilisateur.getNoUtilisateur());
			int nbEnregistrements = smt.executeUpdate();
			if(nbEnregistrements == 0) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
				throw be;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.DELETE_UTILISATEUR_ECHEC);
			throw be;
		}

	}
	
	public void updateUtilisateur (Utilisateur utilisateur) throws BusinessException{
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement smt = cnx.prepareStatement(UPDATE_UTILISATEUR);) {
			
			smt.setString(1, utilisateur.getPseudo());
			smt.setString(2, utilisateur.getNom());
			smt.setString(3, utilisateur.getPrenom());
			smt.setString(4, utilisateur.getEmail());
			smt.setString(5, utilisateur.getTelephone());
			smt.setString(6, utilisateur.getRue());
			smt.setString(7, utilisateur.getCodePostal());
			smt.setString(8, utilisateur.getVille());
			smt.setString(9, utilisateur.getMotDePasse());
			smt.setInt(10, utilisateur.getNoUtilisateur());
			
			smt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.UPDATE_UTILISATEUR_ECHEC);
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
		if (check == 1) {
			utilisateur.setAdministrateur(true);
		} else {
			utilisateur.setAdministrateur(false);
		}

		return utilisateur;
	}

}

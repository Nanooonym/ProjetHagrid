package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
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

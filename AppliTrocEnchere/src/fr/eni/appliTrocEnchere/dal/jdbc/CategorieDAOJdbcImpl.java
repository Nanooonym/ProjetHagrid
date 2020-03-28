package fr.eni.appliTrocEnchere.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.dal.CategorieDAO;
import fr.eni.appliTrocEnchere.dal.CodesResultatDAL;
import fr.eni.appliTrocEnchere.dal.ConnectionProvider;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * @author remit Classe implémentant l'interface Categorie.
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {

	private final static String SELECT_CATEGORIE = "SELECT no_categorie, libelle FROM CATEGORIES;";

	private final static String SELECT_CATEGORIE_BY_NUMERO = "SELECT no_categorie, libelle FROM CATEGORIES WHERE no_categorie = ?";


	@Override
	public List<Categorie> selectCategorie() throws BusinessException {
		List<Categorie> listeDeCategories = new ArrayList<Categorie>();
		Categorie categorieCourante = new Categorie();
		try (Connection cnx = ConnectionProvider.getConnection(); Statement smt = cnx.createStatement();) {
			ResultSet rs = smt.executeQuery(SELECT_CATEGORIE);

			while (rs.next()) {
				if (rs.getInt("no_categorie") != categorieCourante.getNoCategorie()) {
					categorieCourante = mappingCategorie(rs);

					listeDeCategories.add(categorieCourante);
				}

			}

			return listeDeCategories;
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_ECHEC);
			throw be;

		}

	}

	@Override
	public Categorie selectCategorieByNumeroCategorie(int numero) throws BusinessException {

		Categorie categorieCourante;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(SELECT_CATEGORIE_BY_NUMERO);) {
			psmt.setInt(1, numero);
			ResultSet rs = psmt.executeQuery();

			categorieCourante = new Categorie();

			while (rs.next()) {

				categorieCourante = mappingCategorie(rs);

			}
			return categorieCourante;
		} catch (SQLException e) {

			e.printStackTrace();

			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatDAL.SELECT_CATEGORIE_BY_NUMERO_ECHEC);
			throw be;

		}
	}

	private Categorie mappingCategorie(ResultSet rs) throws SQLException {
		Categorie newCategorie = new Categorie();
		newCategorie.setNoCategorie(rs.getInt("no_categorie"));
		newCategorie.setLibelle(rs.getString("libelle"));
		return newCategorie;
	}

}

package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * @author remit Interface sur les méthodes utilisées pour la classe Catégorie
 */
public interface CategorieDAO {

	public List<Categorie> selectCategorie() throws BusinessException;

	public Categorie selectCategorieByNumeroCategorie(int numero) throws BusinessException;

}

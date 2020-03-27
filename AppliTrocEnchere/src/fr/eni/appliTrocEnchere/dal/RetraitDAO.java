package fr.eni.appliTrocEnchere.dal;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * @author remit
 * 
 * Interface avec les différentes méthodes de RetraitDAO
 *
 */
public abstract interface RetraitDAO {

	public void addRetrait(Retrait retrait) throws BusinessException;

	public void updateRetrait(Retrait retrait) throws BusinessException;

	public List<Retrait> selectAllRetrait() throws BusinessException;

}
package fr.eni.appliTrocEnchere.bll;

import java.util.List;

import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.RetraitDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * @author remit
 *Classe RetraitManager permettant de vérifier les erreurs existantes
 */
public class RetraitManager {

	private RetraitDAO retraitDAO;
	BusinessException be;

	// crÃ©ation d'une instance RetraitManager
	public RetraitManager() {
		retraitDAO = DAOFactory.getRetraitDAO();

	}

	/**
	 * @param nouveauLieuDeRetrait
	 * @return unLieuDeRetrait
	 * @throws BusinessException
	 */
	public Retrait addRetrait(Retrait nouveauLieuDeRetrait) throws BusinessException {
		be = new BusinessException();
		verificationRetrait(nouveauLieuDeRetrait, be);

		boolean lieuDeRetraitExistant = false;
		List<Retrait> listeLieuDeRetraits = retraitDAO.selectAllRetrait();
		for (Retrait lieuDeRetrait : listeLieuDeRetraits) {
			if (nouveauLieuDeRetrait.equals(lieuDeRetrait)) {
				lieuDeRetraitExistant = true;
				return lieuDeRetrait;
			}
		}
		
		if (!lieuDeRetraitExistant && !be.hasErreurs()) {
			retraitDAO.addRetrait(nouveauLieuDeRetrait);
			return nouveauLieuDeRetrait;

		} else {
			throw be;
		}

	}

	/**
	 * @param retrait
	 * @param be
	 */
	private void verificationRetrait(Retrait retrait, BusinessException be) {
		String rue = retrait.getRue();
		if (rue == null || rue.equals("")) {
			be.ajouterErreur(CodesResultatBLL.RUE_RETRAIT_ERREUR);
		}

		String codePostal = retrait.getCodePostal();
		if (codePostal == null || codePostal.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CODE_POSTAL_RETRAIT_ERREUR);
		}

		String ville = retrait.getVille();
		if (ville == null || ville.equals("")) {
			be.ajouterErreur(CodesResultatBLL.VILLE_RETRAIT_ERREUR);
		}

	}

}
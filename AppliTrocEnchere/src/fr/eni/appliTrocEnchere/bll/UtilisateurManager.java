package fr.eni.appliTrocEnchere.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.dal.DAOFactory;
import fr.eni.appliTrocEnchere.dal.UtilisateurDAO;
import fr.eni.appliTrocEnchere.exception.BusinessException;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;
	BusinessException be;
	
	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();

	}
	
	public void updateUtilisateur (Utilisateur utilisateur) throws BusinessException{
		be = new BusinessException();
		verificationUtilisateur(utilisateur, be);
		if (!be.hasErreurs()) {
			utilisateurDAO.updateUtilisateur(utilisateur);
		}else {
			throw be;
		}
	}

	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurDAO.selectUtilisateurById(noUtilisateur);
		return utilisateur;
	}
	
	public List<Utilisateur> selectUtilisateurs () throws BusinessException{
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		utilisateurs = utilisateurDAO.selectUtilisateurs();
		return utilisateurs;
	}
	
	public Utilisateur selectUtilisateurByPseudo(String pseudo) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurDAO.selectUtilisateurByPseudo(pseudo);
		return utilisateur;	
	}
	
	public Utilisateur selectUtilisateurByEmail(String email) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurDAO.selectUtilisateurByEmail(email);
		return utilisateur;	
	}
	
	public Utilisateur selectUtilisateursByLogin (String pseudo, String motDePasse) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = utilisateurDAO.selectUtilisateurByLogin(pseudo, motDePasse);
		return utilisateur;
	}
	
	public void insertUtilisateur (Utilisateur utilisateur) throws BusinessException{
		be = new BusinessException();
		verificationUtilisateur(utilisateur, be);
		if(!be.hasErreurs()) {
			utilisateurDAO.insertUtilisateur(utilisateur);
		}else {
			throw be;
		}
	}
	
	public void deleteUtilisateur (Utilisateur utilisateur) throws BusinessException{
		be = new BusinessException();
		
		try {
			utilisateurDAO.deleteUtilisateur(utilisateur);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void verificationUtilisateur (Utilisateur utilisateur, BusinessException be) {
		String pseudo = utilisateur.getPseudo();
		if(pseudo == null || pseudo.equals("")) {
			be.ajouterErreur(CodesResultatBLL.PSEUDO_UTILISATEUR_ERREUR);
		}
		
		String nom = utilisateur.getNom();
		if(nom == null || nom.equals("")) {
			be.ajouterErreur(CodesResultatBLL.NOM_UTILISATEUR_ERREUR);
		}
		
		String prenom = utilisateur.getPrenom();
		if(prenom == null || prenom.equals("")) {
			be.ajouterErreur(CodesResultatBLL.PRENOM_UTILISATEUR_ERREUR);
		}
		
		String email = utilisateur.getEmail();
		if(email == null || email.equals("")) {
			be.ajouterErreur(CodesResultatBLL.EMAIL_UTILISATEUR_ERREUR);
		}
		
		String rue = utilisateur.getRue();
		if(rue == null || rue.equals("")) {
			be.ajouterErreur(CodesResultatBLL.RUE_UTILISATEUR_ERREUR);
		}
		
		String codePostal = utilisateur.getCodePostal();
		if(codePostal == null || codePostal.equals("")) {
			be.ajouterErreur(CodesResultatBLL.CODE_POSTAL_UTILISATEUR_ERREUR);
		}
		
		String ville = utilisateur.getVille();
		if(ville == null || ville.equals("")) {
			be.ajouterErreur(CodesResultatBLL.VILLE_UTILISATEUR_ERREUR);
		}
		
		String motDePasse = utilisateur.getMotDePasse();
		if(motDePasse == null || motDePasse.equals("")) {
			be.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_UTILISATEUR_ERREUR);
		}
		
		
	}
}



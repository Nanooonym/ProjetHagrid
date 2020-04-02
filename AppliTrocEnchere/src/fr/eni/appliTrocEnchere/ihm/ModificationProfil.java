package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bll.Utilities;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ModificationProfil")
public class ModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		String pseudo;
		String prenom;
		String telephone;
		String codePostal;
		String motDePasseActuel;
		String nouveauMotDePasse;
		String nom;
		String email;
		String rue;
		String ville;
		int credit;
		String confirmation;
		UtilisateurManager utilisateurManager;
		Utilisateur utilisateurSession;
		HttpSession session;
	
    public ModificationProfil() {
        super();       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");		
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		utilisateurSession = new Utilisateur();
		utilisateurManager = new UtilisateurManager();
		confirmation = request.getParameter("confirmation");
		nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		Utilisateur utilisateurUpdate = new Utilisateur();
		
		try {
			Utilities.confirmationMotDePasse(nouveauMotDePasse, confirmation);
			
			//Création d'un utilisateur temporaire pour ne pas modifier l'utilisateur en cours en cas d'échec
			utilisateurUpdate = mappingUtilisateur(request);
			
			//Récupération de l'utilisateur
			session = request.getSession();	
			utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");

			//Affectation des valeurs non-demandées dans le formulaire, pour contourner les nullPointerException
			utilisateurUpdate.setNoUtilisateur(utilisateurSession.getNoUtilisateur());
			utilisateurUpdate.setCredit(utilisateurSession.getCredit());
			utilisateurUpdate.setAdministrateur(utilisateurSession.isAdministrateur());
			utilisateurUpdate.setMotDePasse(nouveauMotDePasse);
			
			//Vérification si pseudo et email déjà existants en cas de modification apportée
			existantCheck(utilisateurSession, utilisateurUpdate);
			
			utilisateurManager.updateUtilisateur(utilisateurUpdate);
			
			session.setAttribute("utilisateur", utilisateurUpdate);
			RequestDispatcher rd = request.getRequestDispatcher("/MonProfil");
			rd.forward(request, response);

		} catch (BusinessException e) {

		request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
		doGet(request, response);
		
		}
	}

	public void existantCheck(Utilisateur utilisateurSession, Utilisateur utilisateurUpdate) throws BusinessException {
		
		Utilisateur utilisateurCheck = new Utilisateur();
		
		//Vérifie si le pseudo de session et le pseudo saisi sont différents
		if(!utilisateurSession.getPseudo().equals(utilisateurUpdate.getPseudo())) {
			
			//Vérifier si un Pseudo similaire existe
			utilisateurCheck = utilisateurManager.selectUtilisateurByPseudo(utilisateurUpdate.getPseudo());
			
			if(utilisateurCheck.getPseudo()!=null) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatIHM.UTILISATEUR_DEJA_EXISTANT);
				throw be;
			}
		}
		
		//Vérifie si l'email de session et l'email saisi sont différents
		if(!utilisateurSession.getEmail().equals(utilisateurUpdate.getEmail())) {
		
			utilisateurCheck = utilisateurManager.selectUtilisateurByEmail(utilisateurUpdate.getEmail());
			
			if(utilisateurCheck.getEmail()!=null) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatIHM.EMAIL_DEJA_EXISTANT);
				throw be;
			}
		}
	}
	
	public Utilisateur mappingUtilisateur(HttpServletRequest request) {
		pseudo = request.getParameter("pseudo");
		prenom = request.getParameter("prenom");
		telephone = request.getParameter("telephone");
		codePostal = request.getParameter("codePostal");
		nouveauMotDePasse = request.getParameter("nouveauMotDePasse");
		nom = request.getParameter("nom");
		email = request.getParameter("email");
		rue = request.getParameter("rue");
		ville = request.getParameter("ville");

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, nouveauMotDePasse);
		return utilisateur;
	}
}

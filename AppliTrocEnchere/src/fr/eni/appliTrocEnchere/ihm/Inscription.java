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
 * Servlet implementation class Inscription
 */
	@WebServlet("/Inscription")
	public class Inscription extends HttpServlet {
		private static final long serialVersionUID = 1L;
		String pseudo;
		String prenom;
		String telephone;
		String codePostal;
		String motDePasse;
		String nom;
		String email;
		String rue;
		String ville;
		String confirmation;
		UtilisateurManager utilisateurManager;
		Utilisateur utilisateur;
		HttpSession session;
		Boolean check;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		utilisateurManager = new UtilisateurManager();
		confirmation = request.getParameter("confirmation");
		motDePasse = request.getParameter("motDePasse");
		
		try {
			
				Utilities.confirmationMotDePasse(motDePasse, confirmation);
				utilisateur = mappingUtilisateur(request);
				utilisateurExistantCheck(utilisateur, utilisateurManager);
				utilisateurManager.insertUtilisateur(utilisateur);
				session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
				rd.forward(request, response);

		} catch (BusinessException e) {

			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
			doGet(request, response);
			
		}
	}
	
	public void utilisateurExistantCheck(Utilisateur utilisateur, UtilisateurManager utilisateurManager) throws BusinessException {
		Utilisateur utilisateurCheck = new Utilisateur();
		utilisateurCheck = utilisateurManager.selectUtilisateursByLogin(utilisateur.getPseudo(), utilisateur.getMotDePasse());
		
		if(utilisateurCheck.getPseudo()!=null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.UTILISATEUR_DEJA_EXISTANT);
			throw be;
		}
	}
	
	public Utilisateur mappingUtilisateur(HttpServletRequest request) {
		pseudo = request.getParameter("pseudo");
		prenom = request.getParameter("prenom");
		telephone = request.getParameter("telephone");
		codePostal = request.getParameter("codePostal");
		motDePasse = request.getParameter("motDePasse");
		nom = request.getParameter("nom");
		email = request.getParameter("email");
		rue = request.getParameter("rue");
		ville = request.getParameter("ville");

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		return utilisateur;
	}

}

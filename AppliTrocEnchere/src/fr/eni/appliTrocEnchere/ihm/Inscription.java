package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
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
			
			if(confirmation.equals(motDePasse)) {
				
				utilisateur = mappingUtilisateur(request);
				check = utilisateurExistantCheck(utilisateur, utilisateurManager);
				if(check==true) {
					try {
						utilisateurManager.insertUtilisateur(utilisateur);
						session = request.getSession();
						session.setAttribute("utilisateur", utilisateur);
						RequestDispatcher rd = request.getRequestDispatcher("/Connexion");
						rd.forward(request, response);
					} catch (BusinessException e) {
						
						
						//TODO Gestion de la Lecture d'Erreurs; Ajouter les Codes erreurs IHM (CodesResultatIHM) et les messages dans "messages_erreur.properties"
						List<Integer> listeErreurs = new ArrayList<>();
						List<String> errorMessages = new ArrayList<>();
						listeErreurs = e.getListeCodesErreur();
						for (Integer code : listeErreurs) {
							errorMessages.add(LecteurMessage.getMessageErreur(code));
						}
						request.setAttribute("errorMessages", errorMessages);
						doGet(request, response);
					}

				
				}else {
					request.setAttribute("errorMessage", "Cet utilisateur existe d�j�");
					doGet(request, response);
				}
				
			}else {
				request.setAttribute("errorMessage", "Le mot de passe et la confirmation sont diff�rents");
				doGet(request, response);
			}

		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			doGet(request, response);
		}
	}
	
	public boolean utilisateurExistantCheck(Utilisateur utilisateur, UtilisateurManager utilisateurManager) throws BusinessException {
		Utilisateur utilisateurCheck = new Utilisateur();
		utilisateurCheck = utilisateurManager.selectUtilisateursByLogin(utilisateur.getPseudo(), utilisateur.getMotDePasse());
		
		if(utilisateurCheck.getPseudo()!=null) {
			return false;
		}else {
			return true;
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

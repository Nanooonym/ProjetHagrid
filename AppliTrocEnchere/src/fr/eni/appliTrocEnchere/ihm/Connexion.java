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
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager;
	HttpSession session;

	public Connexion() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur();

		try {
			session = request.getSession();
			if(session.getAttribute("utilisateur")==null) {
				String pseudo = request.getParameter("pseudo");
				String motDePasse = request.getParameter("motDePasse");
				utilisateur = utilisateurManager.selectUtilisateursByLogin(pseudo, motDePasse);
			}else {
				utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			}
			
			if (utilisateur.getPseudo() != null) {
//				session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
			}
			else {
				request.setAttribute("errorMessage", "Cet utilisateur n'existe pas");
			}

		} catch (BusinessException e) {

			e.printStackTrace();
		}
		doGet(request, response);
	}

}
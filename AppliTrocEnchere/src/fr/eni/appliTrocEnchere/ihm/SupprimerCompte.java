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
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * Servlet implementation class SupprimerCompte
 */
@WebServlet("/SupprimerCompte")
public class SupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager;
	HttpSession session;
	Utilisateur utilisateur;

	public SupprimerCompte() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/supprimerCompte.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		utilisateurManager = new UtilisateurManager();


		try {
			session = request.getSession();

			if (session.getAttribute("utilisateur") != null) {
				
				utilisateur = new Utilisateur();
				utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				utilisateurManager.deleteUtilisateur(utilisateur);
				session.invalidate();
				RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
				rd.forward(request, response);
			}

		} catch (BusinessException e) {

			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
			doGet(request, response);
		}

	}

}

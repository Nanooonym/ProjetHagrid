package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

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
			if (session.getAttribute("utilisateur") == null) {
				String pseudo = request.getParameter("pseudo");
				String motDePasse = request.getParameter("motDePasse");
				utilisateur = utilisateurManager.selectUtilisateursByLogin(pseudo, motDePasse);

			} else {
				utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			}

			verificationUtilisateurExistant(utilisateur);
			session.setAttribute("utilisateur", utilisateur);

			Cookie[] cookies = null;
			String[] checkRemember = request.getParameterValues("checkRemember");
			 if(checkRemember == null) {
				 checkRemember = new String[] {"noRmb"};
			 }
			 
			cookies = request.getCookies();
			if (cookies.length > 1) {
				for (Cookie cookie : cookies) {

					// Il y avait un cookie Remember, mais "Remember" est décoché
					if (cookie.getName().equals("remember") && checkRemember[0].equals("noRmb")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}

					// Il y avait un cookie Login, mais "Remember" est décoché
					if (cookie.getName().equals("login") && checkRemember[0].equals("noRmb")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
					
					// Il y avait un cookie Password, mais "Remember" est décoché
					if (cookie.getName().equals("password") && checkRemember[0].equals("noRmb")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			} else if (cookies.length == 1 && checkRemember[0].equals("rmb")) {

				Cookie remember = new Cookie("remember", request.getParameter("checkRemember"));
				Cookie login = new Cookie("login", utilisateur.getPseudo());
				Cookie password = new Cookie("password", utilisateur.getMotDePasse());
				response.addCookie(remember);
				response.addCookie(login);
				response.addCookie(password);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);

		} catch (BusinessException e) {

			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
			doGet(request, response);
		}

	}

	public void verificationUtilisateurExistant(Utilisateur utilisateur) throws BusinessException {
		if (utilisateur.getPseudo() == null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.UTILISATEUR_INEXISTANT);
			throw be;
		}
	}

}

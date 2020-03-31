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

import fr.eni.appliTrocEnchere.bll.EnchereManager;
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereManager enchereManager;
	List<Enchere> listeEncheres;
	List<Enchere> listeEncheresFiltre;
	String categorie;
	String article;

	public Accueil() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		enchereManager = new EnchereManager();
		listeEncheres = new ArrayList<>();

		try {
			listeEncheresFiltre = enchereManager.afficherEncheres(categorie, article);
			for (Enchere enchere : listeEncheresFiltre) {
				listeEncheres.add(enchere);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		request.setAttribute("encheres", listeEncheres);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] typesAchats = request.getParameterValues("Achat");
		listeEncheres = new ArrayList<Enchere>();
		listeEncheresFiltre = new ArrayList<Enchere>();
		enchereManager = new EnchereManager();
		categorie = request.getParameter("categorie");
		article = request.getParameter("article");

		if (typesAchats == null) {
			typesAchats = new String[] { "tout" };
		}

		try {

			for (String checkValue : typesAchats) {

				// Filtre Encheres Ouvertes
				if (checkValue.equals("encheresOuvertes")) {
					listeEncheresFiltre = enchereManager.afficherEncheresOuvertes(categorie, article);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}

				// Filtre Encheres en Cours
				if (checkValue.equals("encheresEnCours")) {
					HttpSession session = request.getSession();
					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) session.getAttribute("utilisateur");
					listeEncheresFiltre = enchereManager.afficherEncheresEnCours(utilisateur, categorie, article);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}

				// Filtre Enchères Remportées
				if (checkValue.equals("encheresRemportees")) {
					HttpSession session = request.getSession();
					Utilisateur utilisateur = new Utilisateur();
					utilisateur = (Utilisateur) session.getAttribute("utilisateur");
					listeEncheresFiltre = enchereManager.afficherEncheresRemportees(utilisateur, categorie, article);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}

				if (checkValue.equals("tout")) {
					listeEncheresFiltre = enchereManager.afficherEncheres(categorie, article);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}
			}
			request.setAttribute("encheres", listeEncheres);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);

		} catch (BusinessException e) {
			request.setAttribute("errorMessage", LecteurMessage.codesErreurToString(e));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}
	}
}

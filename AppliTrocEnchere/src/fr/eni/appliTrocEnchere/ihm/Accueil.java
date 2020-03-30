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


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereManager enchereManager;
	List<Enchere> listeEncheres;
	List<Enchere> listeEncheresFiltre;
	String encheresOuvertes;
	String encheresEnCours;
	String encheresRemportées;

	public Accueil() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
				enchereManager = new EnchereManager();
				listeEncheres = new ArrayList<Enchere>();
				listeEncheres = enchereManager.afficherEncheres();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("encheres", listeEncheres);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String[] achatValues = request.getParameterValues("Achat");

			listeEncheres = new ArrayList<Enchere>();
			listeEncheresFiltre = new ArrayList<Enchere>();
			enchereManager = new EnchereManager();
			
			for (String checkValue : achatValues) {
				if(checkValue.equals("encheresOuvertes")) {
	
					listeEncheresFiltre = enchereManager.afficherEncheresOuvertes();
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}
				
				if(checkValue.equals("encheresEnCours")) {
					HttpSession session = request.getSession();
					Utilisateur utilisateur = new Utilisateur();
//					utilisateur = (Utilisateur) session.getAttribute("utilisateur");
					
					utilisateur.setNoUtilisateur(1);
					
					listeEncheresFiltre = enchereManager.afficherEncheresEnCours(utilisateur);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}
				
				if(checkValue.equals("encheresRemportees")) {
					HttpSession session = request.getSession();
					Utilisateur utilisateur = new Utilisateur();
//					utilisateur = (Utilisateur) session.getAttribute("utilisateur");
					
					utilisateur.setNoUtilisateur(1);
					listeEncheresFiltre = enchereManager.afficherEncheresRemportees(utilisateur);
					for (Enchere enchere : listeEncheresFiltre) {
						listeEncheres.add(enchere);
					}
				}
			}

		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		if(listeEncheres != null) {
			request.setAttribute("encheres", listeEncheres);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		}else {
			doGet(request, response);
		}



	}

}

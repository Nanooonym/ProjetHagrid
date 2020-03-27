package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.exception.BusinessException;


/**
 * 
 */
@WebServlet("/AjoutArticle")
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nomArticle;
	String description;
	String categorie;
	String miseAPrix;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	String rue;
	String codePostal;
	String ville;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Lecture de tous les paramètres :
		request.setCharacterEncoding("UTF-8");
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		//lecture du nom
		nomArticle = request.getParameter("nomArticle");
		if (nomArticle == null || nomArticle.trim().isEmpty()) {
			 listeCodesErreur.add(CodesResultatIHM.FORMAT_NOM_ERREUR);
		}
		//lecture de la description
		description = request.getParameter("description");
		if (description == null || description.trim().isEmpty()) {
			 listeCodesErreur.add(CodesResultatIHM.FORMAT_DESCRIPTION_ERREUR);
		}
		categorie = request.getParameter("categorie");
		if (categorie ==null) {
			 listeCodesErreur.add(CodesResultatIHM.FORMAT_CATEGORIE_ERREUR);
		}
		//lecture de la mise à  prix
	     miseAPrix = request.getParameter("miseAPrix");
	     if (miseAPrix==null) {
	    	 listeCodesErreur.add(CodesResultatIHM.FORMAT_MISE_A_PRIX_ERREUR);
		}
	     //lecture du début de l'enchÃ¨re
	     try {
	    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebut"),dtf);
			
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			listeCodesErreur.add(CodesResultatIHM.FORMAT__DATE_ERREUR);
		}
	     try {
	    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 dateFinEncheres = LocalDate.parse(request.getParameter("dateFin"),dtf);
			
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}
	     if (listeCodesErreur.size()>0) {
				request.setAttribute("listeCodesErreur",listeCodesErreur);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);
			
		}
	     else {
	    	 //Ajout du nouvel Article
			ArticleVenduManager articleVenduManager = new ArticleVenduManager();
			ArticleVendu articleVendu = new ArticleVendu();
			try {
				articleVenduManager.ajouterArticleVendu(articleVendu);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
				
			} catch (BusinessException be) {
				be.printStackTrace();
				request.setAttribute("listeCodesErreur",be.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
				rd.forward(request, response);
				
			}
		}
	
	

		
	}

}
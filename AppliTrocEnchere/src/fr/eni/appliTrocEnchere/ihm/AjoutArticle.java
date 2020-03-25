package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Utilisateur;

/**
 * Servlet implementation class NouvelleVente
 */
@WebServlet("/AjoutArticle")
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nomArticle;
	String description;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	int prixInitial;
	int prixVente;
	Utilisateur utilisateur;
	int noCategorie;
	int noUtilisateur;
	ArticleVenduManager articleVenduManager;
	ArticleVendu nouvelArticle;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateDebut = request.getParameter("dateDebutEncheres");
		String dateFin = request.getParameter("dateFinEncheres");
		LocalDate localDateDebutEncheres = LocalDate.parse(dateDebut);
		LocalDate localDateFinEncheres = LocalDate.parse(dateFin);
		
		nomArticle =  request.getParameter("nomArticle");
		description = request.getParameter("description");
		noCategorie = Integer.valueOf(request.getParameter("noCategorie"));
		prixInitial = Integer.valueOf( request.getParameter("prixInitial") );
	    prixVente = Integer.valueOf( request.getParameter("prixVente") );
	    dateDebutEncheres = localDateDebutEncheres;
	    dateFinEncheres = localDateFinEncheres;
	    
	    
		
		
		
		 
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

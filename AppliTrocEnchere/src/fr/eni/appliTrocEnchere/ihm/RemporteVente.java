package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * Servlet implementation class RemporteVente
 */
@WebServlet("/RemporteVente")
public class RemporteVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ArticleVenduManager articleVenduManager;
	ArticleVendu articleVendu;
	UtilisateurManager utilisateurManager;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemporteVente() {
        super();
        // TODO Auto-generated constructor stub
        
            }

	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idArticle = (String) request.getParameter("idArticle");
		int noArticle = Integer.parseInt(idArticle);
		Retrait retrait = new Retrait();
		articleVenduManager = new ArticleVenduManager();
		
		try {
			retrait = articleVenduManager.selectArticleById(noArticle);
			Utilisateur utilisateurMax = new Utilisateur();
			utilisateurManager = new UtilisateurManager();
			utilisateurMax = utilisateurManager.selectUtilisateurByEnchereMax(noArticle);
			request.setAttribute("utilisateurMax", utilisateurMax);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LocalDate dateDuJour = LocalDate.now();
		request.setAttribute("dateDuJour", dateDuJour);
		
		request.setAttribute("retrait", retrait);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/remporteVente.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

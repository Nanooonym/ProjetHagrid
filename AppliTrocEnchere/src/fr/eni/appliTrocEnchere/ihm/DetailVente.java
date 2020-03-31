package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bll.EnchereManager;
import fr.eni.appliTrocEnchere.bll.RetraitManager;
import fr.eni.appliTrocEnchere.bll.UtilisateurManager;

import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Utilisateur;


@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArticleVenduManager articleVenduManager;
    UtilisateurManager utilisateurManager;
    RetraitManager retraitManager;
    EnchereManager enchereManager;
    Utilisateur utilisateur;
    HttpSession session;
    ArticleVendu articleVendu;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailVente() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		rd.forward(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager enchereManger = new EnchereManager();
		request.setCharacterEncoding("UTF-8");
		
		//Récupération de la session de l'utilisateur
		session = request.getSession();
		utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
	}

}

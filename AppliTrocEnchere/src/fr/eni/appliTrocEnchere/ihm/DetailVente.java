package fr.eni.appliTrocEnchere.ihm;

/**
 * Emeline
 */

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bll.EnchereManager;
import fr.eni.appliTrocEnchere.bll.RetraitManager;
import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.exception.BusinessException;



/**
 * Servlet implementation class DetailVente
 */
@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ArticleVenduManager articleVenduManager;
    UtilisateurManager utilisateurManager;
    RetraitManager retraitManager;
    EnchereManager enchereManager;
    
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
		
		enchereManager = new EnchereManager();
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try {
			
			listeEncheres = enchereManager.afficherEncheres();
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		//Enchere enchere = new Enchere();
		//enchere =listeEncheres.get(1);
		request.setAttribute("encheres", listeEncheres);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		rd.forward(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

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

import fr.eni.appliTrocEnchere.bll.EnchereManager;
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.exception.BusinessException;


/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EnchereManager enchereManager;

	public Accueil() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		enchereManager = new EnchereManager();
		List<Enchere> listeEncheres = new ArrayList<Enchere>();
		
		try {
			
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

		doGet(request, response);
	}

}

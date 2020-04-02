package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * Servlet implementation class SupprimerVente
 */
@WebServlet("/SupprimerVente")
public class SupprimerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager articleVenduManager;
       
  
    public SupprimerVente() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierVente.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			articleVenduManager = new ArticleVenduManager();
			int noArticle = Integer.parseInt(request.getParameter("idArticle"));
			articleVenduManager.deleteVente(noArticle);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
			doGet(request, response);
		}
	}

}

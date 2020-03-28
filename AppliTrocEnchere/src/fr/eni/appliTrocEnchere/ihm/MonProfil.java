package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;

/**
 * Servlet implementation class ServletMonProfil
 */
@WebServlet("/MonProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager; 
    
    public MonProfil() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherMonProfil.jsp");		
		rd.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

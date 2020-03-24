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

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager;

    public ServletTest() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		utilisateurManager = new UtilisateurManager();
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			
			
			utilisateurs = utilisateurManager.selectUtilisateurs();
			
			for(Utilisateur utilisateur : utilisateurs) {
				System.out.println(utilisateur.toString());
			}
			
	
			Utilisateur utilisateur = new Utilisateur();
			utilisateur = utilisateurs.get(1);
			request.setAttribute("utilisateur", utilisateur);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/inscription.jsp");		
			rd.forward(request, response);
			
			
		} catch (BusinessException e) {
			
				System.out.println("Pouet");			

				e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

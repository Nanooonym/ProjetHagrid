package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;


@WebServlet("/AfficherUtilisateur")
public class AfficherUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UtilisateurManager utilisateurManager;
  
    public AfficherUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		utilisateurManager = new UtilisateurManager();
		Utilisateur utilisateur = new Utilisateur();
		
		
		
		try {
			
			
				utilisateur = utilisateurManager.selectUtilisateurById(Integer.parseInt(request.getParameter("noUtilisateur")));
				
				request.setAttribute("utilisateur", utilisateur);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/afficherProfil.jsp");		
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

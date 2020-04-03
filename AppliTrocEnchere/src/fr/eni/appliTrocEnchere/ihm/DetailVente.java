package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;

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
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;


@WebServlet("/DetailVente")
public class DetailVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleVenduManager articleVenduManager;
	UtilisateurManager utilisateurManager;
	RetraitManager retraitManager;
	EnchereManager enchereManager;
	Utilisateur utilisateur;
	Utilisateur utilisateurMax;
	HttpSession session;
	ArticleVendu articleVendu;
	int montantEnchereEnCours;
	LocalDate dateEnchere;
	BusinessException be;
	int noArticle;

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
		String idArticle = (String) request.getParameter("idArticle");
		if(idArticle != null) {
			noArticle = Integer.parseInt(idArticle);
		}else {
			noArticle = Integer.parseInt((String) request.getAttribute("idArticle"));
		}

		Retrait retrait = new Retrait();
		articleVenduManager = new ArticleVenduManager();
		
		try {
			retrait = articleVenduManager.selectArticleById(noArticle);
			utilisateurMax = new Utilisateur();
			utilisateurManager = new UtilisateurManager();
			utilisateurMax = utilisateurManager.selectUtilisateurByEnchereMax(noArticle);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		request.setAttribute("retrait", retrait);
		request.setAttribute("utilisateurMax", utilisateurMax);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		rd.forward(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		articleVenduManager = new ArticleVenduManager();

		request.setCharacterEncoding("UTF-8");

		// Récupération de la session de l'utilisateur
		session = request.getSession();
		utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		// Récupération de la proposition d'enchère
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		
		articleVendu = (ArticleVendu) request.getAttribute("articleVendu");

		// Date de l'enchère
		dateEnchere = LocalDate.now();
		
		String idArticle = (String) request.getParameter("noArticle");
		int noArticle = Integer.parseInt(idArticle);

		try {
		

	   // Construction de l'objet et requête d'insertion
			
			//Select utilisateur meilleure enchère
			
				Retrait retrait = new Retrait();
				
	
				retrait = articleVenduManager.selectArticleById(noArticle);
				montantEnchereEnCours = retrait.getArticle().getPrixVente();
				Utilisateur utilisateurMax = new Utilisateur();
				utilisateurManager = new UtilisateurManager();
				utilisateurMax = utilisateurManager.selectUtilisateurByEnchereMax(noArticle);
				
			
				validerProposition(proposition, retrait, utilisateur);
				enchereManager = new EnchereManager();
				
				enchereManager.encherir(montantEnchereEnCours, proposition, utilisateur, utilisateurMax, noArticle);
			
				retrait = articleVenduManager.selectArticleById(noArticle);
				request.setAttribute("retrait", retrait);
				request.setAttribute("utilisateurMax", utilisateur);
				
				
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
			rd.forward(request, response);

		} catch (BusinessException be) {
			request.setAttribute("idArticle", String.valueOf(noArticle));
			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(be));
			doGet(request, response);
		}
	}
	
	
	public void validerProposition (int proposition,Retrait retrait,Utilisateur utilisateur) throws BusinessException {
		
		
		if ((proposition<= retrait.getArticle().getPrixVente()) || (utilisateur.getCredit()-proposition <0) || (proposition<=0)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.PROPOSITION_INCORRECTE);
			throw be;
		}
		
	}
	

	
}


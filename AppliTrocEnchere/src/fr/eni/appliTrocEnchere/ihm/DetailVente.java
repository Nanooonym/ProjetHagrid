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
import fr.eni.appliTrocEnchere.bo.Enchere;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;

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
	int montantEnchere;
	LocalDate dateEnchere;
	BusinessException be;

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
		int noArticle = Integer.parseInt(idArticle);
		Retrait retrait = new Retrait();
		articleVenduManager = new ArticleVenduManager();
		
		try {
			retrait = articleVenduManager.selectArticleById(noArticle);
			utilisateur = new Utilisateur();
			utilisateurManager = new UtilisateurManager();
			utilisateur = utilisateurManager.selectUtilisateurByEnchereMax(noArticle);
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("retrait", retrait);
		request.setAttribute("utilisateurMax", utilisateur);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/detailVente.jsp");
		rd.forward(request, response);

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		enchereManager = new EnchereManager();
		articleVenduManager = new ArticleVenduManager();
		utilisateurManager = new UtilisateurManager();

		request.setCharacterEncoding("UTF-8");

		// Récupération de la session de l'utilisateur
		session = request.getSession();
		utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		// Récupération de la proposition d'enchère
		int proposition = Integer.parseInt(request.getParameter("proposition"));
		montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));

		articleVendu = (ArticleVendu) request.getAttribute("articleVendu");

		// Date de l'enchère
		dateEnchere = LocalDate.now();

		try {
			enchereManager = new EnchereManager();
			be = new BusinessException();

	   // Construction de l'objet et requête d'insertion
			Enchere enchere = new Enchere();
			enchere.setDateEnchere(dateEnchere);
			enchere.setMontantEnchere(montantEnchere);
			enchere.getArticleVendu().setNoArticle(articleVendu.getNoArticle());
			enchere.getArticleVendu().setNoArticle(utilisateur.getNoUtilisateur());

			int creditDebite = utilisateur.getCredit() - montantEnchere;

			if (proposition > montantEnchere && creditDebite > 0) {
				enchereManager.ajouterEnchere(enchere);
				utilisateurManager.updateCreditUtilisateur(creditDebite, utilisateur.getNoUtilisateur());
				utilisateur.setCredit(creditDebite);
			}

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
			rd.forward(request, response);

		} catch (BusinessException be) {
			be.printStackTrace();
			doGet(request, response);
		}
	}
}

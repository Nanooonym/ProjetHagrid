package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.ArticleVenduManager;
import fr.eni.appliTrocEnchere.bo.ArticleVendu;
import fr.eni.appliTrocEnchere.bo.Categorie;
import fr.eni.appliTrocEnchere.bo.Retrait;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * 
 */
@WebServlet("/AjoutArticle")
public class AjoutArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String nomArticle;
	String description;
	Categorie categorie;
	int miseAPrix;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	String rue;
	String codePostal;
	String ville;
	String etatVente;
	Utilisateur utilisateur;
	HttpSession session;
	Retrait retrait;
	int noCategorie;
	ArticleVendu articleVendu;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelleVente.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		// Lecture de tous les paramètres :
		request.setCharacterEncoding("UTF-8");

		try {
			verificationSaisieArticle(request);

			session = request.getSession();
			utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) session.getAttribute("utilisateur");

			categorie = new Categorie();
			noCategorie = Integer.parseInt(request.getParameter("noCategorie"));
			categorie = articleVenduManager.selectCategorieById(noCategorie);

			articleVendu = new ArticleVendu();
			articleVendu = mappingArticle(request);

			rue = request.getParameter("rue");
			ville = request.getParameter("ville");
			codePostal = request.getParameter("codePostal");

			retrait = new Retrait(articleVendu, rue, ville, codePostal);
			
			articleVenduManager.ajouterArticleVendu(retrait);

			RequestDispatcher rd = request.getRequestDispatcher("/Accueil");
			rd.forward(request, response);

		} catch (BusinessException e) {
			request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
			doGet(request, response);
		}
	}

	public void verificationSaisieArticle(HttpServletRequest request) throws BusinessException {
		nomArticle = request.getParameter("nomArticle");
		BusinessException be = new BusinessException();

		if (nomArticle == null || nomArticle.trim().isEmpty()) {
			be.ajouterErreur(CodesResultatIHM.FORMAT_NOM_ERREUR);
		}
		// lecture de la description
		description = request.getParameter("description");
		if (description == null || description.trim().isEmpty()) {
			be.ajouterErreur(CodesResultatIHM.FORMAT_DESCRIPTION_ERREUR);
		}

		// lecture de la mise à  prix
		miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		if (miseAPrix <= 0) {
			be.ajouterErreur(CodesResultatIHM.FORMAT_MISE_A_PRIX_ERREUR);
		}

		// lecture du début de l'enchÃ¨re
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebut"), dtf);
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFin"), dtf);

		} catch (DateTimeParseException e) {
			be.ajouterErreur(CodesResultatIHM.FORMAT__DATE_ERREUR);
		}

		if (be.hasErreurs()) {
			throw be;
		}
	}

	public ArticleVendu mappingArticle(HttpServletRequest request) {

		nomArticle = request.getParameter("nomArticle");
		description = request.getParameter("description");
		miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		if (dateDebutEncheres.isBefore(LocalDate.now())) {
			etatVente.equals("En Cours");
		} else {
			etatVente.equals("Créée");
		}

		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix,
				miseAPrix, etatVente, utilisateur, categorie);
		return article;
	}

}
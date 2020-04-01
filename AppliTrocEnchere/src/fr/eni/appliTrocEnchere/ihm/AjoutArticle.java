package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;
import java.time.LocalDate;
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
	String miseAPrixCheck;
	int miseAPrix;
	LocalDate dateDebutEncheres;
	LocalDate dateFinEncheres;
	String debut;
	String fin;
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
		// Lecture de tous les param�tres :
		request.setCharacterEncoding("UTF-8");

		try {
			verificationSaisieArticle(request);

			//R�cup�ration de l'utilisateur
			session = request.getSession();
			utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) session.getAttribute("utilisateur");

			//R�cup�ration de la cat�gorie choisie
			categorie = new Categorie();
			noCategorie = Integer.parseInt(request.getParameter("categorie"));
			System.out.println(noCategorie);
			categorie.setNoCategorie(noCategorie);
			//Requête modifiée (gestion de retraits)
			//categorie = articleVenduManager.selectCategorieById(noCategorie);

			//Cr�ation de l'Article
			articleVendu = new ArticleVendu();
			articleVendu = mappingArticle(request);

			//Cr�ation du lieu de retrait
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

		//V�rification de la mise � prix
		miseAPrixCheck = request.getParameter("miseAPrix");
		if(miseAPrixCheck == null || miseAPrixCheck.equals("")) {
			miseAPrixCheck = "0";
		}
		miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		if (miseAPrix <= 0 || miseAPrix%1 != 0) {
			be.ajouterErreur(CodesResultatIHM.FORMAT_MISE_A_PRIX_ERREUR);
		}

		//V�rification des dates de la vente
		try {
			dateDebutEncheres = LocalDate.parse(request.getParameter("dateDebut"));
			dateFinEncheres = LocalDate.parse(request.getParameter("dateFin"));

		} catch (DateTimeParseException e) {
			be.ajouterErreur(CodesResultatIHM.FORMAT_DATE_ERREUR);
		}

		if (be.hasErreurs()) {
			throw be;
		}
	}

	public ArticleVendu mappingArticle(HttpServletRequest request) {

		nomArticle = request.getParameter("nomArticle");
		description = request.getParameter("description");
		miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		debut = request.getParameter("dateDebut");
		fin = request.getParameter("dateFin");
		dateDebutEncheres = LocalDate.parse(debut);
		dateFinEncheres = LocalDate.parse(fin);
		if (dateDebutEncheres.isBefore(LocalDate.now())) {
			etatVente = "En cours";
		} else {
			etatVente = "Créée";
		}

		ArticleVendu article = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix,
				miseAPrix, etatVente, utilisateur, categorie);
		return article;
	}

}
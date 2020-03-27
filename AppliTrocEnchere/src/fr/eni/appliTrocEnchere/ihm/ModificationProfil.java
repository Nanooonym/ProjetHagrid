package fr.eni.appliTrocEnchere.ihm;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.appliTrocEnchere.bll.UtilisateurManager;
import fr.eni.appliTrocEnchere.bo.Utilisateur;
import fr.eni.appliTrocEnchere.exception.BusinessException;
import fr.eni.appliTrocEnchere.exception.LecteurMessage;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/ModificationProfil")
public class ModificationProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String pseudo;
	String prenom;
	String telephone;
	String codePostal;
	String motDePasse;
	String nom;
	String email;
	String rue;
	String ville;
	int credit;
	String confirmation;
	UtilisateurManager utilisateurManager;
	Utilisateur utilisateur;
	HttpSession session;
	
    public ModificationProfil() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/modifierProfil.jsp");		
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entrée post OK");
		utilisateur = new Utilisateur();
		utilisateurManager = new UtilisateurManager();
		confirmation = request.getParameter("confirmation");
		motDePasse = request.getParameter("motDePasse");
		Utilisateur utilisateurTemp = new Utilisateur();
		
		try {
			System.out.println("Avant confirmation mdp OK");
			confirmationMotDePasse(motDePasse, confirmation);
			System.out.println("Après confirmation mdp OK");
			utilisateurTemp = mappingUtilisateur(request);
			System.out.println("mapping Utilisateur OK");
			session = request.getSession();
			utilisateur = (Utilisateur) request.getAttribute("utilisateur");
			System.out.println(utilisateur.getNoUtilisateur());
			System.out.println(utilisateur.getCredit());
			System.out.println(utilisateur.isAdministrateur());
			utilisateurTemp.setNoUtilisateur(utilisateur.getNoUtilisateur());
			utilisateurTemp.setCredit(utilisateur.getCredit());
			utilisateurTemp.setAdministrateur(utilisateur.isAdministrateur());
			utilisateurExistantCheck(utilisateurTemp, utilisateurManager);
			System.out.println("utilisateur existant ok");
			utilisateurManager.updateUtilisateur(utilisateurTemp);
			System.out.println("Update OK");
			
			session.setAttribute("utilisateur", utilisateurTemp);
			RequestDispatcher rd = request.getRequestDispatcher("/MonProfil");
			rd.forward(request, response);

	} catch (BusinessException e) {

		request.setAttribute("errorMessages", LecteurMessage.codesErreurToString(e));
		doGet(request, response);
		
	}
	}

	public void utilisateurExistantCheck(Utilisateur utilisateur, UtilisateurManager utilisateurManager) throws BusinessException {
		Utilisateur utilisateurCheck = new Utilisateur();
		utilisateurCheck = utilisateurManager.selectUtilisateurByPseudoEmail(utilisateur.getPseudo(), utilisateur.getEmail());
		
		if(utilisateurCheck.getPseudo()!=null) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.UTILISATEUR_DEJA_EXISTANT);
			throw be;
		}
	}
	
	
	
	public void confirmationMotDePasse(String motDePasse, String confirmation) throws BusinessException {
		if(!confirmation.equals(motDePasse)) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatIHM.MOT_DE_PASSE_CONFIRMATION_DIFFERENTS);
			throw be;
		}
	}
	
	
	public Utilisateur mappingUtilisateur(HttpServletRequest request) {
		pseudo = request.getParameter("pseudo");
		prenom = request.getParameter("prenom");
		telephone = request.getParameter("telephone");
		codePostal = request.getParameter("codePostal");
		motDePasse = request.getParameter("motDePasse");
		nom = request.getParameter("nom");
		email = request.getParameter("email");
		rue = request.getParameter("rue");
		ville = request.getParameter("ville");

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		return utilisateur;
	}
	
	
	
	
	
	
	
	
	
	
}

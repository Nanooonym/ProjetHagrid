package fr.eni.appliTrocEnchere.bo;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Enchere {

	private int noUtilisateur;
	private int noArticle;
	private LocalTime dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur; 
	private ArticleVendu articleVendu;
	
	//Constructeur par defaut
	
	public Enchere() {
		utilisateur = new Utilisateur();
		articleVendu = new ArticleVendu();
	}
	
	

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public LocalTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}

	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [noUtilisateur=");
		builder.append(noUtilisateur);
		builder.append(", noArticle=");
		builder.append(noArticle);
		builder.append(", dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append(", utilisateur=");
		builder.append(utilisateur);
		builder.append(", articleVendu=");
		builder.append(articleVendu);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((articleVendu == null) ? 0 : articleVendu.hashCode());
		result = prime * result + ((utilisateur == null) ? 0 : utilisateur.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enchere other = (Enchere) obj;
		if (articleVendu == null) {
			if (other.articleVendu != null)
				return false;
		} else if (!articleVendu.equals(other.articleVendu))
			return false;
		if (utilisateur == null) {
			if (other.utilisateur != null)
				return false;
		} else if (!utilisateur.equals(other.utilisateur))
			return false;
		return true;
	}
	
	
	
	
	
}
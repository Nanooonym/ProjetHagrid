package fr.eni.appliTrocEnchere.bo;

import java.io.Serializable;

public class Retrait implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3158055634188880288L;
	// ATTRIBUTS
	private ArticleVendu article;
	private String rue;
	private String codePostal;
	private String ville;

	// CONSTRUCTEURS
	public Retrait() {

	}

	public Retrait(ArticleVendu article, String rue, String codePostal, String ville) {
		this.article = article;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	// GETTERS & SETTERS

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + article.getNoArticle();
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
		Retrait other = (Retrait) obj;
		if (article.getNoArticle() != other.article.getNoArticle())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [noArticle=");
		builder.append(article.getNoArticle());
		builder.append(", article=");
		builder.append(article);
		builder.append(", rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}

}
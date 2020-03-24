package fr.eni.appliTrocEnchere.bo;

import java.util.ArrayList;

/**
 * 
 * @author Emeline
 *
 */

public class Categorie {

	private int noCategorie;
	private String libelle;
	//Mise en commentaire de l'arrayList arcticleVendu en attendant la bo articleVendu
	//private ArrayList<ArticleVendu> categorieArticle = null;
	
	
	//Creation du constructeur par défaut
	public Categorie() {
	}

	//Creation du constructeur avec les attributs
	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}
	
	//Creation des getters et setters
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	//Creation du ToString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append("]");
		return builder.toString();
	}

	//Creation du HashCode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		result = prime * result + noCategorie;
		return result;
	}
	
	//Creation du Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorie other = (Categorie) obj;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (noCategorie != other.noCategorie)
			return false;
		return true;
	}
	
	
	
	
}

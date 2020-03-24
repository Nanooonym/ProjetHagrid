package fr.eni.appliTrocEnchere.bo;

/**
 * 
 * @author Emeline
 *
 */

public class Categorie {

	private int noCategorie;
	private String libelle;
	
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
	
	
}

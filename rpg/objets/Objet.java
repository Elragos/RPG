package rpg.objets;


public abstract class Objet {

	String nom;
	int poids;
	int durabilite;
	TypeObjet type;
	
	public Objet() {
		// TODO Auto-generated constructor stub
	}
	
	public String getNom() {
		return nom;
	}
	
	public TypeObjet getType() {
		return type;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setType(TypeObjet type) {
		this.type = type;
	}
	
}

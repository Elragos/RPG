package rpg.objets;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Objet {

	String nom;
	int poids;
	/*int durabilite;*/
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
	
	public int getPoids() {
		return poids;
	}
	
	/*public int getDurabilite() {
		return durabilite;
	}*/
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setType(TypeObjet type) {
		this.type = type;
	}
	
	public void setPoids(int poids) {
		this.poids = poids;
	}
	
	/*public void setDurabilite(int durabilite) {
		this.durabilite = durabilite;
	}*/
	
	public static Objet genererObjet()
	{
		int randomInt = ThreadLocalRandom.current().nextInt(0, TypeObjet.values().length + 1);
		
		if (randomInt >= TypeObjet.values().length){
			return null;
		}
		TypeObjet randomType = TypeObjet.values()[randomInt];
		switch (randomType) {
			case Antidotes:
				return new Antidote();
			case Armes:
				return new Arme();
			case Jumelles:
				return new Jumelles();
			case Potions:
				return new Potion();
		}
		return null;
	}
}

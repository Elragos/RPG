package rpg.carte;

import java.util.ArrayList;

import rpg.objets.Objet;
import rpg.personnages.Personnage;

public class Case {

	protected ArrayList<Personnage> occupants;
	protected ArrayList<Objet> objets;
	protected TypeTerrain type;
	
	public Case(TypeTerrain type) {
		this.type = type;
		this.occupants = new ArrayList<Personnage>();
		this.objets = new ArrayList<Objet>();
	}
	
	public ArrayList<Personnage> getOccupants() {
		return this.occupants;
	}
	
	public ArrayList<Objet> getObjets() {
		return this.objets;
	}
	
	public int getCoutEnergie() {
		return this.type.ordinal() + 1;
	}
	
	public TypeTerrain getType() {    	
		return this.type;
	}

	@Override
	public String toString() {
		return this.type.toString().substring(0, 1);		
	}	
}
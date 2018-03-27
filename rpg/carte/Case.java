package rpg.carte;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import rpg.objets.Objet;
import rpg.personnages.Personnage;

public class Case {

	protected ArrayList<Personnage> _occupants;
	protected ArrayList<Objet> _objets;
	protected TypeTerrain _type;
	protected int _or;
	
	private final int[] VALEUR_OR = {0,0,0,1,2,5};
	
	public Case(TypeTerrain type) {
		this._occupants = new ArrayList<Personnage>();
		this._objets = new ArrayList<Objet>();
		this._type = type;
		
		Objet random = Objet.genererObjet();
		if (random!= null) {
			this._objets.add(random);
		}
	    
		int randomInt = ThreadLocalRandom.current().nextInt(0, VALEUR_OR.length);		
		this._or = VALEUR_OR[randomInt];
	}
	
	public ArrayList<Personnage> getOccupants() {
		return this._occupants;
	}
	
	public ArrayList<Objet> getObjet() {
		return this._objets;
	}
	
	public int getCoutEnergie() {
		return this._type.ordinal() + 1;
	}
	
	public TypeTerrain getType() {    	
		return this._type;
	}
	
	public int getOr() {		
		return _or;
	}

	@Override
	public String toString() {
		return this._type.toString().substring(0, 1);		
	}	
}


package rpg.carte;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import rpg.objets.Objet;
import rpg.objets.TypeObjet;
import rpg.personnages.Personnage;

public class Case {

	ArrayList<Personnage> occupants;
	ArrayList<Objet> objets;
	int or;
	TypeTerrain type;
	
	public Case(TypeTerrain type) {
		this.occupants = new ArrayList<Personnage>();
		this.objets = new ArrayList<Objet>();
		this.type = type;
		
		Objet random = Objet.genererObjet();
		if (random!= null) {
			this.objets.add(random);
		}
	}
	
	public ArrayList<Personnage> getOccupants() {
		return occupants;
	}
	
	public ArrayList<Objet> getObjet() {
		return objets;
	}
	
	public int getCoutEnergie() {
		return this.type.ordinal() + 1;
	}
	
	public TypeTerrain getType() {    	
		return this.type;
	}
	
	public int getOr() {
		int valeurOr[] = {0,0,0,1,2,5};
	    
		int randomInt = ThreadLocalRandom.current().nextInt(0, valeurOr.length);
		for(int i = 0; i < randomInt; i++)
		{
			or = valeurOr[i];
		}
		return or;
	}

	@Override
	public String toString() {
		return this.type.toString().substring(0, 1);		
	}	
}


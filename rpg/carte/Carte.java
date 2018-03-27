package rpg.carte;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Carte {

	protected int hauteur;
	protected int largeur;
	protected Map<String, Case> cases;
	
	public Carte(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	public void genererCarte() {		
	    this.cases = new HashMap<>();
	    for(int h = 0; h < this.hauteur; h++) {
		    for(int l = 0; l < this.largeur; l++) {
		    	int randomInt = ThreadLocalRandom.current().nextInt(0, TypeTerrain.values().length);
		    	
		    	this.cases.put(String.format("%s-%s", h, l), new Case(TypeTerrain.values()[randomInt]));
		    }
	    }
	}
	
	public void afficher() {
		for(int h = 0; h < this.hauteur; h++) {
		    for(int l = 0; l < this.largeur; l++) {
		    	Case c = this.cases.get(String.format("%s-%s", h, l));
		    	System.out.print(c);
		    }
		    System.out.println();
	    }
	}
	
}

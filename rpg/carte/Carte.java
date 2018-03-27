package rpg.carte;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import rpg.actions.Interactif;

/**
 * Carte du monde.
 * @author Arthur
 *
 */
public class Carte implements Interactif{
	/**
	 * Hauteur de la carte.
	 */
	protected int hauteur;
	/**
	 * Largeur de la carte.
	 */
	protected int largeur;
	/**
	 * Cases composant la carte.
	 */
	protected Map<String, Case> cases;
	
	public Carte(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	/**
	 * Générer la carte aléatoirement.
	 */
	public void genererCarte() {		
	    this.cases = new HashMap<>();
	    for(int h = 0; h < this.hauteur; h++) {
		    for(int l = 0; l < this.largeur; l++) {
		    	int randomInt = ThreadLocalRandom.current().nextInt(0, TypeTerrain.values().length);
		    	
		    	this.cases.put(String.format("%s-%s", h, l), new Case(TypeTerrain.values()[randomInt]));
		    }
	    }
	}
	
	/**
	 * Afficher la carte.
	 */
	public void afficher() {
		for(int h = 0; h < this.hauteur; h++) {
		    for(int l = 0; l < this.largeur; l++) {
		    	Case c = this.cases.get(String.format("%s-%s", h, l));
		    	System.out.print(c);
		    }
		    System.out.println();
	    }
	}
	
	/**
	 * Récupérer une case en fonction de ses coordonnées.
	 * @param coordonnees Les coordonnées de la case demandée.
	 * @return La case trouvé, ou null si inconnu.
	 */
	public Case getCase(String coordonnees) {
		return cases.get(coordonnees);
	}
}

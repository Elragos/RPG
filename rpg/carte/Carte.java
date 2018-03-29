package rpg.carte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import rpg.actions.Interactif;
import rpg.personnages.Ennemi;
import rpg.personnages.Guerisseur;
import rpg.personnages.Heros;
import rpg.personnages.Personnage;

/**
 * Carte du monde.
 * @author Arthur
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
	/**
	 * Liste des ennemis sur la carte.
	 */	
	protected ArrayList<Ennemi> _ennemis;
	/**
	 * Liste des guérisseurs sur la carte.
	 */	
	protected ArrayList<Guerisseur> _guerisseurs;
	
	/**
	 * Créer une carte avec une largeur et une hauteur
	 * @param hauteur Hauteur de la carte.
	 * @param largeur Largeur de la carte.
	 * @param nbEnnemis Nombre d'ennemis sur la carte.
	 * @param nbGuerisseurs Nombre de guérisseurs sur la carte.
	 */
	public Carte(int hauteur, int largeur, int nbEnnemis, int nbGuerisseurs) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		
		genererCarte(nbEnnemis, nbGuerisseurs);
	}
	/**
	 * Générer la carte aléatoirement.
	 * @param nbEnnemis Nombre d'ennemis sur la carte.
	 * @param nbGuerisseurs Nombre de guérisseurs sur la carte.
	 */
	private void genererCarte(int nbEnnemis, int nbGuerisseurs) {
		// Générer les ennemis 
		genererListeEnnemis(nbEnnemis);
		// Générer les guérisseurs 
		genererListGuerisseurs(nbEnnemis);
		
		// On va générer une liste pour qu'il y ait un objet personnage par case (que cet objet soit null ou non)
		int tailleCarte = this.hauteur * this.largeur;
		ArrayList<Personnage> liste = new ArrayList<>(tailleCarte);
		// On ajoute les ennemis et les guérisseurs
		liste.addAll(_ennemis);
		liste.addAll(_guerisseurs);	
		// On complète avec des références null
		while(liste.size() < tailleCarte) {
			liste.add(null);
		}
		// On mélange la liste
		Collections.shuffle(liste);		
		
		// Initialiser le tableau de cases
		this.cases = new HashMap<>();
		//
	    for(int latitude = 0; latitude < this.hauteur; latitude++) {
		    for(int longitude = 0; longitude < this.largeur; longitude++) {
		    	// Générer les coordonnées à partir
		    	String coordString = String.format("%s-%s", latitude, longitude);
		    	Coordonnees coord = Coordonnees.recupererDepuisString(coordString);
		    	// Décider alétoirement du terrain à appliquer
		    	int randomInt = ThreadLocalRandom.current().nextInt(0, TypeTerrain.values().length);
		    	TypeTerrain type = TypeTerrain.values()[randomInt];
		    	// Ajouter la nouvelle case
		    	Case nouvelleCase = new Case(type, coord);
		    	this.cases.put(coordString, nouvelleCase);
		    	// Récupérer un personnage de la liste
		    	Personnage aAjouter = liste.get((latitude * this.largeur) + longitude);
		    	if (aAjouter != null) {
		    		aAjouter.setPosition(nouvelleCase);
		    	}
		    }
	    }
	}
	/**
	 * Générer les ennemis.
	 * @param nbEnnemis Nombre d'ennemis sur la carte.
	 */
	private void genererListeEnnemis(int nbEnnemis){
		// On initialise la liste des ennemis
		_ennemis = new ArrayList<>(nbEnnemis);
		
		// Pour le nombre d'ennemis demandés
		for(int i = 0; i < nbEnnemis; i++) {
			_ennemis.add(Ennemi.genererNouveauMonstre());
		}
	}
	
	/**
	 * Générer les guérisseurs.
	 * @param nbGuerisseurs Nombre de guérissuers sur la carte.
	 */
	private void genererListGuerisseurs(int nbGuerisseurs){
		// On initialise la liste des ennemis
		_guerisseurs = new ArrayList<>(nbGuerisseurs);
		
		// Pour le nombre d'ennemis demandés
		for(int i = 0; i < nbGuerisseurs; i++) {
			_guerisseurs.add(new Guerisseur());
		}
	}
	
	/**
	 * Afficher la carte complète.
	 */
	public void afficher() {
		for(int latitude = 0; latitude < this.hauteur; latitude++) {
		    for(int longitude = 0; longitude < this.largeur; longitude++) {
		    	Case c = this.getCase(new Coordonnees(latitude, longitude));
		    	System.out.printf(" %s ", c);
		    }
		    System.out.println();
	    }
	}
	
	/**
	 * Afficher la carte que voit le héros
	 * @param heros
	 */
	public void afficher(Heros heros) {
		// Champ de vision du héros
		int champVision = heros.getChampVision();
		
		// On récupère les coordonnées du héros
		Coordonnees coordActuelle = heros.getPosition().getCoordonnees();
		
		// En fonction de la largeur de son champ de vision
		for (int latitude = coordActuelle.latitude - champVision; latitude <= coordActuelle.latitude + champVision; latitude++) {
			for (int longitude = coordActuelle.longitude - champVision; longitude <= coordActuelle.longitude + champVision; longitude++) {
				// On récupère la case correspondante aux coordonnées
				Case c = this.getCase(new Coordonnees(latitude, longitude));
				
				// Si on a trouvé la case, on l'affiche
				if (c != null) {
					System.out.printf(" %s ", c);
				}
		    }
		    System.out.println();
		}
	}
	
	/**
	 * Récupérer une case en fonction de ses coordonnées.
	 * @param coordonnees Les coordonnées de la case demandée.
	 * @return La case trouvé, ou null si inconnu.
	 */
	public Case getCase(Coordonnees coord) {
		return this.getCase(coord.toString());
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

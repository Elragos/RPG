package rpg.carte;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import rpg.Main;
import rpg.actions.Combattant;
import rpg.actions.Interactif;
import rpg.objets.ObjetInventaire;
import rpg.personnages.*;

/**
 * Case de la carte.
 * @author marechal
 */
public class Case implements Interactif {

	/**
	 * Liste des personnages sur la case.
	 */
	protected ArrayList<Personnage> _occupants;
	/**
	 * Liste des objets présents sur la case.
	 */
	protected ArrayList<ObjetInventaire> _objets;
	/**
	 * Type de terrain.
	 */
	protected TypeTerrain _type;
	/**
	 * Quantité d'or présente sur la case
	 */
	protected int _or;
	/**
	 * Coordonnées de la case.
	 */
	protected Coordonnees _coordonnees;
	
	/**
	 * Quantités d'or possibles sur la case au début.
	 */
	private final int[] VALEUR_OR = {0,0,0,1,2,5};
	
	/**
	 * Créer une nouvelle case avec un type de terrain et des coordonnée.
	 * Le reste est générée aléatoirement.
	 * @param type Le type de terrain de la case.
	 * @param coordonnees Les coordonnées de la case.
	 */
	public Case(TypeTerrain type, Coordonnees coord) {
		// Initialisation des listes
		this._occupants = new ArrayList<Personnage>();
		this._objets = new ArrayList<ObjetInventaire>();
		// Récupération des paramètres
		this._type = type;
		this._coordonnees = coord;
		
		// Ajouter aléatoirement un objet sur la case
		ObjetInventaire random = ObjetInventaire.genererObjet();
		if (random!= null) {
			this._objets.add(random);
		}
	    
		// Calculer la quantité d'or sur la case.
		int randomInt = ThreadLocalRandom.current().nextInt(0, VALEUR_OR.length);		
		this._or = VALEUR_OR[randomInt];
	}
	
	/**
	 * Appliquer une maladie au héros, ou pas.
	 * @param h Le héros à rendre malade.
	 */
	public void maladie(Heros h) {
		// On tire aléatoirement si on doit appliquer la maladie.
		int randomMaladie = ThreadLocalRandom.current().nextInt(1, 100);
		if (randomMaladie == 12) {
			h.setSante(false);
			System.out.println("Pas de chance, vous venez de tomber malade !");
		}
	}
	
	/**
	 * Récupérer la liste des personnages sur la case.
	 * @return La liste des personnages sur la case.
	 */
	public ArrayList<Personnage> getOccupants() {
		return this._occupants;
	}
	
	/**
	 * Récupérer la liste d'objets présents sur la case.
	 * @return La liste d'objets présents sur la case.
	 */
	public ArrayList<ObjetInventaire> getObjets() {
		return this._objets;
	}
	
	/**
	 * Récupérer le coût en énergie pour se déplacer vers la case.
	 * @return Le coût en énergie pour se déplacer vers la case.
	 */
	public int getCoutEnergie() {
		return this._type.ordinal() + 1;
	}
	
	/**
	 * Récupérer le type de terrain de la case.
	 * @return Le type de terrain de la case.
	 */
	public TypeTerrain getType() {    	
		return this._type;
	}
	
	/**
	 * Récupérer les coordonnées sur la carte.
	 * @return Les coordonnées sur la carte.
	 */
	public Coordonnees getCoordonnees() {
		return this._coordonnees;
	}
	
	/**
	 * Récupérer la quantité d'or sur la case.
	 * @return La quantité d'or sur la case.
	 */
	public int getOr() {		
		return _or;
	}
	/**
	 * Modifier le montant d'or de la case.
	 * @param nouveauMontant Nouveau montant.
	 */
	public void setOr(int nouveauMontant) {
		this._or = nouveauMontant;
	}

	/**
	 * Résoudre la rencontre sur la case
	 */
	public void resoudreRencontre() {
		// Si on a deux occupants ou plus
		if (this.getOccupants().size() >= 2) {
			// On récupère le premier personnage actuellement sur la case
			Personnage premierOccupant = this.getOccupants().get(0);			
			// On récupère le deuxième personnage actuellement sur la case
			Personnage deuxiemeOccupant = this.getOccupants().get(1);
			
			// Si l'un est un héros et l'autre un ennemi
			if ((premierOccupant.getType() == TypePersonnage.Heros && deuxiemeOccupant.getType() == TypePersonnage.Ennemi) || 
					(deuxiemeOccupant.getType() == TypePersonnage.Heros && premierOccupant.getType() == TypePersonnage.Ennemi)){
				// On engage le combat
				Main.EngagerCombat((Combattant)premierOccupant, (Combattant)deuxiemeOccupant);
				// Si un occupant est mort, le retirer de la liste des occupants
				if (!premierOccupant.enVie()) {
					this.getOccupants().remove(premierOccupant);
				}
				if (!deuxiemeOccupant.enVie()) {
					this.getOccupants().remove(deuxiemeOccupant);
				}
			}
			// Si l'un est un héros et l'autre un guérisseur, on lance la rencontre
			else if (premierOccupant.getType() == TypePersonnage.Heros && deuxiemeOccupant.getType() == TypePersonnage.Guerisseur){
				Main.RencontreGuerisseur((Heros)premierOccupant, (Guerisseur)deuxiemeOccupant);
			}
			else if	(deuxiemeOccupant.getType() == TypePersonnage.Heros && premierOccupant.getType() == TypePersonnage.Guerisseur){
				Main.RencontreGuerisseur((Heros)deuxiemeOccupant, (Guerisseur)premierOccupant);
			}
		}
	}
	/**
	 * Afficher la case avec ses coordonnées.
	 * @return Un String correspondant à la case.
	 */
	@Override
	public String toString() {
		return this.toString(true);
	}
	
	/**
	 * Afficher la case.
	 * @param afficherCoordonnes Ajouter les coordonées ?
	 * @return Un String correspondant à la case.
	 */
	public String toString(boolean afficherCoordonnes) {
		// Par défaut, on affiche le type de terrain de la case.
		String contenu = this._type.toString().substring(0, 1);	
		
		// S'il y a un occupant, on affiche l'occupant
		if (this._occupants.size() > 0) {
			Personnage occupant = this._occupants.get(0);
			
			contenu =  occupant.getType().toString().substring(0, 1);	
		}
		
		// Si on affiche les coordonnées
		if (afficherCoordonnes) {
			contenu = String.format("%s (%s)", contenu, this._coordonnees);
		}
			
		return contenu;
	}
}
package rpg.actions;

import java.util.ArrayList;

import rpg.carte.Carte;
import rpg.carte.Case;
import rpg.carte.Coordonnees;
import rpg.personnages.Heros;
import rpg.personnages.Personnage;
import rpg.personnages.TypePersonnage;

/**
 * Action pour se déplacer sur la carte.
 * @author marechal
 *
 */
public class Deplacement implements Action {
	/**
	 * Se déplacer sur la carte.
	 * @param emetteur Le personnage souhaitant se déplacer.
	 * @param receveur La carte du monde.
	 * @param params Les coordonnées où le personnage veut se déplacer.
	 */
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params ) {
		// On récupère les paramètres
		Personnage perso = (Personnage) emetteur;
		Carte carte = (Carte) receveur;
		String coordonnees = params.length > 0 ? params[0].toString() : "";
		
		// On récupère la case où le personnage souhaite se déplacer
		Case destination = carte.getCase(coordonnees);
		// Si trouvé, on regarde si on peut bouger vers la destination possible
		if (destination != null && peutBouger(carte, perso, destination)){
			// On modifie la position du personnage
			perso.setPosition(destination);
			
			// Si c'est le héros, lui infliger des dégats
			if (perso instanceof Heros) {
				Heros h = (Heros) perso;
				System.out.printf("Le terrain vous inflige %s dégats", destination.getCoutEnergie());
				System.out.println();
				h.prendreDegats(destination.getCoutEnergie());
				// Lui mettre 2 dégats supplémentaire s'il est malade
				if (!h.enBonneSante()) {
					System.out.println("Votre maladie vous inflige 2 dégâts");
					h.prendreDegats(2);
				}
				// Le rendre malade, ou pas
				destination.maladie(h);
			}
			
			// On résoud les éventuelles rencontres sur la case
			destination.resoudreRencontre();
			// L'action est un succès
			return true;
		}
		
		// L'action est un échec
		return false;
	}
	
	/**
	 * Fonction de déplacement du personnage.
	 * @param c Carte où se déplacer.
	 * @param quiBouge Personnage à bouger.
	 * @param destination Destination du personnage
	 */
	public static boolean peutBouger(Carte carte, Personnage quiBouge, Case destination) {
		// Si l'un des paramètres n'existe pas, on empêche l'action
		if (carte == null || quiBouge == null || destination == null) {
			return false;
		}
		
		// Si celui qui bouge est un guérisseur, on empêche l'action
		if (quiBouge.getType() == TypePersonnage.Guerisseur) {
			return false;
		}
		
		// S'il souhaite faire du "Sur place", on empêche
		if (destination == quiBouge.getPosition()) {
			return false;
		}
			
		// On récupère le premier occupant de la case 
		Personnage occupantActuel = destination.getOccupants().size() > 0 ? destination.getOccupants().get(0) : null; 
		
		// Si trouvé
		if (occupantActuel != null) {
			// Si celui qui bouge est un ennemi
			if (quiBouge.getType() == TypePersonnage.Ennemi ) {
				// Si l'occupant est un ennemi ou un guérisseur, on bloque le déplacement
				if(occupantActuel.getType() == TypePersonnage.Ennemi || occupantActuel.getType() == TypePersonnage.Guerisseur) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Récupérer les déplacements possibles du personnage sur la carte.
	 * @param c La carte où évolue le personnage.
	 * @param p Le personnage qui bouge.
	 * @return Les cases où le personnage peut se déplacer.
	 */
	public static ArrayList<Case> deplacementsPossibles(Carte c, Personnage p){
		ArrayList<Case> resultat = new ArrayList<Case>();
		
		Coordonnees coordActuelle = p.getPosition().getCoordonnees();
		
		for (int latitude = coordActuelle.latitude - 1; latitude <= coordActuelle.latitude + 1; latitude++) {
			for (int longitude = coordActuelle.longitude - 1; longitude <= coordActuelle.longitude + 1; longitude++) {
				// On récupère la case correspondante aux coordonnées
				Case possibilite = c.getCase(new Coordonnees(latitude, longitude));
				// Si le déplacement est autorisé, on l'ajoute à la liste de résultat
				if (Deplacement.peutBouger(c, p, possibilite)) {
					resultat.add(possibilite);
				}
			}
		}
		
		return resultat;
	}
}
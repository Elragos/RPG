package rpg.actions;

import rpg.Main;
import rpg.carte.Carte;
import rpg.carte.Case;
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
		// Si trouvé
		if (destination != null) 
			// On résoud le déplacement
			return deplacer(carte, perso, destination);
		
		// L'action est un échec
		return false;
	}
	
	/**
	 * Fonction de déplacement du personnage.
	 * @param c Carte où se déplacer.
	 * @param quiBouge Personnage à bouger.
	 * @param destination Destination du personnage
	 */
	private boolean deplacer(Carte carte, Personnage quiBouge, Case destination) {
		// Si celui qui bouge est un guérisseur, on empêche l'action
		if (quiBouge.getType() == TypePersonnage.Guerisseur)
			return false;
		
		// Résultat de l'action
		boolean resultat = false;
		
		// On récupère la case de départ du personnage
		Case depart = quiBouge.getPosition();
		
		// On récupère le premier occupant de la case 
		Personnage occupantActuel = destination.getOccupants().size() > 0 ? destination.getOccupants().get(0) : null; 
		
		// Si trouvé
		if (occupantActuel != null) {
			// Si celui qui bouge est le héros
			if (quiBouge.getType() == TypePersonnage.Heros) {
				// La case de destination lui enlève de l'énergie
				quiBouge.prendreDegats(destination.getCoutEnergie());
				// Si l'occupant est un ennemi, on engage le combat!
				if (occupantActuel.getType() == TypePersonnage.Ennemi)				
					resultat = Main.EngagerCombat((Combattant)quiBouge, (Combattant)occupantActuel);
			}
			
			// Si celui qui bouge est un ennemi
			if (quiBouge.getType() == TypePersonnage.Ennemi) {
				// Si l'occupant est un ennemi, on engage le combat!
				if (occupantActuel.getType() == TypePersonnage.Heros)				
					resultat = Main.EngagerCombat((Combattant)quiBouge, (Combattant)occupantActuel);
				// Si l'occupant est un autre ennemi, on empêche l'action.
				else if (occupantActuel.getType() == TypePersonnage.Ennemi)	
					resultat = false;
			}
		}
		
		// Si l'action a réussi
		if (resultat) {
			// On supprime le personnage de sa case de départ
			depart.getOccupants().remove(quiBouge);
			
			// On ajoute le personnage à l'arrivée
			destination.getOccupants().add(quiBouge);
		}
		
		// Retourner le résultat de l'action
		return resultat;
	}
}
package rpg.actions;

import rpg.Main;
import rpg.carte.Carte;
import rpg.carte.Case;
import rpg.personnages.Personnage;
import rpg.personnages.TypePersonnage;

/**
 * Action pour se d�placer sur la carte.
 * @author marechal
 *
 */
public class Deplacement implements Action {

	/**
	 * Se d�placer sur la carte.
	 * @param emetteur Le personnage souhaitant se d�placer.
	 * @param receveur La carte du monde.
	 * @param params Les coordonn�es o� le personnage veut se d�placer.
	 */
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params ) {
		// On r�cup�re les param�tres
		Personnage perso = (Personnage) emetteur;
		Carte carte = (Carte) receveur;
		String coordonnees = params.length > 0 ? params[0].toString() : "";
		
		// On r�cup�re la case o� le personnage souhaite se d�placer
		Case destination = carte.getCase(coordonnees);
		// Si trouv�
		if (destination != null) 
			// On r�soud le d�placement
			return deplacer(carte, perso, destination);
		
		// L'action est un �chec
		return false;
	}
	
	/**
	 * Fonction de d�placement du personnage.
	 * @param c Carte o� se d�placer.
	 * @param quiBouge Personnage � bouger.
	 * @param destination Destination du personnage
	 */
	private boolean deplacer(Carte carte, Personnage quiBouge, Case destination) {
		// Si celui qui bouge est un gu�risseur, on emp�che l'action
		if (quiBouge.getType() == TypePersonnage.Guerisseur)
			return false;
		
		// R�sultat de l'action
		boolean resultat = false;
		
		// On r�cup�re la case de d�part du personnage
		Case depart = quiBouge.getPosition();
		
		// On r�cup�re le premier occupant de la case 
		Personnage occupantActuel = destination.getOccupants().size() > 0 ? destination.getOccupants().get(0) : null; 
		
		// Si trouv�
		if (occupantActuel != null) {
			// Si celui qui bouge est le h�ros
			if (quiBouge.getType() == TypePersonnage.Heros) {
				// La case de destination lui enl�ve de l'�nergie
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
				// Si l'occupant est un autre ennemi, on emp�che l'action.
				else if (occupantActuel.getType() == TypePersonnage.Ennemi)	
					resultat = false;
			}
		}
		
		// Si l'action a r�ussi
		if (resultat) {
			// On supprime le personnage de sa case de d�part
			depart.getOccupants().remove(quiBouge);
			
			// On ajoute le personnage � l'arriv�e
			destination.getOccupants().add(quiBouge);
		}
		
		// Retourner le r�sultat de l'action
		return resultat;
	}
}
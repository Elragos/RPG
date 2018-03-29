package rpg.objets;

import rpg.personnages.Heros;

/**
 * Classe représentant les jumelles.
 * @author marechal
 */
public class Jumelles extends ObjetInventaire {

	/**
	 * Générer des jumelles.
	 */
	public Jumelles() {
		super("Jumelles", 2);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Afficher un message au rammassage.
	 */
	@Override
	public void auRammassage() {
		System.out.println("Vous ramassez une paire de jumelles.");
	}
	
	public void utiliser(Heros h) {
		h.setChampVision(2);
		System.out.println("Votre champ de vision augmente");
	}
}
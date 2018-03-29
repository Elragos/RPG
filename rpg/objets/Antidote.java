package rpg.objets;

import rpg.personnages.Heros;

/**
 * Antidote du jeu.
 * @author marechal
 */
public class Antidote extends ObjetInventaire {
	/**
	 * Créer un nouvel antidote.
	 */
	public Antidote() {
		super("Antidote", 2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Afficher un message au rammassage.
	 */
	@Override
	public void auRammassage() {
		System.out.println("Vous venez d'obtenir un antidote qui vous soignera intégralement et vous guérira en cas de soucis.");
	}
	
	/**
	 * Utiliser l'antidote sur le héros.
	 * @param h Le héros qui utilise l'antidote.
	 */
	@Override
	public void utiliser(Heros h) {
		// On remet le héros en pleine santé
		h.setEnergieActuelle(h.getEnergieMaximale());
	  	h.setSante(true);
	  	// On supprime l'antidote de l'inventaire
	  	h.getSac().getContenu().remove(this);
	  	// On affiche la confirmation
	  	System.out.println("Vous possedez de nouveau tout vos point de vie et vous êtes en pleine santé. wicked est bon.");
	}
}

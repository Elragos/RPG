package rpg.personnages;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Personnage guérisseur.
 * @author marechal
 */
public class Guerisseur extends Personnage {
	
	/**
	 * Coût de la guérison.
	 */
	private int cout;
	
	/**
	 * Par défaut, un guérisseur prend 5PO pour soigner (ou pas).
	 */
	public Guerisseur() {
		this(5);
	}
	
	/**
	 * Créer un guérisseur, avec un coup d'interaction.
	 * @param cout
	 */
	public Guerisseur(int cout) {
		this._type = TypePersonnage.Guerisseur;
		this.cout = 5;
	}
	
	/**
	 * Récupérer le coût du guérisseur.
	 * @return
	 */
	public int getCout() {
		return cout;
	}
	
	/**
	 * Ensorceler le héros (en bien ou en mal).
	 * @param h Le héros en question.
	 */
	public void ensorceler(Heros h) {
		// On définit que le guérisseur a une chance sur 10 de se louper.
		int randomInt = ThreadLocalRandom.current().nextInt(0, 10);
		// S'il réussi
		if (randomInt != 0) {
			System.out.println("Vous vous sentez en pleine forme !!!");
			h.setEnergieActuelle(h.getEnergieMaximale());
			h.setSante(true);
		}
		// Sinon le héros tombe malade.
		else {
			System.out.println("OUPS !!! Le guérisseur s'est raté ! Vous tombez malade.");
			h.setSante(false);
		}
	}
	
	/**
	 * Pour l'heure, le guérisseur ne peut pas mourir.
	 */
	@Override
	public void finVie() {
		// Nothing to do here
	}
	/**
	 * Pour l'heure, le guérisseur ne peut pas mourir.
	 * @return <code>true</code> car il est immortel.
	 */
	@Override
	public boolean enVie() {
		return true;
	}
	
}
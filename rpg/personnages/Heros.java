package rpg.personnages;

import rpg.Main;
import rpg.actions.Combattant;

public class Heros extends Personnage implements Combattant {

	/**
	 * Cr�er un nouveau h�ros.
	 * @param energieMaximale Energie maximale de d�part.
	 */
	public Heros(int energieMaximale) {	
		super(energieMaximale);
		this._type = TypePersonnage.Heros;
		this._nom = "H�ros du jeu";
	}
	
	/**
	 * A la mort du h�ros, lancer le GameOver.
	 */
	@Override
	public void finVie() {
		Main.GameOver();
	}
	
	/**
	 * Calculer la puissance d'attaque du h�ros.
	 */
	@Override
	public int calculerAttaque() {
		return 2;
	}

	@Override
	public int calculerDefense() {
		// TODO Auto-generated method stub
		return 0;
	}
}

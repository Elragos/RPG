package rpg.personnages;

import rpg.Main;
import rpg.actions.Combattant;

public class Heros extends Personnage implements Combattant {

	/**
	 * Créer un nouveau héros.
	 * @param energieMaximale Energie maximale de départ.
	 */
	public Heros(int energieMaximale) {	
		super(energieMaximale);
		this._type = TypePersonnage.Heros;
		this._nom = "H�ros du jeu";
	}
	
	/**
	 * A la mort du héros, lancer le GameOver.
	 */
	@Override
	public void finVie() {
		Main.GameOver();
	}
	
	/**
	 * Calculer la puissance d'attaque du héros.
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

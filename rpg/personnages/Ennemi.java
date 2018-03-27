package rpg.personnages;

import rpg.actions.Combattant;

/**
 * Classe représentant un ennemi.
 * @author marechal
 *
 */
public class Ennemi extends Personnage implements Combattant {
	
	public Ennemi(int energieDepart) {
		super(energieDepart);
		this._type = TypePersonnage.Ennemi;
		this._nom = "Un monstre";
	}
	
	@Override
	public void finVie() {
		// TODO Auto-generated method stub
		System.out.printf("%s meurt !", this._nom);
		System.out.println();
	}

	@Override
	public int calculerAttaque() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int calculerDefense() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}

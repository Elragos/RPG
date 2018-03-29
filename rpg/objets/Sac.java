package rpg.objets;

import java.util.ArrayList;

import rpg.actions.Interactif;

/**
 * Classe représentant un sac.
 * @author Arthur
 */
public class Sac implements Interactif {
	/**
	 * Poids maximale portable du sac.
	 */
	protected int _poidsMax;
	/**
	 *  Taille maximale du sac.
	 */
	protected int _tailleMax;
	/**
	 * Contenu du sac.
	 */
	protected ArrayList<ObjetInventaire> _contenu;
	
	/**
	 * Créer un nouveau sac.
	 * @param poidsMax Poids maximale portable du sac.
	 * @param tailleMax Taille maximale du sac.
	 */
	public Sac(int poidsMax, int tailleMax) {
		this._contenu = new ArrayList<>();
		this._poidsMax = poidsMax;
		this._tailleMax = tailleMax; 
	}
	
	/**
	 * Récupérer le contenu du sac.
	 * @return Contenu du sac.
	 */
	public ArrayList<ObjetInventaire> getContenu() {
		return _contenu;
	}
	
	/**
	 * Récupérer le poids maximale portable du sac.
	 * @return Poids maximale portable du sac.
	 */
	public int getPoidMax() {
		return this._poidsMax;
	}
	
	/**
	 * Récupérer la taille maximale du sac.
	 * @return Taille maximale du sac.
	 */
	public int getTailleMax() {
		return this._tailleMax;
	}
}
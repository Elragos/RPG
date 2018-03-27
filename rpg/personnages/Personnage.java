package rpg.personnages;

import rpg.actions.Dommageable;
import rpg.carte.Case;

/**
 * D�finition du personnage dans le jeu.
 * @author marechal
 *
 */
public abstract class Personnage implements Dommageable {
	/**
	 * Type du personnage.
	 */
	protected TypePersonnage _type;
	
	/**
	 * Nom du personnage.
	 */
	protected String _nom;
	
	/**
	 * Energie actuelle du h�ros.
	 */
	protected int _energieActuelle;
	
	/**
	 * Energie maximale du h�ros.
	 */
	protected int _energieMaximale;
	
	/**
	 * Case o� se trouve le personnage actuellement.
	 */
	protected Case _position;

	/**
	 * Cr�er un nouveau personnage avec une �nergie maximale de d�part de 20.
	 */
	public Personnage() {
		this(20);
	}
	
	/**
	 * Cr�er un nouveau personnage avec une �nergie maximale de d�part pr�cise.
	 * @param energieMaximale Energie maximale de d�part.
	 */
	public Personnage(int energieMaximale) {	
		this._energieMaximale = energieMaximale;
		this._energieActuelle = energieMaximale;
	}
	
	/**
	 * R�cup�rer le type du personnage.
	 * @return Le type du personnage.
	 */
	public TypePersonnage getType() {
		return this._type;
	}

	/**
	 * R�cup�rer le nom du personnage.
	 * @return Le nom du personnage.
	 */
	public String getNom() {
		return this._nom;
	}
	
	/**
	 * R�cup�rer l'�nergie actuelle du personnage.
	 * @return L'�nergie actuelle du personnage.
	 */
	public int getEnergieActuelle() {
		return _energieActuelle;
	}
	
	/**
	 * Mettre � jour l'�nergie actuelle du personnage.
	 * @param energieActuelle L'�nergie actuelle du personnage.
	 */
	public void setEnergieActuelle(int energieActuelle) {
		if (energieActuelle <= 0) {
			this._energieActuelle = 0;
			this.finVie();
		}
		else if (energieActuelle >= this._energieMaximale) {
			this._energieActuelle = this._energieMaximale;
		}
		else {
			this._energieActuelle = energieActuelle;
		}		
	}
	
	public abstract void finVie();

	/**
	 * R�cup�rer l'�nergie maximale du personnage.
	 * @return L'�nergie maximale du personnage.
	 */
	public int getEnergieMaximale() {
		return _energieMaximale;
	}
	
	/**
	 * Mettre � jour l'�nergie maximale du personnage.
	 * @param energieMaximale L'�nergie maximale du personnage.
	 */
	public void setEnergieMaximale(int energieMaximale) {
		this._energieMaximale = energieMaximale;
	}
	
	@Override
	public void prendreDegats(int degats) {
		System.out.println(String.format("%s prend %s d�gats", this, degats));
		
		this.setEnergieActuelle(this._energieActuelle - degats);
	}
	
	@Override
	public boolean enVie() {
		return this._energieActuelle > 0;
	}
	
	/**
	 * R�cup�rer la position du personnage.
	 * @return La position actuelles du personnage.
	 */
	public Case getPosition() {
		return this._position;
	}
	
	/**
	 * Modifier la position du personnage.
	 * @param position Nouvelle position du personnage.
	 */
	public void setPosition(Case position) {
		position.getOccupants().add(this);
		this._position = position;
	}
	
	/**
	 * Afficher l'objet, pour debug.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("%s (%s) en %s | ", this._nom, this._type, this._position));
		result.append(String.format("Energie : %s / %s", this._energieActuelle, this._energieMaximale));
		
		return result.toString();
	}
}

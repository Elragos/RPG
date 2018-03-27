package rpg.personnages;

import java.util.ArrayList;

import rpg.actions.Dommageable;
import rpg.objets.Objet;

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
	 * Quantit� d'or de chaque personnage de chaque personnage.
	 */
	protected int _or;
	
	protected ArrayList<Objet> objets;
	
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
	public TypePersonnage GetType() {
		return this._type;
	}

	/**
	 * R�cup�rer le nom du personnage.
	 * @return Le nom du personnage.
	 */
	public String GetNom() {
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
	 * R�cup�rer la quantit� d'or d'un personnage
	 * @return quantit� d'or
	 */
	public int get_or() {
		return _or;
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
	 * Afficher l'objet, pour debug.
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(String.format("%s de type %s | ", this._nom, this._type));
		result.append(String.format("Energie : %s / %s", this._energieActuelle, this._energieMaximale));
		
		return result.toString();
	}
}

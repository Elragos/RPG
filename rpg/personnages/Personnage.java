package rpg.personnages;

import java.util.ArrayList;

import rpg.actions.Dommageable;
import rpg.objets.Objet;

/**
 * Définition du personnage dans le jeu.
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
	 * Energie actuelle du héros.
	 */
	protected int _energieActuelle;
	
	/**
	 * Energie maximale du héros.
	 */
	protected int _energieMaximale;
	
	
	/**
	 * Quantité d'or de chaque personnage de chaque personnage.
	 */
	protected int _or;
	
	protected ArrayList<Objet> objets;
	
	/**
	 * Créer un nouveau personnage avec une énergie maximale de départ de 20.
	 */
	public Personnage() {
		this(20);
	}
	
	/**
	 * Créer un nouveau personnage avec une énergie maximale de départ précise.
	 * @param energieMaximale Energie maximale de départ.
	 */
	public Personnage(int energieMaximale) {	
		this._energieMaximale = energieMaximale;
		this._energieActuelle = energieMaximale;
	}
	
	/**
	 * Récupérer le type du personnage.
	 * @return Le type du personnage.
	 */
	public TypePersonnage GetType() {
		return this._type;
	}

	/**
	 * Récupérer le nom du personnage.
	 * @return Le nom du personnage.
	 */
	public String GetNom() {
		return this._nom;
	}
	
	/**
	 * Récupérer l'énergie actuelle du personnage.
	 * @return L'énergie actuelle du personnage.
	 */
	public int getEnergieActuelle() {
		return _energieActuelle;
	}
	
	
	/**
	 * Récupérer la quantité d'or d'un personnage
	 * @return quantité d'or
	 */
	public int get_or() {
		return _or;
	}
	
	/**
	 * Mettre à jour l'énergie actuelle du personnage.
	 * @param energieActuelle L'énergie actuelle du personnage.
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
	 * Récupérer l'énergie maximale du personnage.
	 * @return L'énergie maximale du personnage.
	 */
	public int getEnergieMaximale() {
		return _energieMaximale;
	}
	
	/**
	 * Mettre à jour l'énergie maximale du personnage.
	 * @param energieMaximale L'énergie maximale du personnage.
	 */
	public void setEnergieMaximale(int energieMaximale) {
		this._energieMaximale = energieMaximale;
	}
	
	@Override
	public void prendreDegats(int degats) {
		System.out.println(String.format("%s prend %s dégats", this, degats));
		
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

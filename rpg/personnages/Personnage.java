package rpg.personnages;

import rpg.actions.Dommageable;
import rpg.carte.Case;

/**
 * Définition du personnage dans le jeu.
 * @author marechal
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
	 * Case où se trouve le personnage actuellement.
	 */
	protected Case _position;
	
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
	public TypePersonnage getType() {
		return this._type;
	}

	/**
	 * Récupérer le nom du personnage.
	 * @return Le nom du personnage.
	 */
	public String getNom() {
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
	 * Mettre à jour l'énergie actuelle du personnage.
	 * @param energieActuelle L'énergie actuelle du personnage.
	 */
	public void setEnergieActuelle(int energieActuelle) {
		// Si plus d'énergie
		if (energieActuelle <= 0) {
			this._energieActuelle = 0;
			// Déclarer la fin de vie
			this.finVie();
		}
		// Plafonner au maximum pour le personnage
		else if (energieActuelle >= this._energieMaximale) {
			this._energieActuelle = this._energieMaximale;
		}
		else {
			this._energieActuelle = energieActuelle;
		}		
	}
	
	/**
	 * Fonction à exécuter lorsque le personnage meurt.
	 */
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
	
	/**
	 * Enlever des points de vie au personnage.
	 */
	@Override
	public void prendreDegats(int degats) {		
		this.setEnergieActuelle(this._energieActuelle - degats);
	}
	
	/**
	 * Est-ce que le personnage est en vie ?
	 * @return <code>true</code> s'il est encore en vie, <code>false</code> sinon.
	 */
	@Override
	public boolean enVie() {
		return this._energieActuelle > 0;
	}
	
	/**
	 * Récupérer la position du personnage.
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
		// Si position actuelle définie
		if (this._position != null) {
			// On enlève de la case actuelle
			this._position.getOccupants().remove(this);
		}
		// On ajoute à la case d'arrivée
		position.getOccupants().add(this);
		// On change la référence
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
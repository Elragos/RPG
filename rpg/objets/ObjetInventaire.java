package rpg.objets;

import java.util.concurrent.ThreadLocalRandom;

import rpg.personnages.Heros;

/**
 * Classe représentant un objet dans l'inventaire
 * @author Arthur.
 */
public abstract class ObjetInventaire {
	/**
	 * Nom de l'objet.
	 */
	protected String _nom;
	/**
	 * Poids de l'objet.
	 */
	protected int _poids;
	/**
	 * Type de l'objet
	 */
	protected TypeObjet _type;
	
	/**
	 * Créer un nouvel objet
	 * @param nom Nom de l'objet.
	 * @param poids Poids de l'objet.
	 */
	public ObjetInventaire(String nom, int poids) {
		this._nom = nom;
		this._poids = poids;
	}
	
	/**
	 * Récupérer le nom de l'objet
	 * @return Nom de l'objet.
	 */
	public String getNom() {
		return _nom;
	}
	
	/**
	 * Récupérer le type de l'objet.
	 * @return Type de l'objet.
	 */
	public TypeObjet getType() {
		return _type;
	}
	
	/**
	 * Afficher un message au rammassage.
	 */
	public abstract void auRammassage();
	
	/**
	 * Utiliser un objet sur le héros.
	 * @param h Le héros qui utilise l'objet.
	 */
	public abstract void utiliser(Heros h);
	
	/**
	 * Récupérer le poids de l'objet.
	 * @return Poids de l'objet.
	 */
	public int getPoids() {
		return _poids;
	}
	
	/**
	 * Générer un objet aléatoirement, ou pas.
	 * @return Le nouvel objet, ou null si pas d'objet.
	 */
	public static ObjetInventaire genererObjet()
	{
		int randomInt = ThreadLocalRandom.current().nextInt(0, TypeObjet.values().length + 1);		
		if (randomInt >= TypeObjet.values().length){
			return null;
		}
		TypeObjet randomType = TypeObjet.values()[randomInt];
		switch (randomType) {
			case Antidotes:
				return new Antidote();
			case Armes:
				return new Arme("Arme sans nom", 4, 2);
			case Jumelles:
				return new Jumelles();
			case Potions:
				return new Potion();
		}
		return null;
	}
	
	/**
	 * Afficher l'objet.
	 */
	@Override
	public String toString() {
		return String.format("%s pesant %s kg", this._nom, this._poids);
	}
}

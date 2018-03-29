package rpg.objets;

import rpg.personnages.Heros;

/**
 * Arme du jeu.
 * @author marechal
 */
public class Arme extends ObjetInventaire {
	
	/**
	 * Puissance de l'arme.
	 */
	protected int _puissance;
	
	/**
	 * Créer une nouvelle arme.
	 * @param nom Nom de l'arme.
	 * @param poids Poids de l'arme
	 * @param puissance Puissance de l'arme.
	 */
	public Arme(String nom, int poids, int puissance) {
		super(nom, poids);
		this._puissance = puissance;
	}
	
	/**
	 * Récupérer la puissance de l'arme.
	 * @return puissance de l'arme.
	 */
	public int getPuissance() {
		return _puissance;
	}
	
	/**
	 * Afficher l'objet.
	 */
	@Override
	public String toString() {
		return String.format("%s augmentant l'attaque de %s", super.toString(), this._puissance);
	}
	
	/**
	 * Afficher un message au rammassage.
	 */
	@Override
	public void auRammassage() {
		System.out.printf("Vous venez d'obtenir une arme qui augmente votre attaque de %s.", this._puissance);
		System.out.println();
	}
	
	/**
	 * Utiliser l'arme sur le héros.
	 * @param h Le héros qui utilise l'arme.
	 */
	@Override
	public void utiliser(Heros h) {
		h.setArme(this);
		System.out.printf("Vous êtes maintenant équipé d'une arme qui augmentera vos dégats de %s.", this._puissance);
		System.out.println();
	}
}

package rpg.objets;

import java.util.concurrent.ThreadLocalRandom;

import rpg.personnages.Heros;

/**
 * Classe représentant une postion
 * @author marechal
 *
 */
public class Potion extends ObjetInventaire {
	/**
	 * Quantité d'énergie que ramène la potion.
	 */
	protected int _soin;
	/**
	 * Montants que peut rendre la potion.
	 */
	private final int[] VALEUR_SOIN = {5,10,15,20};

	/**
	 * Créer une nouvelle potion.
	 */
	public Potion() {
		super("Potion de soin", 1);
		// On détermine aléatoirement le montant rendue.
		int randomInt = ThreadLocalRandom.current().nextInt(0, VALEUR_SOIN.length);		
		this._soin = VALEUR_SOIN[randomInt];
		
		// Nommer la potion
		String detailNom = "mineure";
		switch(randomInt) {
			case 0:
				detailNom = "mineure";
				break;
			case 1:
				detailNom = "normale";
				break;
			case 2:
				detailNom = "majeure";
				break;
			case 3:
				detailNom = "grandiose";
				break;
		}		
		this._nom = String.format("Potion de soins %s", detailNom);
	}
	
	/**
	 * Récupérer la quantité d'énergie que ramène la potion.
	 * @return Quantité d'énergie que ramène la potion.
	 */
	public int getSoin() {
		return _soin;
	}
	
	/**
	 * Afficher l'objet.
	 */
	@Override
	public String toString() {
		return String.format("%s soignant de %s PE", super.toString(), this._soin);
	}
	
	/**
	 * Afficher un message au rammassage.
	 */
	@Override
	public void auRammassage() {
		System.out.printf("Vous venez d'obtenir une potion qui vous soignera de %s points de vie en cas de soucis.", this._soin);
		System.out.println();
	}

	/**
	 * Utiliser la potion sur le héros.
	 * @param h Le héros qui utilise la potion.
	 */
	@Override
	public void utiliser(Heros h) {
		h.setEnergieActuelle(h.getEnergieActuelle() + this._soin);
		System.out.printf("Vous gagnez %s points de vie. On a encore eu de la chance.", this._soin);
		System.out.println();
	}
}
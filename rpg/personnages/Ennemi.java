package rpg.personnages;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import rpg.actions.Combattant;

/**
 * Classe représentant un ennemi.
 * @author marechal
 */
public class Ennemi extends Personnage implements Combattant {

	/**
	 * Liste des monstres possibles du jeu.
	 */
	private static final List<Ennemi> ennemisPossibles = Arrays.asList(
		new Ennemi(3, "Gobelin", 1, 0),
		new Ennemi(7, "Orc", 3, 1),
		new Ennemi(3, "Petite Gargouille", 1, 4)
	);
	
	/**
	 * Base d'attaque du monstre.
	 */
	protected int _baseAttaque;
	
	/**
	 * Base défensive du monstre.
	 */
	protected int _baseDefense;
	
	/**
	 * Créer un monstre avec un nom et une énergie de départ.
	 * @param energieDepart Energie de départ du monstre.
	 * @param nom Nom du monstre
	 * @param attaque Puissance d'attaque du monstre.
	 * @param defense Défense du monstre.
	 */
	private Ennemi(int energieDepart, String nom, int attaque, int defense) {
		super(energieDepart);
		
		this._type = TypePersonnage.Ennemi;
		this._nom = nom;
		this._baseAttaque = attaque;
		this._baseDefense = defense;
	}
	
	/**
	 * Lorsque l'ennemi meurt, on l'indique.
	 */
	@Override
	public void finVie() {
		// TODO Auto-generated method stub
		System.out.printf("%s meurt !", this._nom);
		System.out.println();
	}

	/**
	 * Calculer l'attaque du monstre.
	 */
	@Override
	public int calculerAttaque() {
		return _baseAttaque;
	}

	/**
	 * Calculer la défense du monstre.
	 */
	@Override
	public int calculerDefense() {
		return _baseDefense;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	/**
	 * Générer un nouveau monstre à partir de son nom.
	 * @param nomDuMonstre Le nom du monstre.
	 * @return Nouveau monstre, ou null si non trouvé.
	 */
	public static Ennemi genererNouveauMonstre() {
		// Récupérer un ennemi aléatoirement de la liste des ennemis possibles
		int randomIndex = ThreadLocalRandom.current().nextInt(0, ennemisPossibles.size());
		Ennemi resultat = ennemisPossibles.get(randomIndex);
		
		// Renvoyer une copie du monstre
		return resultat.copie();
	}
	
	/**
	 * Retourner une copie du monstre.
	 * @return Une copie du monstre.
	 */
	private Ennemi copie() {
		Ennemi resultat = new Ennemi(
			this._energieMaximale,
			this._nom,
			this._baseAttaque,
			this._baseDefense
		);
		
		return resultat;
	}
}
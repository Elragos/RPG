package rpg.personnages;

import rpg.Main;
import rpg.actions.Combattant;
import rpg.objets.Arme;
import rpg.objets.Sac;

/**
 * Classe représentant le héros.
 * @author marechal
 */
public class Heros extends Personnage implements Combattant {
	
	/**
	 * Base d'attaque du monstre.
	 */
	protected int _baseAttaque;
	
	/**
	 * Base défensive du monstre.
	 */
	protected int _baseDefense;

	/**
	 * Quantité d'or du personnage.
	 */
	protected int _bourse = 10;
	
	/**
	 * Ensemble des objets porté par le personnage.
	 */
	protected Sac _sac;
	
	/**
	 * Défini si le personnage est malade ou non.
	 */
	protected boolean _sante;
	
	/**
	 * Arme que porte le héros
	 */
	protected Arme _arme;
	
	/**
	 * Champ de vision du héros, de base 1.
	 */
	protected int _champVision = 1;
		
	/**
	 * Créer un nouveau héros.
	 * @param energieMaximale Energie maximale de départ.
	 * @param baseAttaque Attaque de base du héros.4
	 * @param baseDefense Défense de base du héros.
	 */
	public Heros(int energieMaximale, int baseAttaque, int baseDefense) {	
		super(energieMaximale);
		this._type = TypePersonnage.Heros;
		this._nom = "Héros du jeu";
		this._sante = true;
		this._baseAttaque = baseAttaque;
		this._baseDefense = baseDefense;
		this._sac = new Sac(10, 5);
	}
	
	/**
	 * Récupérer la quantité d'or d'un personnage.
	 * @return quantité d'or
	 */
	public int getBourse() {
		return this._bourse;
	}
	
	/**
	 * Indiquer une nouvelle quantité d'or à un personnage.
	 * @param bourse
	 */
	public void setBourse(int bourse) {
		this._bourse = bourse;
	}
	
	/**
	 * Modifier le montant de la bourse.
	 * @param montant Montant à ajouter (ou retirer si négatif).
	 */
	public void modifierBourse(int montant) {
		this.setBourse(this.getBourse() - montant);
	}
	
	/**
	 * Récupérer le sac du héros.
	 * @return Le sac du héros.
	 */
	public Sac getSac() {
		return this._sac;
	}
	
	/**
	 * Modifier la santé du héros.
	 * @param sante <code>true</code> s'il est en bonne santé, <code>false</code> sinon.
	 */
	public void setSante(boolean sante) {
		this._sante = sante;
	}
	
	/**
	 * Récupérer l'arme du héros
	 * @return L'arme du héros.
	 */
	public Arme getArme() {
		return this._arme;
	}
	
	/**
	 * Equiper une nouvelle arme.
	 * @param nouvelleArme La nouvelle arme.
	 */
	public void setArme(Arme nouvelleArme) {
		this._arme = nouvelleArme;
	}
	
	/**
	 * Est-ce que le héros est en bonne santé ?
	 * @return <code>true</code> s'il est en bonne santé, <code>false</code> sinon.
	 */
	public boolean enBonneSante() {
		return _sante;
	}
	
	/**
	 * Afficher les statistiques du héros
	 */
	public void afficherStats() {
		System.out.printf("ATK : %s | DEF : %s | PV : %s / %s", calculerAttaque(), calculerDefense(), _energieActuelle, _energieMaximale);
		System.out.println();
	}

	/**
	 * A la mort du héros, lancer le GameOver.
	 */
	@Override
	public void finVie() {
		Main.GameOver();
	}
	
	/**
	 * Calculer la puissance d'attaque du héros.
	 */
	@Override
	public int calculerAttaque() { 
		if (_arme != null) {
			return _baseAttaque + _arme.getPuissance();
		}
		
		return _baseAttaque;
	}
	
	/**
	 * Calculer la défense du héros.
	 */
	@Override
	public int calculerDefense() {
		return _baseDefense;
	}
	
	/**
	 * Modifier le champ de vision du héros.
	 * @param champVision Champ de vision du héros.
	 */
	public void setChampVision(int champVision) {
		this._champVision = champVision;
	}
	
	/**
	 * Récupérer le champ de vision du héros.
	 * @return champVision Champ de vision du héros.
	 */
	public int getChampVision() {
		return this._champVision;
	}
}

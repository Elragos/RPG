package rpg.actions;

/**
 * Interface d�finissant un combattant.
 * @author marechal
 */
public interface Combattant extends Dommageable {
	/**
	 * Calculer l'attaque du combattant.
	 * @return L'attaque du combattant.
	 */
	public int calculerAttaque();
	/**
	 * Calculer la d�fense du combattant.
	 * @return La d�fense du combattant.
	 */
	public int calculerDefense();
}

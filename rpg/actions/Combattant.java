package rpg.actions;

/**
 * Interface définissant un combattant.
 * @author marechal
 */
public interface Combattant extends Dommageable {
	/**
	 * Calculer l'attaque du combattant.
	 * @return L'attaque du combattant.
	 */
	public int calculerAttaque();
	/**
	 * Calculer la défense du combattant.
	 * @return La défense du combattant.
	 */
	public int calculerDefense();
}

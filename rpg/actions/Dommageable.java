package rpg.actions;

/**
 * Interface décrivant un objet interactif pouvant prendre des dégâts.
 * @author marechal
 *
 */
public interface Dommageable extends Interactif {
	/**
	 * Prendre des dégâts.
	 * @param degats force de frappe.
	 */
	public void prendreDegats(int degats);
	
	/**
	 * Evènement lorsque l'objet n'a plus de vie.
	 */
	public void finVie();
	
	/**
	 * Est-ce que l'objet a encore de la vie ?
	 * @return
	 */
	public boolean enVie();
}

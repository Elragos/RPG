package rpg.actions;

/**
 * Interface d�crivant un objet interactif pouvant prendre des d�g�ts.
 * @author marechal
 *
 */
public interface Dommageable extends Interactif {
	/**
	 * Prendre des d�g�ts.
	 * @param degats force de frappe.
	 */
	public void prendreDegats(int degats);
	
	/**
	 * Ev�nement lorsque l'objet n'a plus de vie.
	 */
	public void finVie();
	
	/**
	 * Est-ce que l'objet a encore de la vie ?
	 * @return
	 */
	public boolean enVie();
}

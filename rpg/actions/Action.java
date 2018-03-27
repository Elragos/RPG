package rpg.actions;

/**
 * Interface définissant une action.
 * @author marechal
 *
 */
public interface Action {
	/**
	 * Exécuter une action.
	 * @param emetteur Emetteur de l'action.
	 * @param receveur Receveur de l'action.
	 * @param params Paramètres de l'action.
	 * @return <code>true</code> si l'action a réussie, <code>false</code> sinon.
	 */
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params);
}
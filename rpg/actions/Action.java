package rpg.actions;

/**
 * Interface d�finissant une action.
 * @author marechal
 *
 */
public interface Action {
	/**
	 * Ex�cuter une action.
	 * @param emetteur Emetteur de l'action.
	 * @param receveur Receveur de l'action.
	 * @param params Param�tres de l'action.
	 * @return <code>true</code> si l'action a r�ussie, <code>false</code> sinon.
	 */
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params);
}
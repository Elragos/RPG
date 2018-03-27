package rpg.actions;

/**
 * Action d'attaque basique.
 * @author marechal
 *
 */
public class Frapper implements Action {
	
	/**
	 * Ex�cuter l'action de combat
	 * @param emetteur L'attaquant.
	 * @param receveur Le d�fenseur.
	 * @param params Les param�tres de l'action, non utilis� actuellement.
	 * @return <code>true</code>, vu que c'est du Uncle Bens (toujours un succ�s).
	 */
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		// On r�cup�re les combattants
		Combattant attaquant = (Combattant) emetteur;
		Combattant defenseur = (Combattant) receveur;
		
		// On calcule les d�g�ts que subit l'attaquant
		System.out.println(String.format("%s inflige %s d�gats", attaquant, attaquant.calculerAttaque()));
		System.out.println(String.format("%s a une d�fense de %s", defenseur, defenseur.calculerDefense()));
		
		int degats = attaquant.calculerAttaque() - defenseur.calculerDefense();
		
		// On notifie au d�fenseur les d�g�ts qu'il prend
		defenseur.prendreDegats(degats);
		
		// L'action est toujours r�ussie
		return true;
	}
}

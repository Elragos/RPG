package rpg.actions;

/**
 * Action d'attaque basique.
 * @author marechal
 *
 */
public class Frapper implements Action {
	/**
	 * Exécuter l'action de combat
	 * @param emetteur L'attaquant.
	 * @param receveur Le défenseur.
	 * @param params Les paramètres de l'action, non utilisé actuellement.
	 * @return <code>true</code>, vu que c'est du Uncle Bens (toujours un succès).
	 */
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		// On r�cup�re les combattants
		Combattant attaquant = (Combattant) emetteur;
		Combattant defenseur = (Combattant) receveur;
		
		// On calcule les dégâts que subit l'attaquant
		System.out.println(String.format("%s inflige %s dégâts", attaquant, attaquant.calculerAttaque()));
		System.out.println(String.format("%s a une d�fense de %s", defenseur, defenseur.calculerDefense()));
		
		int degats = attaquant.calculerAttaque() - defenseur.calculerDefense();
		
		// On notifie au défenseur les dégâts qu'il prend
		defenseur.prendreDegats(degats);
		
		// L'action est toujours réussie
		return true;
	}
}
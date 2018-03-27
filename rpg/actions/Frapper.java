package rpg.actions;

/**
 * Action d'attaque basique.
 * @author marechal
 *
 */
public class Frapper implements Action {

	@Override
	public void executer(Interactif emetteur, Interactif receveur) {
		Combattant initiateur = (Combattant) emetteur;
		Combattant cible = (Combattant) receveur;

		System.out.println(String.format("%s inflige %s dégats", initiateur, initiateur.calculerAttaque()));
		
		int degats = initiateur.calculerAttaque() - cible.calculerDefense();
		
		System.out.println(String.format("%s a une défense de %s", cible, cible.calculerDefense()));
		
		cible.prendreDegats(degats);
	}
}

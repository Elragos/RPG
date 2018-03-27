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

		System.out.println(String.format("%s inflige %s d�gats", initiateur, initiateur.calculerAttaque()));
		
		int degats = initiateur.calculerAttaque() - cible.calculerDefense();
		
		System.out.println(String.format("%s a une d�fense de %s", cible, cible.calculerDefense()));
		
		cible.prendreDegats(degats);
	}
}

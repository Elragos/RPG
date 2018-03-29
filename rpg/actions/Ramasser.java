package rpg.actions;

import rpg.objets.ObjetInventaire;
import rpg.objets.Sac;
import rpg.Main;
import rpg.carte.Case;

/**
 * Action de rammasser un objet par terre.
 * @author marechal
 *
 */
public class Ramasser implements Action {
	
	/**
	 * Exécuter l'action.
	 * @param emetteur Sac du héros
	 * @param receveur Case où l'on ramasse.
	 * @param params Paramètres de l'action, non utilisé.
	 * @return <code>true</code> si l'action a réussie, <code>false</code> sinon.
	 */
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		Sac ramasseur = (Sac) emetteur;
		Case cible = (Case) receveur;		
		
		if (cible.getObjets().size() != 0) {			
			ObjetInventaire aRamasser = Main.MenuSelection(cible.getObjets(), "Que voulez-vous ramassez ? ");			
			
			// Si on peut le porter
			if (ramasseur.getContenu().size() < ramasseur.getTailleMax()) {
				aRamasser.auRammassage();				
				ramasseur.getContenu().add(aRamasser);
					
			}
			// Sinon, on demande s'il souhaite échanger
			else if (Main.MenuOuiNon("Votre sac est plein, vous pouvez utiliser un de vos objets ou souhaitez vous faire un échange ?")){
				// On récupère l'objet que le héros veut jeter
				ObjetInventaire aJeter = Main.MenuSelection(ramasseur.getContenu(), "Que voulez-vous laissez à la place ? ");
				
				// On effectue l'échange
				ramasseur.getContenu().remove(aJeter);
				ramasseur.getContenu().add(aRamasser);
				
				// On affiche la confirmation
				System.out.printf("Vous avez jeté votre % et ramassé %", aJeter, aRamasser);
				System.out.println();
			}
		}
		else {
			System.out.println("Il n'y a aucun objet à ramasser");
		}
		// L'action réussie toujours.
		return true;
	}
}
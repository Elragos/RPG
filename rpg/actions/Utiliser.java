package rpg.actions;

import rpg.objets.Sac;
import rpg.personnages.Heros;
import rpg.Main;
import rpg.objets.ObjetInventaire;

public class Utiliser implements Action {

	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		Heros user = (Heros) emetteur;
		Sac cible = (Sac) receveur;
		
		if (cible.getContenu().size() != 0) {
			ObjetInventaire aUtiliser = Main.MenuSelection(cible.getContenu(), "Quel objet souhaitez vous utiliser ?");
			
			aUtiliser.utiliser(user);
		}
		else {
			System.out.println("Votre sac est vide :(");
		}
		return true;
	}
}
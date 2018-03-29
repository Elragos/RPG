package rpg.actions;

import rpg.personnages.Heros;
import rpg.carte.Case;

public class RamasserOr implements Action{
	
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		Heros ramasseur = (Heros) emetteur;
		Case cible = (Case) receveur;

		if(cible.getOr() != 0) {
			System.out.println("Vous ramassez " + cible.getOr() + " pièces d'or.");
			ramasseur.setBourse(cible.getOr() + ramasseur.getBourse());
			cible.setOr(0);
		}
		else {
			System.out.println("Il n'y a pas d'or ici");
		}
		
		// action réussie
		return true;
	}
}
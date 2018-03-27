package rpg.actions;

import rpg.personnages.Personnage;
import rpg.carte.Case;

public class RamasserOr implements Action{
	
	@Override
	public void executer(Interactif emetteur, Interactif receveur) {
		Personnage ramasseur = (Personnage) emetteur;
		Case cible = (Case) receveur;

		if(cible.getOr() != 0) {
			
		}
	}

}

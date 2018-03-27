package rpg.actions;

import rpg.personnages.Personnage;
import rpg.carte.Case;

public class RamasserOr implements Action{
	
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		Personnage ramasseur = (Personnage) emetteur;
		Case cible = (Case) receveur;

		if(cible.getOr() != 0) {
			
		}
		
		return true;
	}
}
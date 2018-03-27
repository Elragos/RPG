package rpg.actions;

import rpg.personnages.Personnage;
import rpg.objets.Objet;

public class Ramasser implements Action{
	
	@Override
	public boolean executer(Interactif emetteur, Interactif receveur, Object... params) {
		Personnage ramasseur = (Personnage) emetteur;
		Objet cible = (Objet) receveur;

		return true;
	}
}
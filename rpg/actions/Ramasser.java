package rpg.actions;

import rpg.personnages.Personnage;
import rpg.objets.Objet;

public class Ramasser implements Action{
	
	@Override
	public void executer(Interactif emetteur, Interactif receveur) {
		Personnage ramasseur = (Personnage) emetteur;
		Objet cible = (Objet) receveur;

		
	}

}

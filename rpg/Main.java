package rpg;

import rpg.actions.*;
import rpg.carte.Carte;
import rpg.personnages.*;

/**
 * Classe de démarrage du programme.
 * @author marechal
 *
 */
public class Main {
	/**
	 * Lancer le programme.
	 * @param args Paramètres, non utilisé pour l'instant.
	 */
	public static void main(String[] args) {
		Heros heros = new Heros(20);
		Ennemi adversaire = new Ennemi(5);
		Ennemi adversaire2 = new Ennemi(5);

		Carte carte = new Carte(20, 20);
		carte.genererCarte();
		carte.afficher();
		
		heros.setPosition(carte.getCase("1-1"));
		adversaire.setPosition(carte.getCase("1-2"));
		adversaire.setPosition(carte.getCase("2-2"));
		
		Action deplacer = new Deplacement();
		
		deplacer.executer(heros, carte, "1-2");
		deplacer.executer(adversaire2, carte, "1-2");
	}
	
	/**
	 * Commencer un combat entre deux attaquant.
	 * @param attaquant Celui qui initie le combat.
	 * @param defenseur Celui qui subit le combat.
	 */
	public static boolean EngagerCombat(Combattant attaquant, Combattant defenseur) {
		System.out.println(String.format("%s VS %s", attaquant, defenseur));
		
		Action frappe = new Frapper();
		
		while(attaquant.enVie() && defenseur.enVie()) {
			frappe.executer(attaquant, defenseur);
			
			if (defenseur.enVie()) {
				frappe.executer(defenseur, attaquant);
			}
		}
		
		// Si à la fin du combat, l'attaquant est en vie, il a réussi le combat.
		return attaquant.enVie();
	}
	
	/**
	 * Afficher l'écran de Game Over.
	 */
	public static void GameOver() {
		System.out.println("VOUS ETES MORT !!!");
		System.exit(0);
	}
}

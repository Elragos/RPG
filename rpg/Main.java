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
		Ennemi adversaire = new Ennemi(20);
		
		System.out.println(heros);
		System.out.println("VS");
		System.out.println(adversaire);
		
		Action frappe = new Frapper();
		
		while(heros.enVie() && adversaire.enVie()) {
			frappe.executer(heros, adversaire);
			
			if (adversaire.enVie()) {
				frappe.executer(adversaire, heros);
			}
		}
		
		Carte carte = new Carte(10, 10);
		carte.genererCarte();
		carte.afficher();
	}
	
	public static void GameOver() {
		System.out.println("VOUS ÊTES MORT !!!");
	}
}

package rpg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import rpg.actions.*;
import rpg.carte.Carte;
import rpg.carte.Case;
import rpg.carte.Coordonnees;
import rpg.personnages.*;

/**
 * Classe de démarrage du programme.
 * @author marechal
 */
public class Main {
	/**
	 * Lecteur de saisie clavier.
	 */
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Flag pour indiquer une fin de partie.
	 */
	private static boolean partieTerminee = false;
	
	/**
	 * Destination finale du jeu, assurant la victoire
	 */
	private static Case destinationFinale;
	
	/**
	 * Lancer le programme.
	 * @param args Paramètres, non utilisé pour l'instant.
	 */
	public static void main(String[] args) {
		// Créer le héros
		Heros heros = new Heros(50, 2, 0);

		// On génère la carte
		int hauteur = 20;
		int largeur = 20;
		int nbEnnemis = 10;
		int nbGuerisseurs = 10;
		
		Carte carte = new Carte(hauteur, largeur, nbEnnemis, nbGuerisseurs);
		// On définit la destination finale
		destinationFinale = carte.getCase(new Coordonnees(hauteur - 1, largeur - 1).toString());
		
		// On positionne le héros en haut à gauche de la carte
		heros.setPosition(carte.getCase("0-0"));
		
		carte.afficher();
		
		// Tant que la partie n'est pas terminée
		while(!partieTerminee) {
			DemanderAction(heros, carte);
		}
		
		// Si le héros est en vie, c'est une victoire
		if (heros.enVie()) {
			System.out.println("Félicitations, vous avez réussi !!!");
		}
		// Sinon, c'est une défaite
		else {
			System.out.println("Malheureusement, vous avez perdu !!!");
		}
		// Fin du programme
		System.out.println("Appuyer sur Entrée pour terminer la partie !");
		sc.nextLine();
	    
		System.out.println("Bye");
	}
	
	/**
	 * Demander l'action à effectuer.
	 * @param heros Heros qui doit agir..
	 * @param carte Carte sur laquelle il évolue.
	 */
	public static void DemanderAction(Heros heros, Carte carte) {
		// Liste des actions du héros
		List<String> actions = Arrays.asList(
			"Se déplacer",
			"Ramasser",
			"Ramasser Or",
			"Utiliser"
		);
		// En fonction du choix du héros, faire l'action correspondante
		switch(MenuSelection(actions, "Que voulez-vous faire ?")) {
			case "Se déplacer":
				GererDeplacement(heros, carte);
				break;
			case "Ramasser":
				Action ramasser = new Ramasser();
				ramasser.executer(heros.getSac(), heros.getPosition());
				break;
			case "Ramasser Or":
				Action ramasserOr = new RamasserOr();
				ramasserOr.executer(heros, heros.getPosition());
				break;
			case "Utiliser":
				Action utiliser = new Utiliser();
				utiliser.executer(heros, heros.getSac());
				break;
		}
	}
	
	/**
	 * Gérer le déplacement du héros.
	 * @param heros Heros à déplacer.
	 * @param carte Carte sur laquelle il évolue.
	 */
	public static void GererDeplacement(Heros heros, Carte carte) {
		// On affiche la carte en fonction du héros
		carte.afficher(heros);
		
		// On affiche les stats actuels du héros
		heros.afficherStats();
		
		// On récupère la liste des possibilités de déplacement du héros
		ArrayList<Case> possibilitesDeplacement = Deplacement.deplacementsPossibles(carte, heros);
				
		// On demande la sélection de la case
		Case destination = MenuSelection(possibilitesDeplacement, "Choisissez la case où vous déplacer :");
		
		// On déplace le héros sur la case
		Action deplacer = new Deplacement();
		deplacer.executer(heros, carte, destination.getCoordonnees());
		
		if (heros.getPosition() == destinationFinale) {
			partieTerminee = true;
		}
	}
	
	/**
	 * Commencer un combat entre deux attaquant.
	 * @param attaquant Celui qui initie le combat.
	 * @param defenseur Celui qui subit le combat.
	 */
	public static void EngagerCombat(Combattant attaquant, Combattant defenseur) {
		System.out.println(String.format("%s VS %s", attaquant, defenseur));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		// On détermine aléatoirement le premier qui frappe
		Combattant premier, second;
		
		int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
		if (randomInt == 0) {
			premier = attaquant;
			second = defenseur;
		}
		else {
			premier = defenseur;
			second = attaquant;
		}
		
		System.out.println(String.format("%s frappe en premier", premier));
		
		Action frappe = new Frapper();
		
		// Tant que l'un des deux est en vie
		while(premier.enVie() && second.enVie()) {
			// Le premier combattant frappe
			frappe.executer(premier, second);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
			// S'il est en vie, le second frappe
			if (second.enVie()) {
				frappe.executer(second, premier);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Résoudre la rencontre avec un guérisseur.
	 * @param heros Le héros.
	 * @param guerisseur Le guérisseur.
	 */
	public static void RencontreGuerisseur(Heros heros, Guerisseur guerisseur) {
		// Demander si l'utilisateur veut se soigner
		String message = String.format("Vous rencontrez un guérisseur. Il vous demande %s PO pour vous soigner. Accepter ?", guerisseur.getCout());

		if (MenuOuiNon(message)) {
			// S'il a assez de PO
			if (heros.getBourse() >= guerisseur.getCout()) {
				// On lui retire le montant demandé par le guérisseur
				heros.modifierBourse(guerisseur.getCout());
				// Le guérisseur tente de le guérir
				guerisseur.ensorceler(heros);
			}
			// Sinon
			else {
				// Reviens plus tard, gamin
				System.out.println("Vous n'avez pas assez d'or. Revenez plus tard.");
			}
		}		
	}
	
	/**
	 * Déclarer un game over.
	 */
	public static void GameOver() {
		// La partie est terminée
		partieTerminee = true;
	}
	
	/**
	 * Sélectionner un item parmi une liste de choix.
	 * @param listeChoix La liste de choix.
	 * @return L'item choisi.
	 */
	public static <T> T MenuSelection(List<T> listeChoix, String messageSelection) {
		// On demande la sélection d'item
		T choix = null;
		
		// Tant qu'un item n'est pas sélectionné
		while(choix == null) {
			// On affiche le message de sélection
			System.out.println(messageSelection);
			// On affiche chaque option possible
			for(int i = 0; i< listeChoix.size(); i++){
				System.out.printf("%s => %s", i +1, listeChoix.get(i));
				System.out.println();
			}
			// On demande le choix de l'utilisateur
			System.out.println("Votre choix ?");
			int indexChoix = sc.nextInt() - 1;
			//On vide la ligne avant d'en lire une autre
		    sc.nextLine();
			
			// Si le choix est valide, on assigne
			if (indexChoix < listeChoix.size() && indexChoix >= 0) {
				choix = listeChoix.get(indexChoix);
			}
			// Sinon on indique une erreur
			else {
				System.out.println("Saisie invalide !!!");
			}
		}
		
		return choix;
	}
	
	/**
	 * Raccourci pour un menu oui / non.
	 * @param messageSelection Message pour la saisie.
	 * @return <code>true</code> pour oui, <code>false</code> pour non.
	 */
	public static boolean MenuOuiNon(String messageSelection) {
		String[] possibilites = {"Oui", "Non"};
		
		String choix = MenuSelection(Arrays.asList(possibilites), messageSelection);
		
		return choix.equals("Oui");
	}
}
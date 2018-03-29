package rpg.carte;

/**
 * Classe représentant les coordonnées sur la carte.
 * @author marechal
 *
 */
public class Coordonnees {
	/**
	 * Latitude.
	 */
	public int latitude;
	/**
	 * Longitude.
	 */
	public int longitude;
	
	/**
	 * Constructeur avec latitude et longitude par défaut (0,0).
	 */
	public Coordonnees() {
		this(0, 0);
	}
	
	/**
	 * Constructeur avec latitude et longitude non définies.
	 * @param latitude
	 * @param longitude
	 */
	public Coordonnees(int latitude, int longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Parser un String pour générer un objet coordonnées
	 * @param source La chaîne à parser.
	 * @return Les coordonnées extraites, ou null si échec.
	 */
	public static Coordonnees recupererDepuisString(String source) {		
		try {
			// Si pas de string, pas de coordonnées
			if (source == null) {
				return null;
			}
			
			// On coupe la chaîne selon les -
			String[] chaineCoupee = source.split("-");
			
			// Si en coupant on a pas 2 morceaux, pas de coordonnées
			if (chaineCoupee.length != 2) {
				return null;
			}
			
			// On a trouvé des coordonnées, on peut instancier l'objet
			return new Coordonnees(
				Integer.parseInt(chaineCoupee[0]),
				Integer.parseInt(chaineCoupee[1])
			);

		}
		// Echec d'extraction des nombres de la chaîne, pas de coordonnées
		catch(NumberFormatException ex) {
			return null;
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s-%s", this.latitude, this.longitude);
	}
}

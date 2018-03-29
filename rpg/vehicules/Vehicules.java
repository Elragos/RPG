package rpg.vehicules;

import rpg.carte.Case;
import rpg.carte.TypeTerrain;

public abstract class Vehicules {

	private int carburant = 3;
	private TypeVehicule typeV;
	
	public Vehicules() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCarburant() {
		return carburant;
	}
	
	public void setCarburant(int carburant) {
		this.carburant = carburant;
	}
	
	public TypeVehicule getTypeV() {
		return typeV;
	}
	
	public void setTypeV(TypeVehicule typeV) {
		this.typeV = typeV;
	}
	
	public void terrainAdequa(Case c) {
		if (c.getType() == TypeTerrain.Foret) {
			setTypeV(TypeVehicule.Moto);
		}
		
		else if (c.getType() == TypeTerrain.Ocean) {
			setTypeV(TypeVehicule.Bateau);
		}
		
		else if (c.getType() == TypeTerrain.Montagne) {
			setTypeV(TypeVehicule.Avion);
		}
		
		else if (c.getType() == TypeTerrain.Plaine) {
			setTypeV(TypeVehicule.Voiture);
		}
	}
}


package application;

import java.util.ArrayList;

public class Sommet {
	public String nom;
	public int ligne;
	public int distanceànous = Integer.MAX_VALUE;
	public boolean visité;
	public ArrayList<Arrête> arrêtes = new ArrayList<Arrête>();
	public int prédécesseur;
	public float x;
	public float y;

	public int getdistanceàlasource() {
		return distanceànous;
	}

	public void setdistanceàlasource(int d) {
		this.distanceànous = d;
	}

	public boolean estVisité() {
		return this.visité;
	}

	public void setvisité(boolean v) {
		this.visité = v;
	}

	public ArrayList<Arrête> getarrêtes() {
		return this.arrêtes;
	}

	public void setarrêtes(ArrayList<Arrête> t) {
		this.arrêtes = t;
	}

	public int getprédécesseur(){
		return prédécesseur;
	}

	public void setprédécesseur(int p){
		this.prédécesseur = p;
	}

}

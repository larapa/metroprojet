package application;

import java.util.ArrayList;

public class Sommet {
	public String nom;
	public int ligne;
	public int distance�nous = Integer.MAX_VALUE;
	public boolean visit�;
	public ArrayList<Arr�te> arr�tes = new ArrayList<Arr�te>();
	public int pr�d�cesseur;

	public int getdistance�lasource() {
		return distance�nous;
	}

	public void setdistance�lasource(int d) {
		this.distance�nous = d;
	}

	public boolean estVisit�() {
		return this.visit�;
	}

	public void setvisit�(boolean v) {
		this.visit� = v;
	}

	public ArrayList<Arr�te> getarr�tes() {
		return this.arr�tes;
	}

	public void setarr�tes(ArrayList<Arr�te> t) {
		this.arr�tes = t;
	}

	public int getpr�d�cesseur(){
		return pr�d�cesseur;
	}

	public void setpr�d�cesseur(int p){
		this.pr�d�cesseur = p;
	}

}

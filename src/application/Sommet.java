package application;

import java.util.ArrayList;

public class Sommet {
	public String nom;
	public int ligne;
	public int distance¨¤nous = Integer.MAX_VALUE;
	public boolean visit¨¦;
	public ArrayList<Arr¨ºte> arr¨ºtes = new ArrayList<Arr¨ºte>();
	public int pr¨¦d¨¦cesseur;
	public float x;
	public float y;

	public int getdistance¨¤lasource() {
		return distance¨¤nous;
	}

	public void setdistance¨¤lasource(int d) {
		this.distance¨¤nous = d;
	}

	public boolean estVisit¨¦() {
		return this.visit¨¦;
	}

	public void setvisit¨¦(boolean v) {
		this.visit¨¦ = v;
	}

	public ArrayList<Arr¨ºte> getarr¨ºtes() {
		return this.arr¨ºtes;
	}

	public void setarr¨ºtes(ArrayList<Arr¨ºte> t) {
		this.arr¨ºtes = t;
	}

	public int getpr¨¦d¨¦cesseur(){
		return pr¨¦d¨¦cesseur;
	}

	public void setpr¨¦d¨¦cesseur(int p){
		this.pr¨¦d¨¦cesseur = p;
	}

}

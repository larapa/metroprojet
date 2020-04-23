package application;
import java.util.ArrayList;
public class Sommet {
	public String nom;
	public int ligne;
	private int distance�nous = Integer.MAX_VALUE;
	private boolean visit�;
	private ArrayList<Arr�te> arr�tes = new ArrayList<Arr�te>();
	private int predecessor;

	public int getDistance�laSource() {
		return distance�nous;
	}

	public void setDistanceFromSource(int d) {
		this.distance�nous = d;
	}

	public boolean estVisit�() {
		return this.visit�;
	}

	public void setVisit�(boolean v) {
		this.visit� = v;
	}

	public ArrayList<Arr�te> getArr�tes() {
		return this.arr�tes;
	}

	public void setArr�tes(ArrayList<Arr�te> t) {
		this.arr�tes = t;
	}
	
	public int getPredecessor(){
		return predecessor;
	}
	
	public void setPredecessor(int predecessor){
		this.predecessor = predecessor;
	}
}

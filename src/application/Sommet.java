package application;
import java.util.ArrayList;
public class Sommet {
	  public String nom;
	  public int ligne;
	  private int distanceànous = Integer.MAX_VALUE;
	  private boolean visité;
	  private ArrayList<Arrête> arrêtes = new ArrayList<Arrête>(); 
	  private int predecessor;
	 
	  public int getDistanceàlaSource() {
	    return distanceànous;
	  }
	 
	  public void setDistanceFromSource(int d) {
	    this.distanceànous = d;
	  }
	 
	  public boolean estVisité() {
	    return this.visité;
	  }
	 
	  public void setVisité(boolean v) {
	    this.visité = v;
	  }
	 
	  public ArrayList<Arrête> getArrêtes() {
	    return this.arrêtes;
	  }
	 
	  public void setArrêtes(ArrayList<Arrête> t) {
	    this.arrêtes = t;
	  }
	  public int getPredecessor(){
		    return predecessor;
		  }
      public void setPredecessor(int predecessor){
		    this.predecessor = predecessor;
		  }
	}


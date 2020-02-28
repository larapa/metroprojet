package projet;

import java.util.ArrayList;

//now we must create graph object and implement dijkstra algorithm
public class Graphe {

public Sommet[] sommets;
public int nombreSommets;
public Arrête[] arrêtes;
private int nombreArrêtes;
private int sat;
private int eat;

public ArrayList<Sommet> path = new ArrayList<Sommet>();
public Graphe(Arrête[] t) {
 this.arrêtes = t;
 this.nombreSommets = calculnombreSommets(t);
 this.sommets = new Sommet[this.nombreSommets];

 for (int n = 0; n < this.nombreSommets; n++) {
   this.sommets[n] = new Sommet();
 }

 // add all the edges to the nodes, each edge added to two nodes (to and from)
 this.nombreArrêtes = this.arrêtes.length;

 for (int m = 0; m < this.nombreArrêtes; m++) {
   this.sommets[arrêtes[m].getorigine()].getArrêtes().add(arrêtes[m]);
   this.sommets[arrêtes[m].getdestination()].getArrêtes().add(arrêtes[m]);
 }

}

private int calculnombreSommets(Arrête[] a) {
 int nombreSommets = 0;

 for (Arrête e : a) {
   if (e.getdestination() > nombreSommets)
	   nombreSommets = e.getdestination();
   if (e.getorigine() > nombreSommets)
	   nombreSommets = e.getorigine();
 }

 nombreSommets++;

 return nombreSommets;
}
public int StringtoInt(String a) {
	for (int i=0; i<this.sommets.length;i++) {
		if (this.sommets[i].nom==a) {
			return i;
		}
	}
	return 1;
}

public void calculateShortestDistances(String a, String b,int k,int l) {
	  int startAt=this.StringtoInt(a)+k;
	  int endAt=this.StringtoInt(b)+ l;
	  this.sat = startAt;
	  this.eat = endAt;
	  this.sommets[startAt].setDistanceFromSource(0);
	  int nextNode = startAt;

 // visit every node
 for (int i = 0; i < this.sommets.length; i++) {
   // loop around the edges of current node
   ArrayList<Arrête> currentSommetArcs = this.sommets[nextNode].getArrêtes();

   for (int joinedArc = 0; joinedArc < currentSommetArcs.size(); joinedArc++) {
     int neighbourIndex = currentSommetArcs.get(joinedArc).getnumerovoisin(nextNode);
     
     // only if not visited
     if (!this.sommets[neighbourIndex].estVisité()) {
    	
       int tentative = this.sommets[nextNode].getDistanceàlaSource() + currentSommetArcs.get(joinedArc).getpoids();

       if (tentative < sommets[neighbourIndex].getDistanceàlaSource()) {
           sommets[neighbourIndex].setDistanceFromSource(tentative);
           sommets[neighbourIndex].setPredecessor(nextNode);
         }
     }
     
   }
  
   // all neighbours checked so node visited
   sommets[nextNode].setVisité(true);
   
   // next node must be with shortest distance
   nextNode = getSommetShortestDistanced();
   

}
}

// now we're going to implement this method in next part !
private int getSommetShortestDistanced() {
 int storedNodeIndex = 0;
 int storedDist = Integer.MAX_VALUE;

 for (int i = 0; i < this.sommets.length; i++) {
   int currentDist = this.sommets[i].getDistanceàlaSource();

   if (!this.sommets[i].estVisité() && currentDist < storedDist) {
     storedDist = currentDist;
     storedNodeIndex = i;
   }
 }

 return storedNodeIndex;
}

// display result
public void printResult(String dep,String arr,int a) {



   String output = ("Ce trajet entre " + dep + "et " + arr + "dure " + (sommets[StringtoInt(arr)+a].getDistanceàlaSource()) + " secondes.");


 System.out.println(output);
}

public Sommet[] getSommets() {
 return sommets;
}

public int getnombreSommets() {
 return nombreSommets;
}

public Arrête[] getArrête() {
 return this.arrêtes;
}

public int getnombreArrêtes() {
 return nombreArrêtes;
}
public void calculatePath(){
    int nodeNow = eat;

    while(nodeNow != sat){
    	int a=sommets[nodeNow].getPredecessor();
    	
        path.add(sommets[a]);
        nodeNow = sommets[nodeNow].getPredecessor();

    }

}

public ArrayList<Sommet> getPath(){

    return path;

}
public void donnenom(String[] t) {
	for (int i=0;i<t.length;i++) {
		this.sommets[i].nom=t[i];
	}
	
}
public int estmultiligne(String t){
	
    int a=0;
	for (int i=0; i<this.sommets.length;i++) {
		if (this.sommets[i].nom==t) {
			a=a+1;
		}
	
}
return a;
}
public void donneligne(int [] t) {
	for (int i=0;i<t.length;i++) {
		this.sommets[i].ligne=t[i];
	}
	
}
public ArrayList<String> transformation() {
	ArrayList<String> u=new ArrayList<String>();
	for (int i=0;i<this.path.size();i++) {
		u.add (this.path.get(i).nom);
	}
	return u;
}
public void retirerligne(int a) {
	
	
	for (int i=0;i<this.arrêtes.length;i++) {
		if (this.sommets[this.arrêtes[i].getdestination()].ligne==a || this.sommets[this.arrêtes[i].getorigine()].ligne==a ) {
		  this.arrêtes[i].poids=100000;
		}
		
	}
	
    	
    }
  
    
public void retirerligne2(ArrayList<Integer> a) {
	for (int i=0;i<a.size();i++ ) {
		this.retirerligne(a.get(i));
	}
}
public void retirerstation(String d) {
	
	for (int i=0;i<this.arrêtes.length;i++) {
		if (this.sommets[this.arrêtes[i].getdestination()].nom==d || this.sommets[this.arrêtes[i].getorigine()].nom==d ) {
		  this.arrêtes[i].poids=1000000;
		}
}
}
public void retirerstation2(ArrayList<String> a) {
	for (int i=0;i<a.size();i++ ) {
		this.retirerstation(a.get(i));
	}
}
}

















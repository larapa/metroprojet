package application;

import java.util.ArrayList;
import java.util.List;

//now we must create graph object and implement dijkstra algorithm
public class Graphe {

    public Sommet[] sommets;
    public int nombreSommets;
    public ArrayList<Arr�te> arr�tes;
    private int nombreArr�tes;
    private int sat;
    private int eat;

    public ArrayList<Sommet> path = new ArrayList<Sommet>();
    public Graphe(ArrayList<Arr�te> t) {
        this.arr�tes = t;
        this.nombreSommets = calculnombreSommets(t);
        this.sommets = new Sommet[this.nombreSommets];

        for (int n = 0; n < this.nombreSommets; n++) {
            this.sommets[n] = new Sommet();
        }

        // add all the edges to the nodes, each edge added to two nodes (to and from)
        this.nombreArr�tes = this.arr�tes.size();

        for (int m = 0; m < this.nombreArr�tes; m++) {
            this.sommets[arr�tes.get(m).getorigine()].getArr�tes().add(arr�tes.get(m));
            this.sommets[arr�tes.get(m).getdestination()].getArr�tes().add(arr�tes.get(m));
        }

    }

    private int calculnombreSommets(ArrayList<Arr�te> a) {
        int nombreSommets = 0;

        for (Arr�te e : a) {
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
            if (this.sommets[i].nom.equals(a)) {
                return i;
            }
        }
        throw new IllegalArgumentException("nom incorrect");
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
            ArrayList<Arr�te> currentSommetArcs = this.sommets[nextNode].getArr�tes();

            for (int joinedArc = 0; joinedArc < currentSommetArcs.size(); joinedArc++) {
                int neighbourIndex = currentSommetArcs.get(joinedArc).getnumerovoisin(nextNode);

                // only if not visited
                if (!this.sommets[neighbourIndex].estVisit�()) {

                    int tentative = this.sommets[nextNode].getDistance�laSource() + currentSommetArcs.get(joinedArc).getpoids();

                    if (tentative < sommets[neighbourIndex].getDistance�laSource()) {
                        sommets[neighbourIndex].setDistanceFromSource(tentative);
                        sommets[neighbourIndex].setPredecessor(nextNode);
                    }
                }

            }

            // all neighbours checked so node visited
            sommets[nextNode].setVisit�(true);

            // next node must be with shortest distance
            nextNode = getSommetShortestDistanced();


        }
    }

    // now we're going to implement this method in next part !
    private int getSommetShortestDistanced() {
        int storedNodeIndex = 0;
        int storedDist = Integer.MAX_VALUE;

        for (int i = 0; i < this.sommets.length; i++) {
            int currentDist = this.sommets[i].getDistance�laSource();

            if (!this.sommets[i].estVisit�() && currentDist < storedDist) {
                storedDist = currentDist;
                storedNodeIndex = i;
            }
        }

        return storedNodeIndex;
    }

    // display result
    public String printResult(String dep,String arr,int a) {



        String output = ("Ce trajet entre " + dep + "et " + arr + "dure " + (sommets[StringtoInt(arr)+a].getDistance�laSource()-25) + " secondes.");


        return output;
    }

    public Sommet[] getSommets() {
        return sommets;
    }

    public int getnombreSommets() {
        return nombreSommets;
    }

    public ArrayList<Arr�te> getArr�te() {
        return this.arr�tes;
    }

    public int getnombreArr�tes() {
        return nombreArr�tes;
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
    public void donnenom(List<String> t) {
        for (int i=0;i<t.size();i++) {
            this.sommets[i].nom=t.get(i);
        }

    }
    //On donne les coordonnes x et y
    public void donnecord(float cord[]) {
    	for (int i = 0; i < cord.length-1; i+=2) {
			this.sommets[i/2].x = cord[i];
		}
    	for (int i = 1; i < cord.length; i+=2) {
			this.sommets[(i+1)/2-1].y = cord[i];
		}
    }
    
    public int estmultiligne(String t){

        int a=0;

        for (int i=0; i<this.sommets.length;i++) {

            if (this.sommets[i].nom.equals( t)) {
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


        for (int i=0;i<this.arr�tes.size();i++) {
            if (this.sommets[this.arr�tes.get(i).getdestination()].ligne==a || this.sommets[this.arr�tes.get(i).getorigine()].ligne==a ) {
                this.arr�tes.get(i).poids=1000000;
            }

        }


    }


    public void retirerligne2(ArrayList<Integer> a) {
        for (int i=0;i<a.size();i++ ) {
            this.retirerligne(a.get(i));
        }
    }


    public void retirerstation(String d) {

        for (int i=0;i<this.arr�tes.size();i++) {

            if (this.sommets[this.arr�tes.get(i).getdestination()].nom.equals(d) || this.sommets[this.arr�tes.get(i).getorigine()].nom.contentEquals(d) ) {

                this.arr�tes.get(i).poids=1000000;
            }
        }
    }
    public void retirerstation2(ArrayList<String> a) {
        for (String s: a ) {
            this.retirerstation(s);
        }
    }
}
















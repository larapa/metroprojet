package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoinsDeChangements {
	String départ;
	String arrivée;
	ArrayList<Integer> LignesInterdites;
	ArrayList<String> StationsInterdites;
	
	public MoinsDeChangements(String départ, String arrivée,ArrayList<Integer> LignesInterdites, ArrayList<String> StationsInterdites) {
		this.départ = départ;
		this.arrivée = arrivée;
		this.LignesInterdites = LignesInterdites;
		this.StationsInterdites = StationsInterdites;
	}
	public String afficheChemin2() throws IOException {

		String texte1;
		String texte2;



		ArrayList<Arrête> arrêtes = new  ArrayList<Arrête>();
		List<String> arrêtesString = null;

		arrêtesString = Files.readAllLines(Paths.get("./src/application/Arrêtes.txt"));

		for (String s: arrêtesString) {
			String str[] = s.split(" ");
			ArrayList<Integer> b = new ArrayList<Integer>();
			for (String t: str) {
				int n = Integer.parseInt(t);
				b.add(n);
			}
			arrêtes.add(new Arrête(b.get(0), b.get(1), b.get(2)+25));
		}
        
        BufferedReader br;
        String motLu;
        ArrayList<Integer> lignesbis=new ArrayList<Integer>();
        List<String> nombis = new ArrayList<String>();
        try{
            br = new BufferedReader(new FileReader(new File("./src/application/Stations1.txt")));
            while((motLu = br.readLine()) != null)
            {
            if (motLu.charAt(motLu.length()-2) ==';'){
            int monEntier = Integer.parseInt(motLu.charAt(motLu.length()-1)+"");
            lignesbis.add(monEntier);
            nombis.add(motLu.substring(0,motLu.length()-2 ));
            
            	
            }
            else {
            	int monEntier=Integer.parseInt(motLu.charAt(motLu.length()-1)+"") + (Integer.parseInt(motLu.charAt(motLu.length()-2)+"")*10) ;
                lignesbis.add(monEntier);
                nombis.add(motLu.substring(0,motLu.length()-3 ));
            }
            
          
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        List<String>nom= nombis;
 	   
        int [] lignes= new int [lignesbis.size()];
        for (int i=0;i<lignesbis.size();i++) {
        	lignes[i]=lignesbis.get(i);
        }
    
	   

		//on crée le graphe:
		Graphe g = new Graphe(arrêtes);

		g.donnenom(nom);
		g.donneligne(lignes);
		//on prend en compte les contraintes
		g.retirerligne2(LignesInterdites);
		g.retirerstation2(StationsInterdites);
		g.transformearrête();
		int w=0;

		int z=0;
		int k=0;
		int u=100000000;

		while (w<g.estmultiligne(départ)) {

			int y=0;
			while (y<g.estmultiligne(arrivée)) {
				Graphe e = new Graphe(arrêtes);

				e.donnenom(nom);
				e.donneligne(lignes);
				e.retirerligne2(LignesInterdites);
				e.retirerstation2(StationsInterdites);
				e.transformearrête();
				e.calculateShortestDistances(départ,arrivée,w,y);
				int m=e.sommets[e.StringtoInt(arrivée) + y].getDistanceàlaSource();

				if (m<u) {
					u=m;
					k=w;
					z=y;
				}
				y=y+1;
			}
			w=w+1;
		}

		g.calculateShortestDistances(départ,arrivée,k,z);

		g.calculatePath();
		ArrayList<Sommet> path = g.getPath();
		ArrayList<String> h =g.transformation();
		Collections.reverse(h);
		Collections.reverse(path);



		if (h.contains(arrivée)) {
			System.out.println(g.sommets[g.StringtoInt(départ)].ligne);
			h.set(0, h.get(0)+"(" + g.path.get(0+k).ligne +")");
			for (int m=1; m<h.size()-1;m++) {
				if (h.get(m).equals(h.get(m+1))){
					h.set(m+1, h.get(m+1)+"(" + g.path.get(m+1).ligne +")");
				}
			}
			if ((g.sommets[g.StringtoInt(arrivée)+z].getDistanceàlaSource()-25)>3600000) {
				texte1="";
			}
			else {
			texte1=("Le trajet avec le moins de changements est " + h+" :");}

			texte2=g.printResult2(départ, arrivée,z);

		}
		else {
			h.add(arrivée);

			h.set(0, h.get(0)+"(" + g.sommets[g.StringtoInt(départ)+k].ligne +")");
			for (int m=1; m<h.size()-1;m++) {
				if (h.get(m).equals(h.get(m+1))){
					h.set(m+1, h.get(m+1)+"(" + g.path.get(m+1).ligne +")");
				}
			}
			if ((g.sommets[g.StringtoInt(arrivée)+z].getDistanceàlaSource()-25)>3600000) {
				texte1="";
			}
			else {
			texte1=("Le trajet avec le moins de changements est " + h+" :");}

			texte2= g.printResult2(départ, arrivée,z);


		}

		return texte1 + texte2;

	}
	
}

package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

import java.io.File;

import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main extends Application  {

	Button bouton1;
	Button bouton2;
	Button bouton3;
	Stage window;
	Scene scene;
	ComboBox<String> comboBox1;
	ComboBox<String> comboBox2;
	ComboBox<String> comboBox3;
	ListView<Integer> listview;
	public static String afficheChemin(String départ, String arrivée,ArrayList<Integer> LignesInterdites, ArrayList<String> StationsInterdites) throws IOException {

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
			arrêtes.add(new Arrête(b.get(0), b.get(1), b.get(2)));
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

		//calcul du plus court chemin
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
			texte1="Le plus court chemin est: " + h;

			texte2=g.printResult(départ, arrivée,z);

		}
		else {
			h.add(arrivée);

			h.set(0, h.get(0)+"(" + g.sommets[g.StringtoInt(départ)+k].ligne +")");
			for (int m=1; m<h.size()-1;m++) {
				if (h.get(m).equals(h.get(m+1))){
					h.set(m+1, h.get(m+1)+"(" + g.path.get(m+1).ligne +")");
				}
			}
			texte1=("Le plus court chemin est " + h);

			texte2=g.printResult(départ, arrivée,z);


		}

		return texte1 + " : " + texte2;

	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Menu itinéraire métro");
        BufferedReader br;
        String préc="";
        String motLu;
        ArrayList<Integer> lignesbis=new ArrayList<Integer>();
        List<String> nombis = new ArrayList<String>();
        try{
            br = new BufferedReader(new FileReader(new File("./src/application/Stations1.txt")));
            while((motLu = br.readLine()) != null)
            {
            
            if (motLu.charAt(motLu.length()-2) ==';'){
            if (préc.equals(motLu.substring(0,motLu.length()-2 ))){
            	
            }
            else {
            int monEntier = Integer.parseInt(motLu.charAt(motLu.length()-1)+"");
            lignesbis.add(monEntier);
            nombis.add(motLu.substring(0,motLu.length()-2 ));
            préc=motLu.substring(0,motLu.length()-2 );
            }	
            }
            else {
            	if (préc.equals(motLu.substring(0,motLu.length()-3 ))){
	            	
	            }
            	else {
            	int monEntier=Integer.parseInt(motLu.charAt(motLu.length()-1)+"") + (Integer.parseInt(motLu.charAt(motLu.length()-2)+"")*10) ;
                lignesbis.add(monEntier);
                nombis.add(motLu.substring(0,motLu.length()-3 ));
                préc=motLu.substring(0,motLu.length()-3 );
            	}
            }
            
          
            }
            br.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }


		comboBox1 = new ComboBox<>();
		comboBox1.getItems().addAll(nombis);


		comboBox1.setPromptText("Station de départ");
		comboBox1.setEditable(true);



		comboBox2 = new ComboBox<>();
		comboBox2.getItems().addAll(nombis);


		comboBox2.setPromptText("Station d'arrivée");
		comboBox2.setEditable(true);



		comboBox3 = new ComboBox<>();
		comboBox3.getItems().addAll(nombis);


		comboBox3.setPromptText("Stations à interdire");
		comboBox3.setEditable(true);





		listview = new ListView<>();
		listview.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);
		listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);



		ArrayList<String> stationsinterdites = new ArrayList<String>();


		//afficheChemin(comboBox1.getValue(), comboBox2.getValue(), new int[] {9,8}, new String[] {})




		bouton2 = new Button();
		bouton2.setText("Ajouter");
		bouton2.setOnAction(e ->  stationsinterdites.add(comboBox3.getValue()) );





		bouton3 = new Button();
		bouton3.setText("Supprimer");
		bouton3.setOnAction(e ->  stationsinterdites.remove(comboBox3.getValue()) );




		bouton1 = new Button();
		bouton1.setText("Calcul itinéraire");

		bouton1.setOnAction(e ->  {
			try {
				System.out.println(afficheChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites ));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});




		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(comboBox1,comboBox2,listview,comboBox3,bouton2, bouton3, bouton1);

		scene = new Scene(layout, 600, 500);
		window.setScene(scene);
		window.show();
	}

	ArrayList<Integer> tabint() {

		ObservableList<Integer> l;
		l = listview.getSelectionModel().getSelectedItems();
		ArrayList<Integer> tab = new ArrayList<Integer>(l); // ObservableList<String> --> Arraylist<String>

		return tab;

	}




	public static void main(String[] args) {
		launch(args);







	}


}

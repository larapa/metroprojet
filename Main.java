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
	public static String afficheChemin(String d�part, String arriv�e,ArrayList<Integer> LignesInterdites, ArrayList<String> StationsInterdites) throws IOException {
		
		String texte1;
		String texte2;

		
		 
		 ArrayList<Arr�te> arr�tes = new  ArrayList<Arr�te>();
		 List<String> arr�tesString = Files.readAllLines(Paths.get("C:/fichierstexte/Arr�tes.txt"));
		 for (String s: arr�tesString) {
			 String str[] = s.split(" ");
			 ArrayList<Integer> b = new ArrayList<Integer>();
				for (String t: str) {
					int n = Integer.parseInt(t);
					b.add(n);
				}
				arr�tes.add(new Arr�te(b.get(0), b.get(1), b.get(2)));
		 }
		 
	    List<String>nom= Files.readAllLines(Paths.get("C:/fichierstexte/Stations1.txt"));
	   
	    int [] lignes = {12,2,9,4,3,2,1,11,3,12,7,10,2,2,4,13,1,5,8,6,11,2,14,6,14,9,6,2,5,5,6,16,8,9,16,8,10,10,3,13,5,16,9,1,7,6,5,10,13,7,1,13,10,8,10,1,2,6,9,7,9,8,6,7,4,4,1,1,11,14,4,7,13,4,10,2,8,1,12,8,12,12,7,6,14,2,2,7,9,8,8,8,16,6,8,4,6,6,6,10,13,6,1,3,9,8,12,8,7,1,9,8,13,13,3,3,15,10,5,1,14,4,5,7,4,5,13,1,6,11,1,13,3,9,5,1,11,13,8,9,5,9,2,5,16,10,11,12,10,7,6,2,7,13,10,6,8,9,8,12,5,7,7,8,7,4,1,8,13,7,16,3,8,1,10,12,14,8,12,7,13,9,13,11,7,8,8,13,13,3,9,12,4,9,12,10,10,9,10,9,8,10,13,9,2,8,12,13,4,6,4,2,1,2,6,9,6,12,12,5,9,10,4,3,7,8,5,1,7,3,6,12,6,15,13,2,6,7,12,2,8,7,5,6,7,13,2,11,16,13,7,3,1,9,7,7,2,8,1,10,7,7,4,3,3,8,7,13,4,9,5,9,13,13,12,1,12,7,11,15,16,7,14,11,2,3,3,6,5,3,11,9,4,6,12,1,8,5,8,9,7,9,2,8,9,3,9,9,12,3,4,11,3,5,8,9,9,9,13,13,15,13,12,4,6,12,13,3,1,5,4,1,9,4,4,8,3,4,12,2,5,7,4,8,9,7,10,12,6,10,3,2,7,12,6,9,1,11,10,13,12,4,2,7,7,7,2,3,12,9,3,8,8,10,5,4};
	   
	   
  
	    		
	    //on cr�e le graphe:
       Graphe g = new Graphe(arr�tes);
	    
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
	    while (w<g.estmultiligne(d�part)) {
	    	int y=0;
	    	while (y<g.estmultiligne(arriv�e)) {
	    	Graphe e = new Graphe(arr�tes);
		    
		    e.donnenom(nom);
		    e.donneligne(lignes);
		    e.retirerligne2(LignesInterdites);
		    e.retirerstation2(StationsInterdites);
	    	e.calculateShortestDistances(d�part,arriv�e,w,y);
	    	int m=e.sommets[e.StringtoInt(arriv�e) + y].getDistance�laSource();
	    	
	    	if (m<u) {
	    		u=m;
	    		k=w;
	    		z=y;
	    	}
	    	y=y+1;
	    }
	    w=w+1;
	    }
	    
	    g.calculateShortestDistances(d�part,arriv�e,k,z);
	 
	    g.calculatePath();

	    
      
	    ArrayList<Sommet> path = g.getPath();
	    ArrayList<String> h =g.transformation();
	    Collections.reverse(h);
	    Collections.reverse(path);
	    
	   
	   
       if (h.contains(arriv�e)) {
       System.out.println(g.sommets[g.StringtoInt(d�part)].ligne);
       h.set(0, h.get(0)+"(" + g.path.get(0+k).ligne +")");
       for (int m=1; m<h.size()-1;m++) {
       	if (h.get(m)==h.get(m+1)){
       		 h.set(m+1, h.get(m+1)+"(" + g.path.get(m+1).ligne +")");
       	}
       }
       texte1="Le plus court chemin est: " + h;
	  
	    texte2=g.printResult(d�part, arriv�e,z);
	  
	  }
       else {
       	h.add(arriv�e);
       	
           h.set(0, h.get(0)+"(" + g.sommets[g.StringtoInt(d�part)+k].ligne +")");
           for (int m=1; m<h.size()-1;m++) {
           	if (h.get(m)==h.get(m+1)){
           		 h.set(m+1, h.get(m+1)+"(" + g.path.get(m+1).ligne +")");
           	}
           }
          texte1=("Le plus court chemin est " + h);
    
   	    texte2=g.printResult(d�part, arriv�e,z);
   	    
   	    
       }
       
       return texte1 + " : " + texte2;
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Menu itin�raire m�tro");
		
		
		
		 comboBox1 = new ComboBox<>();
	        comboBox1.getItems().addAll(Files.readAllLines(Paths.get("C:/fichierstexte/Stations2.txt"))
	        );
	        
	      
	        comboBox1.setPromptText("Station de d�part");
	        comboBox1.setEditable(true);
	        
	        
	        
	        comboBox2 = new ComboBox<>();
	        comboBox2.getItems().addAll(Files.readAllLines(Paths.get("C:/fichierstexte/Stations2.txt"))
	        );
	        
	      
	        comboBox2.setPromptText("Station d'arriv�e"); 
	      comboBox2.setEditable(true);
	      
	      
	      
	        comboBox3 = new ComboBox<>();
	        comboBox3.getItems().addAll(Files.readAllLines(Paths.get("C:/fichierstexte/Stations2.txt"))
	        );
	        
	      
	        comboBox3.setPromptText("Stations � interdire"); 
	      comboBox3.setEditable(true);
	      
	      
	      
	      
	      
	      listview = new ListView<>();
	        listview.getItems().addAll(1,2,3,4,5,6,7,8,9);
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
				bouton1.setText("Calcul itin�raire");
				
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

package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application  {
	Label label;
	Button bouton1;
	Button bouton2;
	Button bouton3;
	Button bouton4;
	Button bouton5;
	Button bouton6;
	Button bouton7;
	Stage window;
	Scene scene;
	Scene scene2;
	ComboBox<String> comboBox1;
	ComboBox<String> comboBox2;
	ComboBox<String> comboBox3;
	ComboBox<String> comboBox4;
	ListView<String> listview;
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
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
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
		comboBox4 = new ComboBox<>();
		List<String> test = new ArrayList<String>();
		test.add("Itinéraire le plus rapide");
		test.add("Itinéraire avec le moins de changements");
		test.add("Les deux");
		comboBox4.getItems().addAll(test);
		comboBox4.setPromptText("Choix du type d'itinéraire");
		comboBox4.setEditable(true);
		listview = new ListView<>();
		listview.getItems().addAll("1","2","3","3 bis","4","5","6","7","7 bis","8","9","10","11","12","13","14");
		listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		ArrayList<String> stationsinterdites = new ArrayList<String>();
		bouton2 = new Button();
		bouton2.setText("Ajouter");
		bouton2.setOnAction(e ->  stationsinterdites.add(comboBox3.getValue()) );
		bouton3 = new Button();
		bouton3.setText("Supprimer");
		bouton3.setOnAction(e ->  stationsinterdites.remove(comboBox3.getValue()) );
		
		
		bouton4 = new Button();
		bouton4.setText("Nouveau itinéraire");
		bouton4.setOnAction(e -> window.setScene(scene)   );
		
		
		bouton5 = new Button();
		bouton5.setText("Afficher le plus court chemin");
		MyEventHandler afficher = new MyEventHandler();
		bouton5.setOnAction(afficher);
		
		bouton6 = new Button();
		bouton6.setText("Afficher l'itinéraire avec le moins de changement");
		MyEventHandler2 afficher2 = new MyEventHandler2();
		bouton6.setOnAction(afficher2);
		
		
		label = new Label();
		label.setWrapText(true);
		
		//
		bouton1 = new Button();
		bouton1.setText("Calcul itinéraire");
		
		
		
		bouton1.setOnAction(e ->   {
			try {
				if(comboBox4.getValue()==null) {
					PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,true);//important ici
					PlusCourtChemin CC = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,false);//important ici
					label.setText(C.afficheChemin() + "\n" +"\n" + CC.afficheChemin() );
					window.setScene(scene2);
				}
				else {
				if (comboBox4.getValue().equals("Itinéraire avec le moins de changements")) {
					
					PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,false);//important ici
					label.setText(C.afficheChemin());
					window.setScene(scene2);
				}
				else {
					if(comboBox4.getValue().equals("Itinéraire le plus rapide")) {
						PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,true);//important ici
						label.setText(C.afficheChemin()); //important ici
						window.setScene(scene2);
					}
					else {
						PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,true);//important ici
						PlusCourtChemin CC = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites,false);//important ici
						label.setText(C.afficheChemin() + "\n" +"\n" + CC.afficheChemin() );
						window.setScene(scene2);
					}
				}
				}
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(comboBox1,comboBox2,listview,comboBox3,bouton2, bouton3,comboBox4, bouton1);
		scene = new Scene(layout, 600, 500);
		
		
		VBox layout2 = new VBox(10);
		layout2.setPadding(new Insets(20, 20, 20, 20));		// 2eme scene
		layout2.getChildren().addAll(label,bouton4,bouton5,bouton6);
		scene2 = new Scene(layout2, 600, 500);
		
		
		window.setScene(scene);
		window.show();
		
		}
		ArrayList<Integer> tabint() {
			ObservableList<String> l;
			l = listview.getSelectionModel().getSelectedItems();
			ArrayList<String> tab = new ArrayList<String>(l);// ObservableList<String> --> Arraylist<String>
			ArrayList<Integer> tab2 = new ArrayList<Integer>();
			for (String s: tab) {
				if (s.equals("3 bis")) {
					tab2.add(15);
				}
				else if (s.equals("7 bis")) {
					tab2.add(16);
				}
				else {
					tab2.add(Integer.parseInt(s));
					
				}
			}
			return tab2;
		}

		public static void main(String[] args) {
			launch(args);
		}

}

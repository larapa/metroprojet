 



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



import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.io.InputStream;

import java.net.URISyntaxException;

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

	ComboBox<String> comboBox4;

	

	ListView<Integer> listview;

	

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

		comboBox4 = new ComboBox<>();

		List<String> test = new ArrayList<String>();

		test.add("Itinéraire le plus rapide");

		test.add("Itinéraire avec le moins de changements");

		test.add("Les deux");

		comboBox4.getItems().addAll(test);

		comboBox4.setPromptText("Choix du type d'itinéraire");

		comboBox4.setEditable(true);





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



		bouton1.setOnAction(e ->   {

			try {

				if (comboBox4.getValue().equals("Itinéraire avec le moins de changements")) {

				MoinsDeChangements C = new MoinsDeChangements(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites);//important ici

				System.out.println(C.afficheChemin2()); //important ici

				}

				else {

					if(comboBox4.getValue().equals("Itinéraire le plus rapide")) {

					PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites);//important ici

					System.out.println(C.afficheChemin()); //important ici

					}

					else {

						PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites);//important ici

						System.out.println(C.afficheChemin());

						MoinsDeChangements CC = new MoinsDeChangements(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites);//important ici

						System.out.println(CC.afficheChemin2()); 

						

					}

				}

			} catch (IOException e1) {

				// TODO Auto-generated catch block

				e1.printStackTrace();

			}

		});









		VBox layout = new VBox(10);

		layout.setPadding(new Insets(20, 20, 20, 20));

		layout.getChildren().addAll(comboBox1,comboBox2,listview,comboBox3,bouton2, bouton3,comboBox4, bouton1);



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

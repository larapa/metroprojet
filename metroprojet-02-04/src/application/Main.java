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

import java.io.File;
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
	ListView<Integer> listview;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Menu itinéraire métro");


		comboBox1 = new ComboBox<>();
		comboBox1.getItems().addAll(Files.readAllLines(Paths.get("./src/application/Stations2.txt"))
		);


		comboBox1.setPromptText("Station de départ");
		comboBox1.setEditable(true);



		comboBox2 = new ComboBox<>();
		comboBox2.getItems().addAll(Files.readAllLines(Paths.get("./src/application/Stations2.txt"))
		);


		comboBox2.setPromptText("Station d'arrivée");
		comboBox2.setEditable(true);



		comboBox3 = new ComboBox<>();
		comboBox3.getItems().addAll(Files.readAllLines(Paths.get("./src/application/Stations2.txt"))
		);


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
				PlusCourtChemin C = new PlusCourtChemin(comboBox1.getValue(), comboBox2.getValue(), tabint(),stationsinterdites);
				System.out.println(C.afficheChemin());
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
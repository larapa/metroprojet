package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

 class MyEventHandler implements EventHandler<ActionEvent>{
	public void handle(ActionEvent event){
		 Stage secondStage = new Stage();
            Label label = new Label(""); 
            StackPane secondPane = new StackPane(label);
            Image image = new Image("application/abc.png");
			ImageView imageView = new ImageView();
			imageView.setImage(image);
			label.setGraphic(imageView);
            Scene secondScene = new Scene(secondPane, 1000, 1000);
            secondStage.setScene(secondScene);
            secondStage.show();
	}
}
 class MyEventHandler2 implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event){
			 Stage secondStage = new Stage();
	            Label label = new Label(""); 
	            StackPane secondPane = new StackPane(label);
	            Image image = new Image("application/abc2.png");
				ImageView imageView = new ImageView();
				imageView.setImage(image);
				label.setGraphic(imageView);
	            Scene secondScene = new Scene(secondPane, 1000, 1000);
	            secondStage.setScene(secondScene);
	            secondStage.show();
		}
 }
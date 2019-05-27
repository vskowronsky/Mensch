package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TestGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		PlayerPane playerPane = new PlayerPane();
		
		ScenePane root = new ScenePane(playerPane);
		//primaryStage.getChildren().add(stage);

		
		PlayerStage stage = new PlayerStage(root);
		stage.show();
	}
	
	public static void main (String[]args){
		launch(args);
	}

	
}

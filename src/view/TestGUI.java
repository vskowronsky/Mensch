package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		PlayerPane playerPane = new PlayerPane();
		
		ScenePane root = new ScenePane(playerPane);
		PlayerStage stage = new PlayerStage(root);
		primaryStage.getChildren().add(stage);
		primaryStage.setTitle("Mensch ärger Dich nicht!");
		primaryStage.show();
	}
	
	public static void main (String[]args){
		launch(args);
	}

	
}

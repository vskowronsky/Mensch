package view;

import javafx.application.Application;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class TestGUI extends Application {

	public void start(Stage primaryStage) throws Exception {
		PlayerPane playerPane = new PlayerPane();
		InfoPane infoPane = new InfoPane(1, new MenuBar());
		DicePane dicePane = new DicePane();
		
		ScenePane root = new ScenePane(playerPane, infoPane, dicePane);
		//primaryStage.getChildren().add(stage);

		
		PlayerStage stage = new PlayerStage(root);
		stage.show();
	}
	
	public static void main (String[]args){
		launch(args);
	}

	
}

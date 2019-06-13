package view;

import java.util.Random;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class TestGUI extends Application {
	
		
	/*
	 * private PlayerStage stage; private ScenePane scenepane; private PlayerPane
	 * boardOfPlayer; private DicePane dicepane; private InfoPane infopane;
	 */
		
	/*
	 * public TestGUI() { boardOfPlayer = new PlayerPane(); Label lb = new
	 * Label("Hallo"); Button dicebtn = new Button("Würfeln");
	 * 
	 * boardOfPlayer.setOnMouseClicked((MouseEvent me) -> { int x = (int)
	 * me.getX()/boardOfPlayer.getUnit(); int y = (int)
	 * me.getY()/boardOfPlayer.getUnit(); lb.setText("Sie haben die Position (" + x
	 * + "," + y + ")"); });
	 * 
	 * dicebtn.setOnMouseClicked((MouseEvent me) -> {
	 * 
	 * });
	 * 
	 * 
	 * 
	 * scenepane = new ScenePane(boardOfPlayer, lb, dicebtn); stage = new
	 * PlayerStage(scenepane); stage.show(); }
	 * 
	 * public int dice() { Random random = new Random(); int dice =
	 * random.nextInt(6) + 1; return dice; }
	 */
	
	
	
	public void start(Stage primaryStage) throws Exception {
		PlayerPane playerPane = new PlayerPane();
		InfoPane infoPane = new InfoPane(1, new MenuBar());
		DicePane dicePane = new DicePane(null);
		
		ScenePane root = new ScenePane(playerPane, infoPane, dicePane);
		//primaryStage.getChildren().add(stage);

		
		PlayerStage stage = new PlayerStage(root);
		stage.show();
	}
	
	public static void main (String[]args){
		launch(args);
	}

	
}

package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayerStage extends Stage {

private ScenePane pane;
	
	public PlayerStage(ScenePane pane){
		super();
		this.pane = pane;
		init();
	}
	
	
	private void init(){
		setTitle("Mensch �rger Dich nicht!");
		Scene scene = new Scene(pane,1000,1000, Color.WHITESMOKE);
//		scene.getStylesheets().add("style.css");
		setScene(scene);
	}
}


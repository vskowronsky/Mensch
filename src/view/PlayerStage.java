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
	
	//(pane,BREITE, H�HE, Farbe)
	private void init(){
		setTitle("Mensch �rger Dich nicht!");
		Scene scene = new Scene(pane,1000,900, Color.BLUE);
//		scene.getStylesheets().add("style.css");
		setScene(scene);
	}
}


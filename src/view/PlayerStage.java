package view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PlayerStage extends Stage {

private ScenePane pane;
	
	public PlayerStage(ScenePane pane){
		super();
		this.pane = pane;
		init();
	}
	
	//(pane,BREITE, HÖHE, Farbe)
	private void init(){
		setTitle("Mensch ärger Dich nicht!");
		Scene scene = new Scene(pane,1100,700, Color.DARKKHAKI);
//		scene.getStylesheets().add("style.css");
		setScene(scene);
	}
}


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
//		this.setFullScreen(true);
	}
	
	//(pane,BREITE, H�HE, Farbe)
	private void init(){
		setTitle("Mensch �rger Dich nicht!");
		Scene scene = new Scene(pane, 1100, 800, Color.DARKKHAKI);
//		scene.getStylesheets().add("style.css");
		setScene(scene);
	}
}


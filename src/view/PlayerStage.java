package view;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Klasse erstellt die Stage für das Graphical User Interface. Beim Aufrufen wird ein Titel gesetzt
 * und die Anfangsgröße des Fensters festgelegt.
 * @author Laura, Vanessa
 */
public class PlayerStage extends Stage {

private ScenePane pane;

	
	public PlayerStage(ScenePane pane){
		super();
		this.pane = pane;
		init();
	}
	
	private void init(){
		setTitle("Mensch ärger Dich nicht!");
		Scene scene = new Scene(pane, 1100, 800, Color.DARKKHAKI);
		scene.getStylesheets().add("file:src/view/stylesheet.css");
		setScene(scene);
	}
}


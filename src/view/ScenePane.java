package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
//in den Konstruktor die DicePane und InfoPane geben
	public ScenePane(PlayerPane playerPane) {
		super();
		this.playerPane = playerPane;
		init();
	}


	private void init(){
		AnchorPane center = new AnchorPane();
		center.getChildren().add(playerPane);
		AnchorPane.setTopAnchor(playerPane, 60.);
		AnchorPane.setLeftAnchor(playerPane, 80.);
		this.setCenter(center);
	}

}

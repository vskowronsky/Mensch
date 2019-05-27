package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;



public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	
//in den Konstruktor die DicePane und InfoPane geben
	public ScenePane(PlayerPane playerPane, InfoPane infoPane) {
		super();
		this.playerPane = playerPane;
		this.infoPane = infoPane;
		init();
	}


	private void init(){
		AnchorPane center = new AnchorPane();
		center.getChildren().addAll(playerPane, infoPane);
		AnchorPane.setTopAnchor(playerPane, 20.);
		AnchorPane.setLeftAnchor(playerPane, 20.);
		AnchorPane.setBottomAnchor(infoPane, 990.);
		this.setCenter(center);
	}

}

package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.MenuBar;


public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MenuBar menuBar;
	
	
	public ScenePane(PlayerPane playerPane, InfoPane infoPane, DicePane dicePane, MenuBar menuBar) {
		super();
		this.playerPane = playerPane;
		this.infoPane = infoPane;
		this.dicePane = dicePane;
		this.menuBar = menuBar;
		
		init();
	}


	private void init(){
		AnchorPane center = new AnchorPane();
		center.getChildren().add(playerPane);
		
		AnchorPane.setTopAnchor(playerPane, 20.);
		AnchorPane.setLeftAnchor(playerPane, 20.);
		this.setCenter(center);
		
		center.getChildren().add(menuBar);
		this.setTop(menuBar);
		
		VBox rightBox = new VBox();
		rightBox.getChildren().addAll(dicePane, infoPane);
		this.setRight(rightBox); 
	}
	
	public void enable(){
		
	}
	
	public void disable(){
		
	}
}

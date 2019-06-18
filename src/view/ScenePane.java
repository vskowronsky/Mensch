package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import controller.player.Player;
import controller.player.PlayerGUI;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;


public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MessagePane messagePane;
	private MenuBar menuBar;
	private PlayerGUI playerGUI;
	private VBox  test;


	public ScenePane(PlayerPane playerPane, InfoPane infoPane, DicePane dicePane, MessagePane messagePane, MenuBar menuBar, PlayerGUI playerGUI) {
		super();
		this.playerPane = playerPane;
		this.infoPane = infoPane;
		this.dicePane = dicePane;
		this.messagePane = messagePane;
		this.menuBar = menuBar;
		this.playerGUI = playerGUI;

		init();
	}


	private void init(){
		this.setTop(menuBar);
		this.setCenter(playerPane);
		this.setRight(dicePane); 
		this.setLeft(infoPane);
		this.setBottom(messagePane);

		//		test = new VBox();
		//		this.setLeft(test);
	}

	public void enable(){
		setPrefWidth((playerGUI.stageWidth-playerPane.width)*0.5);
	}


	public void disable(){

	}
}

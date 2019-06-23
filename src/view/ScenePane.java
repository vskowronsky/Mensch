package view;


import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import controller.player.PlayerGUI;
import javafx.scene.control.MenuBar;


public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MessagePane messagePane;
	private MenuBar menuBar;
	private PlayerGUI playerGUI;



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
	}

	public void enable(){
		infoPane.updateWidth();
		playerPane.updateWidth();
		dicePane.updateWidth();
//		messagePane.updateWidth();
	}
}

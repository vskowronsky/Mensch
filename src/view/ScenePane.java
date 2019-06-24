package view;

import javafx.scene.layout.BorderPane;
import controller.player.PlayerGUI;
import javafx.scene.control.MenuBar;

/**
 * Klasse erzeugt die Szene des Graphical User Interfaces.
 * @author Laura, Vanessa
 *
 */
public class ScenePane extends BorderPane{
	private PlayerPane playerPane;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MessagePane messagePane;
	private MenuBar menuBar;
	private PlayerGUI playerGUI;


	/**
	 * Konstuktor der Klasse setzt die Attributvariablen.
	 * @param playerPane Die �bergebene PlayerPane
	 * @param infoPane Die �bergebene InfoPane
	 * @param dicePane Die �bergebene DicePane
	 * @param messagePane Die �bergebene MessagePane
	 * @param menuBar Die �bergebene Men�leiste
	 * @param playerGUI Der �bergebene Spieler
	 * @author Laura
	 */
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

	/**
	 * Methode setzt die Panes bzw. die Men�leiste des Konstruktors an die richtige Stelle der Szene.
	 * @author Laura
	 */
	private void init(){
		this.setTop(menuBar);
		this.setCenter(playerPane);
		this.setRight(dicePane); 
		this.setLeft(infoPane);
		this.setBottom(messagePane);
	}

	/**
	 * Methode wird beim Ver�ndern der Fenstergr��e aus PlayerGUI getriggert und ruft die Methoden zur
	 * dynamischen Gr��enanpassung bei infoPane, playerPane und dicePane auf
	 * @author Vanessa
	 */
	public void enable(){
		infoPane.updateWidth();
		playerPane.updateWidth();
		dicePane.updateWidth();
	}
}

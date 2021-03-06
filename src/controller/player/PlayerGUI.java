package controller.player;

import java.util.Optional;
import controller.game.Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Content;
import model.Position;
import view.CircleWithPos;
import view.DicePane;
import view.InfoPane;
import view.MessagePane;
import view.PlayerPane;
import view.PlayerStage;
import view.ScenePane;

/**
 * Klasse erzeugt einen Spieler f�r das Graphical User Interface.
 */
public class PlayerGUI implements Player{

	private PlayerPane playerPane;
	private ScenePane root;
	private PlayerStage stage;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MessagePane messagePane;
	@SuppressWarnings("unused")
	private Content content;
	private Game game;
	@SuppressWarnings("unused")
	private int id;
	private boolean choosing;
	public double stageWidth;
	public double stageHeight;

	public PlayerGUI () {
		this.content = Content.FREE;
		this.game = null;
		this.id = -1;
		choosing = false;
	}

	/**
	 * Initialisiert einen Spieler mit den �bergebenen Parametern. 
	 * Desweiteren werden Elemente der GUI initialisiert.
	 * @author Vanessa
	 */
	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
		MenuBar menuBar = setMenu();
		messagePane = new MessagePane();
		playerPane = new PlayerPane(this, messagePane);
		infoPane = new InfoPane(id, this);
		dicePane = new DicePane(this, messagePane);
		
		root = new ScenePane(playerPane, infoPane, dicePane, messagePane, menuBar, this);
		stage = new PlayerStage(root);
		
		stage.show();
	
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			stageWidth = stage.getWidth();
			stageHeight = stage.getHeight();
			root.enable();
			playerPane.update(game.getBoard());
		});
		
		stageWidth = stage.getWidth();
		stageHeight = stage.getHeight();
		root.enable();
	}

	/**
	 * Aktivierung des Spielers
	 * @author Vanessa
	 */
	public void enable() {
		playerPane.update(game.getBoard());
		infoPane.setText("Machen Sie Ihren Zug.");
		
		TextInputDialog input = new TextInputDialog();
		input.setTitle("Speichern/Laden");
		input.setHeaderText(null);
		input.setContentText("Dateiname: ");
		
		infoPane.saveBtn.setOnAction((ActionEvent t) -> {
			AudioClip clickSound = new AudioClip("file:src/view/Click-Sound.wav");
			clickSound.play();
			Optional<String> result = input.showAndWait();
			System.out.println(result);
			if (result.isPresent()) game.save(result.get());});

		infoPane.loadBtn.setOnAction((ActionEvent t) -> {
			AudioClip clickSound = new AudioClip("file:src/view/Click-Sound.wav");
			clickSound.play();
			Optional<String> result = input.showAndWait();
			System.out.println(result);
			if (result.isPresent()) game.load(result.get());});	
		
		update();
	}

	/**
	 * Aufrauf der Methode update im Spiel.
	 */
	private void update() {
		game.update();

	}

	/**
	 * Methode um die Menuleiste mit ihren Unterpunkten zu initialisieren. 
	 * @return Die Men�leiste
	 * @author Laura, Vanessa
	 */
	private MenuBar setMenu() {
//		TextInputDialog input = new TextInputDialog();
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
//		Menu saveload = new Menu("Save/Load");
		Menu about = new Menu("About");
		
		MenuItem rules = new MenuItem("Rules");
		rules.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Label firstRule = new Label("1. Mensch �rgere Dich nicht wird mit 4 Spielern gespielt, wobei einige von einer KI ersetzt werden k�nnen");
				Label secondRule = new Label("2. Ziel ist es, alle Spielfiguren aus dem Haus ins eigene Zielfeld zu bringen. Hierzu m�ssen die Spielfiguren das Spielfeld umrunden.");
				Label thirdRule = new Label("3. Zun�chst einmal muss man durch das W�rfeln einer 6 die Spielfiguren aus dem Haus auf das Spielfeld bekommen.");
				Label fourthRule = new Label("4. Im Anschluss darf ein weiteres Mal gew�rfelt werden.");
				Label fifthRule = new Label("5. Die Spielfigur zieht ab diesem Moment immer so viele Felder weiter, wie Augen auf dem W�rfel zu erkennen sind.");
				Label sixthRule = new Label("6. Wenn alle Figuren im Haus oder auf der letzten Position des Zieles stehen, darf 3 mal gew�rfelt werden.");
				Label seventhRule = new Label("7. Bei einer 6 darf generell noch einmal gew�rfelt werden.");
				Label eighthRule = new Label("8. F�r den Fall, dass man durch das W�rfeln mit der eigenen Spielfigur auf ein Feld kommt, auf dem schon eine gegnerische Spielfigur steht, muss diese geschlagen werden.");
				Label ninthRule = new Label("9. Die Spielfigur des Gegners wandert zur�ck in dessen Startfeld.");
				Label tenthRule = new Label("10. Das Startfeld ist immer dann frei zu machen, wenn sich noch Figuren im Haus befinden.");
				Label eleventhRule = new Label("11. Wenn die W�rfelzahl zu hoch ist um in die Zielstra�e zu kommen, kann nicht gezogen werden.");
				Label twelfthRule = new Label("12. In der Zielstra�e darf nicht �bersprungen werden.");
				Label thirteenthRule = new Label("13. Gewonnen hat der Spieler, der seine 4 Spielfiguren als Erster in sein Zielfeld man�vriert hat.");
				Group layout = new Group();
				VBox labelBox = new VBox(15);
				labelBox.getChildren().addAll(firstRule, secondRule, thirdRule, fourthRule, fifthRule, sixthRule, 
						seventhRule, eighthRule, ninthRule, tenthRule, eleventhRule, twelfthRule, thirteenthRule);
				layout.getChildren().add(labelBox);
				Scene secondScene = new Scene(layout, 1000,500);
				Stage newWindow = new Stage();
				newWindow.setTitle("Regeln von Mensch �rgere Dich Nicht");
				newWindow.setScene(secondScene);
				newWindow.setX(stage.getX() + 200);
				newWindow.setY(stage.getY() + 100);
				newWindow.show();	
			}});
		
		MenuItem exit = new MenuItem("Exit");
		exit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.exit(0);}});

		about.getItems().addAll(rules, exit);
		menuBar.getMenus().addAll(about);
		return menuBar;
	}

	/**
	 * Spieler wird deaktiviert und das Spielfeld aktualisiert.
	 * @author Vanessa
	 */
	public void disable() {
		playerPane.update(game.getBoard());
	}

	/**
	 * Der Spieler hat gewonnen. Das Spielfeld wird aktualisiert. 
	 * Eine Nachricht sowie ein Audioclip werden an die MessagePane �bergeben.
	 * @author Vanessa
	 */
	public void win() {
		playerPane.update(game.getBoard());
		messagePane.message("Sie haben gewonnen!", new AudioClip("file:src/view/Success-Sound.wav"));
	}

	/**
	 * Der Spieler hat verloren. Das Spielfeld wird aktualisiert. 
	 * Eine Nachricht sowie ein Audioclip werden an die MessagePane �bergeben.
	 * @author Vanessa
	 */
	public void lose() {
		playerPane.update(game.getBoard());
		messagePane.message("Sie haben verloren!", new AudioClip("file:src/view/Lose-Sound.mp3"));
	}

	/**
	 * Der Spieler ist dran eine Figur auszuw�hlen.
	 * @author Vanessa
	 */
	public Position chooseMeeple(){
		choosing = true;
		return null;
	}

	/**
	 * Verarbeitung und Weiterreichung verschiedener Nachrichten, die vom Spiel geschickt wurden.
	 * Das Spielfeld wird aktualisiert.
	 * @author Vanessa
	 */
	public void message(String message) {

		System.out.println(message);
		switch (message) {
		case "Sie haben eine 1 gew�rfelt.": dicePane.diceRoll(message); break;
		case "Sie haben eine 2 gew�rfelt.": dicePane.diceRoll(message); break;
		case "Sie haben eine 3 gew�rfelt.": dicePane.diceRoll(message); break;
		case "Sie haben eine 4 gew�rfelt.": dicePane.diceRoll(message); break;
		case "Sie haben eine 5 gew�rfelt.": dicePane.diceRoll(message); break;
		case "Sie haben eine 6 gew�rfelt.": dicePane.diceRoll(message); break;
		
		case "Warten auf Spieler 1." : infoPane.setText(message); break;
		case "Warten auf Spieler 2." : infoPane.setText(message); break;
		case "Warten auf Spieler 3." : infoPane.setText(message); break;
		case "Warten auf Spieler 4." : infoPane.setText(message); break;
		
		case "Sie wurden von YELLOW geworfen.": 
			messagePane.message(message, new AudioClip("file:src/view/EnemyHasThrown.wav"));
			break;
		case "Sie wurden von GREEN geworfen.":
			messagePane.message(message, new AudioClip("file:src/view/EnemyHasThrown.wav"));
			break;
		case "Sie wurden von BLUE geworfen.": 
			messagePane.message(message, new AudioClip("file:src/view/EnemyHasThrown.wav")); 
			break;
		case "Sie wurden von RED geworfen.": 
			messagePane.message(message, new AudioClip("file:src/view/EnemyHasThrown.wav"));
			break;
		
		case "Sie haben eine Figur geworfen.": 
			messagePane.message(message, new AudioClip("file:src/view/EnemyThrown.mp3")); 
			break;
			
		case "Sie ziehen aus dem Haus." : 
			messagePane.message(message, new AudioClip("file:src/view/LeaveHouse-Sound.wav")); 
			break;
			
		default: messagePane.message(message, null); break;
		}
		playerPane.update(game.getBoard());
	}
	
	/**
	 * Eventhandler, wenn ein Kreis geklickt wurde. Ist nur m�glich, wenn chooseMeeple schon 
	 * aufgerufen wurden.
	 * @author Vanessa
	 */
	public EventHandler<MouseEvent> circleClickedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {
				choosing = false;
				AudioClip clickSound = new AudioClip("file:src/view/Click-Sound.wav");
				clickSound.play();
				if(circle.getPosition() < 40) {
					circle.setStroke(Color.BLACK);
					circle.setStrokeWidth(2);
				}else {
					circle.setStroke(null);
					circle.setStrokeWidth(2);
				}
				game.returnPosition(new Position (circle.getPosition()));
			}
			else { circle.setCursor(Cursor.WAIT);
			}
		}
	};

	/**
	 * Eventhandler, wenn die Maus einen Kreis betritt. 
	 * @author Vanessa
	 */
	public EventHandler<MouseEvent> circleEnteredEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {
				if(circle.getPosition() < 40) {
					circle.setCursor(Cursor.HAND);
					circle.setStroke(Color.MAGENTA);
					circle.setStrokeWidth(5);
				} else if (circle.getPosition() >= 40 && circle.getFill() != Color.FLORALWHITE) {
					circle.setCursor(Cursor.HAND);
					circle.setStroke(Color.MAGENTA);
					circle.setStrokeWidth(5);
				}
			} else { circle.setCursor(Cursor.WAIT);
			}
		}
	};

	/**
	 * Eventhandler, wenn die Maus einen Kreis verl�sst. 
	 * @author Vanessa
	 */
	public EventHandler<MouseEvent> circleExitedEventHandler = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {
				circle.setCursor(Cursor.DEFAULT);
				if(circle.getPosition() < 40) {
					circle.setStroke(Color.BLACK);
					circle.setStrokeWidth(2);
					
					if (circle.getPosition() == 0) {
						circle.setStrokeWidth(4);
						circle.setStroke(Color.GOLD);
					}
					if (circle.getPosition() == 10) {
						circle.setStrokeWidth(4);
						circle.setStroke(Color.MEDIUMSEAGREEN);
					}
					if (circle.getPosition() == 20)  {
						circle.setStrokeWidth(4);
						circle.setStroke(Color.ROYALBLUE);
					}
					if (circle.getPosition() == 30) {
						circle.setStrokeWidth(4);
						circle.setStroke(Color.FIREBRICK);
					}
					

				}else {
					circle.setStroke(null);
					circle.setStrokeWidth(2);
				}
			}
			else { circle.setCursor(Cursor.WAIT);
			}
		}
	};
}

package controller.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Optional;

import controller.exceptions.NoMoveException;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Board;
import model.Content;
import model.Position;
import view.CircleWithPos;
import view.DicePane;
import view.InfoPane;
import view.PlayerPane;
import view.PlayerStage;
import view.ScenePane;

public class PlayerGUI implements Player{

	private PlayerPane playerPane;
	private ScenePane root;
	private PlayerStage stage;
	private InfoPane infoPane;
	private DicePane dicePane;
	private Content content;
	private Game game;
	private int id;
	private boolean choosing;
	private int position;
	public double stageWidth;

	public PlayerGUI () {
		this.content = Content.FREE;
		this.game = null;
		this.id = -1;
		choosing = false;
		this.position = -1;
	}



	public void initialize(Content content, Game game, int id) {

		this.content = content;
		this.game = game;
		this.id = id;
		MenuBar menuBar = setMenu();

		playerPane = new PlayerPane(this);
		infoPane = new InfoPane(id);
		dicePane = new DicePane();
		root = new ScenePane(playerPane, infoPane, dicePane, menuBar, this);
		stage = new PlayerStage(root);
		stage.show();
		
		
		
		
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			stageWidth = stage.getWidth();
			root.enable();
		});

	}



	public void enable() {

		int sl = -1;
		playerPane.update(game.getBoard());
		infoPane.enable();
		do {
			System.out.println("Sie sind dran, Spieler " + id);
			System.out.println(
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 f�r Setzen, 1 f�r Speichern, 2 f�r Laden");
			
			// IMMER GLEICH UPDATE
			sl = 0;

		} while (sl < 0 || sl > 2);
		if (sl == 0) {
			update();
		}
		if (sl == 1) {
			save();
			update();
		}
		if (sl == 2) {
			load();
			update();
		}

	}
	
	// Werden sp�ter wieder gel�scht
	private void update() {
		stageWidth = stage.getWidth();
		root.enable();
		game.update();

	}
	// Werden sp�ter wieder gel�scht
	private void save() {
		System.out.println("Bitte geben Sie der zu speichernden Datei einen Namen.");
		
		game.save(enterFileName());
	}
	
	
	// Werden sp�ter wieder gel�scht
	private void load() {
		//		int name = -1;
		//		while (name != -1) {
		System.out.println("Bitte geben Sie den Namen der zu ladenden Datei ein.");
		//			if ((enterFileName()) {
		game.load(enterFileName());
		//		} else {
		//			System.out.println("Der Name der zu ladenen Datei existiert nicht. Bitte gegen Sie ihn erneut ein.");
		//			name = -1;
		//		}
		//		}
		//		
	}


	private MenuBar setMenu() {
		TextInputDialog input = new TextInputDialog();
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
		Menu saveload = new Menu("Save/Load");
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
				Label seventhRule = new Label("7. Bei einer 6 darf generell noch einmal gew�rfelt weredn.");
				Label eighthRule = new Label("8. F�r den Fall, dass man durch das W�rfeln mit der eigenen Spielfigur auf ein Feld kommt, auf dem schon eine gegnerische Spielfigur steht, muss diese geschlagen werden.");
				Label ninthRule = new Label("9. Die Spielfigur des Gegners wandert zur�ck in dessen Startfeld.");
				Label tenthRule = new Label("10. Das Startfeld ist immer dann frei zu machen, wenn sich noch Figuren im Haus befinden.");
				Label eleventhRule = new Label("11. Wenn die W�rfelzahl zu hoch ist um in die Zielstra�e zu kommen, kann nicht gezogen werden.");
				Label twelfthRule = new Label("12. In der Zielstra�e darf nicht �bersprungen werden.");
				Label thirteenthRule = new Label("13. Gewonnen hat der Spieler, der seine 4 Spielfiguren als Erster in sein Zielfeld man�vriert hat.");
				Group layout = new Group();
				VBox labelBox = new VBox(20);

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
			}
		});

		MenuItem exit = new MenuItem("Exit");
		exit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				System.exit(0);
			}});
		
		MenuItem menusave = new MenuItem("Save");
		menusave.setOnAction((ActionEvent t) -> {
			
			Optional<String> result = input.showAndWait();
					game.save(result.get());});
		MenuItem menuload = new MenuItem("Load");
		menuload.setOnAction((ActionEvent t) -> {game.load(enterFileName()); playerPane.update(game.getBoard());});		


		saveload.getItems().addAll(menusave, menuload);

		about.getItems().addAll(rules, exit);


		menuBar.getMenus().addAll(saveload,about);



		return menuBar;
	}

	private String readString() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			return eingabe;
		}catch (Exception e) {
			return null;
		}
	}

	private String enterFileName() {
		return (readString());
	}

	private int readInt() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = "";
			Integer string_to_int;
			eingabe = input.readLine();
			string_to_int = new Integer(eingabe);
			return string_to_int.intValue();
		} catch (Exception e) {
			return -1;
		}
	}

	public void disable() {
		playerPane.update(game.getBoard());
		infoPane.disable();
		root.disable();
	}

	public void win() {
		playerPane.update(game.getBoard());
		infoPane.win();
	}

	public void lose() {
		playerPane.update(game.getBoard());
		infoPane.lose();
	}

	public Position chooseMeeple(){
		choosing = true;

		return null;
	}

	public void message(String message) {
		playerPane.update(game.getBoard());
		switch (message) {
		case "Sie haben eine 1 gew�rfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 2 gew�rfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 3 gew�rfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 4 gew�rfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 5 gew�rfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 6 gew�rfelt.": dicePane.diceroll(message); break;
		default: infoPane.message(message); break;
		}

	}

	public void showRules() {
		Label firstRule = new Label("Startposition sind die 4 Felder, auf denen die Figuren am Anfang des Spiels stehen.");
		StackPane secondLayout = new StackPane();
		secondLayout.getChildren().add(firstRule);

		Scene secondScene = new Scene(secondLayout, 400,300);

		Stage newWindow = new Stage();
		newWindow.setTitle("Rules of ludo");
		newWindow.setScene(secondScene);

		newWindow.show();

	}

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
			}
			else { circle.setCursor(Cursor.WAIT);
			}
		}
	};

	public EventHandler<MouseEvent> circleExitedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {
				circle.setCursor(Cursor.DEFAULT);
				if(circle.getPosition() < 40) {
					circle.setStroke(Color.BLACK);
					circle.setStrokeWidth(2);
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

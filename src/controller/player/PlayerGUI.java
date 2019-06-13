package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.exceptions.NoMoveException;
import controller.game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
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
	//	MenuBar menuBar;

	public PlayerGUI () {
		this.content = Content.FREE;
		this.game = null;
		this.id = -1;
		choosing = false;
		this.position = -1;
	}

	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
		MenuItem menusave = new MenuItem("Speichern");
		menusave.setOnAction((ActionEvent t) -> {game.save(enterFileName());});
		MenuItem menuload = new MenuItem("Laden");
		menuload.setOnAction((ActionEvent t) -> {game.load(enterFileName()); playerPane.update(game.getBoard());});
		Menu saveload = new Menu("Speichern/Laden");
		saveload.getItems().addAll(menusave, menuload);

		//		Text text = new Text("Rules of ludo");
		//		Menu rules = new Menu("Rules", text);
		menuBar.getMenus().addAll(saveload);


		return menuBar;
	}

	public void initialize(Content content, Game game, int id) {

		this.content = content;
		this.game = game;
		this.id = id;
		MenuBar menuBar = setMenu();

		playerPane = new PlayerPane(this);
		infoPane = new InfoPane(id);
		dicePane = new DicePane();
		root = new ScenePane(playerPane, infoPane, dicePane, menuBar);
		stage = new PlayerStage(root);
		stage.show();
	}

	public void enable() {
	
		int sl = -1;
		playerPane.update(game.getBoard());
		infoPane.enable();
		do {
			System.out.println("Sie sind dran, Spieler " + id);
			System.out.println(
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 f�r Setzen, 1 f�r Speichern, 2 f�r Laden");
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
		System.out.println("Der Spielzug ist beendet.");
		playerPane.update(game.getBoard());
		infoPane.disable();
		root.disable();
	}

	public void win() {
		//		System.out.println("Spieler " + id + " hat gewonnen!");
		playerPane.update(game.getBoard());
		infoPane.win();
	}

	public void lose() {
		//		System.out.println("Spieler " + id + " hat verloren!");
		playerPane.update(game.getBoard());
		infoPane.lose();
	}

	public Position chooseMeeple() throws NoMoveException {
		choosing = true;

		return null;
	}

	public void message(String message) {
		System.out.println(message);
		
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

	public EventHandler<MouseEvent> circleClickedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {
				circle.setCursor(Cursor.HAND);
				choosing = false;
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
				} else if (circle.getPosition() > 40 && circle.getFill() != Color.FLORALWHITE) {
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

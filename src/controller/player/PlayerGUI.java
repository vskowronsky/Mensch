package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.exceptions.NoMoveException;
import controller.game.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 für Setzen, 1 für Speichern, 2 für Laden");
			
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


	// Werden später wieder gelöscht
	private void update() {
		root.enable();
		game.update();

	}
	// Werden später wieder gelöscht
	private void save() {
		System.out.println("Bitte geben Sie der zu speichernden Datei einen Namen.");
		game.save(enterFileName());
	}
	
	
	// Werden später wieder gelöscht
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
		MenuBar menuBar = new MenuBar();
		menuBar.setUseSystemMenuBar(true);
		Menu saveload = new Menu("Save/Load");
		MenuItem rules = new MenuItem("Rules");
		Menu about = new Menu("About");
		rules.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				Label firstRule = new Label("Startposition sind die 4 Felder, auf denen die Figuren am Anfang des Spiels stehen.");
				Label secondRule = new Label("Zielposition sind die 4 Felder, die erreicht werden, wenn die Spielfigur einmal um das Spielbrett gezogen ist.");
				StackPane secondLayout = new StackPane();
				VBox labelBox = new VBox(20);

				labelBox.getChildren().addAll(firstRule, secondRule);
				secondLayout.getChildren().add(labelBox);

				Scene secondScene = new Scene(secondLayout, 500,300);

				Stage newWindow = new Stage();
				newWindow.setTitle("Rules of ludo");
				newWindow.setScene(secondScene);

				newWindow.setX(stage.getX() + 200);
				newWindow.setY(stage.getY() + 100);
				newWindow.show();	
			}
		});

		MenuItem menusave = new MenuItem("Save");
		//		menusave.setOnAction((ActionEvent t) -> {game.save(enterFileName());});
		MenuItem menuload = new MenuItem("Load");
		//		menuload.setOnAction((ActionEvent t) -> {game.load(enterFileName()); playerPane.update(game.getBoard());});		


		saveload.getItems().addAll(menusave, menuload);

		about.getItems().add(rules);


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
		case "Sie haben eine 1 gewürfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 2 gewürfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 3 gewürfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 4 gewürfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 5 gewürfelt.": dicePane.diceroll(message); break;
		case "Sie haben eine 6 gewürfelt.": dicePane.diceroll(message); break;
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

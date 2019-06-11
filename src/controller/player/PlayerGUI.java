package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.exceptions.NoMoveException;
import controller.game.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
		playerPane = new PlayerPane(this);
		infoPane = new InfoPane(1, new MenuBar());
		dicePane = new DicePane();
		root = new ScenePane(playerPane, infoPane, dicePane);
		stage = new PlayerStage(root);
		stage.show();
		
	
	}

	public void enable() {
		System.out.println("NEUE RUNDE!");
		int sl = -1;
		playerPane.update(game.getBoard());
		do {
			System.out.println("Sie sind dran, Spieler " + id);
			System.out.println(
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 für Setzen, 1 für Speichern, 2 für Laden");
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
		root.disable();
	}

	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
		playerPane.update(game.getBoard());
	}

	public void lose() {
		System.out.println("Spieler " + id + " hat verloren!");
		playerPane.update(game.getBoard());
		
	}

	public Position chooseMeeple(int diceValue) throws NoMoveException {
		
		choosing = true;
	

		
		System.out.println("Sie haben eine " + diceValue + " gewürfelt.");

		
//		Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                System.out.println("Test");
//            }
//        });
//        new Thread(sleeper).start();
		 
        return (new Position(position));
		
	}
	
	

	

	public void message(String message) {
		System.out.println(message);
	}

	public EventHandler<MouseEvent> circleClickedEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {


				circle.setCursor(Cursor.HAND);

				choosing = false;
				position = circle.getPosition();
			
				

			}
			else { circle.setCursor(Cursor.WAIT);

			}
		}

	};
	
	public EventHandler<MouseEvent> circleEnteredEventHandler = new EventHandler<MouseEvent>() {

		public void handle(MouseEvent t) {
			CircleWithPos circle = (CircleWithPos) t.getSource();
			if (choosing) {


				circle.setCursor(Cursor.HAND);
				

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


			}
			else { circle.setCursor(Cursor.WAIT);

			}
		}

	};


}

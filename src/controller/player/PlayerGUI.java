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
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Board;
import model.Content;
import model.Position;
import view.CircleWithPos;
import view.DicePane;
import view.InfoPane;
import view.MessagePane;
import view.PlayerPane;
import view.PlayerStage;
import view.ScenePane;

public class PlayerGUI implements Player{

	private PlayerPane playerPane;
	private ScenePane root;
	private PlayerStage stage;
	private InfoPane infoPane;
	private DicePane dicePane;
	private MessagePane messagePane;
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
		messagePane = new MessagePane();
		root = new ScenePane(playerPane, infoPane, dicePane, messagePane, menuBar, this);
		stage = new PlayerStage(root);
		stage.show();
		
						
		stage.widthProperty().addListener((obs, oldVal, newVal) -> {
			stageWidth = stage.getWidth();
			root.enable();
		});

	}



	public void enable() {
		TextInputDialog input = new TextInputDialog();
//		int sl = -1;
		playerPane.update(game.getBoard());
		infoPane.enable();
		update();
			
			infoPane.saveBtn.setOnAction((ActionEvent t) -> {
				AudioClip clickSound = new AudioClip("file:src/view/Click-Sound.wav");
				clickSound.play();
				Optional<String> result = input.showAndWait();
						game.save(result.get());});
			
			infoPane.loadBtn.setOnAction((ActionEvent t) -> {
				AudioClip clickSound = new AudioClip("file:src/view/Click-Sound.wav");
				clickSound.play();
				Optional<String> result = input.showAndWait();
				game.load(result.get());});	
	}
	
	// Werden sp�ter wieder gel�scht
	private void update() {
		stageWidth = stage.getWidth();
		root.enable();
		game.update();

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
				Label firstRule = new Label("In the beginning of the game every player has four Meeple in a house.");
				Label secondRule = new Label("If you throw a 6 the computer set one meeple from the house to its starting position");
				Group layout = new Group();
				VBox labelBox = new VBox(20);

				labelBox.getChildren().addAll(firstRule, secondRule);
				layout.getChildren().add(labelBox);

				Scene secondScene = new Scene(layout, 500,300);

				Stage newWindow = new Stage();
				newWindow.setTitle("Rules of ludo");
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
		menuload.setOnAction((ActionEvent t) -> {
			Optional<String> result = input.showAndWait();
			game.load(result.get());});	

		saveload.getItems().addAll(menusave, menuload);

		about.getItems().addAll(rules, exit);

		menuBar.getMenus().addAll(saveload,about);

		return menuBar;
	}

	public void disable() {
		playerPane.update(game.getBoard());
		infoPane.disable();
		root.disable();
	}

	public void win() {
		playerPane.update(game.getBoard());
		messagePane.win();
	}

	public void lose() {
		playerPane.update(game.getBoard());
		messagePane.lose();
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
		default: messagePane.message(message); break;
		}
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

package view;

import controller.player.PlayerGUI;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;

import javafx.scene.effect.DropShadow;

import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Klasse zeigt den Würfel an.
 * @author Laura, Vanessa
 */
public class DicePane extends VBox {
	public double width;
	private double unit;
	private double diceWidth;
	private double diceUnit;
	private double radius;
	private PlayerGUI playerGUI;
	private MessagePane messagePane;
	private String lastmessage;
	private Rectangle mainRec;
	VBox vbox;
	Group dice;

	/**
	 * Konstruktor der Klasse. Setzt die Atttributvariablen.
	 * @param playerGUI Der übergebene Spieler
	 * @author Laura, Vanessa
	 */
	public DicePane(PlayerGUI playerGUI, MessagePane messagePane) {
		super();
//		this.setBackground(Background.EMPTY);
//		String style = "-fx-background-color: rgba(222, 221, 221, 1);";
//		this.setStyle(style);
		this.playerGUI = playerGUI;
		this.messagePane = messagePane;
		this.width = 250;
		this.unit = width / 5.;
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;
		lastmessage = "";

		init();
	}

	/**
	 * Initalisierung des Würfels noch ohne Augenzahl.
	 * @author Laura
	 */
	private void init() {

		this.getChildren().clear();
		dice = new Group();
		
		if (playerGUI.stageWidth*0.6 >= playerGUI.stageHeight - messagePane.getHeight()) {
			dice.setTranslateY(playerGUI.stageWidth*0.3-diceWidth/2- messagePane.getHeight()/2);
			dice.setTranslateX(playerGUI.stageWidth*0.1-diceWidth/2);
		} else {
		dice.setTranslateY(playerGUI.stageWidth*0.3-diceWidth/2);
		dice.setTranslateX(playerGUI.stageWidth*0.1-diceWidth/2);
		}
		
		mainRec = new Rectangle(diceWidth,diceWidth);
		

		mainRec.setHeight(diceWidth); 
		mainRec.setWidth(diceWidth);
		mainRec.setStroke(Color.BLACK);
		mainRec.setStrokeWidth(1.5);
		mainRec.setFill(Color.FLORALWHITE);
		mainRec.setArcWidth(20.);
		mainRec.setArcHeight(20.);

		dice.getChildren().add(mainRec);
		
		
		DropShadow dropShadow = new DropShadow(); 


		dropShadow.setHeight(20); 
		dropShadow.setWidth(20); 
		dropShadow.setRadius(10); 
		dropShadow.setOffsetX(10); 
		dropShadow.setOffsetY(10); 

		mainRec.setEffect(dropShadow);

		this.getChildren().add(dice);
	}

	/**
	 * Methode zum Erzeugen eines mischenden Würfels. Fünf falsche Würfel werden gezeigt bevor
	 * der richtige Würfel gezeigt wird.
	 * @param message Vom Spiel weitergereichtes Würfelergebnis
	 * @author Vanessa
	 */
	public void diceRoll(String message) {
		AudioClip sound = new AudioClip("file:src/view/Dice.mp3");
		sound.play();

		dice("Sie haben eine 3 gewürfelt.");
		FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
		trans.setFromValue(1.0);
		trans.setToValue(.20);
		trans.setCycleCount(2);
		trans.setAutoReverse(true);
		trans.play();
		trans.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				dice("Sie haben eine 5 gewürfelt.");
				FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
				trans.setFromValue(1.0);
				trans.setToValue(.20);
				trans.setCycleCount(2);
				trans.setAutoReverse(true);
				trans.play();
				trans.setOnFinished(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						dice("Sie haben eine 3 gewürfelt.");
						FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
						trans.setFromValue(1.0);
						trans.setToValue(.20);
						trans.setCycleCount(2);
						trans.setAutoReverse(true);
						trans.play();
						trans.setOnFinished(new EventHandler<ActionEvent>() {

							public void handle(ActionEvent event) {
								dice("Sie haben eine 4 gewürfelt.");
								FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
								trans.setFromValue(1.0);
								trans.setToValue(.20);
								trans.setCycleCount(2);
								trans.setAutoReverse(true);
								trans.play();
								trans.setOnFinished(new EventHandler<ActionEvent>() {

									public void handle(ActionEvent event) {
										dice("Sie haben eine 2 gewürfelt.");
										FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
										trans.setFromValue(1.0);
										trans.setToValue(.20);
										trans.setCycleCount(2);
										trans.setAutoReverse(true);
										trans.play();
										trans.setOnFinished(new EventHandler<ActionEvent>() {

											public void handle(ActionEvent event) {
												dice(message);
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});

	}

	/**
	 * Methode zeigt entsprechend der Nachricht die Würfelzahl an.
	 * @param message Vom Spiel weitergereichtes Würfelergebnis
	 * @author Laura
	 */
	public void dice(String message) {
		lastmessage = message;

		switch (message) {
		case "Sie haben eine 1 gewürfelt.": 
			Circle circle = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().add(circle);
			break;
		case "Sie haben eine 2 gewürfelt.": 
			Circle circle2 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle3 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle2, circle3);
			break;
		case "Sie haben eine 3 gewürfelt.": 
			Circle circle4 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle5 = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			Circle circle6 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle4, circle5, circle6);
			break;
		case "Sie haben eine 4 gewürfelt.":
			Circle circle7 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle8 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit/1.5, radius);
			Circle circle9 = new Circle(diceUnit/1.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle10 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle7, circle8, circle9, circle10);
			break;
		case "Sie haben eine 5 gewürfelt.":
			Circle circle11 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle12 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit/1.5, radius);
			Circle circle13 = new Circle(diceUnit/1.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle14 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle15 = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle11, circle12, circle13, circle14, circle15);
			break;
		case "Sie haben eine 6 gewürfelt.":
			Circle circle16 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle17 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit/1.5, radius);
			Circle circle18 = new Circle(diceUnit/1.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle19 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle20 = new Circle(diceUnit/1.5, diceUnit+diceUnit/2.2, radius );
			Circle circle21 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit+diceUnit/2.2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle16, circle17, circle18, circle19, circle20, circle21);
			break;
		default: break;
		}
	}

	/**
	 * Methode passt die Größe des Würfels dynamisch an die veränderbare Größe des Fensters an.
	 * @author Vanessa
	 */
	public void updateWidth() {
		this.getChildren().clear();
		this.width = playerGUI.stageWidth*0.2;
		this.unit = this.width / 5.;
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;

		this.setPrefWidth(this.width);
		init();
		dice(lastmessage);
	}
	
	Rectangle createShadowedBox(double size,
		    double shadowWidth, double shadowHeight,
		    double offsetX, double offsetY,
		    double radius)
		{
		    Rectangle r = new Rectangle(size, size);
		    r.setFill(Color.PINK);
		    r.setStroke(Color.BLACK);
		    r.setStrokeWidth(2);
		    DropShadow e = new DropShadow();
		    e.setWidth(shadowWidth);
		    e.setHeight(shadowHeight);
		    e.setOffsetX(offsetX);
		    e.setOffsetY(offsetY);
		    e.setRadius(radius);
		    r.setEffect(e);
		    return r;
		}

}
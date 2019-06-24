package view;

import controller.player.PlayerGUI;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;

import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Klasse zeigt den W�rfel an.
 * @author Laura, Vanessa
 */
public class DicePane extends VBox {
	public double width;
	private double unit;
	private double diceWidth;
	private double diceUnit;
	private double radius;
	private PlayerGUI playerGUI;
	private String lastmessage;
	private Rectangle mainRec;
	VBox vbox;
	Group dice;

	/**
	 * Konstruktor der Klasse. Setzt die Atttributvariablen.
	 * @param playerGUI Der �bergebene Spieler
	 * @author Laura, Vanessa
	 */
	public DicePane(PlayerGUI playerGUI) {
		super();
		this.setBackground(Background.EMPTY);
		String style = "-fx-background-color: rgba(222, 221, 221, 1);";
		this.setStyle(style);
		this.playerGUI = playerGUI;
		this.width = 250;
		this.unit = width / 5.;
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;
		lastmessage = "";

		init();
	}

	/**
	 * Initalisierung des W�rfels noch ohne Augenzahl.
	 * @author Laura
	 */
	private void init() {
		dice = new Group();
		mainRec = new Rectangle();

		mainRec.setHeight(diceWidth); 
		mainRec.setWidth(diceWidth);
		mainRec.setStroke(Color.BLACK);
		mainRec.setStrokeWidth(1.5);
		mainRec.setFill(Color.FLORALWHITE);
		mainRec.setArcWidth(20.);
		mainRec.setArcHeight(20.);

		dice.getChildren().add(mainRec);
		
		 
	      DropShadow dropShadow = new DropShadow(); 
	      
	      //Festlegen der Art der Unsch�rfe
	      dropShadow.setBlurType(BlurType.GAUSSIAN); 
	      
	      //Einstellen der Schattenfarbe
	      dropShadow.setColor(Color.GREY); 
	      
	      //Einstellen der Schattenh�he
	      dropShadow.setHeight(5); 
	      
	      //Einstellen der Schattenbreite
	      dropShadow.setWidth(5); 
	      
	      //Einstellen des Radius des Schattens 
	      dropShadow.setRadius(5); 
	      
	      //Einstellen der Verschiebung des Schattens
	      dropShadow.setOffsetX(3); 
	      dropShadow.setOffsetY(2); 
	      
	      //Einstellen der Ausbreitung des Schattens 
	      dropShadow.setSpread(12);  
	      
	      //Anwendung des Schatteneffekts auf den W�rfel 
	      dice.setEffect(dropShadow);

		this.setPadding(new Insets(this.width*1.2,this.width/4,0,this.width/4));
		//		this.setSpacing(30);
		this.getChildren().add(dice);
	}

	/**
	 * Methode zum Erzeugen eines mischenden W�rfels. F�nf falsche W�rfel werden gezeigt bevor
	 * der richtige W�rfel gezeigt wird.
	 * @param message Vom Spiel weitergereichtes W�rfelergebnis
	 * @author Vanessa
	 */
	public void diceRoll(String message) {
		AudioClip sound = new AudioClip("file:src/view/Dice.mp3");
		sound.play();

		dice("Sie haben eine 3 gew�rfelt.");
		FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
		trans.setFromValue(1.0);
		trans.setToValue(.20);
		trans.setCycleCount(2);
		trans.setAutoReverse(true);
		trans.play();
		trans.setOnFinished(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				dice("Sie haben eine 5 gew�rfelt.");
				FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
				trans.setFromValue(1.0);
				trans.setToValue(.20);
				trans.setCycleCount(2);
				trans.setAutoReverse(true);
				trans.play();
				trans.setOnFinished(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						dice("Sie haben eine 3 gew�rfelt.");
						FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
						trans.setFromValue(1.0);
						trans.setToValue(.20);
						trans.setCycleCount(2);
						trans.setAutoReverse(true);
						trans.play();
						trans.setOnFinished(new EventHandler<ActionEvent>() {

							public void handle(ActionEvent event) {
								dice("Sie haben eine 4 gew�rfelt.");
								FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
								trans.setFromValue(1.0);
								trans.setToValue(.20);
								trans.setCycleCount(2);
								trans.setAutoReverse(true);
								trans.play();
								trans.setOnFinished(new EventHandler<ActionEvent>() {

									public void handle(ActionEvent event) {
										dice("Sie haben eine 2 gew�rfelt.");
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
	 * Methode zeigt entsprechend der Nachricht die W�rfelzahl an.
	 * @param message Vom Spiel weitergereichtes W�rfelergebnis
	 * @author Laura
	 */
	public void dice(String message) {
		lastmessage = message;

		switch (message) {
		case "Sie haben eine 1 gew�rfelt.": 
			Circle circle = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().add(circle);
			break;
		case "Sie haben eine 2 gew�rfelt.": 
			Circle circle2 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle3 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle2, circle3);
			break;
		case "Sie haben eine 3 gew�rfelt.": 
			Circle circle4 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle5 = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			Circle circle6 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle4, circle5, circle6);
			break;
		case "Sie haben eine 4 gew�rfelt.":
			Circle circle7 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle8 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit/1.5, radius);
			Circle circle9 = new Circle(diceUnit/1.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle10 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle7, circle8, circle9, circle10);
			break;
		case "Sie haben eine 5 gew�rfelt.":
			Circle circle11 = new Circle(diceUnit/1.5, diceUnit/1.5, radius);
			Circle circle12 = new Circle(2*diceUnit+diceUnit/3.5, diceUnit/1.5, radius);
			Circle circle13 = new Circle(diceUnit/1.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle14 = new Circle(2*diceUnit+diceUnit/3.5, 2*diceUnit+diceUnit/3.5, radius);
			Circle circle15 = new Circle(diceUnit+diceUnit/2, diceUnit+diceUnit/2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle11, circle12, circle13, circle14, circle15);
			break;
		case "Sie haben eine 6 gew�rfelt.":
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
	 * Methode passt die Gr��e des W�rfels dynamisch an die ver�nderbare Gr��e des Fensters an.
	 * @author Vanessa
	 */
	public void updateWidth() {
		this.getChildren().clear();
		this.width = (playerGUI.stageWidth)*0.2;
		this.unit = this.width / 5.;
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;
		init();
		dice(lastmessage);
	}

}
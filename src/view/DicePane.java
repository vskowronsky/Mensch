package view;


import controller.player.PlayerGUI;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;

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

	public DicePane(PlayerGUI playerGUI) {
		super();
		this.playerGUI = playerGUI;
		this.width = 250;
		this.unit = width / 5.; //50
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;
		lastmessage = "";

		init();
	}

	private void init() {
		dice = new Group();
		mainRec = new Rectangle();

		mainRec.setHeight(diceWidth); 
		mainRec.setWidth(diceWidth);

		mainRec.setStroke(Color.BLACK);
		mainRec.setStrokeWidth(1.5);
		mainRec.setFill(Color.WHITE);

		mainRec.setStroke(Color.BLACK); 
		mainRec.setFill(Color.FLORALWHITE);
		mainRec.setArcWidth(20.);
		mainRec.setArcHeight(20.);


		dice.getChildren().add(mainRec);

		this.setPadding(new Insets(this.width*1.2,this.width/4,0,0 ));
		//		this.setSpacing(30);

		this.getChildren().add(dice);

	}


	public void diceRoll(String message ) {
		
		AudioClip sound = new AudioClip("file:src/view/Dice.mp3");
		sound.play();

		dice("Sie haben eine 3 gew�rfelt.");

		FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
		trans.setFromValue(1.0);
		trans.setToValue(.20);
		// Let the animation run forever
		trans.setCycleCount(2);
		// Reverse direction on alternating cycles
		trans.setAutoReverse(true);
		// Play the Animation
		trans.play();

		trans.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				dice("Sie haben eine 5 gew�rfelt.");
				FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
				trans.setFromValue(1.0);
				trans.setToValue(.20);
				// Let the animation run forever
				trans.setCycleCount(2);
				// Reverse direction on alternating cycles
				trans.setAutoReverse(true);
				// Play the Animation
				trans.play();

				trans.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						dice("Sie haben eine 3 gew�rfelt.");
						FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
						trans.setFromValue(1.0);
						trans.setToValue(.20);
						// Let the animation run forever
						trans.setCycleCount(2);
						// Reverse direction on alternating cycles
						trans.setAutoReverse(true);
						// Play the Animation
						trans.play();
						trans.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								dice("Sie haben eine 4 gew�rfelt.");
								FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
								trans.setFromValue(1.0);
								trans.setToValue(.20);
								// Let the animation run forever
								trans.setCycleCount(2);
								// Reverse direction on alternating cycles
								trans.setAutoReverse(true);
								// Play the Animation
								trans.play();

								trans.setOnFinished(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent event) {
										dice("Sie haben eine 2 gew�rfelt.");
										FadeTransition trans = new FadeTransition(Duration.seconds(0.05), dice);
										trans.setFromValue(1.0);
										trans.setToValue(.20);
										// Let the animation run forever
										trans.setCycleCount(2);
										// Reverse direction on alternating cycles
										trans.setAutoReverse(true);
										// Play the Animation
										trans.play();

										trans.setOnFinished(new EventHandler<ActionEvent>() {

											@Override
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

	public void updateWidth() {
		this.getChildren().clear();
		this.width = (playerGUI.stageWidth)*0.2;
		this.unit = this.width / 5.; //50
		this.diceWidth = 2*unit;
		this.diceUnit = diceWidth/3.;
		this.radius = diceUnit/3.;

		init();
		dice(lastmessage);
	}

}
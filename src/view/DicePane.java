package view;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DicePane extends BorderPane {
	public final int width = 250;
	private double unit = width / 5.; //50
	private double diceWidth = 2*unit;
	private double diceUnit = diceWidth/3.;
	private double radius = diceUnit/2.;

	Label label = new Label("Würfelzahl:");
	Rectangle mainRec = new Rectangle();
	VBox vbox;
	Group dice;

	public DicePane() {
		super();
		dice = new Group();
		init();
	}

	private void init() {
		vbox = new VBox(20);

		this.setPrefHeight(300);
		label.setFont(Font.font(Font.getDefault().getFamily(),30));

		mainRec.setHeight(diceWidth); 
		mainRec.setWidth(diceWidth);
<<<<<<< HEAD
		mainRec.setStroke(Color.BLACK);
		mainRec.setStrokeWidth(3);
		mainRec.setFill(Color.WHITE);
=======
		mainRec.setStroke(Color.BLACK); 
		mainRec.setFill(Color.FLORALWHITE);
		mainRec.setArcWidth(20.);
		mainRec.setArcHeight(20.);
>>>>>>> b2ecf6fa346087c890b1282aa78744d4e324fff0
		
		dice.getChildren().add(mainRec);

		vbox.setPadding(new Insets(50, 50,50, 50));
		vbox.setSpacing(30);

		vbox.getChildren().addAll(label, dice);

		this.setCenter(vbox);
	}

	public void diceroll(String message) {
		switch (message) {
		case "Sie haben eine 1 gewürfelt.": 
			Circle circle = new Circle(diceUnit+radius, diceUnit+radius, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().add(circle);
			break;
		case "Sie haben eine 2 gewürfelt.": 
			Circle circle2 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle3 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle2, circle3);
			break;
		case "Sie haben eine 3 gewürfelt.": 
			Circle circle4 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle5 = new Circle(diceUnit+radius, diceUnit+radius, radius);
			Circle circle6 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle4, circle5, circle6);
			break;
		case "Sie haben eine 4 gewürfelt.":
			Circle circle7 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle8 = new Circle(2*diceUnit+diceUnit/2., diceUnit/2., radius);
			Circle circle9 = new Circle(diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			Circle circle10 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle7, circle8, circle9, circle10);
			break;
		case "Sie haben eine 5 gewürfelt.":
			Circle circle11 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle12 = new Circle(2*diceUnit+diceUnit/2., diceUnit/2., radius);
			Circle circle13 = new Circle(diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			Circle circle14 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			Circle circle15 = new Circle(diceUnit+radius, diceUnit+radius, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle11, circle12, circle13, circle14, circle15);
			break;
		case "Sie haben eine 6 gewürfelt.":
			Circle circle16 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle17 = new Circle(2*diceUnit+diceUnit/2., diceUnit/2., radius);
			Circle circle18 = new Circle(diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			Circle circle19 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			Circle circle20 = new Circle(diceUnit/2., diceUnit+diceUnit/2., radius );
			Circle circle21 = new Circle(2*diceUnit+diceUnit/2., diceUnit+diceUnit/2, radius);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle16, circle17, circle18, circle19, circle20, circle21);
			break;
		default: break;
		}
	}

}
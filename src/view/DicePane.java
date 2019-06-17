package view;


import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class DicePane extends BorderPane {
	public final int width = 250;
	private double unit = width / 5.;
	private double diceWidth = 2*unit;
	private double diceUnit = diceWidth/3.;
	private double radius = diceUnit/2.;

	Label label = new Label("Würfelzahl:");
	Label label2 = new Label("");
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
		mainRec.setStroke(Color.BLACK); 
		mainRec.setFill(Color.WHITE);
		
		dice.getChildren().add(mainRec);

		vbox.setPadding(new Insets(50, 50,50, 50));
		vbox.setSpacing(30);

		vbox.getChildren().addAll(label, dice, label2);

		this.setCenter(vbox);
	}

	public void diceroll(String message) {
		switch (message) {
		case "Sie haben eine 1 gewürfelt.": 
			Circle circle = new Circle(diceUnit+radius, diceUnit+radius, radius);
			mainRec.setFill(Color.AQUA);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().add(circle);
			label2.setText(message);
			break;
		case "Sie haben eine 2 gewürfelt.": 
			Circle circle2 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle3 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			mainRec.setFill(Color.PINK);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle2, circle3);
			label2.setText(message);
			break;
		case "Sie haben eine 3 gewürfelt.": 
			Circle circle4 = new Circle(diceUnit/2., diceUnit/2., radius);
			Circle circle5 = new Circle(diceUnit+radius, diceUnit+radius, radius);
			Circle circle6 = new Circle(2*diceUnit+diceUnit/2., 2*diceUnit+diceUnit/2., radius);
			mainRec.setFill(Color.BLUE);
			dice.getChildren().clear();
			dice.getChildren().add(mainRec);
			dice.getChildren().addAll(circle4, circle5, circle6);
			label2.setText(message);
			break;
		case "Sie haben eine 4 gewürfelt.":
			
			mainRec.setFill(Color.BROWN);
			//			getChildren().add(rec4);
			label2.setText(message);
			break;
		case "Sie haben eine 5 gewürfelt.":
			Image image5 = new Image("file:src/view/Dice_5.png"); 
			ImageView iv5 = new ImageView();
			iv5.setImage(image5);
			label2.setText(message);
			break;
		case "Sie haben eine 6 gewürfelt.":
			Image image6 = new Image("file:src/view/Dice_6.png"); 
			ImageView iv6 = new ImageView();
			iv6.setImage(image6);
			label2.setText(message);
			break;
		default: break;
		}
	}

}
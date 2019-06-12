package view;


import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;



public class DicePane extends BorderPane {
	
	HBox test;
	Label label = new Label("");
	
	public DicePane() {
		super();
		init();
	}
	
	private void init() {
//		VBox vbox = new VBox(20);
		test = new HBox(30);
		this.setPrefHeight(300);
		this.setStyle("Fx-fx-background:#123456");
		test.getChildren().add(label);
		
//		vbox.getChildren().add(test);
		
		this.setCenter(test);
	}
	
	public void diceroll(String message) {
		label.setText(message);

	}
	
	
}

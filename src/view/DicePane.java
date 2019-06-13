package view;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;



public class DicePane extends Pane {

	
	
	public DicePane(Button button) {
		super();
		init();
		initDice();
		//this.button = button;
	}
	
	private void init() {
		HBox test = new HBox();
		this.setPrefHeight(300);
		this.setStyle("Fx-fx-background:#123456");
	}
	
	private void initDice() {
		final Button button = new Button();
		button.setPrefHeight(50);
		button.setPrefWidth(50);   
		button.setAlignment(Pos.CENTER_RIGHT);
	}
	
}

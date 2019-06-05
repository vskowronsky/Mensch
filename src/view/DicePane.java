package view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class DicePane extends Pane {

	public DicePane() {
		super();
		init();
	}
	
	private void init() {
		HBox test = new HBox();
		this.setPrefHeight(300);
		this.setStyle("Fx-fx-background:#123456");
	}
}

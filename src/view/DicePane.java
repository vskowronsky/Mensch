package view;


import com.sun.org.apache.regexp.internal.recompile;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;



public class DicePane extends BorderPane {
	
	HBox text;
	Label label = new Label("");
	Rectangle rec1;
	VBox vbox;
	
	public DicePane() {
		super();
		init();
	}
	
	private void init() {
		vbox = new VBox(20);
		
		text = new HBox(30);
		this.setPrefHeight(300);
		label.setFont(Font.font(Font.getDefault().getFamily(),30));
		
		
		
		
		
		
		
		  rec1 = new Rectangle(); rec1.setHeight(50); rec1.setWidth(50);
		  rec1.setStroke(Color.BLACK); rec1.setFill(Color.WHITE);
		 
		
		vbox.setPadding(new Insets(50, 50,50, 50));
		vbox.setSpacing(30);
		
		vbox.getChildren().addAll(label, rec1);
		
		this.setCenter(vbox);
	}
	
	public void diceroll(String message) {
		label.setText(message);

	}
	
	
}

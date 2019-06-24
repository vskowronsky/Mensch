package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class MessagePane extends BorderPane {
	private Label label;
	private Label label2;
	private Label label3;

	public MessagePane() {
		super();
		
		label = new Label("");
		label2 = new Label("");
		label3 = new Label("");
		init();
	}
	
	public void init() {
		VBox messageBox = new VBox();
		messageBox.setBackground(Background.EMPTY);
		  String style = "-fx-background-color: rgba(199, 193, 193, 1);";
		  messageBox.setStyle(style);
		label.setFont(Font.font(Font.getDefault().getFamily(),20));
		label2.setFont(Font.font(Font.getDefault().getFamily(),20));
		label3.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(label, label2, label3);
		
		this.setPadding(new Insets(6,0,0,0));
		
		this.setCenter(messageBox);
	}
	
	
	public void message(String message, AudioClip sound) {
		if (sound != null) sound.play();
		
		label.setText(label2.getText());
		label2.setText(label3.getText());
		label3.setText(message);
	}
}

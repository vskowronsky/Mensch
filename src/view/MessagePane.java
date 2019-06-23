package view;

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
	private String labelWin;
	private String labelLose;
	private Label title;

	public MessagePane() {
		super();
		
		label = new Label("");
		label2 = new Label("");
		label3 = new Label("");
		title = new Label("Nachricht:");
		labelWin = "Sie haben gewonnen!";
		labelLose = "Sie haben verloren!";
		init();
	}
	
	public void init() {
		VBox messageBox = new VBox();
		messageBox.setBackground(Background.EMPTY);
		  String style = "-fx-background-color: rgba(0, 0, 255, 0.5);";
		  messageBox.setStyle(style);
		title.setFont(Font.font(Font.getDefault().getFamily(),20));
		label.setFont(Font.font(Font.getDefault().getFamily(),20));
		label2.setFont(Font.font(Font.getDefault().getFamily(),20));
		label3.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(title, label, label2, label3);
		
		this.setCenter(messageBox);
	}
	
	
	public void message(String message, AudioClip sound) {
		if (sound != null) sound.play();
		
		label.setText(label2.getText());
		label2.setText(label3.getText());
		label3.setText(message);
	}
	
	
//	public void enemyHasThrown(String message) {
//		AudioClip sound = new AudioClip("file:src/view/EnemyHasThrown.wav");
//		sound.play();
//		label.setText(label2.getText());
//		label2.setText(message);
//		
//	}
//	
//	public void leftHouse(String message) {
//		AudioClip sound = new AudioClip("file:src/view/EnemyHasThrown.wav");
//		sound.play();
//		label.setText(label2.getText());
//		label2.setText(message);
//		
//	}
}

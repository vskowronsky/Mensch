package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class MessagePane extends BorderPane {
	private Label label;
	private String labelWin;
	private String labelLose;
	private Label title;

	public MessagePane() {
		super();
		
		label = new Label("");
		title = new Label("Nachricht:");
		labelWin = "Sie haben gewonnen!";
		labelLose = "Sie haben verloren!";
		init();
	}
	
	public void init() {
//		VBox bigBox = new VBox(30);
		VBox messageBox = new VBox();
		title.setFont(Font.font(Font.getDefault().getFamily(),20));
		label.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(title, label);
//		bigBox.getChildren().add(messageBox);
		
		this.setCenter(messageBox);
	}
	
	public void message(String message) {
		label.setText(message);
	}
	
	public void win() {
		AudioClip successSound = new AudioClip("file:src/view/Success-Sound.wav");
		successSound.play();
		label.setText(labelWin);
	}

	public void lose() {
		AudioClip loseSound = new AudioClip("file:src/view/Lose-Sound.mp3");
		loseSound.play();
		label.setText(labelLose);
	}

	public void soundEnemy(String message) {
		AudioClip enemySound = new AudioClip("file:src/view/Enemy-Sound.mp3");
		enemySound.play();
		label.setText(message);
		
	}
}

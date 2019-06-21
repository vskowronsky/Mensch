package view;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MessagePane extends Pane {
	private Label label1;
	private Label label;
	private String labelWin;
	private String labelLose;

	public MessagePane() {
		super();
		
		label1 = new Label("HAAAAALLLLOOOO");
		labelWin = "Sie haben gewonnen!";
		labelLose = "Sie haben verloren!";
		Rectangle rec = new Rectangle (100, 100, 90, 90);
		init();
	}
	
	public void init() {
		VBox bigBox = new VBox(30);
		
		
		HBox messageBox = new HBox();
		label1.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(label1);
		bigBox.getChildren().add(messageBox);
	}
	
	//Hier war das falsche Label gesetzt
	public void message(String message) {
		label1.setText(message);
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
}

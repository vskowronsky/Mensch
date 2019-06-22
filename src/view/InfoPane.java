package view;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoPane extends BorderPane {
	private int id;
	private Color playerColor;

	
	private Label label;
	private String labelDisable;
	private String labelEnable;
	public Button saveBtn;
	public Button loadBtn;
	private String labelWin;
	private String labelLose;
	
	public InfoPane(int id) {
		super();
		this.id = id;
		
		if(id == 1) {
			playerColor = Color.GOLD;
		} else if(id == 2) {
			playerColor = Color.MEDIUMSEAGREEN;
		} else if(id == 3) {
			playerColor = Color.ROYALBLUE;
		} else if(id == 4) {
			playerColor = Color.FIREBRICK;
		}
		
		label = new Label("");
		int id1 = id%4 +1;
		labelDisable = "Warten auf Spieler " + id1 + ".";
		labelEnable = "Machen Sie Ihren Zug.";
//		labelWin = "Sie haben gewonnen!";
//		labelLose = "Sie haben verloren!";
		
		init();
	}
	
	private void init() {
		VBox bigBox = new VBox(30);
		
		Text text = new Text("Spieler " + id);
		text.setFill(playerColor);
		text.setEffect(new Lighting());
		text.setFont(Font.font(Font.getDefault().getFamily(),50));
		
		Text buttonText = new Text("Was möchten Sie machen?");
		saveBtn = new Button("Speichern");
		loadBtn = new Button("Laden");
		
		HBox messageBox = new HBox();
		label.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(label);
		
		bigBox.setPadding(new Insets(5, 5, 5, 5));
	    bigBox.setSpacing(5);
	    
		bigBox.getChildren().addAll(text, messageBox, buttonText, saveBtn, loadBtn);
		
		this.setCenter(bigBox);
	}
	
	
	public void disable() {
		label.setText(labelDisable);
	}

	public void enable() {
		label.setText(labelEnable);
	}

//	public void win() {
//		AudioClip successSound = new AudioClip("file:src/view/Success-Sound.wav");
//		successSound.play();
//		label.setText(labelWin);
//			}
//
//	public void lose() {
//		AudioClip loseSound = new AudioClip("file:src/view/Lose-Sound.mp3");
//		loseSound.play();
//		label.setText(labelLose);
//	}
	 
//	public void message(String message) {
//		label.setText(message);
//	}
}

package view;


import controller.player.PlayerGUI;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.effect.Lighting;
import javafx.scene.layout.Background;
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
	private PlayerGUI playerGUI;
	private double width;
	private Label statusLabel;
	private String labelDisable;
	private String labelEnable;
	public Button saveBtn;
	public Button loadBtn;
	private String labelWin;
	private String labelLose;
	
	public InfoPane(int id, PlayerGUI playerGUI) {
		super();
		this.id = id;
		this.playerGUI = playerGUI;
		
		if(id == 1) {
			playerColor = Color.GOLD;
		} else if(id == 2) {
			playerColor = Color.MEDIUMSEAGREEN;
		} else if(id == 3) {
			playerColor = Color.ROYALBLUE;
		} else if(id == 4) {
			playerColor = Color.FIREBRICK;
		}
		this.width = 200;
		statusLabel = new Label("");
		init();
	}
	
	private void init() {
		
		VBox bigBox = new VBox(30);
		bigBox.setBackground(Background.EMPTY);
		  String style = "-fx-background-color: rgba(222, 221, 221, 1);";
		  bigBox.setStyle(style);
		bigBox.setPrefWidth(this.width);
		
		Label playerName = new Label("Spieler " + id);
		playerName.setTextFill(playerColor);
//		playerName.setEffect(new Lighting());
		playerName.setFont(Font.font(Font.getDefault().getFamily(),this.width/5));
		
		saveBtn = new Button("Spielstand speichern");
		loadBtn = new Button("Spielstand laden");

		statusLabel.setFont(Font.font(Font.getDefault().getFamily(),this.width/11));
		
		bigBox.setPadding(new Insets(5, 5, 5, 5));
	    bigBox.setSpacing(5);
	    
		bigBox.getChildren().addAll(playerName, statusLabel, saveBtn, loadBtn);
		
		this.setCenter(bigBox);
		
		
	}
	
	
	public void setText(String message) {
		statusLabel.setText(message);
	}
	
	public void updateWidth() {
		
		this.width = (playerGUI.stageWidth)*0.2;
		
		setPrefWidth(this.width);
		init();
	}

}

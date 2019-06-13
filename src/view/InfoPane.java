package view;

import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoPane extends BorderPane {
	private PlayerPane playerPane;
	private int id;
	private Color playerColor;
	private ProgressBar progressBar;

	
	private Label label;
	private String labelDisable;
	private String labelEnable;
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
		
		progressBar = new ProgressBar();
		progressBar.setPrefWidth(120);
		label = new Label("");
		int id1 = id%4 +1;
		labelDisable = "Warten auf Spieler " + id1;
		labelEnable = "Machen Sie Ihren Zug";
		labelWin = "Sie haben gewonnen!";
		labelLose = "Sie haben verloren!";
		
		init();
	}
	
	private void init() {
		VBox bigBox = new VBox(30);
		
		Text text = new Text("Spieler " + id);
		text.setFill(playerColor);
		text.setEffect(new Lighting());
		text.setFont(Font.font(Font.getDefault().getFamily(),50));
		
		HBox progress = new HBox();
		progress.getChildren().add(progressBar);
		
		HBox messageBox = new HBox();
		messageBox.getChildren().addAll(label);
		
		
		bigBox.getChildren().addAll(text, progress, messageBox);
		
		this.setCenter(bigBox);
	}
	
	
	public void disable() {
		label.setText(labelDisable);
		progressBar.setProgress(-1);
	}

	public void enable() {
		label.setText(labelEnable);
		progressBar.setProgress(1);
	}

	public void win() {
		label.setText(labelWin);
	}

	public void lose() {
		label.setText(labelLose);
	}
	 
	public void message(String message) {
		label.setText(message);
	}
}

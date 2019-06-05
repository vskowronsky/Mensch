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
	private MenuBar menuBar;
	
	private Label label;
	private String labelDisable;
	private String labelEnable;
	private String labelWin;
	private String labelLose;
	
	public InfoPane(int id, MenuBar menuBar) {
		super();
		this.id = id;
		
		if(id == 1) {
			playerColor = Color.YELLOW;
		} else if(id == 2) {
			playerColor = Color.GREEN;
		} else if(id == 3) {
			playerColor = Color.BLUE;
		} else if(id == 4) {
			playerColor = Color.DARKRED;
		}
		
		this.menuBar = menuBar;
		progressBar = new ProgressBar();
		progressBar.setPrefWidth(120);
		label = new Label("");
		int id1 = id%2 +1;
		labelDisable = "Warten auf Spieler " + id1;
		labelEnable = "Machen Sie Ihren Zug";
		labelWin = "Sie haben gewonnen!";
		labelLose = "Sie haben verloren!";
		
		
		init();
	}
	
	
	
	private void init() {
		HBox bottomBox = new HBox(30);
		
		Text text = new Text("Spieler " + id);
		text.setFill(playerColor);
		text.setEffect(new Lighting());
		text.setFont(Font.font(Font.getDefault().getFamily(),50));
		
		VBox bottomRightBox = new VBox(10);
		bottomRightBox.getChildren().addAll(progressBar, label);
		bottomBox.getChildren().addAll(text, bottomRightBox);
		
		this.setBottom(bottomBox);
	}
}

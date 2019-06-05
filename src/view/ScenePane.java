package view;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;


public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	/*private DicePane dicePane;
	private int id;
	private Color playerColor;
	private ProgressBar progressBar;
	private MenuBar menuBar;
	
	private Label label;
	private String labelDisable;
	private String labelEnable;
	private String labelWin;
	private String labelLose;
	*/
	
//in den Konstruktor die DicePane und InfoPane geben
	public ScenePane(PlayerPane playerPane, InfoPane infoPane) {
		super();
		this.playerPane = playerPane;
		this.infoPane = infoPane;
		/*this.dicePane = dicePane;
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
		*progressBar = new ProgressBar();
		*progressBar.setPrefWidth(120);
		*label = new Label("");
		*int id1 = id%2 +1;
		*labelDisable = "Warten auf Spieler " + id1;
		*labelEnable = "Machen Sie Ihren Zug";
		*labelWin = "Sie haben gewonnen!";
		*labelLose = "Sie haben verloren!";
		*/
		init();
		//this.getChildren().add(menuBar);
	}


	private void init(){
		AnchorPane center = new AnchorPane();
		center.getChildren().addAll(playerPane, infoPane);
		AnchorPane.setTopAnchor(playerPane, 20.);
		AnchorPane.setLeftAnchor(playerPane, 20.);
		AnchorPane.setBottomAnchor(infoPane, 990.);
		this.setCenter(center);
	}
	
	/*public void disable(){
		label.setText(labelDisable);
		progressBar.setProgress(-1);
	}
	
	public void enable(){
		label.setText(labelEnable);
		progressBar.setProgress(1);
	}
	
	public void win(){
		label.setText(labelWin);
	}
	
	public void lose(){
		label.setText(labelLose);
	}
*/

}

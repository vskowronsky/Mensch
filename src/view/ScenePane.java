package view;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;


public class ScenePane extends BorderPane{

	private PlayerPane playerPane;
	private InfoPane infoPane;
	private DicePane dicePane;
	/*private int id;
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
	public ScenePane(PlayerPane playerPane, InfoPane infoPane, DicePane dicePane) {
		super();
		this.playerPane = playerPane;
		this.infoPane = infoPane;
		this.dicePane = dicePane;
		//this.id = id;
		
		
		init();
		//this.getChildren().add(menuBar);
	}


	private void init(){
		AnchorPane center = new AnchorPane();
		center.getChildren().addAll(playerPane, infoPane);
		
		AnchorPane.setTopAnchor(playerPane, 20.);
		AnchorPane.setLeftAnchor(playerPane, 20.);
		this.setCenter(center);
		
		VBox rightBox = new VBox();
		
		rightBox.getChildren().addAll(dicePane, infoPane);
		
		this.setRight(rightBox); 
		
		
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

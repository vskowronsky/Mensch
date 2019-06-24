package view;


import controller.player.PlayerGUI;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Klasse informiert über den Spielstand und ermöglicht Speichern und Laden des Spiels.
 * @author Vanessa
 */
public class InfoPane extends VBox {
	private int id;
	private Color playerColor;
	private PlayerGUI playerGUI;
	private double width;
	private Label statusLabel;
	public Button saveBtn;
	public Button loadBtn;

	/**
	 * Konstruktor der Klasse setzt die Atttributvariablen und erzeugt ein Label.
	 * @param id ID des Spielers der dran ist
	 * @param playerGUI Der Spieler
	 * @author Vanessa
	 */
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
		
		init();
	}

	/**
	 * Initialisierung der InfoPane
	 * @author Vanessa
	 */
	private void init() {
		this.setSpacing(10);
		this.setPadding(new Insets(10));
		this.getChildren().clear();
		statusLabel = new Label("");
		
		
		

		Label playerName = new Label("Spieler " + id);
		playerName.setTextFill(playerColor);

		InnerShadow innerShadow = new InnerShadow(); 

		//Festlegen der Art der Unschärfe
		innerShadow.setOffsetX(2); 
		innerShadow.setOffsetY(2); 

		//Einstellen der Schattenfarbe 
		innerShadow.setColor(Color.GREY);        

		//Anwendung des Schatteneffekts auf den Text 
		playerName.setEffect(innerShadow);  
		//		playerName.setEffect(new Lighting());
		playerName.setFont(Font.font(Font.getDefault().getFamily(),this.width/5));

		VBox vbButtons = new VBox(10);
		
		
		
		saveBtn = new Button("Spielstand speichern");
		saveBtn.setId("glass-grey");
		
		
		loadBtn = new Button("Spielstand laden");
		loadBtn.setId("glass-grey");
		
		saveBtn.setTranslateY(playerGUI.stageWidth*0.18);	
		loadBtn.setTranslateY(playerGUI.stageWidth*0.18);
		statusLabel.setTranslateY(playerGUI.stageWidth*0.18);
		
		
		saveBtn.setMaxWidth(Double.MAX_VALUE);
		loadBtn.setMaxWidth(Double.MAX_VALUE);
		
		vbButtons.getChildren().addAll(saveBtn, loadBtn);

		statusLabel.setFont(Font.font(Font.getDefault().getFamily(),this.width/11));


		this.getChildren().addAll(playerName, statusLabel, vbButtons);

	}

	/**
	 * Set-Methode des Labels
	 * @param message Nachricht, die vom Spiel geschickt wurde.
	 * @author Vanessa
	 */
	public void setText(String message) {
		statusLabel.setText(message);
	}

	/**
	 * Methode passt die Größe der InfoPane dynamisch an die veränderbare Größe des Fensters an.
	 * @author Vanessa
	 */
	public void updateWidth() {
		String temp = statusLabel.getText();
		this.width = (playerGUI.stageWidth)*0.2;
		setPrefWidth(this.width);
		init();
		statusLabel.setText(temp);
	}
}

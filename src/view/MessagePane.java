package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

/**
 * Klasse zeigt Nachrichten, die vom Spiel geschickt werden an.
 * @author Vanessa

 */
public class MessagePane extends BorderPane {
	private Label label;
	private Label label2;
	private Label label3;

	/**
	 * Konstruktor setzt die Attributvariablen der Klasse.
	 * @author Vanessa
	 */
	public MessagePane() {
		super();	
		label = new Label("");
		label2 = new Label("");
		label3 = new Label("");
		init();
	}
	
	/**
	 * Initialisierung der Elemente.
	 * @author Vanessa
	 */
	public void init() {
		VBox messageBox = new VBox();
		messageBox.setBackground(Background.EMPTY);
		  String style = "-fx-background-color: rgba(0, 0, 255, 0.5);";
		  messageBox.setStyle(style);
		label.setFont(Font.font(Font.getDefault().getFamily(),20));
		label2.setFont(Font.font(Font.getDefault().getFamily(),20));
		label3.setFont(Font.font(Font.getDefault().getFamily(),20));
		messageBox.getChildren().addAll(label, label2, label3);
		
		this.setCenter(messageBox);
	}
	
	/**
	 * Methode erzeugt ein durchlaufendes Nachrichtenband. Die unterste Nachricht ist die aktuellste 
	 * Nachricht.
	 * @param message Nachricht, die vom Spiel geschickt wurde
	 * @param sound AudioClip, der vom Spiel geschickt wurde
	 * @author Vanessa
	 */
	public void message(String message, AudioClip sound) {
		if (sound != null) {
			sound.play();
		}
		label.setText(label2.getText());
		label2.setText(label3.getText());
		label3.setText(message);
	}
}

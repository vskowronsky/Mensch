package view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

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
	
	public InfoPane() {
		super();
	}
}

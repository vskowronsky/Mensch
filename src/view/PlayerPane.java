package view;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class PlayerPane extends Pane{
public final int width = 880;
	
	public PlayerPane() {
		super();
		init();
	}
	
	public void init() {
		Pane p = paneInit();
//		Scene scene = new Scene(p, width, width);
//		this.setScene(scene);
//		this.show();
	}
	
	public Pane paneInit() {
		Pane p = new Pane();
		int unit = width/11;
		Rectangle rHY1 = new Rectangle(unit/2, unit/2);
		p.getChildren().add(rHY1);
		return p;
	}
}

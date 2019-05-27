package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class PlayerPane extends Pane{
	public final int width = 880;

	public PlayerPane() {
		super();
		int unit = width/11;
		Rectangle rHY1 = new Rectangle(unit/2, unit/2);
		Rectangle rHY2 = new Rectangle(100, 30,unit/2, unit/2);
		Rectangle rHY3= new Rectangle(50, 400,unit/2, unit/2);
		Rectangle rHY4= new Rectangle(700, 30,unit/2, unit/2);
		Rectangle rHY5 = new Rectangle(50, 250, unit/2, unit/2);
		
		
		//Polygon triangle = new Polygon();
		//triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0);
		//triangle.setFill(Color.BLACK);
		//triangle.setStroke(Color.RED); 

		getChildren().addAll(rHY1,rHY2,rHY3,rHY4,rHY5);
	}

}

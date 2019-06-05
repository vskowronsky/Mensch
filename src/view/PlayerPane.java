package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import model.Board;

public class PlayerPane extends Pane{
	public final int width = 770;
	public int unit;
	public Board board;
	int x = unit/2;
	int y = width/2;
	
	public PlayerPane() {
		super();
		this.board = new Board();
		int unit = width/11;
		/*Rectangle rHY1 = new Rectangle(unit/2, unit/2);
		Rectangle rHY2 = new Rectangle(100, 30,unit/2, unit/2);
		Rectangle rHY3= new Rectangle(50, 400,unit/2, unit/2);
		Rectangle rHY4= new Rectangle(700, 30,unit/2, unit/2);
		Rectangle rHY5 = new Rectangle(50, 250, unit/2, unit/2);
		*/
		
		Circle circle1 = new Circle(x, y, unit/3, Color.WHITE);
		circle1.setStroke(Color.BLACK);
		
		//Polygon triangle = new Polygon();
		//triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0);
		//triangle.setFill(Color.BLACK);
		//triangle.setStroke(Color.RED); 

		
		
		
		
		getChildren().addAll(circle1);
	}

	
}

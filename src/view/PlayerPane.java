package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Board;

public class PlayerPane extends Pane{
	public final int width = 660;
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
		
//		Circle circle1 = new Circle(x, y, unit/3, Color.WHITE);
//		circle1.setStroke(Color.BLACK);
//		
		//Polygon triangle = new Polygon();
		//triangle.getPoints().addAll(50.0, 0.0, 0.0, 50.0, 100.0, 50.0);
		//triangle.setFill(Color.BLACK);
		//triangle.setStroke(Color.RED); 

		
		
		
		
//		getChildren().addAll(circle1);
		
		init();
	}

	private void init() {
		initHouse();
		initBoard();
		initStreet();
	}
	
	private void initHouse() {
	
	}
	
	private void initBoard() {
		int unit = width/11;
		int x = unit/2;
		int y = width/2;
		Line line = new Line(x, y, 21*x, y);
		Line line2 = new Line(y, x, y, x*21);
		getChildren().addAll(line, line2);
		for(int i = 1; i <= 11; i++) {
			Circle circle1 = new Circle(x, y, unit/3, Color.GREEN);
			Circle circle2 = new Circle(y, x, unit/3, Color.GREEN);
			circle1.setStroke(Color.YELLOW);
			circle1.setStrokeWidth(3);
			circle2.setStroke(Color.YELLOW);
			circle2.setStrokeWidth(3);
			x = x + unit;
			getChildren().addAll(circle1, circle2);
		}
		Text t1 = new Text("A");
		t1.setX(unit/2);
		t1.setY(width/2);
		t1.setFill(Color.BLUE);
		t1.setRotate(90);
		t1.setFont(Font.font("Times New Roman", 40 ));
		getChildren().add(t1);
	}
	
	private void initStreet() {
		
	}
}

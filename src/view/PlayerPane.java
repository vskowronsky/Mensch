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
		/*
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
		//initBoard();
		initStreet();
	}
	
	private void initHouse() {
		double unit = width/11;
//		double x = unit/2;
//		double y = width/2;
		double size = unit*0.9;
		Rectangle recHouseGreen1 = new Rectangle(0, 0, size, size);
		Rectangle recHouseGreen2 = new Rectangle(unit, 0, size, size);
		Rectangle recHouseGreen3 = new Rectangle(0, unit, size, size);
		Rectangle recHouseGreen4 = new Rectangle(unit, unit,size, size);
		
		recHouseGreen1.setFill(Color.FLORALWHITE);
		recHouseGreen2.setFill(Color.FLORALWHITE);
		recHouseGreen3.setFill(Color.FLORALWHITE);
		recHouseGreen4.setFill(Color.FLORALWHITE);
		
		recHouseGreen1.setStroke(Color.MEDIUMSEAGREEN);
		recHouseGreen2.setStroke(Color.MEDIUMSEAGREEN);
		recHouseGreen3.setStroke(Color.MEDIUMSEAGREEN);
		recHouseGreen4.setStroke(Color.MEDIUMSEAGREEN);
		
		recHouseGreen1.setStrokeWidth(3);
		recHouseGreen2.setStrokeWidth(3);
		recHouseGreen3.setStrokeWidth(3);
		recHouseGreen4.setStrokeWidth(3);
		
		Rectangle recHouseBlue1 = new Rectangle(width-unit, 0, size, size);
		Rectangle recHouseBlue2 = new Rectangle(width-2*unit, 0, size, size);
		Rectangle recHouseBlue3 = new Rectangle(width-unit, unit, size, size);
		Rectangle recHouseBlue4 = new Rectangle(width-2*unit, unit, size, size);
		
		recHouseBlue1.setFill(Color.FLORALWHITE);
		recHouseBlue2.setFill(Color.FLORALWHITE);
		recHouseBlue3.setFill(Color.FLORALWHITE);
		recHouseBlue4.setFill(Color.FLORALWHITE);
		
		recHouseBlue1.setStroke(Color.ROYALBLUE);
		recHouseBlue2.setStroke(Color.ROYALBLUE);
		recHouseBlue3.setStroke(Color.ROYALBLUE);
		recHouseBlue4.setStroke(Color.ROYALBLUE);
		
		recHouseBlue1.setStrokeWidth(3);
		recHouseBlue2.setStrokeWidth(3);
		recHouseBlue3.setStrokeWidth(3);
		recHouseBlue4.setStrokeWidth(3);
		
		Rectangle recHouseRed1 = new Rectangle(width-unit, width-unit, size, size);
		Rectangle recHouseRed2 = new Rectangle(width-2*unit, width-unit, size, size);
		Rectangle recHouseRed3 = new Rectangle(width-unit, width-2*unit, size, size);
		Rectangle recHouseRed4 = new Rectangle(width-2*unit, width-2*unit, size, size);
		
		recHouseRed1.setFill(Color.FLORALWHITE);
		recHouseRed2.setFill(Color.FLORALWHITE);
		recHouseRed3.setFill(Color.FLORALWHITE);
		recHouseRed4.setFill(Color.FLORALWHITE);
		
		recHouseRed1.setStroke(Color.DARKRED);
		recHouseRed2.setStroke(Color.DARKRED);
		recHouseRed3.setStroke(Color.DARKRED);
		recHouseRed4.setStroke(Color.DARKRED);
		
		recHouseRed1.setStrokeWidth(3);
		recHouseRed2.setStrokeWidth(3);
		recHouseRed3.setStrokeWidth(3);
		recHouseRed4.setStrokeWidth(3);
		
		Rectangle recHouseYellow1 = new Rectangle(0, 9*unit, size, size);
		Rectangle recHouseYellow2 = new Rectangle(unit, 9*unit, size, size);
		Rectangle recHouseYellow3 = new Rectangle(0, 10*unit, size, size);
		Rectangle recHouseYellow4 = new Rectangle(unit, 10*unit, size, size);
		
		recHouseYellow1.setFill(Color.FLORALWHITE);
		recHouseYellow2.setFill(Color.FLORALWHITE);
		recHouseYellow3.setFill(Color.FLORALWHITE);
		recHouseYellow4.setFill(Color.FLORALWHITE);
		
		recHouseYellow1.setStroke(Color.YELLOW);
		recHouseYellow2.setStroke(Color.YELLOW);
		recHouseYellow3.setStroke(Color.YELLOW);
		recHouseYellow4.setStroke(Color.YELLOW);
		
		recHouseYellow1.setStrokeWidth(3);
		recHouseYellow2.setStrokeWidth(3);
		recHouseYellow3.setStrokeWidth(3);
		recHouseYellow4.setStrokeWidth(3);
		
		getChildren().addAll(recHouseGreen1, recHouseGreen2, recHouseGreen3, recHouseGreen4,
			recHouseBlue1, recHouseBlue2, recHouseBlue3, recHouseBlue4, 
			recHouseRed1, recHouseRed2, recHouseRed3, recHouseRed4,
			recHouseYellow1, recHouseYellow2, recHouseYellow3, recHouseYellow4);
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
		double unit = width/11;
//		double x = unit/2;
//		double y = width/2;
		double size = unit*0.9;
		
		Rectangle recStreetGreen1 = new Rectangle(unit, 5*unit, size, size);
		Rectangle recStreetGreen2 = new Rectangle(2*unit, 5*unit, size, size);
		Rectangle recStreetGreen3 = new Rectangle(3*unit, 5*unit, size, size);
		Rectangle recStreetGreen4 = new Rectangle(4*unit, 5*unit,size, size);
		
		recStreetGreen1.setFill(Color.FLORALWHITE);
		recStreetGreen2.setFill(Color.FLORALWHITE);
		recStreetGreen3.setFill(Color.FLORALWHITE);
		recStreetGreen4.setFill(Color.FLORALWHITE);
		
		recStreetGreen1.setStroke(Color.MEDIUMSEAGREEN);
		recStreetGreen2.setStroke(Color.MEDIUMSEAGREEN);
		recStreetGreen3.setStroke(Color.MEDIUMSEAGREEN);
		recStreetGreen4.setStroke(Color.MEDIUMSEAGREEN);
		
		recStreetGreen1.setStrokeWidth(3);
		recStreetGreen2.setStrokeWidth(3);
		recStreetGreen3.setStrokeWidth(3);
		recStreetGreen4.setStrokeWidth(3);
		
		Rectangle recStreetBlue1 = new Rectangle(5*unit, unit, size, size);
		Rectangle recStreetBlue2 = new Rectangle(5*unit, 2*unit, size, size);
		Rectangle recStreetBlue3 = new Rectangle(5*unit, 3*unit, size, size);
		Rectangle recStreetBlue4 = new Rectangle(5*unit, 4*unit, size, size);
		
		recStreetBlue1.setFill(Color.FLORALWHITE);
		recStreetBlue2.setFill(Color.FLORALWHITE);
		recStreetBlue3.setFill(Color.FLORALWHITE);
		recStreetBlue4.setFill(Color.FLORALWHITE);
		
		recStreetBlue1.setStroke(Color.ROYALBLUE);
		recStreetBlue2.setStroke(Color.ROYALBLUE);
		recStreetBlue3.setStroke(Color.ROYALBLUE);
		recStreetBlue4.setStroke(Color.ROYALBLUE);
		
		recStreetBlue1.setStrokeWidth(3);
		recStreetBlue2.setStrokeWidth(3);
		recStreetBlue3.setStrokeWidth(3);
		recStreetBlue4.setStrokeWidth(3);
		
		getChildren().addAll(recStreetGreen1, recStreetGreen2, recStreetGreen3, recStreetGreen4,
				recStreetBlue1, recStreetBlue2, recStreetBlue3, recStreetBlue4);
	}
}

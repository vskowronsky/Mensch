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
		initBoard();
		initStreet();
		initMeeple();
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
		//int x = unit/2;
		//int y = width/2;
		double size = unit*0.9;
	
		//Verbindungslinien
		Line line0 = new Line(4.5*unit, 10.5*unit, 4.5*unit, 6.5*unit);
		Line line1 = new Line(4.5*unit, 6.5*unit, 4.5*unit, 6.5*unit);
		Line line2 = new Line(4.5*unit, 6.5*unit, 0.5*unit, 6.5*unit);
		Line line3 = new Line(0.5*unit, 6.5*unit, 0.5*unit, 4.5*unit);
		Line line4 = new Line(4.5*unit, 4.5*unit, 4.5*unit, 0.5*unit);
		Line line5 = new Line(4.5*unit, 0.5*unit, 6.5*unit, 0.5*unit);
		Line line6 = new Line(6.5*unit, 0.5*unit, 6.5*unit, 4.5*unit);
		Line line7 = new Line(6.5*unit, 4.5*unit, 10.5*unit, 4.5*unit);
		Line line8 = new Line(10.5*unit, 4.5*unit, 10.5*unit, 6.5*unit);
		Line line9 = new Line(10.5*unit, 6.5*unit, 6.5*unit, 6.5*unit);
		Line line10 = new Line(6.5*unit, 6.5*unit, 6.5*unit, 10.5*unit);
		Line line11 = new Line(6.5*unit, 10.5*unit, 4.5*unit, 10.5*unit);
		
		//Vertikale Kreise 0-4
		Circle circle0 = new Circle(4.5*unit, 10.5*unit, size/2);
		circle0.setFill(Color.WHITE);
		circle0.setStroke(Color.BLACK);
		
		Circle circle1 = new Circle(4.5*unit, 9.5*unit, size/2);
		circle1.setFill(Color.WHITE);
		circle1.setStroke(Color.BLACK);
		
		Circle circle2 = new Circle(4.5*unit, 8.5*unit, size/2);
		circle2.setFill(Color.WHITE);
		circle2.setStroke(Color.BLACK);
		
		Circle circle3 = new Circle(4.5*unit, 7.5*unit, size/2);
		circle3.setFill(Color.WHITE);
		circle3.setStroke(Color.BLACK);
		
		Circle circle4 = new Circle(4.5*unit, 6.5*unit, size/2);
		circle4.setFill(Color.WHITE);
		circle4.setStroke(Color.BLACK); 
		
		//Horizontale Kreise 5-8
		Circle circle5 = new Circle(3.5*unit, 6.5*unit, size/2);
		circle5.setFill(Color.WHITE);
		circle5.setStroke(Color.BLACK); 
		
		Circle circle6 = new Circle(2.5*unit, 6.5*unit, size/2);
		circle6.setFill(Color.WHITE);
		circle6.setStroke(Color.BLACK); 
		
		Circle circle7 = new Circle(1.5*unit, 6.5*unit, size/2);
		circle7.setFill(Color.WHITE);
		circle7.setStroke(Color.BLACK); 
		
		Circle circle8 = new Circle(0.5*unit, 6.5*unit, size/2);
		circle8.setFill(Color.WHITE);
		circle8.setStroke(Color.BLACK); 
		
		//einzelner Kreis 9
		Circle circle9 = new Circle(0.5*unit, 5.5*unit, size/2);
		circle9.setFill(Color.WHITE);
		circle9.setStroke(Color.BLACK);
		
		//Horizontale Kreise 10-13
		Circle circle10 = new Circle(0.5*unit, 4.5*unit, size/2);
		circle10.setFill(Color.WHITE);
		circle10.setStroke(Color.BLACK);
		
		Circle circle11 = new Circle(1.5*unit, 4.5*unit, size/2);
		circle11.setFill(Color.WHITE);
		circle11.setStroke(Color.BLACK);
		
		Circle circle12 = new Circle(2.5*unit, 4.5*unit, size/2);
		circle12.setFill(Color.WHITE);
		circle12.setStroke(Color.BLACK);
		
		Circle circle13 = new Circle(3.5*unit, 4.5*unit, size/2);
		circle13.setFill(Color.WHITE);
		circle13.setStroke(Color.BLACK);
		
		//Vertikale Kreise 14-18
		Circle circle14 = new Circle(4.5*unit, 4.5*unit, size/2);
		circle14.setFill(Color.WHITE);
		circle14.setStroke(Color.BLACK);
		
		Circle circle15 = new Circle(4.5*unit, 3.5*unit, size/2);
		circle15.setFill(Color.WHITE);
		circle15.setStroke(Color.BLACK);
		
		Circle circle16 = new Circle(4.5*unit, 2.5*unit, size/2);
		circle16.setFill(Color.WHITE);
		circle16.setStroke(Color.BLACK);
		
		Circle circle17 = new Circle(4.5*unit, 1.5*unit, size/2);
		circle17.setFill(Color.WHITE);
		circle17.setStroke(Color.BLACK);
		
		Circle circle18 = new Circle(4.5*unit, 0.5*unit, size/2);
		circle18.setFill(Color.WHITE);
		circle18.setStroke(Color.BLACK);
		
		//einzelner Kreis19
		Circle circle19 = new Circle(5.5*unit, 0.5*unit, size/2);
		circle19.setFill(Color.WHITE);
		circle19.setStroke(Color.BLACK);
		
		//Vertikale Kreise 20-24
		Circle circle20 = new Circle(6.5*unit, 0.5*unit, size/2);
		circle20.setFill(Color.WHITE);
		circle20.setStroke(Color.BLACK);
		
		Circle circle21 = new Circle(6.5*unit, 1.5*unit, size/2);
		circle21.setFill(Color.WHITE);
		circle21.setStroke(Color.BLACK);
		
		Circle circle22 = new Circle(6.5*unit, 2.5*unit, size/2);
		circle22.setFill(Color.WHITE);
		circle22.setStroke(Color.BLACK);
		
		Circle circle23 = new Circle(6.5*unit, 3.5*unit, size/2);
		circle23.setFill(Color.WHITE);
		circle23.setStroke(Color.BLACK);
		
		Circle circle24 = new Circle(6.5*unit, 4.5*unit, size/2);
		circle24.setFill(Color.WHITE);
		circle24.setStroke(Color.BLACK);
		
		//Horizontale Kreise 25-28
		Circle circle25 = new Circle(7.5*unit, 4.5*unit, size/2);
		circle25.setFill(Color.WHITE);
		circle25.setStroke(Color.BLACK);
		
		Circle circle26 = new Circle(8.5*unit, 4.5*unit, size/2);
		circle26.setFill(Color.WHITE);
		circle26.setStroke(Color.BLACK);
		
		Circle circle27 = new Circle(9.5*unit, 4.5*unit, size/2);
		circle27.setFill(Color.WHITE);
		circle27.setStroke(Color.BLACK);
		
		Circle circle28 = new Circle(10.5*unit, 4.5*unit, size/2);
		circle28.setFill(Color.WHITE);
		circle28.setStroke(Color.BLACK);
		
		//einzelner Kreis 29
		Circle circle29 = new Circle(10.5*unit, 5.5*unit, size/2);
		circle29.setFill(Color.WHITE);
		circle29.setStroke(Color.BLACK);
		
		//Horizontale Kreise 30-33
		Circle circle30 = new Circle(10.5*unit, 6.5*unit, size/2);
		circle30.setFill(Color.WHITE);
		circle30.setStroke(Color.BLACK);
		
		Circle circle31 = new Circle(9.5*unit, 6.5*unit, size/2);
		circle31.setFill(Color.WHITE);
		circle31.setStroke(Color.BLACK);
		
		Circle circle32 = new Circle(8.5*unit, 6.5*unit, size/2);
		circle32.setFill(Color.WHITE);
		circle32.setStroke(Color.BLACK);
		
		Circle circle33 = new Circle(7.5*unit, 6.5*unit, size/2);
		circle33.setFill(Color.WHITE);
		circle33.setStroke(Color.BLACK);
		
		//Vertikale Kreise 34-38
		Circle circle34 = new Circle(6.5*unit, 6.5*unit, size/2);
		circle34.setFill(Color.WHITE);
		circle34.setStroke(Color.BLACK);
		
		Circle circle35 = new Circle(6.5*unit, 7.5*unit, size/2);
		circle35.setFill(Color.WHITE);
		circle35.setStroke(Color.BLACK);
		
		Circle circle36 = new Circle(6.5*unit, 8.5*unit, size/2);
		circle36.setFill(Color.WHITE);
		circle36.setStroke(Color.BLACK);
		
		Circle circle37 = new Circle(6.5*unit, 9.5*unit, size/2);
		circle37.setFill(Color.WHITE);
		circle37.setStroke(Color.BLACK);
		
		Circle circle38 = new Circle(6.5*unit, 10.5*unit, size/2);
		circle38.setFill(Color.WHITE);
		circle38.setStroke(Color.BLACK);
		
		//einzelner Kreis 39
		Circle circle39 = new Circle(5.5*unit, 10.5*unit, size/2);
		circle39.setFill(Color.WHITE);
		circle39.setStroke(Color.BLACK);
		
		getChildren().addAll(line0,line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11,
							circle0, circle1, circle2, circle3, circle4, circle5,  circle6, circle7, circle8,circle9, 
							circle10, circle11, circle12, circle13, circle14, circle15, circle16, circle17, circle18, circle19, 
							circle20, circle21, circle22, circle23, circle24, circle25, circle26, circle27, circle28, circle29, 
							circle30, circle31, circle32, circle33, circle34, circle35, circle36, circle37, circle38, circle39);
	
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
		
		Rectangle recStreetRed1 = new Rectangle(width-2*unit, 5*unit, size, size);
		Rectangle recStreetRed2 = new Rectangle(width-3*unit, 5*unit, size, size);
		Rectangle recStreetRed3 = new Rectangle(width-4*unit, 5*unit, size, size);
		Rectangle recStreetRed4 = new Rectangle(width-5*unit, 5*unit, size, size);
		
		recStreetRed1.setFill(Color.FLORALWHITE);
		recStreetRed2.setFill(Color.FLORALWHITE);
		recStreetRed3.setFill(Color.FLORALWHITE);
		recStreetRed4.setFill(Color.FLORALWHITE);
		
		recStreetRed1.setStroke(Color.DARKRED);
		recStreetRed2.setStroke(Color.DARKRED);
		recStreetRed3.setStroke(Color.DARKRED);
		recStreetRed4.setStroke(Color.DARKRED);
		
		recStreetRed1.setStrokeWidth(3);
		recStreetRed2.setStrokeWidth(3);
		recStreetRed3.setStrokeWidth(3);
		recStreetRed4.setStrokeWidth(3);
		
		Rectangle recStreetYellow1 = new Rectangle(5*unit,width-2*unit, size, size);
		Rectangle recStreetYellow2 = new Rectangle(5*unit,width-3*unit, size, size);
		Rectangle recStreetYellow3 = new Rectangle(5*unit,width-4*unit, size, size);
		Rectangle recStreetYellow4 = new Rectangle(5*unit,width-5*unit, size, size);
		
		recStreetYellow1.setFill(Color.FLORALWHITE);
		recStreetYellow2.setFill(Color.FLORALWHITE);
		recStreetYellow3.setFill(Color.FLORALWHITE);
		recStreetYellow4.setFill(Color.FLORALWHITE);
		
		recStreetYellow1.setStroke(Color.YELLOW);
		recStreetYellow2.setStroke(Color.YELLOW);
		recStreetYellow3.setStroke(Color.YELLOW);
		recStreetYellow4.setStroke(Color.YELLOW);
		
		recStreetYellow1.setStrokeWidth(3);
		recStreetYellow2.setStrokeWidth(3);
		recStreetYellow3.setStrokeWidth(3);
		recStreetYellow4.setStrokeWidth(3);
		
		getChildren().addAll(recStreetGreen1, recStreetGreen2, recStreetGreen3, recStreetGreen4,
				recStreetBlue1, recStreetBlue2, recStreetBlue3, recStreetBlue4,
				recStreetRed1, recStreetRed2, recStreetRed3, recStreetRed4,
				recStreetYellow1, recStreetYellow2, recStreetYellow3, recStreetYellow4);
	}
	
	private void initMeeple() {
		
	}
}

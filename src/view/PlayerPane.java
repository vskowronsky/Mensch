package view;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import controller.player.PlayerGUI;
import javafx.event.EventHandler;
import model.Board;
import model.Content;

public class PlayerPane extends Pane{
	public final int width = 660;
	private double unit = width/11;
	private double size = unit*0.9;
	private double dif = 0.05*unit;
	private double radius = size/2*0.94;
	private double middle = unit/2;
	public Board board;
	PlayerGUI gui;
	public CircleWithPos[] boardOfCircles;
	public Rectangle[] recStreetGreen;
	public Rectangle[] recStreetBlue;
	public Rectangle[] recStreetRed;
	public Rectangle[] recStreetYellow;
	public CircleWithPos[] circleStreetGreen;
	public CircleWithPos[] circleStreetBlue;
	public CircleWithPos[] circleStreetRed;
	public CircleWithPos[] circleStreetYellow;
	public CircleWithPos[] circleHouseGreen;
	public CircleWithPos[] circleHouseBlue;
	public CircleWithPos[] circleHouseRed;
	public CircleWithPos[] circleHouseYellow;



	public PlayerPane(PlayerGUI gui) {
		super();
		this.gui = gui;
		this.board = new Board();
		init();
	}

	private void init() {
		initHouse();
		initBoard();
		initStreet();
		circleStreet();
		circleHouse();
	}

	private void initHouse() {
		Rectangle recHouseGreen1 = new Rectangle(0+dif, 0+dif, size, size);
		Rectangle recHouseGreen2 = new Rectangle(unit+dif, 0+dif, size, size);
		Rectangle recHouseGreen3 = new Rectangle(0+dif, unit+dif, size, size);
		Rectangle recHouseGreen4 = new Rectangle(unit+dif, unit+dif,size, size);

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

		Rectangle recHouseBlue1 = new Rectangle(width-unit+dif, 0+dif, size, size);
		Rectangle recHouseBlue2 = new Rectangle(width-2*unit+dif, 0+dif, size, size);
		Rectangle recHouseBlue3 = new Rectangle(width-unit+dif, unit+dif, size, size);
		Rectangle recHouseBlue4 = new Rectangle(width-2*unit+dif, unit+dif, size, size);

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

		Rectangle recHouseRed1 = new Rectangle(width-unit+dif, width-unit+dif, size, size);
		Rectangle recHouseRed2 = new Rectangle(width-2*unit+dif, width-unit+dif, size, size);
		Rectangle recHouseRed3 = new Rectangle(width-unit+dif, width-2*unit+dif, size, size);
		Rectangle recHouseRed4 = new Rectangle(width-2*unit+dif, width-2*unit+dif, size, size);

		recHouseRed1.setFill(Color.FLORALWHITE);
		recHouseRed2.setFill(Color.FLORALWHITE);
		recHouseRed3.setFill(Color.FLORALWHITE);
		recHouseRed4.setFill(Color.FLORALWHITE);

		recHouseRed1.setStroke(Color.FIREBRICK);
		recHouseRed2.setStroke(Color.FIREBRICK);
		recHouseRed3.setStroke(Color.FIREBRICK);
		recHouseRed4.setStroke(Color.FIREBRICK);

		recHouseRed1.setStrokeWidth(3);
		recHouseRed2.setStrokeWidth(3);
		recHouseRed3.setStrokeWidth(3);
		recHouseRed4.setStrokeWidth(3);

		Rectangle recHouseYellow1 = new Rectangle(0+dif, 9*unit+dif, size, size);
		Rectangle recHouseYellow2 = new Rectangle(unit+dif, 9*unit+dif, size, size);
		Rectangle recHouseYellow3 = new Rectangle(0+dif, 10*unit+dif, size, size);
		Rectangle recHouseYellow4 = new Rectangle(unit+dif, 10*unit+dif, size, size);

		recHouseYellow1.setFill(Color.FLORALWHITE);
		recHouseYellow2.setFill(Color.FLORALWHITE);
		recHouseYellow3.setFill(Color.FLORALWHITE);
		recHouseYellow4.setFill(Color.FLORALWHITE);

		recHouseYellow1.setStroke(Color.GOLD);
		recHouseYellow2.setStroke(Color.GOLD);
		recHouseYellow3.setStroke(Color.GOLD);
		recHouseYellow4.setStroke(Color.GOLD);

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
		//Verbindungslinien
		Line line0 = new Line(4.5*unit, 10.5*unit, 4.5*unit, 6.5*unit);
		Line line1 = new Line(4.5*unit, 6.5*unit, 4.5*unit, 6.5*unit);
		Line line2 = new Line(4.5*unit, 6.5*unit, 0.5*unit, 6.5*unit);
		Line line3 = new Line(0.5*unit, 6.5*unit, 0.5*unit, 4.5*unit);
		Line line4 = new Line(0.5*unit, 4.5*unit, 4.5*unit, 4.5*unit);
		Line line5 = new Line(4.5*unit, 4.5*unit, 4.5*unit, 0.5*unit);
		Line line6 = new Line(4.5*unit, 0.5*unit, 6.5*unit, 0.5*unit);
		Line line7 = new Line(6.5*unit, 0.5*unit, 6.5*unit, 4.5*unit);
		Line line8 = new Line(6.5*unit, 4.5*unit, 10.5*unit, 4.5*unit);
		Line line9 = new Line(10.5*unit, 4.5*unit, 10.5*unit, 6.5*unit);
		Line line10 = new Line(10.5*unit, 6.5*unit, 6.5*unit, 6.5*unit);
		Line line11 = new Line(6.5*unit, 6.5*unit, 6.5*unit, 10.5*unit);
		Line line12 = new Line(6.5*unit, 10.5*unit, 4.5*unit, 10.5*unit);

		boardOfCircles = new CircleWithPos[40];

		boardOfCircles[0] = new CircleWithPos(4.5*unit, 10.5*unit, size/2, 0);
		boardOfCircles[1] = new CircleWithPos(4.5*unit, 9.5*unit, size/2, 1);
		boardOfCircles[2] = new CircleWithPos(4.5*unit, 8.5*unit, size/2, 2);
		boardOfCircles[3] = new CircleWithPos(4.5*unit, 7.5*unit, size/2, 3);
		boardOfCircles[4] = new CircleWithPos(4.5*unit, 6.5*unit, size/2, 4);
		boardOfCircles[5] = new CircleWithPos(3.5*unit, 6.5*unit, size/2, 5);
		boardOfCircles[6] = new CircleWithPos(2.5*unit, 6.5*unit, size/2, 6);
		boardOfCircles[7] = new CircleWithPos(1.5*unit, 6.5*unit, size/2, 7);
		boardOfCircles[8] = new CircleWithPos(0.5*unit, 6.5*unit, size/2, 8);
		boardOfCircles[9] = new CircleWithPos(0.5*unit, 5.5*unit, size/2, 9);

		boardOfCircles[10] = new CircleWithPos(0.5*unit, 4.5*unit, size/2, 10);
		boardOfCircles[11] = new CircleWithPos(1.5*unit, 4.5*unit, size/2, 11);
		boardOfCircles[12] = new CircleWithPos(2.5*unit, 4.5*unit, size/2, 12);
		boardOfCircles[13] = new CircleWithPos(3.5*unit, 4.5*unit, size/2, 13);
		boardOfCircles[14] = new CircleWithPos(4.5*unit, 4.5*unit, size/2, 14);
		boardOfCircles[15] = new CircleWithPos(4.5*unit, 3.5*unit, size/2, 15);
		boardOfCircles[16] = new CircleWithPos(4.5*unit, 2.5*unit, size/2, 16);
		boardOfCircles[17] = new CircleWithPos(4.5*unit, 1.5*unit, size/2, 17);
		boardOfCircles[18] = new CircleWithPos(4.5*unit, 0.5*unit, size/2, 18);
		boardOfCircles[19] = new CircleWithPos(5.5*unit, 0.5*unit, size/2, 19);

		boardOfCircles[20] = new CircleWithPos(6.5*unit, 0.5*unit, size/2, 20);
		boardOfCircles[21] = new CircleWithPos(6.5*unit, 1.5*unit, size/2, 21);
		boardOfCircles[22] = new CircleWithPos(6.5*unit, 2.5*unit, size/2, 22);
		boardOfCircles[23] = new CircleWithPos(6.5*unit, 3.5*unit, size/2, 23);
		boardOfCircles[24] = new CircleWithPos(6.5*unit, 4.5*unit, size/2, 24);
		boardOfCircles[25] = new CircleWithPos(7.5*unit, 4.5*unit, size/2, 25);
		boardOfCircles[26] = new CircleWithPos(8.5*unit, 4.5*unit, size/2, 26);
		boardOfCircles[27] = new CircleWithPos(9.5*unit, 4.5*unit, size/2, 27);
		boardOfCircles[28] = new CircleWithPos(10.5*unit, 4.5*unit, size/2, 28);
		boardOfCircles[29] = new CircleWithPos(10.5*unit, 5.5*unit, size/2, 29);

		boardOfCircles[30] = new CircleWithPos(10.5*unit, 6.5*unit, size/2, 30);
		boardOfCircles[31] = new CircleWithPos(9.5*unit, 6.5*unit, size/2, 31);
		boardOfCircles[32] = new CircleWithPos(8.5*unit, 6.5*unit, size/2, 32);
		boardOfCircles[33] = new CircleWithPos(7.5*unit, 6.5*unit, size/2, 33);
		boardOfCircles[34] = new CircleWithPos(6.5*unit, 6.5*unit, size/2, 34);
		boardOfCircles[35] = new CircleWithPos(6.5*unit, 7.5*unit, size/2, 35);
		boardOfCircles[36] = new CircleWithPos(6.5*unit, 8.5*unit, size/2, 36);
		boardOfCircles[37] = new CircleWithPos(6.5*unit, 9.5*unit, size/2, 37);
		boardOfCircles[38] = new CircleWithPos(6.5*unit, 10.5*unit, size/2, 38);
		boardOfCircles[39] = new CircleWithPos(5.5*unit, 10.5*unit, size/2, 39);

		for (CircleWithPos cir: boardOfCircles) {
			cir.setFill(Color.FLORALWHITE);
			cir.setStroke(Color.BLACK);
			cir.setStrokeWidth(2);
			cir.setOnMouseClicked(gui.circleClickedEventHandler);
			cir.setOnMouseEntered(gui.circleEnteredEventHandler);
			cir.setOnMouseExited(gui.circleExitedEventHandler);
		}

		getChildren().addAll(line0,line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12);
		getChildren().addAll(boardOfCircles);
	}

	private void initStreet() {
		recStreetGreen = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			recStreetGreen[i] = new Rectangle ((i+1)*unit+dif, 5*unit+dif, size, size);
			recStreetGreen[i].setFill(Color.FLORALWHITE);
			recStreetGreen[i].setStroke(Color.MEDIUMSEAGREEN);
			recStreetGreen[i].setStrokeWidth(3);
		}

		recStreetBlue = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			recStreetBlue[i] = new Rectangle (5*unit+dif, (i+1)*unit+dif, size, size);
			recStreetBlue[i].setFill(Color.FLORALWHITE);
			recStreetBlue[i].setStroke(Color.ROYALBLUE);
			recStreetBlue[i].setStrokeWidth(3);
		}

		recStreetRed = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			recStreetRed[i] = new Rectangle (width-(i+2)*unit+dif, 5*unit+dif, size, size);
			recStreetRed[i].setFill(Color.FLORALWHITE);
			recStreetRed[i].setStroke(Color.FIREBRICK);
			recStreetRed[i].setStrokeWidth(3);
		}

		recStreetYellow = new Rectangle[4];
		for(int i = 0; i < 4; i++) {
			recStreetYellow[i] = new Rectangle (5*unit+dif,width-(i+2)*unit+dif, size, size);
			recStreetYellow[i].setFill(Color.FLORALWHITE);
			recStreetYellow[i].setStroke(Color.GOLD);
			recStreetYellow[i].setStrokeWidth(3);
		}

		getChildren().addAll(recStreetGreen);
		getChildren().addAll(recStreetBlue);
		getChildren().addAll(recStreetRed);
		getChildren().addAll(recStreetYellow);
	}

	private void circleStreet() {
		circleStreetGreen = new CircleWithPos[4];
		for(int i = 0; i < 4; i++) {
			circleStreetGreen[i] = new CircleWithPos (((i+1)*unit+middle), (5*unit+middle), radius, i+50);
			circleStreetGreen[i].setFill(Color.MEDIUMSEAGREEN);
			circleStreetGreen[i].setOnMouseClicked(gui.circleClickedEventHandler);
			circleStreetGreen[i].setOnMouseEntered(gui.circleEnteredEventHandler);
			circleStreetGreen[i].setOnMouseExited(gui.circleExitedEventHandler);
			
		}

		circleStreetBlue = new CircleWithPos[4];
		for(int i = 0; i < 4; i++) {
			circleStreetBlue[i] = new CircleWithPos(5*unit+middle, (i+1)*unit+middle, radius, i+60);
			circleStreetBlue[i].setFill(Color.ROYALBLUE);
			circleStreetBlue[i].setOnMouseClicked(gui.circleClickedEventHandler);
			circleStreetBlue[i].setOnMouseEntered(gui.circleEnteredEventHandler);
			circleStreetBlue[i].setOnMouseExited(gui.circleExitedEventHandler);
		}

		circleStreetRed = new CircleWithPos[4];
		for(int i = 0; i < 4; i++) {
			circleStreetRed[i] = new CircleWithPos(width-(i+2)*unit+middle, 5*unit+middle, radius, i+70);
			circleStreetRed[i].setFill(Color.FIREBRICK);
			circleStreetRed[i].setOnMouseClicked(gui.circleClickedEventHandler);
			circleStreetRed[i].setOnMouseEntered(gui.circleEnteredEventHandler);
			circleStreetRed[i].setOnMouseExited(gui.circleExitedEventHandler);
		}

		circleStreetYellow = new CircleWithPos[4];
		for(int i = 0; i < 4; i++) {
			circleStreetYellow[i] = new CircleWithPos(5*unit+middle, width-(i+2)*unit+middle, radius, i+40);
			circleStreetYellow[i].setFill(Color.GOLD);
			circleStreetYellow[i].setOnMouseClicked(gui.circleClickedEventHandler);
			circleStreetYellow[i].setOnMouseEntered(gui.circleEnteredEventHandler);
			circleStreetYellow[i].setOnMouseExited(gui.circleExitedEventHandler);
		
		}

		getChildren().addAll(circleStreetGreen);
		getChildren().addAll(circleStreetBlue);
		getChildren().addAll(circleStreetRed);
		getChildren().addAll(circleStreetYellow);
	}

	private void circleHouse() {
		circleHouseGreen = new CircleWithPos[4];
		circleHouseGreen[0] = new CircleWithPos(0+middle, 0+middle, radius, 0);
		circleHouseGreen[1] = new CircleWithPos(unit+middle, 0+middle, radius, 1);
		circleHouseGreen[2] = new CircleWithPos(0+middle, unit+middle, radius, 2);
		circleHouseGreen[3] = new CircleWithPos(unit+middle, unit+middle,radius, 3);

		for (CircleWithPos cir : circleHouseGreen) {
			cir.setFill(Color.MEDIUMSEAGREEN);
		}

		circleHouseBlue = new CircleWithPos[4];
		circleHouseBlue[0] = new CircleWithPos(width-unit+middle, 0+middle, radius, 0);
		circleHouseBlue[1] = new CircleWithPos(width-2*unit+middle, 0+middle, radius, 1);
		circleHouseBlue[2] = new CircleWithPos(width-unit+middle, unit+middle, radius, 2);
		circleHouseBlue[3] = new CircleWithPos(width-2*unit+middle, unit+middle, radius, 3);

		for (CircleWithPos cir : circleHouseBlue) {
			cir.setFill(Color.ROYALBLUE);
		}

		circleHouseRed = new CircleWithPos[4];
		circleHouseRed[0] = new CircleWithPos(width-unit+middle, width-unit+middle, radius, 0);
		circleHouseRed[1] = new CircleWithPos(width-2*unit+middle, width-unit+middle, radius, 1);
		circleHouseRed[2] = new CircleWithPos(width-unit+middle, width-2*unit+middle, radius, 2);
		circleHouseRed[3] = new CircleWithPos(width-2*unit+middle, width-2*unit+middle, radius, 3);

		for(CircleWithPos cir : circleHouseRed) {
			cir.setFill(Color.FIREBRICK);
		}

		circleHouseYellow = new CircleWithPos[4];
		circleHouseYellow[0] = new CircleWithPos(0+middle, 9*unit+middle, radius, 0);
		circleHouseYellow[1] = new CircleWithPos(unit+middle, 9*unit+middle, radius, 1);
		circleHouseYellow[2] = new CircleWithPos(0+middle, 10*unit+middle, radius, 2);
		circleHouseYellow[3] = new CircleWithPos(unit+middle, 10*unit+middle, radius, 3);

		for(CircleWithPos cir : circleHouseYellow) {
			cir.setFill(Color.GOLD);
		}

		getChildren().addAll(circleHouseGreen);
		getChildren().addAll(circleHouseBlue);
		getChildren().addAll(circleHouseRed);
		getChildren().addAll(circleHouseYellow);
	}

	public void update(Board board) {
		
		for (int i = 0; i < boardOfCircles.length; i++) {
			switch(board.getPlayboard()[i]) {
			case FREE: boardOfCircles[i].setFill(Color.FLORALWHITE); break;
			case YELLOW: boardOfCircles[i].setFill(Color.GOLD); break;
			case GREEN: boardOfCircles[i].setFill(Color.MEDIUMSEAGREEN); break;
			case BLUE: boardOfCircles[i].setFill(Color.ROYALBLUE); break;
			case RED: boardOfCircles[i].setFill(Color.FIREBRICK); break;
			default: break;
			}
		}

		for (int i = 0; i < circleStreetGreen.length; i++) {
			if (board.getStreetG()[i] == Content.FREE){
				circleStreetGreen[i].setFill(Color.FLORALWHITE);
			} else {
				circleStreetGreen[i].setFill(Color.MEDIUMSEAGREEN);
			}
		}

		for (int i = 0; i < circleStreetBlue.length; i++) {
			if (board.getStreetB()[i] == Content.FREE) {
				circleStreetBlue[i].setFill(Color.FLORALWHITE);
			} else {
				circleStreetBlue[i].setFill(Color.ROYALBLUE);
			}
		}

		for (int i = 0; i < circleStreetRed.length; i++) {
			if (board.getStreetR()[i] == Content.FREE) {
				circleStreetRed[i].setFill(Color.FLORALWHITE);
			} else {
				circleStreetRed[i].setFill(Color.FIREBRICK);
			}
		}

		for (int i = 0; i < circleStreetYellow.length; i++) {
			if (board.getStreetY()[i] == Content.FREE) {
				circleStreetYellow[i].setFill(Color.FLORALWHITE);
			} else {
				circleStreetYellow[i].setFill(Color.GOLD);
			}
		}

		for (int i = 0; i < 4; i++) {
			circleHouseGreen[i].setFill(Color.FLORALWHITE);
		}

		for (int i = 0; i < 4; i++) {
			circleHouseBlue[i].setFill(Color.FLORALWHITE);
		}

		for (int i = 0; i < 4; i++) {
			circleHouseRed[i].setFill(Color.FLORALWHITE);
		}

		for (int i = 0; i < 4; i++) {
			circleHouseYellow[i].setFill(Color.FLORALWHITE);
		}

		for (int i = 0; i < board.getHouseG(); i++) {
			circleHouseGreen[i].setFill(Color.MEDIUMSEAGREEN);
		}

		for (int i = 0; i < board.getHouseB(); i++) {
			circleHouseBlue[i].setFill(Color.ROYALBLUE);
		}

		for (int i = 0; i < board.getHouseR(); i++) {
			circleHouseRed[i].setFill(Color.FIREBRICK);
		}

		for (int i = 0; i < board.getHouseY(); i++) {
			circleHouseYellow[i].setFill(Color.GOLD);
		}
	}
}

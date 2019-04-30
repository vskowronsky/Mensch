package model;

import java.io.Serializable;

import controller.game.Status;

public class Board implements Serializable {
	protected Content[] board;
	protected Content[] streetY;
	protected Content[] streetG;
	protected Content[] streetB;
	protected Content[] streetR;
	public final Position STARTY = new Position(0);
	public final Position STARTG = new Position(10);;
	public final Position STARTB = new Position(20);;
	public final Position STARTR = new Position(30);;
	public final Position ENDY = new Position(39);;
	public final Position ENDG = new Position(9);;
	public final Position ENDB = new Position(19);;
	public final Position ENDR = new Position(29);;
	protected int houseY;
	protected int houseG;
	protected int houseB;
	protected int houseR;
	protected int finishedY;
	protected int finishedG;
	protected int finishedB;
	protected int finishedR;
	protected transient Dice dice;
	protected int diceValue;

	public Board()
	{
		dice = new Dice();
		System.out.println("created board");
	}
	
	public Board(String test) {

		dice = new Dice(); 

		board = new Content[40];
		for (int i = 0; i < board.length; i++) {
			board[i] = Content.FREE;
		}

		streetY = new Content[4];
		for (int i = 0; i < streetY.length; i++) {
			streetY[i] = Content.FREE;
		}
		streetG = new Content[4];
		for (int i = 0; i < streetG.length; i++) {
			streetG[i] = Content.FREE;
		}
		streetB = new Content[4];
		for (int i = 0; i < streetB.length; i++) {
			streetB[i] = Content.FREE;
		}
		streetR = new Content[4];
		for (int i = 0; i < streetR.length; i++) {
			streetR[i] = Content.FREE;
		}
		//zum Testen von CheckThreeThrows; muss auf Null gesetzt werden!!
		houseY = 4;
		houseG = 4;
		houseB = 4;
		houseR = 4;

		finishedY = 0;
		finishedG = 0;
		finishedB = 0;
		finishedR = 0;	
	}

	public void diceThrow() {
		diceValue = dice.throwDice();	
	}

	public int getDiceValue() {
		return diceValue;
	}

	public boolean checkThreeThrows(Status status) {
		switch (status) {
		case PLAYER1: if(houseY + finishedY == 4) {return true;} break;
		case PLAYER2: if(houseG + finishedG == 4) {return true;} break;
		case PLAYER3: if(houseB + finishedB == 4) {return true;} break;
		case PLAYER4: if(houseR + finishedR == 4) {return true;} break;

		default: break;
		}
		return false;

	}

	public boolean checkNumHouse(Status status) {
		switch (status) {
		case PLAYER1: if (houseY != 0) {return true;} break;
		case PLAYER2: if (houseG != 0) {return true;} break;
		case PLAYER3: if (houseB != 0) {return true;} break;
		case PLAYER4: if (houseR != 0) {return true;} break;

		default: break;
		}
		return false;
	}

	public boolean checkDoubleDice(Status status) {
		switch (status) {
		case PLAYER1: if (houseY == 0) {return true;} break;
		case PLAYER2: if (houseG == 0) {return true;} break;
		case PLAYER3: if (houseB == 0) {return true;} break;
		case PLAYER4: if (houseR == 0) {return true;} break;

		default: break;
		}
		return false;
	}

	public boolean checkWin(Content content) {
		switch (content) {
		case YELLOW:
			for (Content street : streetY) {
				if (street == Content.FREE) {
					return false;
				}
			}
			return true;
		case GREEN:
			for (Content street : streetG) {
				if (street == Content.FREE) {
					return false;
				}
			}
			return true;
		case BLUE:
			for (Content street : streetB) {
				if (street == Content.FREE) {
					return false;
				}
			}
			return true;
		case RED:
			for (Content street : streetR) {
				if (street == Content.FREE) {
					return false;
				}
			}
			return true;
		default: return false;
		}
	}

	public Content checkPosition(Position chosenPosition, Content content) {
		if(chosenPosition.getIndex() > 50) {
			Position streetP = chosenPosition;
			switch(content) {
			case YELLOW: return streetY[streetP.getIndex()-50];
			case GREEN: return streetG[streetP.getIndex()-50];
			case BLUE: return streetB[streetP.getIndex()-50];
			case RED: return streetR[streetP.getIndex()-50];
			default: break;
			}
		}else {

			// Gibt den Inhalt im Board an der ausgewählen Position zurück
			return board[chosenPosition.getIndex()];
		}
		return null;
	}

	public String toString() {
		String path = "";
		path += "Anzahl Figuren zu Hause: \n";
		path += "Player1 (YELLOW): " + houseY + "\n";
		path += "Player2 (GREEN): " + houseG + "\n";
		path += "Player3 (BLUE): " + houseB + "\n";
		path += "Player4 (RED): " + houseR + "\n";
		path += "\n";
		path += "Spielbrett:";

		for (int i = 0; i < board.length; i++) {
			if (i % 10 == 0) {
				path += "\n" + board[i];
			} else {
				path += "\t" + board[i];
			}
		}

		path += "\n";
		path += "\n Ziel Player1 (YELLOW):\t";
		for (Content street : streetY) {
			path += street.toString() + "\t";
		}
		path += "\n Ziel Player2 (GREEN):\t";
		for (Content street : streetG) {
			path += street.toString() + "\t";
		}
		path += "\n Ziel Player3 (BLUE):\t";
		for (Content street : streetB) {
			path += street.toString() + "\t";
		}
		path += "\n Ziel Player4 (RED):\t";
		for (Content street : streetR) {
			path += street.toString() + "\t";
		}
		return path;
	}

}

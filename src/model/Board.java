package model;

public class Board {
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

	public Board() {

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

		houseY = 4;
		houseG = 4;
		houseB = 4;
		houseR = 4;
		
		
		//!!!!!! NUR ZUM TESTEN
//		board[5] = Content.GREEN;
//		board[2] = Content.YELLOW;
		
		
	}

	public int getHouseY() {
		return houseY;
	}

	public void setHouseY(int houseY) {
		this.houseY = houseY;
	}

	public int getHouseG() {
		return houseG;
	}

	public void setHouseG(int houseG) {
		this.houseG = houseG;
	}

	public int getHouseB() {
		return houseB;
	}

	public void setHouseB(int houseB) {
		this.houseB = houseB;
	}

	public int getHouseR() {
		return houseR;
	}

	public void setHouseR(int houseR) {
		this.houseR = houseR;
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

	public Content checkPosition(Position chosenPosition) {
		

		// Gibt den Inhalt im Board an der ausgewählen Position zurück
		return board[chosenPosition.getIndex()];
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

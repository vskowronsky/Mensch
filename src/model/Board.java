package model;

import java.io.Serializable;

import controller.game.Status;

public class Board implements Serializable {
/**
 * Konstruktor der Klasse Board. Beim Implementieren des Boardes wird ein Würfel implementiert, ein Array board, das 
 * das Spielfeld darstellt, vier weitere Arrays, die die Zielstraße des jeweiligen Spielers sind, sowie int-Werte für die jeweiligen
 * Häuser der Spieler (4) und finished (0), da noch kein Spielstein am Ziel angekommen ist.
 */
	private static final long serialVersionUID = -928528169455344979L;
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
	protected Dice dice;
	protected int diceValue;
	protected Position newPosition;
	boolean enforce = false;


	public Board() {

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
	
		houseY = 4;
		houseG = 4;
		houseB = 4;
		houseR = 4;

		finishedY = 0;
		finishedG = 0;
		finishedB = 0;
		finishedR = 0;	
	}

	/**
	 * Die Wüfel-Methode wird aufgerufen und die Würfelzahl in der deklarierten Variable gespeichert.
	 */
	public void diceThrow() {
		diceValue = dice.throwDice();	
	}

	public int getDiceValue() {
		return diceValue;
	}
	
	/**
	 * Mit checkThreeThrows wird überprüft, ob der Spieler dreimal würfeln darf. Dazu werden die Variablen house und finished 
	 * geprüft. 
	 */

	public boolean checkThreeThrows(Content content) {
		switch (content) {
		case YELLOW: if(houseY + finishedY == 4) {return true;} break;
		case GREEN: if(houseG + finishedG == 4) {return true;} break;
		case BLUE: if(houseB + finishedB == 4) {return true;} break;
		case RED: if(houseR + finishedR == 4) {return true;} break;

		default: break;
		}
		return false;

	}

	/**
	 * Methode, um zu überprüfen, ob bei einer 6 im Laufe des Spieles ein Spielstein aus dem Haus gezogen werden muss 
	 * 
	 */
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

	/**
	 * Methode, um zu überpüfen, ob bei einer gewürfelten 6, diese für einen regulären Zug genutzt werden darf.
	 * 
	 */
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
	
	/**
	 * Überprüfung, ob sich der Spielstein in der Nähe seiner Zielstraße befindet.
	 * 
	 *
	 */
	public boolean checkNearEnd(Content content, Position position) {
		switch(content) {
		case YELLOW: 
			if(position.getIndex() > 6 &&(position.getIndex()+diceValue > 39 && position.getIndex()+diceValue <= 45)) {return true;} break;
		case GREEN: 
			if(position.getIndex() <= 9 &&(position.getIndex()+diceValue > 9 && position.getIndex()+diceValue <= 15)) {return true;} break;
		case BLUE: 
			if(position.getIndex() <= 19 &&(position.getIndex()+diceValue > 19 && position.getIndex()+diceValue <= 25)) {return true;} break;
		case RED: 
			if(position.getIndex() <= 29 &&(position.getIndex()+diceValue > 29 && position.getIndex()+diceValue <= 35)) {return true;} break;
		default:
			break;
		}
		return false;
	}
	
	
	/**
	 * Überprüfung, ob sich auf der Startposition der jeweilige eigene Content befindet. Wenn dies stimmt, wird true zurückgegeben.
	 * 
	 */
	public boolean checkStartFree(Content content) {
		switch(content) {
		case YELLOW: if(board[0] == Content.YELLOW) {return true;} break;
		case GREEN: if(board[10] == Content.GREEN) {return true;} break;
		case BLUE: if(board[20] == Content.BLUE) {return true;} break;
		case RED: if(board[30] == Content.RED) {return true;} break;
		default: break;
		}
		return false;
	}
	
	public int checkEnemy(Content content, Position position) {
		if (board[position.getIndex()] != Content.FREE) {
			if (board[position.getIndex()] == content) {
				return 1;

			} else {
				return 2;
			}
		} else {
			return 0;
		}

	}

	/**
	 * Überprüfung, ob die Zielstraße voll ist. Dazu wird die finished-Variable überprüft. Diese gibt an, wie viele Spielsteine
	 * auf der richtigen Position stehen. Ein finished von 4 bedeutet eine volle Straße und der Spieler hat gewonnen.
	 * 
	 */
	public boolean checkWin(Content content) {
		switch (content) {
		case YELLOW:
			if(finishedY == 4) {
				return true;
				} else {
					return false;
				}
		case GREEN:
			if(finishedG == 4) {
				return true;
				} else {
					return false;
				}
		case BLUE:
			if(finishedB == 4) {
				return true;
				} else {
					return false;
				}
		case RED:
			if(finishedR == 4) {
				return true;
				} else {
					return false;
				}
		default: return false;
		}
	}

	/**
	 * Methode, um zu überprüfen, ob sich an der vom Spieler ausgewählten Position (Position chosenPosition) der Content 
	 * vom Spieler befindet. 
	 * Die Methode teilt sich in zwei Teile auf: Wenn eine Zahl größer als 40 geprüft wird, dann wird eine Position
	 * in der Zielstraße überprüft. Wenn die Zahl zwischen 0 und gleich 39 liegt, dann wird die Position auf dem Spielbrett
	 * überprüft. 
	 * Zurückgegeben wird der jeweilige Content auf der ausgewählten Position (entweder FREE, YELLOW, GREEN, BLUE oder RED).
	 * 
	 */
	public Content checkPosition(Position chosenPosition, Content content) {
		try {
			int streetPosition;
			if(chosenPosition.getIndex() >= 40) {

				switch(content) {
				case YELLOW:
					streetPosition = chosenPosition.getIndex()-40;
					if (finishedY + streetPosition >= 4) {
						return null;
					}else {
						return streetY[streetPosition];
					}
				case GREEN: 
					streetPosition = chosenPosition.getIndex()-50;
					if (finishedG + streetPosition >= 4) {
						return null;
					}else {
						return streetG[streetPosition];
					}
				case BLUE: 
					streetPosition = chosenPosition.getIndex()-60;
					if (finishedB + streetPosition >= 4) {
						return null;
					}else {
						return streetB[streetPosition];
					}
				case RED: 

					streetPosition = chosenPosition.getIndex()-70;
					if (finishedR + streetPosition >= 4) {
						return null;
					}else {
						return streetR[streetPosition];
					}
				default: break;
				}
			}else {

				// Gibt den Inhalt im Board an der ausgewählen Position zurück
				return board[chosenPosition.getIndex()];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
		return null;
	}

	/**
	 * Ausgabe des Spielfeldes.
	 */
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

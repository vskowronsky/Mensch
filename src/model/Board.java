package model;

import java.io.Serializable;
import java.util.Random;

import controller.game.Status;

/**
 * @author vanes
 *
 */
public class Board implements Serializable {

	private static final long serialVersionUID = -928528169455344979L;
	protected Content[] playboard;
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
	protected int diceValue;
	boolean enforce = false;

	/**
	 * Konstruktor der Klasse Board. Beim Aufruf des Konstrukturs wird ein Array playboard, das Spielfeld, sowie vier
	 * weitere Arrays, die Ziele der jeweiligen Farben, erzeugt. Zu dem werden alle Häuser, ein int, auf 4 gesetzt.
	 * Die Variable finished gibt an, wie viele Spielsteine an der richtigen Position im Ziel stehen. Beim ersten
	 * Aufruf des Boardes ist dies Null.
	 * @author Laura, Vanessa 
	 */
	public Board() {
		playboard = new Content[40];
		for (int i = 0; i < playboard.length; i++) {
			playboard[i] = Content.FREE;
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
	 * Methode, um zu würfeln. Dabei wird ein zufällige ganzzahlige Zahl von 1 bis 6 vom Board erzeugt.
	 * @author Laura
	 */
	public void diceThrow() {
		diceValue = new Random().nextInt(6) +1;	
	}

	/**
	 * Get-Methode der Würfelzahl.
	 * @return Die Würfelzahl als int
	 * @author Laura
	 */
	public int getDiceValue() {
		return diceValue;
	}
	
	/**
	 * Überprüfung, ob der Spieler dreimal würfeln darf. Dazu muss die Variable des Hauses und die finished-Variable
	 * gleich 4 sein.
	 * @param content Farbe des Spielers, der dran ist.
	 * @return boolean true wenn Haus + finished = 4, sonst false.
	 * @author Vanessa
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
	 * Methode, um zu überprüfen, ob bei einer 6 der Spieler aus dem Haus ziehen muss.
	 * @param status Spieler, der gerade am Zug ist.
	 * @return true, wenn er aus dem Haus ziehen muss, sonst false.
	 * @author Vanessa
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
	 * Methode, um zu überpüfen, ob eine gewürfelte 6 für einen regulären Zug genutzt werden darf.
	 * @param status Spieler, der gerade am Zug ist.
	 * @return true, wenn die 6 für einen regulären Zug genutzt werden darf, sonst false.
	 * @author Vanessa
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
	 * Dies wird mit einer verschachtelten UND-Verknüpfung überprüft. Dazu muss die Position, auf der sich die Spielfigur
	 * befindet, kleiner gleich ihrer Endposition sein. Damit ist die Spielfigur einmal um das Spielfeld gezogen.
	 * Die nächste Überprüfung ist, ob die neue Position (position.getIndex()+diceValue) größer als die Endposition 
	 * ist und gleichzeitig nicht größer gleich Endposition plus 6 (höchste Würfelzahl). 
	 * Gelb benötigt die erste UND-Verknüpfung nicht, da es gerade von 0 bis 39 über das Array läuft.
	 * Diese Methode wird benötigt, um später abfragen zu können, ob die Spielfigur ins Ziel abbiegen kann.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param position Position des ausgewählten Spielsteins.
	 * @return true, wenn die Spielfigur in der Nähe der Zielstraße ist, sonst false.
	 * @author Vanessa
	 */
	public boolean checkNearEnd(Content content, Position position) {
		switch(content) {
		case YELLOW: 
			if(position.getIndex()+diceValue > 39 && position.getIndex()+diceValue <= 45){
				return true;} 
			break;
		case GREEN: 
			if(position.getIndex() <= 9 &&(position.getIndex()+diceValue > 9 && position.getIndex()+diceValue <= 15)){
				return true;} 
			break;
		case BLUE: 
			if(position.getIndex() <= 19 &&(position.getIndex()+diceValue > 19 && position.getIndex()+diceValue <= 25)){
				return true;} 
			break;
		case RED: 
			if(position.getIndex() <= 29 &&(position.getIndex()+diceValue > 29 && position.getIndex()+diceValue <= 35)){
				return true;} 
			break;
		default:
			break;
		}
		return false;
	}
	
	
	/**
	 * Überprüfung, ob sich auf der Startposition der eigene Content befindet.
	 * @param content Farbe des Spielers, der dran ist.
	 * @return true, wenn es stimmt, sonst false
	 * @author Vanessa
	 */
	public boolean checkStartFree(Content content) {
		switch(content) {
		case YELLOW: if(playboard[0] == Content.YELLOW) {return true;} break;
		case GREEN: if(playboard[10] == Content.GREEN) {return true;} break;
		case BLUE: if(playboard[20] == Content.BLUE) {return true;} break;
		case RED: if(playboard[30] == Content.RED) {return true;} break;
		default: break;
		}
		return false;
	}
	
	/**
	 * Überprüfung, was sich auf der Position befindet.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param position Position, die überprüft werden soll.
	 * @return Bei 0 ist das Feld frei; bei 1 befindet sich dort der eigene Content;
	 * bei 3 ein gegnerischer Content
	 * @author Vanessa
	 */
	public int checkEnemy(Content content, Position position) {
		if (playboard[position.getIndex()] != Content.FREE) {
			if (playboard[position.getIndex()] == content) {
				return 1;
			} else {
				return 2;
			}
		} else {
			return 0;
		}
	}

	/**
	 * Überprüfung, ob gewonnen wurde.
	 * @param content Farbe des Spielers, der dran ist.
	 * @return true, wenn finished 4 ist, sonst false.
	 * @author Vanessa
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
	 * toString-Methode zur Ausgabe des Spielfeldes.
	 * @author Laura, Vanessa
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

		for (int i = 0; i < playboard.length; i++) {
			if (i % 10 == 0) {
				path += "\n" + playboard[i];
			} else {
				path += "\t" + playboard[i];
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

	
	public Content[] getPlayboard() {
		return playboard;
	}


	public Content[] getStreetY() {
		return streetY;
	}


	public Content[] getStreetG() {
		return streetG;
	}


	public Content[] getStreetB() {
		return streetB;
	}


	public Content[] getStreetR() {
		return streetR;
	}


	public int getHouseY() {
		return houseY;
	}


	public int getHouseG() {
		return houseG;
	}


	public int getHouseB() {
		return houseB;
	}


	public int getHouseR() {
		return houseR;
	}


	public int getFinishedY() {
		return finishedY;
	}


	public int getFinishedG() {
		return finishedG;
	}


	public int getFinishedB() {
		return finishedB;
	}


	public int getFinishedR() {
		return finishedR;
	}
}

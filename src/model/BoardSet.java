package model;

import controller.exceptions.MissedEnemyException;
import controller.exceptions.MoveStreetException;
import controller.exceptions.NoMoveException;
import controller.exceptions.OwnMeepleException;
import controller.game.Game;
import controller.game.Status;

import java.io.Serializable;

public class BoardSet extends Board implements Serializable {

	private static final long serialVersionUID = -6925781465692944476L;
	private Position newPosition;
	private boolean leftHouse;
	
	/**
	 * Konstruktor der Klasse BoardSet. Ruft den Konstruktor der übergeordneten Klasse auf.
	 */
	public BoardSet() {
		super();
	}

	/**
	 * Hauptmethode zum Setzen einer Spielfigur. 
	 * Dabei wird für die übergebene Position überprüft, ob diese eine Figur schlagen muss 
	 * oder ob sie eine Position ist, die in der Zielstraße gesetzt wird oder eine normale 
	 * Spielfigur auf dem Spielfeld.
	 * @param position Position der Spielfigur
	 * @param content Farbe des Spielers, der dran ist.
	 * @param game Das Spiel selber zum Nachrichtenaustausch.
	 * @return true, wenn der Spielstein gesetzt werden konnte.
	 * @throws OwnMeepleException
	 * @throws MoveStreetException
	 * @throws NoMoveException
	 * @throws MissedEnemyException
	 * @author Vanessa
	 */
	public boolean setMeeple(Position position, Content content, Game game) throws OwnMeepleException, 
	MoveStreetException, NoMoveException, MissedEnemyException {
		
		if (!leftHouse) enforcer(position, content);

		if(position.getIndex() >= 40){
			Position currentPos = positionInStreet(content, position);
			if (currentPos.getIndex() + diceValue < nextStreetMeeple(currentPos,content)) {
				setStreet(content, currentPos);
			} else {
				return false;
			}
		} else {
			if (position.getIndex() + diceValue >= 40) {
				newPosition = new Position((position.getIndex() + diceValue) - 40);
				if (checkNearEnd(content, position)) {
					enterStreet(position, content);
				} else {
					flingEnemy(content, game);
					playboard[(position.getIndex() + diceValue) - 40] = content;
				}
			} else {
				newPosition = new Position(position.getIndex() + diceValue);
				if (checkNearEnd(content, position)) {
					enterStreet(position, content);
				} else {
					flingEnemy(content, game);
					playboard[position.getIndex() + diceValue] = content;
				}
			} 
			if (diceValue != 0) {
				playboard[position.getIndex()] = Content.FREE;
			}
		}
		leftHouse = false;
		return true;
	}

	/**
	 * Schlagzwang-Methode. Überprüfung, ob eine gegnerische Spielfigur geworden werden kann.
	 * Inklusive Ahndung, wenn der Gegner nicht geworfen wurde. 
	 * @param position Position der Spielfigur
	 * @param content Farbe des Spielers, der dran ist.
	 * @throws MissedEnemyException
	 * @author Vanessa
	 */
	public void enforcer(Position position, Content content) throws MissedEnemyException {
		// enforce = true sagt, dass wir einen Meeple haben, der einen Gegner schlagen kann 
		enforce = false;

		for (int i = 0; i <=39; i++) {
			if (playboard[i] == content) {	
				if (i + diceValue >= 40) {
					if (checkEnemy(content, new Position((i + diceValue) - 40)) == 2) {
						enforce = true;
						break;
					}
				} else {
					if (checkEnemy(content, new Position(i + diceValue)) == 2) {
						enforce = true;
						break;
					}
				}
			}
		}

		if (position.getIndex() + diceValue >= 40) {
			if (checkEnemy(content, new Position((position.getIndex() + diceValue) - 40)) != 2 
					&& enforce && !checkNearEnd(content,position) && position.getIndex()<=40) {
				switch(content) {
				case YELLOW: houseY ++; break;
				case GREEN: houseG ++; break;
				case BLUE: houseB ++; break;
				case RED: houseR ++; break;
				default: break;
				}
				playboard[position.getIndex()]  = Content.FREE;
				throw new MissedEnemyException();
			}
		} else {
			if (checkEnemy(content, new Position(position.getIndex() + diceValue)) != 2 
					&& enforce && !checkNearEnd(content,position) && position.getIndex()<=40) {
				switch(content) {
				case YELLOW: houseY ++; break;
				case GREEN: houseG ++; break;
				case BLUE: houseB ++; break;
				case RED: houseR ++; break;
				default: break;
				}
				playboard[position.getIndex()] = Content.FREE;
				throw new MissedEnemyException();
			}
		}
	}

	/**
	 * Methode, zum Freimachen des Startfeldes.
	 * @param status Spieler, der gerade am Zug ist.
	 * @param game Das Spiel selber zum Nachrichtenaustausch.
	 * @throws OwnMeepleException
	 * @throws MoveStreetException
	 * @throws NoMoveException
	 * @throws MissedEnemyException
	 * @author Vanessa
	 */
	public void setStart(Status status, Game game) throws OwnMeepleException, MoveStreetException,
	NoMoveException, MissedEnemyException  {
		switch(status) {
		case PLAYER1: setMeeple(STARTY, Content.YELLOW, game); break;
		case PLAYER2: setMeeple(STARTG, Content.GREEN, game); break;
		case PLAYER3: setMeeple(STARTB, Content.BLUE, game); break;
		case PLAYER4: setMeeple(STARTR, Content.RED, game); break;
		default: break;
		}
	}

	/**
	 * Methode zum Setzen der Spielfigur in der Straße. 
	 * Inklusive Erhöhung der finished-Variable, wenn die Spielfigur an einer fixen Position
	 * angekommen ist.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param currentPos Position in der Straße.
	 * @author Vanessa
	 */
	public void setStreet(Content content, Position currentPos) {
		switch(content) {
		case YELLOW: 
			streetY[currentPos.getIndex() + diceValue] = content;
			streetY[currentPos.getIndex()] = Content.FREE;
			if(isFinished(currentPos.getIndex() + diceValue, content)){
				finishedY++;
			}
			break;
		case GREEN: 
			streetG[currentPos.getIndex() + diceValue] = content;
			streetG[currentPos.getIndex()] = Content.FREE;
			if(isFinished(currentPos.getIndex() + diceValue, content)){
				finishedG++;
			}
			break;
		case BLUE: 
			streetB[currentPos.getIndex() + diceValue] = content;
			streetB[currentPos.getIndex()] = Content.FREE;
			if(isFinished(currentPos.getIndex() + diceValue, content)){
				finishedB++;
			}
			break;
		case RED: 
			streetR[currentPos.getIndex() + diceValue] = content;
			streetR[currentPos.getIndex()] = Content.FREE;
			if(isFinished(currentPos.getIndex() + diceValue, content)){
				finishedR++;
			}
			break;
		default: break;
		}
	}

	/**
	 * Methode zur Erstellung einer neuen Position in der Zielstraße.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param position Position der Spielfigur
	 * @return neue Position der Spielfigur 
	 * @author Vanessa
	 */
	public Position positionInStreet(Content content, Position position) {
		switch(content) {
		case YELLOW: return (new Position(position.getIndex() - 40));
		case GREEN: return (new Position(position.getIndex() - 50)); 
		case BLUE: return (new Position(position.getIndex() - 60)); 
		case RED: return (new Position(position.getIndex() - 70));
		default: return null;
		}
	}

	/**
	 * Methode überprüft von der Stelle an der die Spielfigur sich befindet, ob es bis zum Ende des
	 * Ziels noch ein freies Feld gibt.
	 * @param diff Position, an der sich die Spielfigur befindet
	 * @param content Farbe des Spielers, der dran ist.
	 * @return false, wenn es noch ein freies Feld gibt, sonst true
	 * @author Vanessa
	 */
	public boolean isFinished(int diff, Content content) {
		switch (content) {
		case YELLOW: for (int i = diff; i < 4; i++) {
			if(streetY[i] == Content.FREE){
				return false;
			}
		} return true;
		case GREEN: for (int i = diff; i < 4; i++) {
			if(streetG[i] == Content.FREE){
				return false;
			}
		} return true;
		case BLUE: for (int i = diff; i < 4; i++) {
			if(streetB[i] == Content.FREE){
				return false;
			}
		} return true;
		case RED: for (int i = diff; i < 4; i++) {
			if(streetR[i] == Content.FREE){
				return false;
			}
		} return true;
		default: return false;
		}
	}

	/**
	 * Methode überprüft, wo sich die nächste Spielfigur von der ausgewählten Figur aus befindet.
	 * Dadurch wird ein Überspringen im Ziel nicht möglich.
	 * @param position Position der Spielfigur
	 * @param content Farbe des Spielers, der dran ist.
	 * @return Position der nächsten Spielfigur
	 */
	public int nextStreetMeeple(Position position, Content content) {
		int nextPos = position.getIndex()+1;
		switch (content) {
		case YELLOW:
			for(int i = nextPos; i < 4; i++) {
				if(streetY[i] == Content.YELLOW) {return i;}	
			}
			return 4;
		case GREEN:
			for(int i = nextPos; i < 4; i++) {
				if(streetG[i] == Content.GREEN) {return i;}	
			}
			return 4;
		case BLUE:
			for(int i = nextPos; i < 4; i++) {
				if(streetB[i] == Content.BLUE) {return i;}	
			}
			return 4;
		case RED:
			for(int i = nextPos; i < 4; i++) {
				if(streetR[i] == Content.RED) {return i;}	
			}
			return 4;
		default: return -1;
		} 
	}

	/**
	 * Methode prüft wie weit die Spielfigur in die Zielstraße gehen kann und setzt diese wenn möglich.
	 * @param position Position der Spielfigur
	 * @param content Farbe des Spielers, der dran ist
	 * @throws MoveStreetException
	 * @author Vanessa
	 */
	public void enterStreet(Position position, Content content) throws MoveStreetException {
		int diff;
		switch(content) {
		case YELLOW: 
			diff = (diceValue - (ENDY.getIndex() - position.getIndex())-1);
			if (diff < nextStreetMeeple(new Position(-1),content))  {
				streetY[diff] = content; 
			} else {
				throw new MoveStreetException();
			}
			if (isFinished(diff, content)) {
				finishedY++;
			}
			break;
		case GREEN:
			diff = (diceValue - (ENDG.getIndex() - position.getIndex())-1);
			if (diff < nextStreetMeeple(new Position(-1),content))  {
				streetG[diff] = content; 
			} else {
				throw new MoveStreetException();
			}
			if (isFinished(diff, content)) {
				finishedG++;
			}
			break;
		case BLUE:
			diff = (diceValue - (ENDB.getIndex() - position.getIndex())-1);
			if (diff < nextStreetMeeple(new Position(-1),content))  {
				streetB[diff] = content; 
			} else {
				throw new MoveStreetException();
			}
			if (isFinished(diff, content)) {
				finishedB++;
			}
			break;
		case RED:
			diff = (diceValue - (ENDR.getIndex() - position.getIndex())-1);
			if (diff < nextStreetMeeple(new Position(-1),content))  {
				streetR[diff] = content; 
			} else {
				throw new MoveStreetException();
			}
			if (isFinished(diff, content)) {
				finishedR++;
			}
			break;
		default: break;
		}	
	}

	
	/**
	 * Methode, um einen Gegner zu werfen. 
	 * Dabei wird der, der geschlagen hat informiert sowie der, der geschlagen wurde.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param game Das Spiel selber zum Nachrichtenaustausch.
	 * @throws OwnMeepleException
	 * @author Vanessa
	 */
	public void flingEnemy(Content content, Game game) throws OwnMeepleException {
		if (checkEnemy(content, newPosition) == 1) {
			throw new OwnMeepleException();
		} else if (checkEnemy(content, newPosition) == 2) {
			String message = "Sie haben eine Figur geworfen.";
			String enemyMessage = "Sie wurden von "+ content.toString() + " geworfen.";
			
			switch(playboard[newPosition.getIndex()]) {
			case YELLOW: houseY++; game.message(message); break;
			case GREEN: houseG++; game.message(message); break;
			case BLUE: houseB++; game.message(message); break;
			case RED: houseR++; game.message(message); break;
			default: break;}
			
			game.enemyMessage(playboard[newPosition.getIndex()], enemyMessage);
		}
	}

/**
 * Methode zum Verlassen des Hauses. 
 * Falls dreimalgewürfelt werden durfte, wird hier dreimal gewürfelt. 
 * Die Spielfigur wird automatisch auf ihr Startfeld und beim nächsten Zug nach 
 * vorne gesetzt.
 * @param status Spieler, der gerade am Zug ist.
 * @param game Das Spiel selber zum Nachrichtenaustausch.
 * @throws OwnMeepleException
 * @throws MoveStreetException
 * @throws NoMoveException
 * @throws MissedEnemyException
 * @author Vanessa
 */
	
	public void leaveHouse(Status status, Game game) throws OwnMeepleException, MoveStreetException, 
	NoMoveException, MissedEnemyException {
		int throwCount = 1;
		switch (status) {
		case PLAYER1:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				game.message("Sie haben eine " + diceValue + " gewürfelt.");
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTY, Content.YELLOW, game);
					houseY--;
					game.message("Sie ziehen aus dem Haus.");
					diceThrow();
					game.pause(1000);
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					leftHouse = true;
					setMeeple(STARTY, Content.YELLOW, game);
					break;
				} else {
					game.pause(1000);
					throwCount++;
					diceThrow();
					if (throwCount == 3 && diceValue != 6) {
						game.message("Sie haben eine " + diceValue + " gewürfelt.");
						game.message("Es wurde keine 6 gewürfelt. Versuchen Sie es in der nächsten Runde nochmal.");
					}
				}
			}
			break;

		case PLAYER2:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				game.message("Sie haben eine " + diceValue + " gewürfelt.");
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTG, Content.GREEN, game);
					houseG--;
					diceThrow();
					game.pause(1000);
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					leftHouse = true;
					setMeeple(STARTG, Content.GREEN, game);
					break;
				} else {
					game.pause(1000);
					throwCount++;
					diceThrow();
					if (throwCount == 3 && diceValue != 6) {
						game.message("Sie haben eine " + diceValue + " gewürfelt.");
						game.message("Es wurde keine 6 gewürfelt. Versuchen Sie es in der nächsten Runde nochmal.");
					}
				}
			}
			break;

		case PLAYER3:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				game.message("Sie haben eine " + diceValue + " gewürfelt.");
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTB, Content.BLUE, game);
					houseB--;
					diceThrow();
					game.pause(1000);
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					leftHouse = true;
					setMeeple(STARTB, Content.BLUE, game);
					break;
				} else {
					game.pause(1000);
					throwCount++;
					diceThrow();
					if (throwCount == 3 && diceValue != 6) {
						game.message("Sie haben eine " + diceValue + " gewürfelt.");
						game.message("Es wurde keine 6 gewürfelt. Versuchen Sie es in der nächsten Runde nochmal.");
					}
				}
			}
			break;

		case PLAYER4:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				game.message("Sie haben eine " + diceValue + " gewürfelt.");
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTR, Content.RED, game);
					houseR--;
					diceThrow();
					game.pause(1000);
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					leftHouse = true;
					setMeeple(STARTR, Content.RED, game);
					break;
				} else {
					game.pause(1000);
					throwCount++;
					diceThrow();
					if (throwCount == 3 && diceValue != 6) {
						game.message("Sie haben eine " + diceValue + " gewürfelt.");
						game.message("Es wurde keine 6 gewürfelt. Versuchen Sie es in der nächsten Runde nochmal.");
					}
				}
			}	
			break;
		default: break;
		}
	}
	
	/**
	 * Set-Methode der boolean-Variabel leftHouse
	 * @author Vanessa
	 */
	public void setLeftHouseFalse() {
		leftHouse = false;
	}
}

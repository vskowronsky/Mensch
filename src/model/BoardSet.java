package model;

import controller.MoveException;
import controller.OwnMeepleException;
import controller.game.Game;
import controller.game.Status;

//import java.io.Serializable;

public class BoardSet extends Board /*implements Serializable*/ {

	//private static final long serialVersionUID = -6925781465692944476L;
	private Position newPosition;
	public BoardSet() {
		super();
	}

	public boolean setMeeple(Position position, Content content, Game game) throws OwnMeepleException, MoveException {

		if (position.getIndex() + diceValue >= 40) {
			newPosition = new Position((position.getIndex() + diceValue) - 40);
			if (checkNearEnd(content, position)) {
				enterStreet(position, content);
				//game.endMessage();
			} else {
				checkEnemy(content, game);
				board[(position.getIndex() + diceValue) - 40] = content;
			}
		}else {
			newPosition = new Position(position.getIndex() + diceValue);
			if (checkNearEnd(content, position)) {
				enterStreet(position, content);
				//game.endMessage();
			} else {
				checkEnemy(content, game);
				board[position.getIndex() + diceValue] = content;
			}
		}


		if(diceValue != 0) {
			board[position.getIndex()] = Content.FREE;
		}

		return true;
	}
//Methode, um zu prüfen, ob es leeren Stellen von meiner Position aus bis zum Ende gibt 
//Kann der Meeple noch vor ziehen?
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
	
	public int nextStreetMeeple(Content content) {
		switch (content) {
		case YELLOW:
			for(int i = 0; i < 4; i++) {
				if(streetY[i] == Content.YELLOW) {
					return i;
				}	
			}
			return 4;
		case GREEN:
			for(int i = 0; i < 4; i++) {
				if(streetG[i] == Content.GREEN) {
					return i;
				}	
			}
			return 4;
		case BLUE:
			for(int i = 0; i < 4; i++) {
				if(streetB[i] == Content.BLUE) {
					return i;
				}	
			}
			return 4;
		case RED:
			for(int i = 0; i < 4; i++) {
				if(streetR[i] == Content.RED) {
					return i;
				}	
			}
			return 4;
			
			default: return -1;
			} 
	}

	public void enterStreet(Position position, Content content) throws MoveException {
		int diff;
		switch(content) {
		case YELLOW: 
			diff = (diceValue - (ENDY.getIndex() - position.getIndex())-1);
			if (diff < nextStreetMeeple(content))  {
				streetY[diff] = content; 
			} else {
				throw new MoveException();
			}
			
			if (isFinished(diff, content)) {
				finishedY++;
			}
			break;
		case GREEN:
			diff = (diceValue - (ENDG.getIndex() - position.getIndex())-1);
			streetG[diff] = content; 
			break;
		case BLUE:
			diff = (diceValue - (ENDB.getIndex() - position.getIndex())-1);
			streetB[diff] = content; 
			break;
		case RED:
			diff = (diceValue - (ENDR.getIndex() - position.getIndex())-1);
			streetR[diff] = content; 
			break;
		default: break;
		}	
	}

	public void checkEnemy(Content content, Game game) throws OwnMeepleException {
		if (board[newPosition.getIndex()] != Content.FREE) {
			if (board[newPosition.getIndex()] == content) {
				throw new OwnMeepleException();

			} else {
				switch(board[newPosition.getIndex()]) {
				case YELLOW: houseY++; game.enemyMessage(); break;
				case GREEN: houseG++; game.enemyMessage(); break;
				case BLUE: houseB++; game.enemyMessage(); break;
				case RED: houseR++; game.enemyMessage(); break;
				default:
					break;}
			}
		}
	}

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

	public void leaveHouse(Status status, Game game) throws OwnMeepleException, MoveException {
		int throwCount = 1;
		switch (status) {
		case PLAYER1:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6) || (throwCount ==2 && diceValue == 6)) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTY, Content.YELLOW, game);
					houseY--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.diceMessage();
					setMeeple(STARTY, Content.YELLOW, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.diceMessage();
				}
			}
			break;

		case PLAYER2:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6) ) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTG, Content.GREEN, game);
					houseG--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.diceMessage();
					setMeeple(STARTG, Content.GREEN, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.diceMessage();
				}
			}
			break;

		case PLAYER3:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6) ) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTB, Content.BLUE, game);
					houseB--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.diceMessage();
					setMeeple(STARTB, Content.BLUE, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.diceMessage();
				}
			}
			break;

		case PLAYER4:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6) ) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTR, Content.RED, game);
					houseR--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.diceMessage();
					setMeeple(STARTR, Content.RED, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.diceMessage();
				}
			}		
		default: 
		}
	}
}

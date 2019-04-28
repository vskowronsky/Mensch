package model;

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

	public boolean setMeeple(Position position, Content content, Game game) throws OwnMeepleException {

		if (position.getIndex() + diceValue >= 40) {
			newPosition = new Position((position.getIndex() + diceValue) - 40);
			if (checkNearEnd(content, position)) {
				enterStreet(position, content);
				game.endMessage();
			} else {
				checkEnemy(content, game);
				board[(position.getIndex() + diceValue) - 40] = content;
			}
		}else {
			newPosition = new Position(position.getIndex() + diceValue);
			if (checkNearEnd(content, position)) {
				enterStreet(position, content);
				game.endMessage();
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

	public void enterStreet(Position position, Content content) {
		int diff;
		switch(content) {
		case YELLOW: 
			
			diff = (diceValue - (ENDY.getIndex() - position.getIndex())-1);
			streetY[diff] = content; 
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

	public void leaveHouse(Status status, Game game) throws OwnMeepleException {
		int throwCount = 1;
		switch (status) {
		case PLAYER1:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6) ) {
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

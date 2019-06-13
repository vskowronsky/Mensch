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
	public BoardSet() {
		super();
	}

	

	public boolean setMeeple(Position position, Content content, Game game) throws OwnMeepleException, 
	MoveStreetException, NoMoveException, MissedEnemyException {


		enforcer(position, content);
	
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
		return true;
	}

	// Schlagzwangüberprüfung
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

	//	public boolean movePossible(Content content, Position position, Game game) {
	//		//ist setMeeple für irgendeins der Figuren der Farbe möglich?
	//		int meeple = 1;
	//		int currentMeeple = 1;
	//		for (int i = 0; i<= 74; i++) {
	//			if(checkPosition(new Position (i), content) == content && meeple == currentMeeple) {
	//				try {
	//					if(!setMeeple(position, content, game)) {
	//						return true;
	//					} else {
	//						throw new NoMoveException();
	//					}
	//				
	//			}finally {
	//				
	//			}
	//				}else if (checkPosition(new Position (i), content) == content) {
	//				currentMeeple++;
	//			}
	//		}
	//		return true;
	//	}

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

	public Position positionInStreet(Content content, Position position) {
		switch(content) {
		case YELLOW: return (new Position(position.getIndex() - 40));
		case GREEN: return (new Position(position.getIndex() - 50)); 
		case BLUE: return (new Position(position.getIndex() - 60)); 
		case RED: return (new Position(position.getIndex() - 70));

		default: return null;
		}
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

	public int nextStreetMeeple(Position position, Content content) {
		int nextPos = position.getIndex()+1;
		switch (content) {
		case YELLOW:
			for(int i = nextPos; i < 4; i++) {
				if(streetY[i] == Content.YELLOW) {
					return i;
				}	
			}
			return 4;
		case GREEN:
			for(int i = nextPos; i < 4; i++) {
				if(streetG[i] == Content.GREEN) {
					return i;
				}	
			}
			return 4;
		case BLUE:
			for(int i = nextPos; i < 4; i++) {
				if(streetB[i] == Content.BLUE) {
					return i;
				}	
			}
			return 4;
		case RED:
			for(int i = nextPos; i < 4; i++) {
				if(streetR[i] == Content.RED) {
					return i;
				}	
			}
			return 4;

		default: return -1;
		} 
	}

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

	public void flingEnemy(Content content, Game game) throws OwnMeepleException {

		if (checkEnemy(content, newPosition) == 1) {
			throw new OwnMeepleException();
		}else if (checkEnemy(content, newPosition) == 2) {
			
			String message = "Eine Figur wurde geworfen.";
			switch(playboard[newPosition.getIndex()]) {
			case YELLOW: houseY++; game.message(message); break;
			case GREEN: houseG++; game.message(message); break;
			case BLUE: houseB++; game.message(message); break;
			case RED: houseR++; game.message(message); break;
			default:
				break;}
		}

	}


	public void leaveHouse(Status status, Game game) throws OwnMeepleException, MoveStreetException, 
	NoMoveException, MissedEnemyException {
		int throwCount = 1;
		switch (status) {
		case PLAYER1:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTY, Content.YELLOW, game);
					houseY--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					setMeeple(STARTY, Content.YELLOW, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
				}
			}
			break;

		case PLAYER2:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTG, Content.GREEN, game);
					houseG--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					setMeeple(STARTG, Content.GREEN, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
				}
			}
			break;

		case PLAYER3:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTB, Content.BLUE, game);
					houseB--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					setMeeple(STARTB, Content.BLUE, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					
				}
			}
			break;

		case PLAYER4:
			while (throwCount < 3 || (throwCount == 3 && diceValue == 6)) {
				if (diceValue == 6) {
					diceValue = 0;
					setMeeple(STARTR, Content.RED, game);
					houseR--;
					diceValue = dice.throwDice();
					//Benachrichtigt die Spiellogik, dem Spieler mitzuteilen,
					// dass der Würfel neu gesetzt wurde;
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
					setMeeple(STARTR, Content.RED, game);
					break;
				} else {
					throwCount++;
					diceValue = dice.throwDice();
					game.message("Sie haben eine " + diceValue + " gewürfelt.");
				}
			}	
			break;
		default: break;
		}
	}
}

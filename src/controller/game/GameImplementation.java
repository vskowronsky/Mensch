package controller.game;

import controller.MissedEnemyException;
import controller.MoveStreetException;
import controller.NoMoveException;
import controller.OwnMeepleException;
import controller.player.Player;
import model.*;

public class GameImplementation implements Game {
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private BoardSet board;
	private Status status;
	private boolean error;
	private boolean first;
	int counter;

	public GameImplementation(Player player1, Player player2, Player player3, Player player4) {
		board = new BoardSet();
		this.player1 = player1;
		this.player2 = player2;
		this.player3 = player3;
		this.player4 = player4;
		this.player1.initialize(Content.YELLOW, this, 1);
		this.player2.initialize(Content.GREEN, this, 2);
		this.player3.initialize(Content.BLUE, this, 3);
		this.player4.initialize(Content.RED, this, 4);
		this.status = Status.PLAYER1;
		player1.enable();
	}

	public void start() {
		System.out.println("Das Spiel wurde erfolgreich geladen.");
		if (status == Status.PLAYER1) {
			player2.disable();
			player3.disable();
			player4.disable();
			player1.enable();
		} else if (status == Status.PLAYER2) {
			player1.disable();
			player3.disable();
			player4.disable();
			player2.enable();
		} else if (status == Status.PLAYER3) {
			player1.disable();
			player2.disable();
			player4.disable();
			player3.enable();
		} else if (status == Status.PLAYER4) {
			player1.disable();
			player2.disable();
			player3.disable();
			player4.enable();
		}
	}

	@Override
	public void update() {
counter++;
		board.diceThrow();
		first = true;
		
		do {
			try {
				error = true;
				switch (status) {
				case PLAYER1:
					if (board.checkStartFree(Content.YELLOW) && first) {
						first = false;
						board.setStart(status, this);
						player1.freeStart();
					} else 
						if (board.checkThreeThrows(Content.YELLOW)) {
						player1.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player1.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this);
						
						board.diceThrow();
						player1.doubleDiceResult();
						
						board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this);
					}else {
						if (board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this)) {
							
						} else {
							throw new MoveStreetException();						
						}
					}
					status = Status.PLAYER2;
					player1.disable();
					break;

				case PLAYER2:
					if (board.checkStartFree(Content.GREEN) && first) {
						first = false;
						board.setStart(status, this);
						player2.freeStart();
					} else 
						if (board.checkThreeThrows(Content.GREEN)) {
						player2.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player2.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player2.chooseMeeple(), Content.GREEN, this);
						
						board.diceThrow();
						player2.doubleDiceResult();
						
						board.setMeeple(player2.chooseMeeple(), Content.GREEN, this);
					} else {
						if (board.setMeeple(player2.chooseMeeple(), Content.GREEN, this)) {
							
						} else {
							throw new MoveStreetException();
						}
					}
					status = Status.PLAYER3;
					player2.disable();
					break;

				case PLAYER3:
					if (board.checkStartFree(Content.BLUE) && first) {
						first = false;
						board.setStart(status, this);
						player3.freeStart();
					} else 
						if (board.checkThreeThrows(Content.BLUE)) {
						player3.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player3.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player3.chooseMeeple(), Content.BLUE, this);
						
						board.diceThrow();
						player3.doubleDiceResult();
						
						board.setMeeple(player3.chooseMeeple(), Content.BLUE, this);	
						
					} else {
						if (board.setMeeple(player3.chooseMeeple(), Content.BLUE, this)) {
							
						} else {
							throw new MoveStreetException();
						}
					}
					status = Status.PLAYER4;
					player3.disable();
					break;

				case PLAYER4:
					if (board.checkStartFree(Content.RED) && first) {
						first = false;
						board.setStart(status, this);
						player4.freeStart();
					} else 
						if (board.checkThreeThrows(Content.RED)) {
						player4.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player4.diceResult();
						first = false;
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player4.chooseMeeple(), Content.RED, this);
						
						board.diceThrow();
						player4.doubleDiceResult();
						
						board.setMeeple(player4.chooseMeeple(), Content.RED, this);
					} else {
						if (board.setMeeple(player4.chooseMeeple(), Content.RED, this)) {
							
						} else {
							
							throw new MoveStreetException();
						}
					}
					status = Status.PLAYER1;
					player4.disable();
					break;
					
					default: break;
				}
				error = false;

			} catch (OwnMeepleException e) {
				ownMeepleMessage();
			}
			catch (MoveStreetException e) {
				moveNotPossibleMessage();
			}
			catch (MissedEnemyException e) {
				error = false;
				
				switch(status) {
				case PLAYER1:
					player1.missedEnemyResult();
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.missedEnemyResult();
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.missedEnemyResult();
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.missedEnemyResult();
					status = Status.PLAYER1;
					player4.disable();
					break;
				default: break;
				}
			}
			catch (NoMoveException e) {
				error = false;
				
				switch(status) {
				case PLAYER1:
					player1.noMoveAtAll();
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.noMoveAtAll();
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.noMoveAtAll();
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.noMoveAtAll();
					status = Status.PLAYER1;
					player4.disable();
					break;
				default: break;
				}
			}
		} while (error);

		if (board.checkWin(Content.YELLOW)) {
			status = Status.WINPLAYER1;
			player1.win();
			player2.lose();
			player3.lose();
			player4.lose();
			System.out.println(counter);
		} else if (board.checkWin(Content.GREEN)) {
			status = Status.WINPLAYER2;
			player2.win();
			player3.lose();
			player4.lose();
			player1.lose();
			System.out.println(counter);
		} else if (board.checkWin(Content.BLUE)) {
			status = Status.WINPLAYER3;
			player3.win();
			player4.lose();
			player1.lose();
			player2.lose();
			System.out.println(counter);
		} else if (board.checkWin(Content.RED)) {
			status = Status.WINPLAYER4;
			player4.win();
			player1.lose();
			player2.lose();
			player3.lose();
			System.out.println(counter);
		}

		if (status == Status.PLAYER1) {
			player1.enable();
		} else if (status == Status.PLAYER2) {
			player2.enable();
		} else if (status == Status.PLAYER3) {
			player3.enable();
		} else if (status == Status.PLAYER4) {
			player4.enable();
		}

		
	}

	public void save(String fileName) {
		PersistenceObject po = new PersistenceObject(status, board);
		SaveLoad.save(po, fileName);
	}

	public void load(String fileName) {
		PersistenceObject po = SaveLoad.load(fileName);
		status = po.getStatus();
		board = po.getBoard();
		System.out.println(board);
	}

	public Board getBoard() {
		return board;
	}

	public Content checkPosition(Position chosenPosition, Content content) {
		return board.checkPosition(chosenPosition, content);
	}

	public int dice() {
		return board.getDiceValue();
	}

	
	public void diceMessage() {
		switch (status) {
		case PLAYER1: player1.diceResult(); break;
		case PLAYER2: player2.diceResult(); break;
		case PLAYER3: player3.diceResult(); break;
		case PLAYER4: player4.diceResult(); break;
		default: break;
		}
	}

	public void moveNotPossibleMessage() {
		switch (status) {
		case PLAYER1: player1.moveNotPossible(); break;
		case PLAYER2: player2.moveNotPossible(); break;
		case PLAYER3: player3.moveNotPossible(); break;
		case PLAYER4: player4.moveNotPossible(); break;
		default: break;
		}
	}

	public void ownMeepleMessage() {
		switch (status) {
		case PLAYER1: player1.throwOwnMeeple(); break;
		case PLAYER2: player2.throwOwnMeeple(); break;
		case PLAYER3: player3.throwOwnMeeple(); break;
		case PLAYER4: player4.throwOwnMeeple(); break;
		default: break;
		}
	}

	public void enemyMessage() {
		switch (status) {
		case PLAYER1: player1.enemyResult(); break;
		case PLAYER2: player2.enemyResult(); break;
		case PLAYER3: player3.enemyResult(); break;
		case PLAYER4: player4.enemyResult(); break;
		default: break;
		}
	}
	
//	public void missedEnemyMessage() {
//		switch (status) {
//		case PLAYER1: player1.missedEnemyResult(); break;
//		case PLAYER2: player2.missedEnemyResult(); break;
//		case PLAYER3: player3.missedEnemyResult(); break;
//		case PLAYER4: player4.missedEnemyResult(); break;
//		default: break;
//		}
//	}

}

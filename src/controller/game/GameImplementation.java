package controller.game;

import controller.IO;
import controller.MoveStreetException;
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

		board.diceThrow();

		do {
			try {
				error = true;
				switch (status) {
				case PLAYER1:
					if (board.checkThreeThrows(status)) {
						player1.diceResult();
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player1.diceResult();
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this);
						
						board.diceThrow();
						player1.doubleDiceResult();
						
						board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this);
					}else {
						if (board.setMeeple(player1.chooseMeeple(), Content.YELLOW, this)) {

						} else {
							// player.nachrichtSpielzugNichtMoeglich();
						}
					}
					status = Status.PLAYER2;
					player1.disable();
					break;

				case PLAYER2:
					if (board.checkThreeThrows(status)) {
						player2.diceResult();
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player2.diceResult();
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player2.chooseMeeple(), Content.GREEN, this);
						
						board.diceThrow();
						player2.doubleDiceResult();
						
						board.setMeeple(player2.chooseMeeple(), Content.GREEN, this);
					} else {
						if (board.setMeeple(player2.chooseMeeple(), Content.GREEN, this)) {
							
						} else {
							// player.nachrichtSpielzugNichtMoeglich();
						}
					}
					status = Status.PLAYER3;
					player2.disable();
					break;

				case PLAYER3:
					if (board.checkThreeThrows(status)) {
						player3.diceResult();
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player3.diceResult();
						board.leaveHouse(status, this);
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player3.chooseMeeple(), Content.BLUE, this);
						
						board.diceThrow();
						player3.doubleDiceResult();
						
						board.setMeeple(player3.chooseMeeple(), Content.BLUE, this);	
						
					} else {
						if (board.setMeeple(player3.chooseMeeple(), Content.BLUE, this)) {

						} else {
							// player.nachrichtSpielzugNichtMoeglich();
						}
					}
					status = Status.PLAYER4;
					player3.disable();
					break;

				case PLAYER4:
					if (board.checkThreeThrows(status)) {
						player4.diceResult();
						board.leaveHouse(status, this);
					} else if (dice() == 6 && board.checkNumHouse(status)) {
						player4.diceResult();
						board.leaveHouse(status, this);
						
					} else if (dice () == 6 && board.checkDoubleDice(status)) {
						board.setMeeple(player4.chooseMeeple(), Content.RED, this);
						
						board.diceThrow();
						player4.doubleDiceResult();
						
						board.setMeeple(player4.chooseMeeple(), Content.RED, this);
					} else {
						if (board.setMeeple(player4.chooseMeeple(), Content.RED, this)) {

						} else {
							// player.nachrichtSpielzugNichtMoeglich();
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
		} while (error);

		if (board.checkWin(Content.YELLOW)) {
			status = Status.WINPLAYER1;
			player1.win();
			player2.lose();
			player3.lose();
			player4.lose();
		} else if (board.checkWin(Content.GREEN)) {
			status = Status.WINPLAYER2;
			player2.win();
			player3.lose();
			player4.lose();
			player1.lose();
		} else if (board.checkWin(Content.BLUE)) {
			status = Status.WINPLAYER3;
			player3.win();
			player4.lose();
			player1.lose();
			player2.lose();
		} else if (board.checkWin(Content.RED)) {
			status = Status.WINPLAYER4;
			player4.win();
			player1.lose();
			player2.lose();
			player3.lose();
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

	public void save() {
		PersistenceObject po = new PersistenceObject(status, board);
		SaveLoad.save(po);
	}

	public void load() {
		PersistenceObject po = SaveLoad.load();
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
		case PLAYER1:
			player1.diceResult();
			break;
		case PLAYER2:
			player2.diceResult();
			break;
		case PLAYER3:
			player3.diceResult();
			break;
		case PLAYER4:
			player4.diceResult();
			break;
		default:
			break;
		}
	}

	public void moveNotPossibleMessage() {
		switch (status) {
		case PLAYER1:
			player1.moveOverrun();
			break;
		case PLAYER2:
			player2.moveOverrun();
			break;
		case PLAYER3:
			player3.moveOverrun();
			break;
		case PLAYER4:
			player4.moveOverrun();
			break;
		default:
			break;
		}
	}

	public void ownMeepleMessage() {
		switch (status) {
		case PLAYER1:
			player1.throwOwnMeeple();
			break;
		case PLAYER2:
			player2.throwOwnMeeple();
			break;
		case PLAYER3:
			player3.throwOwnMeeple();
			break;
		case PLAYER4:
			player4.throwOwnMeeple();
			break;
		default:
			break;
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

}

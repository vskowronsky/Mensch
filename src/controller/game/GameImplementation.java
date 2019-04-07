package controller.game;

import controller.IO;
import controller.player.Player;
import model.*;

public class GameImplementation implements Game {
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private BoardSet board;
	private Status status;
	private Dice dice;
	private int diceValue;
	private Position position;

	public GameImplementation(Player player1, Player player2, Player player3, Player player4) {
		dice = new Dice();
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
			System.out.println("Spieler 1 ist dran.");
		} else if (status == Status.PLAYER2) {
			player1.disable();
			player3.disable();
			player4.disable();
			player2.enable();
			System.out.println("Spieler 2 ist dran.");
		} else if (status == Status.PLAYER3) {
			player1.disable();
			player2.disable();
			player4.disable();
			player3.enable();
			System.out.println("Spieler 3 ist dran.");
		} else if (status == Status.PLAYER4) {
			player1.disable();
			player2.disable();
			player3.disable();
			player4.enable();
			System.out.println("Spieler 4 ist dran.");
		}
	}

	@Override
	public void update() {
		diceValue = dice.throwDice();

		if (!leaveHouse()) {

			switch (status) {
			case PLAYER1:
				if (board.setMeeple(player1.chooseMeeple(), Content.YELLOW, diceValue)) {
					status = Status.PLAYER2;
					player1.disable();
				}
				break;
			case PLAYER2:
				if (board.setMeeple(player2.chooseMeeple(), Content.GREEN, diceValue)) {
					status = Status.PLAYER3;
					player2.disable();
				}
				break;
			case PLAYER3:
				if (board.setMeeple(player3.chooseMeeple(), Content.BLUE, diceValue)) {
					status = Status.PLAYER4;
					player3.disable();
				}
				break;
			case PLAYER4:
				if (board.setMeeple(player4.chooseMeeple(), Content.RED, diceValue)) {
					status = Status.PLAYER1;
					player4.disable();
				}
				break;
			}
		}

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

	public void safe() {
		PersistenceObject po = new PersistenceObject(status, board);
		SaveLoad.save(po);
	}

	public void load() {
//		PersistenceObject po = SaveLoad.load();
//		status = po.getStatus();
//		board = po.getBoard();

		start();
		update();
	}

	public Board getBoard() {
		return board;
	}

	public Content checkPosition(Position chosenPosition) {
		return board.checkPosition(chosenPosition);
	}

	public boolean leaveHouse() {
		int throwCount = 1;
		switch (status) {
		case PLAYER1:
			player1.diceResult();
			if (board.getHouseY() == 4) {
				while (throwCount < 3) {
					if (diceValue == 6) {
						board.setMeeple(board.STARTY, Content.YELLOW, 0);
						board.setHouseY(3);
						diceValue = dice.throwDice();
						player1.diceResult();
						board.setMeeple(board.STARTY, Content.YELLOW, diceValue);
						break;

					} else {
						throwCount++;
						diceValue = dice.throwDice();
						player1.diceResult();
					}
				}

				status = Status.PLAYER2;
				player1.disable();
				return true;
			}
			// else Haus >0<4; zweimal würfeln, Männchen raus
			// board.setHouse(getHouse()-1);

			// Schwieriger Fall, muss ausgelagert werden
			// else Haus = 0, aber ne 6; zweimal würfeln, Figur setzen
			// status = Status.PLAYER2;
			// player1.disable();
			// return true;

			return false;

		case PLAYER2:
			player2.diceResult();

			return false;
		case PLAYER3:
			player3.diceResult();

			return false;
		case PLAYER4:
			player4.diceResult();

			return false;
			default: 
				return false;
		}


	}

	public int dice() {
		return diceValue;
	}

}

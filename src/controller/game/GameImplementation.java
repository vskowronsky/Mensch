package controller.game;

import java.util.ArrayList;
import java.util.List;

import controller.exceptions.MissedEnemyException;
import controller.exceptions.MoveStreetException;
import controller.exceptions.NoMoveException;
import controller.exceptions.OwnMeepleException;
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
	private boolean firstChoose;
	List<Integer> possibleMeeple;
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
		firstChoose = true;
		do {
			try {
				error = true;
				switch (status) {
				case PLAYER1:
					if (board.checkStartFree(Content.YELLOW) && first) {
						first = false;
						board.setStart(status, this);
						player1.message("Das Startfeld war belegt und musste vorrangig gespielt werden.");
					} else 
						if (board.checkThreeThrows(Content.YELLOW)) {
							player1.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player1.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this);

							board.diceThrow();
							player1.message("Sie d�rfen nochmal w�rfeln. Sie haben eine " + board.getDiceValue() + " gew�rfelt");
							checkMovePossible(Content.YELLOW);
							board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this);
						}else {
							if (board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this)) {

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
						player2.message("Das Startfeld war belegt und musste vorrangig gespielt werden.");
					} else 
						if (board.checkThreeThrows(Content.GREEN)) {
							player2.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player2.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this);

							board.diceThrow();
							player2.message("Sie d�rfen nochmal w�rfeln. Sie haben eine " + board.getDiceValue() + " gew�rfelt");
							checkMovePossible(Content.GREEN);
							board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this);
						} else {
							if (board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this)) {

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
						player3.message("Das Startfeld war belegt und musste vorrangig gespielt werden.");
					} else 
						if (board.checkThreeThrows(Content.BLUE)) {
							player3.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player3.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this);

							board.diceThrow();
							player3.message("Sie d�rfen nochmal w�rfeln. Sie haben eine " + board.getDiceValue() + " gew�rfelt");
							checkMovePossible(Content.BLUE);
							board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this);	

						} else {
							if (board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this)) {

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
						player4.message("Das Startfeld war belegt und musste vorrangig gespielt werden.");
					} else 
						if (board.checkThreeThrows(Content.RED)) {
							player4.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player4.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.RED), Content.RED, this);

							board.diceThrow();
							player4.message("Sie d�rfen nochmal w�rfeln. Sie haben eine " + board.getDiceValue() + " gew�rfelt");
							checkMovePossible(Content.RED);
							board.setMeeple(chooseMeeple(Content.RED), Content.RED, this);
						} else {
							if (board.setMeeple(chooseMeeple(Content.RED), Content.RED, this)) {

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
				message("Sie k�nnen sich nicht selber vom Spielbrett werfen.");
			}
			catch (MoveStreetException e) {
				message("Spielzug nicht m�glich. W�hlen Sie eine andere Figur.");
			}
			catch (MissedEnemyException e) {
				error = false;

				switch(status) {
				case PLAYER1:
					player1.message("Sie haben verpasst einen Gegner zu schlagen. Daf�r wurde Ihre Figur zur�ck ins Haus gesetzt.");
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.message("Sie haben verpasst einen Gegner zu schlagen. Daf�r wurde Ihre Figur zur�ck ins Haus gesetzt.");
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.message("Sie haben verpasst einen Gegner zu schlagen. Daf�r wurde Ihre Figur zur�ck ins Haus gesetzt.");
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.message("Sie haben verpasst einen Gegner zu schlagen. Daf�r wurde Ihre Figur zur�ck ins Haus gesetzt.");
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
					player1.message("Kein Zug ist m�glich. Der n�chste Spieler ist dran.");
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.message("Kein Zug ist m�glich. Der n�chste Spieler ist dran.");
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.message("Kein Zug ist m�glich. Der n�chste Spieler ist dran.");
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.message("Kein Zug ist m�glich. Der n�chste Spieler ist dran.");
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
			System.out.println("Spieler 1 ist dran");
			player1.enable();
		
		} else if (status == Status.PLAYER2) {
			System.out.println("Spieler 2 ist dran");
			player2.enable();

		} else if (status == Status.PLAYER3) {
			System.out.println("Spieler 3 ist dran");
			player3.enable();
			
		} else if (status == Status.PLAYER4) {
			System.out.println("Spieler 4 ist dran");
			System.out.println(counter);
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


	public Position chooseMeeple(Content content) throws NoMoveException{
		int chosen = -1;
		boolean meeplePossible = false;
		Position chosenPosition;
		Integer chosenMeeple = null;
		
		if (firstChoose) {
		checkMovePossible(content);
		firstChoose =false;
		}
		
		while (chosen == -1) {
			
			if (possibleMeeple.isEmpty()) {
				throw new NoMoveException();
			}
			
			switch (content) {

			case YELLOW:
				player1.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
				chosenPosition = player1.chooseMeeple();
				
			
				
				for (Integer meeple : possibleMeeple) {
					if (meeple == chosenPosition.getIndex()) {
						meeplePossible = true;
						chosenMeeple = meeple;
					}
				}
				
				if (meeplePossible) {
					possibleMeeple.remove(chosenMeeple);
					
					return chosenPosition;
				}else {

					player1.message("Bitte w�hlen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case GREEN:
				player2.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
				chosenPosition = player2.chooseMeeple();
				
				
				for (Integer meeple : possibleMeeple) {
					if (meeple == chosenPosition.getIndex()) {
						meeplePossible = true;
						chosenMeeple = meeple;
					}
				}
				
				if (meeplePossible) {
					possibleMeeple.remove(chosenMeeple);
					
					return chosenPosition;
				}else {
					player2.message("Bitte w�hlen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case BLUE:
				player3.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
				chosenPosition = player3.chooseMeeple();
				for (Integer meeple : possibleMeeple) {
					if (meeple == chosenPosition.getIndex()) {
						meeplePossible = true;
						chosenMeeple = meeple;
					}
				}
				
				if (meeplePossible) {
					possibleMeeple.remove(chosenMeeple);
					return chosenPosition;
				}else {
					player3.message("Bitte w�hlen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case RED:
				player4.message("Sie haben eine " + board.getDiceValue() + " gew�rfelt.");
				chosenPosition = player4.chooseMeeple();
				
				for (Integer meeple : possibleMeeple) {
					if (meeple == chosenPosition.getIndex()) {
						meeplePossible = true;
						chosenMeeple = meeple;
					}
				}
				
				if (meeplePossible) {
					possibleMeeple.remove(chosenMeeple);
					return chosenPosition;
				}else {
					player4.message("Bitte w�hlen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;
			default: break;
			}
		}
		return null;

	}

	public void message(String message) {
		switch (status) {

		case PLAYER1: player1.message(message); break;
		case PLAYER2: player2.message(message); break;
		case PLAYER3: player3.message(message); break;
		case PLAYER4: player4.message(message); break;
		default: break;
		}
	}

	@Override
	public void returnPosition(Position position) {
		// TODO Auto-generated method stub

	}


	// Erzeugt ein Array mit allen Positionen von bewegbaren Meeple beinhaltet
	public void checkMovePossible(Content content) {

		int j = 0;
		switch (content) {
		
		case YELLOW: 	
			possibleMeeple = new ArrayList<Integer>();

			for (int i = 0; i < board.getPlayboard().length;i++) {
				if (board.getPlayboard()[i] == content) {
					possibleMeeple.add(i);
					j++;
				}
			}

			for (int i = 0; i < board.getStreetY().length;i++) {
				if (board.getStreetY()[i] == content && j<= 4-board.getHouseY()-board.getFinishedY()-1) {
					possibleMeeple.add(i + 40);
					j++;
				}
			}

			break;

		case GREEN: 	
			possibleMeeple = new ArrayList<Integer>();
		

			for (int i = 0; i < board.getPlayboard().length;i++) {
				if (board.getPlayboard()[i] == content) {
					possibleMeeple.add(i);
					j++;
				}
			}

			for (int i = 0; i < board.getStreetG().length;i++) {
				if (board.getStreetG()[i] == content && j<= 4-board.getHouseG()-board.getFinishedG()-1) {
					possibleMeeple.add(i+50);
					j++;
				}
			}

			break;

		case BLUE: 	
			possibleMeeple = new ArrayList<Integer>();
	
			for (int i = 0; i < board.getPlayboard().length;i++) {
				if (board.getPlayboard()[i] == content) {
					possibleMeeple.add(i);
					j++;
				}
			}

			for (int i = 0; i < board.getStreetB().length;i++) {
				if (board.getStreetB()[i] == content && j<= 4-board.getHouseB()-board.getFinishedB()-1) {
					possibleMeeple.add(i+60);
					j++;
				}
			}

			break;

		case RED: 	
			possibleMeeple = new ArrayList<Integer>();


			for (int i = 0; i < board.getPlayboard().length;i++) {
				if (board.getPlayboard()[i] == content) {
					possibleMeeple.add(i);
					j++;
				}
			}

			for (int i = 0; i < board.getStreetR().length;i++) {
				if (board.getStreetR()[i] == content && j<= 4-board.getHouseR()-board.getFinishedR()-1) {
					possibleMeeple.add(i+70);
					j++;
				}
			}

			break;
			
		default:
			break;

		}

	}
}
package controller.game;

import java.util.ArrayList;
import java.util.List;
import controller.exceptions.LoadException;
import controller.exceptions.MissedEnemyException;
import controller.exceptions.MoveStreetException;
import controller.exceptions.NoMoveException;
import controller.exceptions.OwnMeepleException;
import controller.exceptions.SaveException;
import controller.player.Player;
import model.*;

/**
 * Klasse erzeugt eine Implementation des Spieles, die Logik des Spieles.
 */
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

	/**
	 * Konstruktor des Spiels. Hier wird ein Spielfeld initialisiert und vier Spielern zugeordnet.
	 */
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

	/**
	 * Methode um das Spiel zu starten. Ja nach Status wird der jeweilige Spieler aktiviert,
	 * die anderen deaktiviert.s
	 * @author Laura, Vanessa
	 */
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

	/**
	 * Die update-Methode startet einen neuen Spielzug für einen Spieler. 
	 * Das Spiel entscheidet anhand des aktuellen Zustandes des Spiels wie es weiter geht.
	 * @author Vanessa
	 */
	public void update() {

		pause(500);

		counter++;
		board.diceThrow();
		first = true;
		firstChoose = true;
		do {
			board.setLeftHouseFalse();
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
							player1.message("Sie dürfen dreimal würfeln.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this);
							board.diceThrow();
							player1.message("Sie haben nochmal gewürfelt.");
							player1.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
							checkMovePossible(Content.YELLOW);
							board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this);
						}else {
							if (board.setMeeple(chooseMeeple(Content.YELLOW), Content.YELLOW, this)) {

							} else {
								throw new MoveStreetException();						
							}
						}
					playerchange();
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
							player2.message("Sie dürfen dreimal würfeln.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player2.message("Sie ziehen aus dem Haus.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this);
							board.diceThrow();
							player2.message("Sie haben nochmal gewürfelt.");
							player2.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
							checkMovePossible(Content.GREEN);
							board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this);
						} else {
							if (board.setMeeple(chooseMeeple(Content.GREEN), Content.GREEN, this)) {

							} else {
								throw new MoveStreetException();
							}
						}
					playerchange();
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
							player3.message("Sie dürfen dreimal würfeln.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player3.message("Sie ziehen aus dem Haus.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this);
							board.diceThrow();
							player3.message("Sie haben nochmal gewürfelt.");
							player3.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
							checkMovePossible(Content.BLUE);
							board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this);	

						} else {
							if (board.setMeeple(chooseMeeple(Content.BLUE), Content.BLUE, this)) {

							} else {
								throw new MoveStreetException();
							}
						}
					playerchange();
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
							player4.message("Sie dürfen dreimal würfeln.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkNumHouse(status) && first) {
							player4.message("Sie ziehen aus dem Haus.");
							first = false;
							board.leaveHouse(status, this);
						} else if (board.getDiceValue() == 6 && board.checkDoubleDice(status)) {
							board.setMeeple(chooseMeeple(Content.RED), Content.RED, this);
							board.diceThrow();
							player4.message("Sie haben nochmal gewürfelt.");
							player4.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
							checkMovePossible(Content.RED);
							board.setMeeple(chooseMeeple(Content.RED), Content.RED, this);
						} else {
							if (board.setMeeple(chooseMeeple(Content.RED), Content.RED, this)) {

							} else {

								throw new MoveStreetException();
							}
						}
					playerchange();
					status = Status.PLAYER1;
					player4.disable();
					break;

				default: break;
				}
				error = false;

			} catch (OwnMeepleException e) {
				message("Sie können sich nicht selber vom Spielbrett werfen.");
			}
			catch (MoveStreetException e) {
				message("Spielzug nicht möglich. Wählen Sie eine andere Figur.");
			}
			catch (MissedEnemyException e) {
				error = false;

				switch(status) {
				case PLAYER1:
					player1.message("Sie haben verpasst einen Gegner zu schlagen. Dafür wurde Ihre Figur zurück ins Haus gesetzt.");
					playerchange();
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.message("Sie haben verpasst einen Gegner zu schlagen. Dafür wurde Ihre Figur zurück ins Haus gesetzt.");
					playerchange();
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.message("Sie haben verpasst einen Gegner zu schlagen. Dafür wurde Ihre Figur zurück ins Haus gesetzt.");
					playerchange();
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.message("Sie haben verpasst einen Gegner zu schlagen. Dafür wurde Ihre Figur zurück ins Haus gesetzt.");
					playerchange();
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
					player1.message("Kein Zug ist möglich. Der nächste Spieler ist dran.");
					playerchange();
					status = Status.PLAYER2;
					player1.disable();
					break;
				case PLAYER2:
					player2.message("Kein Zug ist möglich. Der nächste Spieler ist dran.");
					playerchange();
					status = Status.PLAYER3;
					player2.disable();
					break;
				case PLAYER3:
					player3.message("Kein Zug ist möglich. Der nächste Spieler ist dran.");
					playerchange();
					status = Status.PLAYER4;
					player3.disable();
					break;
				case PLAYER4: 
					player4.message("Kein Zug ist möglich. Der nächste Spieler ist dran.");
					playerchange();
					status = Status.PLAYER1;
					player4.disable();
					break;
				default: break;
				}
			}

			catch (SaveException e) {
				save(e.savefile);
				error = false;
			}

			catch (LoadException e) {
				load(e.loadfile);
				error = false;
			}

		} while (error);

		if (board.checkWin(Content.YELLOW)) {
			status = Status.WIN;
			player1.win();
			player2.lose();
			player3.lose();
			player4.lose();
			System.out.println(counter);		
		} else if (board.checkWin(Content.GREEN)) {
			status = Status.WIN;
			player2.win();
			player3.lose();
			player4.lose();
			player1.lose();
			System.out.println(counter);	
		} else if (board.checkWin(Content.BLUE)) {
			status = Status.WIN;
			player3.win();
			player4.lose();
			player1.lose();
			player2.lose();
			System.out.println(counter);	
		} else if (board.checkWin(Content.RED)) {
			status = Status.WIN;
			player4.win();
			player1.lose();
			player2.lose();
			player3.lose();
			System.out.println(counter);
		}

		if (status == Status.WIN) {
			pause(10000);
			System.exit(0);

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
			player4.enable();
		}
	}

	/**
	 * Methode erzeugt ein neues PersistenceObject, das den aktuellen Status sowie das Board übergeben
	 * bekommt. 
	 * @param fileName Name, der zu speichernden Datei
	 */
	public void save(String fileName) {
		PersistenceObject po = new PersistenceObject(status, board);
		SaveLoad.save(po, fileName);
	}

	/**
	 * Methode lädt ein PersistenceObject mit dem übergebenen Namen und übernimmt den Status und das 
	 * Board.
	 * @param fileName Name der zu ladenen Datei
	 */
	public void load(String fileName) {
		PersistenceObject po = SaveLoad.load(fileName);
		this.status = po.getStatus();
		this.board = po.getBoard();

		System.out.println(po.getBoard());
		System.out.println(this.board);

		message("Loading successfull");
	}

	/**
	 * Get-Methode des Boards.
	 * @return Das Board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Methode überprüft, ob die vom Spieler ausgewählte Spielfigur bewegbar ist. Wenn nicht,
	 * wird der Spieler erneut aufgefordert zu wählen oder informiert, dass kein Zug möglich ist.
	 * @param content Farbe des Spielers, der dran ist.
	 * @author Vanessa
	 */
	public Position chooseMeeple(Content content) throws NoMoveException, SaveException, LoadException{
		int chosen = -1;
		boolean meeplePossible = false;
		Position chosenPosition;
		Integer chosenMeeple = null;
		boolean firstDiceMessage = true;

		if (firstChoose) {
			checkMovePossible(content);
			firstChoose = false;
		}

		while (chosen == -1) {
			if (possibleMeeple.isEmpty()) {
				throw new NoMoveException();
			}
			switch (content) {
			case YELLOW:
				if (firstDiceMessage) {
					firstDiceMessage = false;
					player1.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
				}
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
				} else {
					player1.message("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case GREEN:
				if (firstDiceMessage) {
					firstDiceMessage = false;
					player2.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
				}
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
				} else {
					player2.message("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case BLUE:
				if (firstDiceMessage) {
					firstDiceMessage = false;
					player3.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
				}
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
				} else {
					player3.message("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;

			case RED:
				if (firstDiceMessage) {
					firstDiceMessage = false;
					player4.message("Sie haben eine " + board.getDiceValue() + " gewürfelt.");
				}
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
				} else {
					player4.message("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}
				break;
			default: break;
			}
		}
		return null;
	}



	/**
	 * Methode erzeugt eine ArrayList, die alle Positionen von bewegbaren Meeple beinhaltet.
	 * @param content Farbe des Spielers, der dran ist.
	 * @author Vanessa
	 */
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

		default: break;
		}
	}

	/**
	 * Methode zur Weitergabe von Nachrichten.
	 * @param message Der Nachrichtentext
	 * @author Vanessa
	 */
	public void message(String message) {
		switch (status) {
		case PLAYER1: player1.message(message); break;
		case PLAYER2: player2.message(message); break;
		case PLAYER3: player3.message(message); break;
		case PLAYER4: player4.message(message); break;
		default: break;
		}
	}

	/**
	 * Methode um den Spieler zu informieren, dass er von einem Gegner geschlagen wurde.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param message Nachricht, von wem geschlagen wurde.
	 * @author Vanessa
	 */
	public void enemyMessage(Content content, String message) {
		switch (content) {
		case YELLOW: player1.message(message); break;
		case GREEN: player2.message(message); break;
		case BLUE: player3.message(message); break;
		case RED: player4.message(message); break;
		default: break;
		}
	}

	/**
	 * Methode um den Spieler zu informieren, auf welche Spieler gewartet werden muss.
	 * @author Vanessa
	 */
	public void playerchange() {
		switch (status) {
		case PLAYER1 : 
			player1.message("Ihr Spielzug ist beendet.");
			player1.message("Warten auf Spieler 2.");
			player3.message("Warten auf Spieler 2.");
			player4.message("Warten auf Spieler 2.");
			break;

		case PLAYER2 : 
			player1.message("Warten auf Spieler 3.");
			player2.message("Ihr Spielzug ist beendet.");
			player2.message("Warten auf Spieler 3.");
			player4.message("Warten auf Spieler 3.");
			break;
		case PLAYER3 : 
			player1.message("Warten auf Spieler 4.");
			player2.message("Warten auf Spieler 4.");
			player3.message("Ihr Spielzug ist beendet.");
			player3.message("Warten auf Spieler 4.");
			break;
		case PLAYER4 :
			player2.message("Warten auf Spieler 1.");
			player3.message("Warten auf Spieler 1.");
			player4.message("Ihr Spielzug ist beendet.");
			player4.message("Warten auf Spieler 1.");
			break;
		default: break;
		}
	}

	/**
	 * Methode pausiert den aktuellen Thread.
	 * @param int Zeit, die pausiert werden soll
	 * @author Vanessa
	 */
	public void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Wird nicht überschrieben.
	 */
	public void returnPosition(Position position) {
	}
}
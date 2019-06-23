package controller.player;


import controller.game.Game;
import model.Board;
import model.Content;
import model.Position;

/**
 * Klasse erzeugt eine k�nstliche Intelligenz als Spieler
 */
public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
	private int meeplecounter;
	private int enemycounter;
	private boolean six;
	private Board board;

	public PlayerKI(){
		id = 0;
		content = Content.FREE;
		game = null;
	}

	/**
	 * Initialisiert einen k�nstlichen Spieler mit den �bergebenen Parametern.
	 * @author Vanessa
	 */
	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	/**
	 * Aktivierung der KI.
	 * @author Vanessa
	 */
	public void enable() {
		this.meeplecounter = 1;
		this.enemycounter = 1;
		six = false;
		System.out.println("NEUE RUNDE!");
		System.out.println("KI " +id + " ist dran.");
		this.board = game.getBoard();
		System.out.println(this.board );
		game.update();
	}

	/**
	 * Methode printet, dass der Spielzug beendet ist.
	 * @author Vanessa
	 */
	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
	}

	/**
	 * Methode gibt die Reihenfolge vor, in dem die KI eine Spielfigur ausw�hlt.
	 * Zun�chst wird �berpr�ft, ob ein Gegner geschlagen werden kann. Wenn dies nicht m�glich ist, 
	 * wird ein Spielfigur auf dem Spielfeld gesetzt. Wenn dies misslingt, wird eine Figur in der
	 * Spielstra�e gesetzt.
	 * @return Die ausgew�hlte Position
	 * @author Vanessa
	 */
	public Position chooseMeeple(){
		int localCounter = 1;
		int localEnemyCounter = 1;

		Content[] street = null;
		int dif = 0;
		switch (this.content) {
		case YELLOW : street = board.getStreetY(); dif = 40; break;
		case GREEN : street = board.getStreetG(); dif = 50; break;
		case BLUE : street = board.getStreetB(); dif = 60; break;
		case RED : street = board.getStreetR(); dif = 70; break;
		default : break;
		}

		for (int i = 0; i<this.board.getPlayboard().length; i++) {
			if (i+this.board.getDiceValue() >= 40) {
				if (this.board.getPlayboard()[i] == this.content && 
						this.board.getPlayboard()[i+this.board.getDiceValue()-40] != Content.FREE && 
						this.board.getPlayboard()[i+this.board.getDiceValue()-40] != content) {
					if (localEnemyCounter == this.enemycounter) {
						this.enemycounter++;
						game.returnPosition(new Position (i));
						return null;
					} else {
						localEnemyCounter++;
					}
				}
			} else {
				if (this.board.getPlayboard()[i] == this.content && 
						this.board.getPlayboard()[i+this.board.getDiceValue()] != Content.FREE && 
						this.board.getPlayboard()[i+this.board.getDiceValue()] != content) {
					if (localEnemyCounter == this.enemycounter) {
						this.enemycounter++;
						game.returnPosition(new Position (i));
						return null;
					} else {
						localEnemyCounter++;
					}
				}
			}
		}

		for (int i = 0; i<this.board.getPlayboard().length; i++) {
			if (this.board.getPlayboard()[i] == this.content) {
				if (localCounter == this.meeplecounter) {
					meeplecounter++;
					System.out.println("Ausgew�hltes i: " + i);
					game.returnPosition(new Position (i));
					return null;
				} else {
					localCounter++;
				}
			}
		}

		for (int i = 0; i<4; i++) {
			if (street[i] == this.content) {
				if (localCounter == this.meeplecounter) {
					meeplecounter++;
					System.out.println("Ausgew�hltes i: " + (dif+i));
					game.returnPosition(new Position (i+dif));
					return null;
				} else {
					localCounter++;
				}
			}
		}
		return null;
	}

	/**
	 * Methode zur Print-Ausgabe in der Konsole der KI.
	 * @author Vanessa
	 */
	public void win() {
		System.out.println("KI " + id + " hat gewonnen!");
		this.board = game.getBoard();
		System.out.println(this.board);
	}

	/**
	 * Methode zur Print-Ausgabe in der Konsole der KI.
	 * @author Vanessa
	 */
	public void lose() {
		System.out.println("KI " + id + " hat verloren!");
		this.board = game.getBoard();
		System.out.println(this.board);
	}

	/**
	 * Methode zur Print-Ausgabe in der Konsole der KI.
	 * Bei einer bestimmten Nachricht, werden zwei globale Variablen der Klasse neu gesetzt.
	 * @author Vanessa
	 */
	public void message(String message){
		System.out.println(message);
		this.board = game.getBoard();

		if (message.equals("Sie haben nochmal gew�rfelt.")) {
			this.meeplecounter = 1;
			this.enemycounter = 1; 
		}

	}
}

package controller.player;

import controller.exceptions.LoadException;
import controller.exceptions.SaveException;
import controller.game.Game;
import model.Content;
import model.Position;

/**
 * Interface des Player
 */

public interface Player {
	
	/**
	 * Initialisiert den Player-Client
	 * @param content Farbe des Spielers, der dran ist.
	 * @param game Das Spiel selber.
	 * @param id ID des Spielers
	 */
	public void initialize(Content content, Game game, int id);
	
	/**
	 * Aktiviert den Spieler für den nächsten Zug.
	 */
	public void enable();
	
	/**
	 * Deaktiviert den Spieler für den nächsten Zug.
	 */
	public void disable();
	
	/**
	 * Informiert den Spieler, dass er gewonnen hat.
	 */
	public  void win();
	
	/**
	 * Informiert den Spieler, dass er verloren hat.
	 */
	public void lose();	
	
	/**
	 * Spieler wählt eine Position aus.
	 * @return Die gewählte Position
	 * @throws SaveException
	 * @throws LoadException
	 */
	public Position chooseMeeple() throws SaveException, LoadException;
	
	/**
	 * Bekommt eine Message übergeben, die dann weiterverarbeitet wird.
	 * @param message Ein String
	 */
	public void message(String message);

	
}

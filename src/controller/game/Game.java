package controller.game;


import controller.exceptions.LoadException;
import controller.exceptions.NoMoveException;
import controller.exceptions.SaveException;
import model.Board;
import model.Content;
import model.Position;

/**
 * Interface des Games
 */
public interface Game {

	/**
	 * Startmethode zum Starten des Spiels.
	 */
	public void start();

	/**
	 * Spieler ruft update auf, um zu signalisieren, dass er bereit ist.
	 */
	public void update();

	/**
	 * Methode wird aufgrufen um das Spiel zu speichern.
	 * @param string Name unter dem gespeichert werden soll
	 */
	public void save(String string);

	/**
	 * Methode wird aufgerufen um das Spiel zu laden.
	 * @param string Name der zu ladenen Datei
	 */
	public void load(String string);

	/**
	 * Rückgabe des aktuellen Spielfeldes mit der tatsächlichen Anordnung der Spielfiguren.
	 * Das Spiel kontrolliert das Spielfeld und reicht es an die Spieler.
	 */
	public Board getBoard();

	/**
	 * Methode zur Weitergabe von Nachrichten.
	 * @param message Der Nachrichtentext
	 */
	public void message(String message);

	/**
	 * Methode überprüft, ob die ausgewählte Spielfigur bewegbar ist.
	 * @param content Farbe des Spielers, der dran ist
	 * @return Die ausgewählte Position
	 * @throws NoMoveException
	 * @throws SaveException
	 * @throws LoadException
	 */
	public Position chooseMeeple(Content content) throws NoMoveException, SaveException, LoadException;

	/**
	 * Methode erhält eine ausgewählte Position.
	 * @param position Position, die ausgewählt wurde
	 */
	public void returnPosition(Position position);

	/**
	 * Methode um den Spieler zu informieren, dass er von einem Gegner geschlagen wurde.
	 * @param content Farbe des Spielers, der dran ist.
	 * @param message Nachricht, von wem geschlagen wurde.
	 */
	public void enemyMessage(Content content, String message);

	/**
	 * Methode um das Spiel kurz zu verlangsamen.
	 * @param time Zeit, die pausiert werden soll
	 */
	public void pause (int time);

}

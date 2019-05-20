package controller.game;

import controller.exceptions.OwnMeepleException;
import model.Board;
import model.Content;
import model.Position;

public interface Game {
	/**
	 * Startmethod for starting the game
	 */
	
	public void start();
	/**
	 * The player calls the method update, pointing that he is ready for the next turn
	 * @throws OwnMeepleException 
	 */
	public void update();
	
	/**
	 * The player calls this method to safe the game 
	 * @param string 
	 */
	public void save(String string);
	
	/**
	 * The player calls this method to load the game
	 * @throws OwnMeepleException 
	 */
	public void load(String string);
	
	/**
	 * Returns the current board with actual settings. The game controls the board and submits it to the players.
	 */
	public Board getBoard();
	
	/**
	 * Gibt den aktuellen Würfelwurf zurück
	 */
	public int dice();
	
	/**
	 * Gibt die Würfelzahl an den Player als Text aus
	 */
	public void diceMessage();
	
	
	public void moveNotPossibleMessage();
	
	public void ownMeepleMessage();
	
	public void enemyMessage();
	
	public Content checkPosition(Position chosenPosition, Content content);
	

}

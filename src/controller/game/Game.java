package controller.game;

import controller.OwnMeepleException;
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
	 */
	public void safe();
	
	/**
	 * The player calls this method to load the game
	 * @throws OwnMeepleException 
	 */
	public void load();
	
	/**
	 * Returns the current board with actual settings. The game controls the board and submits it to the players.
	 */
	public Board getBoard();
	
	/**
	 * Gibt den aktuellen W�rfelwurf zur�ck
	 */
	public int dice();
	
	/**
	 * Gibt die W�rfelzahl an den Player als Text aus
	 */
	public void diceMessage();
	
	
	public void endMessage();
	
	public void ownMeepleMessage();
	
	public Content checkPosition(Position chosenPosition);

}

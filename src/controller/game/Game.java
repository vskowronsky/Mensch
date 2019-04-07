package controller.game;

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
	 */
	public void update();
	
	/**
	 * The player calls this method to safe the game 
	 */
	public void safe();
	
	/**
	 * The player calls this method to load the game
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
	
	public boolean leaveHouse();
	
	public Content checkPosition(Position chosenPosition);

}

package controller.player;

import controller.exceptions.LoadException;
import controller.exceptions.SaveException;
import controller.game.Game;
import model.Content;
import model.Position;

public interface Player {
	
	/**
	 * 
	 * Initializes the Player-Client
	 * 
	 * @param content
	 * @param game
	 * @param id
	 *
	 */
	
	public void initialize(Content content, Game game, int id);
	
	/**
	 * Enables the player for the next turn 
	 */
	public void enable();
	
	/**
	 * Disables the player for the next turn
	 */
	public void disable();
	
	/**
	 * Sets the information that the player has won
	 */
	public  void win();
	
	/**
	 * Sets the information that the player has lost
	 */
	public void lose();	
	
	public Position chooseMeeple() throws SaveException, LoadException;
	
	
	/**
	 * Receives and displays a message
	 * @param message
	 */
	public void message(String message);

	
}

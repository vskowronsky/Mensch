package controller.player;

import controller.exceptions.NoMoveException;
import controller.game.Game;
import model.Content;
import model.Position;

public interface Player {
	
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
	
	public Position chooseMeeple(int diceValue) throws NoMoveException;

	public void message(String message);

	
}

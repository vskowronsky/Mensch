package controller.player;

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
	
	public Position chooseMeeple();

	
	/**
	 * Wird aufgerufen, um dem Spieler zu sagen, dass er die Würfelzahl abfragen soll
	 */
	public void diceResult();
	
	public void endOverrun();
	
	public void throwOwnMeeple();
	
	public void doubleDiceResult();
}

package controller.game;


import controller.exceptions.LoadException;
import controller.exceptions.NoMoveException;
import controller.exceptions.OwnMeepleException;
import controller.exceptions.SaveException;
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
	
	
	public void message(String message);
		

	public Position chooseMeeple(Content content) throws NoMoveException, SaveException, LoadException;
	
	public void returnPosition(Position position);
	
}

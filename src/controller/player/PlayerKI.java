package controller.player;

import java.util.Random;

import controller.game.Game;
import model.Board;
import model.Content;
import model.Position;

public class PlayerKI implements Player {
	
	private int id;
	private Content content;
	private Game game;
	private Board board;
	private Random random;

	public PlayerKI(){
		id = 0;
		content = Content.FREE;
		game = null;
		random = new Random();
	}
	
	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	public void enable() {
		board = game.getBoard();
		game.update();
	}

	public void disable() {
		
	}

//	public Position placeContent() {
//		Position position = new Position(random.nextInt(4),random.nextInt(4));
//		while(!board.checkPosition(position)){
//			position = new Position(random.nextInt(4),random.nextInt(4));
//		}
//		return position;
//		
//	}

	public void win() {
	}

	public void lose() {
	}

	@Override
	public Position chooseMeeple() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void diceResult() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveOverrun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void throwOwnMeeple() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doubleDiceResult() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enemyResult() {
		// TODO Auto-generated method stub
		
	}

}

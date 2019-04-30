package controller.player;

import controller.game.Game;
import model.Board;
import model.Content;
import model.Position;

public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
	private Board board;

	public PlayerKI(){
		id = 0;
		content = Content.FREE;
		game = null;
	}

	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	//setzt die "0" zum Setzen auf dem Board
	public void enable() {
		board = game.getBoard();
		game.update();
	}

	public void disable() {

	}
	//KI soll über das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zurückgeben
	@Override
	public Position chooseMeeple() {
		for(int i = 0; i <40; i++) {
			if (board.checkPosition(new Position (i), this.content) == content) {
				return new Position(i);
			}
		}
		return null;
	}


	public void win() {
	}

	public void lose() {
	}

	@Override
	public void diceResult() {

	}

	@Override
	public void moveOverrun() {

	}

	@Override
	public void throwOwnMeeple() {

	}

	@Override
	public void doubleDiceResult() {

	}

	@Override
	public void enemyResult() {

	}

}

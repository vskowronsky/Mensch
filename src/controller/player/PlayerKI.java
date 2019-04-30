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
		System.out.println("NEUE RUNDE!");
		board = game.getBoard();
		System.out.println("KI " +id + " ist dran.");
		System.out.println(board);
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
				System.out.println("Figur an Position "+i+" wurde ausgewählt.");
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
		System.out.println("KI "+id+ "hat eine" + game.dice() + " gewürfelt.");
	}

	@Override
	public void moveOverrun() {
		System.out.println("Spielzug nicht möglich. Sie müssen neu setzen.");
	}

	@Override
	public void throwOwnMeeple() {
		System.out.println("Sie können sich nicht selber vom Spielbrett werfen.");
	}

	@Override
	public void doubleDiceResult() {
		System.out.println("Die KI durfte noch einmal würfen. Würfelzahl: " + game.dice());
	}

	@Override
	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}

}

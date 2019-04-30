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
	
	//KI soll �ber das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zur�ckgeben
	@Override
	public Position chooseMeeple() {
		for(int i = 0; i <40; i++) {
			if (board.checkPosition(new Position (i), this.content) == content) {
				System.out.println("Figur an Position "+i+" wurde ausgew�hlt.");
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
		System.out.println("KI "+id+ "hat eine" + game.dice() + " gew�rfelt.");
	}

	@Override
	public void moveOverrun() {
		System.out.println("Spielzug nicht m�glich. Sie m�ssen neu setzen.");
	}

	@Override
	public void throwOwnMeeple() {
		System.out.println("Sie k�nnen sich nicht selber vom Spielbrett werfen.");
	}

	@Override
	public void doubleDiceResult() {
		System.out.println("Die KI durfte noch einmal w�rfen. W�rfelzahl: " + game.dice());
	}

	@Override
	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}

}

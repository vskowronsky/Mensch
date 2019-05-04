package controller.player;


import controller.NoMoveException;
import controller.game.Game;
import model.Content;
import model.Position;

public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
	private int meeple;

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

		this.meeple = 1;
		System.out.println("NEUE RUNDE!");
		System.out.println("KI " +id + " ist dran.");
		System.out.println(game.getBoard());
		game.update();
	}

	public void disable() {

	}

	//KI soll über das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zurückgeben
	@Override
	public Position chooseMeeple() throws NoMoveException {
		int currentMeeple = 1;
		
		for(int i = 0; i <75; i++) {
			if ((game.checkPosition(new Position (i), this.content) == content) && meeple == currentMeeple) {
				System.out.println("Figur an Position "+i+" wurde ausgewählt.");
				return new Position(i);
			} else if (game.checkPosition(new Position (i), this.content) == content) {
				currentMeeple++;
			}
		}
		throw new NoMoveException();
	}


	public void win() {
	}

	public void lose() {
	}

	@Override
	public void diceResult() {
		System.out.println("KI "+id+ " hat eine " + game.dice() + " gewürfelt.");
	}

	@Override
	public void moveNotPossible() {
		System.out.println("Die KI wählt eine andere Figur.");
		meeple++;
	}

	@Override
	public void throwOwnMeeple() {
		System.out.println("Die KI darf sich nicht selber vom Spielbrett werfen.");
		meeple++;
	}

	@Override
	public void doubleDiceResult() {
		System.out.println("Die KI durfte noch einmal würfen. Würfelzahl: " + game.dice());
	}

	@Override
	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}
	
	public void noMoveAtAll(){
		System.out.println("Die KI kann keinen Zug ausführen.");
	}

}

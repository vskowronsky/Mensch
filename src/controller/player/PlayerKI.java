package controller.player;


import controller.exceptions.NoMoveException;
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
		System.out.println("Der Spielzug ist beendet.");
	}

	//KI soll �ber das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zur�ckgeben
	@Override
	public Position chooseMeeple() throws NoMoveException {
		int currentMeeple = 1;
		diceResult();
			for (int i = 40; i < 75; i++) {
				if ((game.checkPosition(new Position (i), this.content) == content) && meeple == currentMeeple) {
					System.out.println("Figur an Position "+i+" wurde ausgew�hlt.");
					return new Position(i);
					
				} else if (game.checkPosition(new Position (i), this.content) == content) {
					currentMeeple++;
				}
			} 
			
			for (int i = 0; i <=39; i++) {
				if ((game.checkPosition(new Position (i), this.content) == content) && meeple == currentMeeple) {
					System.out.println("Figur an Position "+i+" wurde ausgew�hlt.");
					return new Position(i);
				} else if (game.checkPosition(new Position (i), this.content) == content) {
					currentMeeple++;
				}
			}
			throw new NoMoveException();
	}

	


	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
		System.out.println(game.getBoard());
	}

	public void lose() {
		System.out.println("Spieler " + id + " hat verloren!");
	}

	@Override
	public void diceResult() {
		System.out.println("KI "+id+ " hat eine " + game.dice() + " gew�rfelt.");
	}

	@Override
	public void moveNotPossible() {
		System.out.println("Die KI w�hlt eine andere Figur.");
		meeple++;
	}

	@Override
	public void throwOwnMeeple() {
		System.out.println("Die KI darf sich nicht selber vom Spielbrett werfen.");
		meeple++;
	}

	@Override
	public void doubleDiceResult() {
		System.out.println("Die KI durfte noch einmal w�rfeln. W�rfelzahl: " + game.dice());
	}

	@Override
	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}
	
	public void missedEnemyResult() {
		System.out.println("Sie haben verpasst einen Gegner zu schlagen. Daf�r wurde Ihre Figur zur�ck ins Haus gesetzt.");
	}
	
	public void noMoveAtAll(){
		System.out.println("Die KI kann keinen Zug ausf�hren.");
	}

	@Override
	public void freeStart() {
		System.out.println("Das Startfeld war belegt und musste vorrangig gespielt werden.");	
	}

}

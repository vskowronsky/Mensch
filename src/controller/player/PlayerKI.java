package controller.player;


import controller.exceptions.NoMoveException;
import controller.game.Game;
import model.Content;
import model.Position;

public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
//	private int meeple;
	private int meeplepos;
	private boolean end;

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

	public void enable() {

//		this.meeple = 1;
		this.meeplepos = 40;
		this.end = false;
		System.out.println("NEUE RUNDE!");
		System.out.println("KI " +id + " ist dran.");
		System.out.println(game.getBoard());
		game.update();
	}

	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
	}

	//KI soll über das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zurückgeben
	public Position chooseMeeple(int diceValue) throws NoMoveException{
//		int currentMeeple = 1;

		if (meeplepos == 40 && end) {
			throw new NoMoveException();
			
		}

		return new Position (meeplepos);


//		if () && meeple == currentMeeple) {
//			System.out.println("Figur an Position "+i+" wurde ausgewählt.");
//			return new Position(i);
//
//		} else if (game.checkPosition(new Position (i), this.content) == content) {
//			currentMeeple++;
//		}
//
//		if ((game.checkPosition(new Position (i), this.content) == content) && meeple == currentMeeple) {
//			System.out.println("Figur an Position "+i+" wurde ausgewählt.");
//			return new Position(i);
//		} else if (game.checkPosition(new Position (i), this.content) == content) {
//			currentMeeple++;
//		}

	}

	public void win() {
		System.out.println("KI " + id + " hat gewonnen!");
		System.out.println(game.getBoard());
	}

	public void lose() {
		System.out.println("KI " + id + " hat verloren!");
	}

	public void message(String message){
		System.out.println(message);

		if (message.equals("Sie können sich nicht selber vom Spielbrett werfen.") ||
				message.equals("Spielzug nicht möglich. Wählen Sie eine andere Figur.") ||
				message.equals("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.")) {
			if (meeplepos == 74) {
				meeplepos = 0;
			}else if( meeplepos == 39) {
				end = true;
				meeplepos++;
			} else {
				meeplepos++;
			}
		}
	}
}

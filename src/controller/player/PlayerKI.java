package controller.player;


import controller.exceptions.NoMoveException;
import controller.game.Game;
import model.Board;
import model.Content;
import model.Position;

public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
	private int meeplecounter;
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

	public void enable() {

		this.meeplecounter = 1;
		System.out.println("NEUE RUNDE!");
		System.out.println("KI " +id + " ist dran.");
		this.board = game.getBoard();
		System.out.println(this.board);
		game.update();
	}

	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
	}

	//KI soll über das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zurückgeben
	public Position chooseMeeple() throws NoMoveException{

		int counter = 1;
		System.out.println(this.content);
		System.out.println(counter);
		System.out.println(meeplecounter);
		//		if (meeplecounter == 4) throw new NoMoveException();
		
		Content[] street = null;
		int dif = 0;
		switch (this.content) {
		
		case YELLOW : street = board.streetY; dif = 40; break;
		case GREEN : street = board.streetG; dif = 50; break;
		case BLUE : street = board.streetB; dif = 60; break;
		case RED : street = board.streetR; dif = 70; break;
		default : break;
		}
		
		
		for (int i = 0; i<4; i++) {

			if (street[i] == this.content)

				if (counter == this.meeplecounter) {
					meeplecounter++;
					game.returnPosition(new Position (i+dif));
					return null;
				} else {
					counter++;
				}

		}
		
		
		for (int i = 0; i<this.board.playboard.length; i++) {

			if (this.board.playboard[i] == this.content)

				if (counter == this.meeplecounter) {
					meeplecounter++;
					game.returnPosition(new Position (i));
					return null;
				} else {
					counter++;
				}

		}

		System.out.println(this.content);
		System.out.println(counter);
		System.out.println(meeplecounter);
		throw new NoMoveException();


	}

	public void win() {
		System.out.println("KI " + id + " hat gewonnen!");
		this.board = game.getBoard();
		System.out.println(this.board);
	}

	public void lose() {
		System.out.println("KI " + id + " hat verloren!");
	}

	public void message(String message){
		System.out.println(message);
		if (message.equals("Sie dürfen nochmal würfeln. Sie haben eine 3 gewürfelt")){
			this.meeplecounter = 1;
		}
		
	}
}

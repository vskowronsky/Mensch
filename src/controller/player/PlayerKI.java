package controller.player;


import controller.game.Game;
import model.Board;
import model.Content;
import model.Position;

public class PlayerKI implements Player {

	private int id;
	private Content content;
	private Game game;
	private int meeplecounter;
	private int enemycounter;
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
		this.enemycounter = 1;
		System.out.println("NEUE RUNDE!");
		System.out.println("KI " +id + " ist dran.");
		this.board = game.getBoard();
		System.out.println(this.board );
		game.update();
	}

	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
	}

	//KI soll über das Array board gehen und die erste Figur, die seinem Content entspricht, 
	//zurückgeben
	public Position chooseMeeple(){
		int localCounter = 1;
		int localEnemyCounter = 1;

		Content[] street = null;
		int dif = 0;
		switch (this.content) {

		case YELLOW : street = board.getStreetY(); dif = 40; break;
		case GREEN : street = board.getStreetG(); dif = 50; break;
		case BLUE : street = board.getStreetB(); dif = 60; break;
		case RED : street = board.getStreetR(); dif = 70; break;
		default : break;
		}

		for (int i = 0; i<this.board.getPlayboard().length; i++) {
			
			if (i+this.board.getDiceValue() >= 40) {
				if (this.board.getPlayboard()[i] == this.content && this.board.getPlayboard()[i+this.board.getDiceValue()-40] != Content.FREE && this.board.getPlayboard()[i+this.board.getDiceValue()-40] != content) {
					if (localEnemyCounter == this.enemycounter) {
						this.enemycounter++;
						game.returnPosition(new Position (i));
						return null;
					} else {
						localEnemyCounter++;
					}
				}
			} else {
				if (this.board.getPlayboard()[i] == this.content && this.board.getPlayboard()[i+this.board.getDiceValue()] != Content.FREE && this.board.getPlayboard()[i+this.board.getDiceValue()] != content) {
					if (localEnemyCounter == this.enemycounter) {
						this.enemycounter++;
						game.returnPosition(new Position (i));
						return null;
					} else {
						localEnemyCounter++;
					}
				}
			}

		}
		
		for (int i = 0; i<this.board.getPlayboard().length; i++) {
			if (this.board.getPlayboard()[i] == this.content) {

				if (localCounter == this.meeplecounter) {
					meeplecounter++;
					System.out.println("Ausgewähltes i: " + i);
					game.returnPosition(new Position (i));
					return null;
				} else {
					localCounter++;
				}
			}
		
		}
		for (int i = 0; i<4; i++) {

			if (street[i] == this.content) {

				if (localCounter == this.meeplecounter) {
					meeplecounter++;
					System.out.println("Ausgewähltes i: " + i+dif);
					game.returnPosition(new Position (i+dif));
					return null;
				} else {
					localCounter++;
				}
			}
		}
		return null;
	}

	public void win() {
		System.out.println("KI " + id + " hat gewonnen!");
		this.board = game.getBoard();
		System.out.println(this.board);
	}

	public void lose() {
		System.out.println("KI " + id + " hat verloren!");
		this.board = game.getBoard();
		System.out.println(this.board);
	}

	public void message(String message){
		System.out.println(message);
		this.board = game.getBoard();

		switch (message) {
		case "Sie dürfen nochmal würfeln. Sie haben eine 1 gewürfelt" : 
			this.meeplecounter = 1;
			this.enemycounter = 1; 
			break;
		case "Sie dürfen nochmal würfeln. Sie haben eine 2 gewürfelt" : 
			this.meeplecounter = 1;
			this.enemycounter = 1; 
			break;
		case "Sie dürfen nochmal würfeln. Sie haben eine 3 gewürfelt" : 
			this.meeplecounter = 1;
			this.enemycounter = 1; 
			break;
		case "Sie dürfen nochmal würfeln. Sie haben eine 4 gewürfelt" : 
			this.meeplecounter = 1; 
			this.enemycounter = 1; 
			break;
		case "Sie dürfen nochmal würfeln. Sie haben eine 5 gewürfelt" : 
			this.meeplecounter = 1; 
			break;
		case "Sie dürfen nochmal würfeln. Sie haben eine 6 gewürfelt" : 
			this.meeplecounter = 1; 
			this.enemycounter = 1; 
			break;	
		}
	}
}

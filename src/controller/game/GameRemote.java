package controller.game;

import controller.exceptions.LoadException;
import controller.exceptions.NoMoveException;
import controller.exceptions.SaveException;
import controller.net.Client;
import controller.player.Player;
import javafx.concurrent.WorkerStateEvent;
//import javafx.concurrent.WorkerStateEvent;
//import javafx.event.EventHandler;
import model.Board;
import model.Content;
import model.Position;

public class GameRemote implements Game {


	private Board board;
	private Player player;
	private Client client;

	public GameRemote(Client client, Player player){
		this.client = client;
		this.player = player;

		client.setOnSucceeded( (WorkerStateEvent t) -> {
			System.out.println("Client succeeded");
			String s = (String) t.getSource().getValue(); 
			client.reset();
			process(s);
		});
		 
		listen();
	}

	public void start() {
		 
	}

	private void process(String s){
		switch(s){
		case "initialize" : initialize(); break;
		case "enable": enable(); break;
		case "disable": disable(); break;
		case "choose" : chooseMeeple(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "message": message(""); break;
		default: break;
		}
	}

	public void listen(){
			client.start();
	}
	
	/**
	 * Wenn "initialize" empfangen wird, wird ein Spieler erstellt und der Client gestartet (wartet auf Anweisungen)
	 */
	public void initialize(){
		int id = client.receiveID();
		Content c = client.receiveContent();
		player.initialize(c,this,id);
		listen();
	}

	/**
	 * Setzt das Board vom Spiel
	 * @param board
	 */
	public void setBoard(Board board){
		this.board = board;
	}

	/**
	 * Durchreichen von dem Befehl "enable", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 * @throws NoMoveException 
	 */
	public void enable() {
		this.board = client.receiveBoard();
		player.enable();
	}

	/**
	 * Durchreichen von dem Befehl "disable", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void disable() {
		this.board = client.receiveBoard();
		this.player.disable();
		listen();
	}

	/**
	 * Durchreichen von dem Befehl "choose", ruft die entsprechende Methode im Spieler auf
	 * @throws NoMoveException 
	 */
	public void chooseMeeple(){	
			 try {
				player.chooseMeeple();
			} catch (SaveException | LoadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * Durchreichen von dem Befehl "win", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void win() {
		this.board = client.receiveBoard();
		this.player.win();
	}

	/**
	 * Durchreichen von dem Befehl "lose", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void lose() {
		this.board = client.receiveBoard();
		this.player.lose();
	}


	/** Methode update sendet "update" an den Server und lauscht.
	 * @see controller.game.Game#update()
	 */
	public void update() {
		client.send("update");
		listen();
	}

	/** Methode sendet "save" an den Server
	 * @see controller.game.Game#safe()
	 */
	public void save (String string){ 
		client.send("save");
		client.send(string);
		listen();
	}

	/** Methode sendet "load" an den Server
	 * @see controller.game.Game#load()
	 */
	public void load(String string)  {
		client.send("load");
		client.send(string);
		listen();
	}

	/** Get-Methode vom Board
	 * @see controller.game.Game#getBoard()
	 */
	public Board getBoard() {
		return this.board;
	}

	public void message(String message) {
		String receivedmessage = client.receiveString();
		this.board = client.receiveBoard();
		player.message(receivedmessage);
		listen();
	}

	public Position chooseMeeple(Content content) {
		return null;
	}
	
	
	// EVENT 
	public void returnPosition(Position p) {
		client.send("choose");
		client.send(p);
		listen();
	
	}


}
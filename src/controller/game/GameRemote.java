package controller.game;

import controller.exceptions.NoMoveException;
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
	private boolean status;

	public GameRemote(Client client, Player player){
		this.client = client;
		this.player = player;

		client.setOnSucceeded( (WorkerStateEvent t) -> { String s = (String) t.getSource().getValue(); client.reset(); try {
			process(s);
		} catch (NoMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}});
		 
		/*
		 * client.setOnSucceeded(
				(WorkerStateEvent t) ->{
					String s = (String) t.getSource().getValue();
					System.out.println("done:" + t.getSource().getValue());
					client.reset();
					switch(s){
    					case "set" : set(); break;
    					case "enable": enable(); break;
    					case "disable": disable(); break;
    					case "choose" : chooseMeeple(); break;
    					case "win": win(); break;
    					case "lose": lose(); break;
    					case "message": message(""); break;
					}
				});

		client.start();
		 */
		listen();
	}

	public void start() {
		// TODO Auto-generated method stub
	}

	private void process(String s) throws NoMoveException{
		switch(s){
		case "initialize" : initialize(); break;
		case "enable": enable(); break;
		case "disable": disable(); break;
		case "choose" : chooseMeeple(); break;
		case "win": win(); break;
		case "lose": lose(); break;
		case "message": message(""); break;
		}
	}

	public void listen(){
	//	try {
			client.start();
			//process(client.listen());
			//process(client.start());
	//	} catch (NoMoveException e) {
			// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	}
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
		status = true;
		this.board = client.receiveBoard();
		player.enable();
		//listen();
	}

	/**
	 * Durchreichen von dem Befehl "disable", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void disable() {
		status = false; 
		this.board = client.receiveBoard();
		player.disable();
		listen();
	}

	/**
	 * Durchreichen von dem Befehl "choose", ruft die entsprechende Methode im Spieler auf
	 * @throws NoMoveException 
	 */
	public void chooseMeeple(){
		try {
			Position p = player.chooseMeeple(client.receiveDice());
			client.send("success");
			client.send(p);
		} catch (NoMoveException e) {
			client.send("NoMove");
		}
		
		finally {
		listen();
		}
	}

	/**
	 * Durchreichen von dem Befehl "win", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void win() {
		this.board = client.receiveBoard();
		player.win();
	}

	/**
	 * Durchreichen von dem Befehl "lose", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
	 */
	public void lose() {
		this.board = client.receiveBoard();
		player.lose();
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
	}


	/** Methode sendet "load" an den Server
	 * @see controller.game.Game#load()
	 */
	public void load(String string)  {
		client.send("load");
		client.send(string);
	}

	/** Get-Methode vom Board
	 * @see controller.game.Game#getBoard()
	 */
	public Board getBoard() {
		return board;
	}

	public void message(String message) {
		player.message(client.receiveString());
		listen();
	}

	public Position chooseMeeple(Content content) {
		
		return null;
	}

}
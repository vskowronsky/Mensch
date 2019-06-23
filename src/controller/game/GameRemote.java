package controller.game;

import controller.exceptions.LoadException;
import controller.exceptions.SaveException;
import controller.net.Client;
import controller.player.Player;
import javafx.concurrent.WorkerStateEvent;
import model.Board;
import model.Content;
import model.Position;

/**
 * Klasse zur Simulation eines Spieles für einen Client.
 */
public class GameRemote implements Game {
	private Board board;
	private Player player;
	private Client client;

	/**
	 * Konstruktor des Spieles
	 * @param Übergabe eines Client-Objektes zur Kommunikation zum Server/richtigen Spiel.
	 * Es wird gelauscht.
	 */
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

	/**
	 * Vom Server gesendete Nachrichten werden hier verarbeitet und an die entsprechende Methode 
	 * weitergereicht.
	 * @param s Vom Server gesendete Nachricht.
	 * @author Vanessa
	 */
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

	/**
	 * Methode startet den Client neu und eröffnet damit einen neuen Task.
	 */
	public void listen(){
			client.start();
	}
	
	/**
	 * Wenn "initialize" empfangen wird, wird ein Spieler erstellt und der Client gestartet.
	 * Er lauscht auf Anweisungen.
	 */
	public void initialize(){
		int id = client.receiveID();
		Content c = client.receiveContent();
		player.initialize(c,this,id);
		listen();
	}

	/**
	 * Durchreichen von dem Befehl "enable", 
	 * empfängt ein Board und ruft die entsprechende Methode beim Spieler auf
	 */
	public void enable() {
		this.board = client.receiveBoard();
		player.enable();
	}

	/**
	 * Durchreichen von dem Befehl "disable", 
	 * empfängt ein Board und ruft die entsprechende Methode beim Spieler auf. 
	 * Lauscht auf Anweisungen.
	 */
	public void disable() {
		this.board = client.receiveBoard();
		this.player.disable();
		listen();
	}

	/**
	 * Durchreichen von dem Befehl "choose", ruft die entsprechende Methode beim Spieler auf
	 */
	public void chooseMeeple(){	
		try {
			player.chooseMeeple();
		} catch (SaveException e) {
			e.printStackTrace();
		} catch (LoadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Durchreichen von dem Befehl "win", 
	 * empfängt ein Board und ruft die entsprechende Methode beim Spieler auf
	 */
	public void win() {
		this.board = client.receiveBoard();
		this.player.win();
	}

	/**
	 * Durchreichen von dem Befehl "lose", 
	 * empfängt ein Board und ruft die entsprechende Methode beim Spieler auf
	 */
	public void lose() {
		this.board = client.receiveBoard();
		this.player.lose();
	}

	/** Methode sendet "update" an den Server und lauscht.
	 */
	public void update() {
		client.send("update");
		listen();
	}

	/** Methode sendet "save" an den Server sowie einen String
	 * @author Vanessa
	 */
	public void save (String string){ 
		client.send("save");
		client.send(string);
		listen();
	}

	/** Methode sendet "load" an den Server sowie einen String
	 * @author Vanessa
	 */
	public void load(String string)  {
		client.send("load");
		client.send(string);
		listen();
	}

	/** Get-Methode vom Board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Methode erhält vom Client eine Nachricht und das Board wird in this.board neu geschrieben. 
	 * Die Nachricht wird an den Spieler weitergegeben.
	 * @author Vanessa
	 */
	public void message(String message) {
		String receivedMessage = client.receiveString();
		this.board = client.receiveBoard();
		player.message(receivedMessage);
		listen();
	}
	
	/**
	 * Methode sendet "choose" an den Server sowie die ausgewählte Position
	 * @author Vanessa
	 */
		public void returnPosition(Position p) {
			client.send("choose");
			client.send(p);
			listen();
		}

	/**
	 * Wird nicht überschrieben.
	 */
	public Position chooseMeeple(Content content) {
		return null;
	}
	
	/**
	 * Wird nicht überschrieben.
	 */
	public void start() {
	}
	
	/**
	 * Wird nicht überschrieben.
	 */
	public void enemyMessage(Content content, String message) {
	}

	/**
	 * Wird nicht überschrieben.
	 */
	public void pause(int time) {
	}


}
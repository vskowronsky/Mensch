package controller.player;

import java.net.Socket;

import controller.exceptions.LoadException;
import controller.exceptions.SaveException;
import controller.game.Game;
import controller.net.Server;
import model.Content;
import model.Position;

/**
 * Klasse simuliert einen Spieler, der die Anfragen von dem Spiel über 
 * das Netzwerk weiterreicht.
 */

public class PlayerRemote implements Player {
	private Server server;
	private Content content;
	private int id;
	private Game game;
	
	/**
	 * Konstruktor der Klasse PlayerRemote
	 * @param socket Ein Socket zum Senden an den Client
	 */
	public PlayerRemote(Socket socket){
		this.server = new Server(socket, this);
	}

	/**
	 * Methode um einen Spieler beim Client zu erzeugen.
	 */
	public void initialize(Content content, Game game, int id) {
		this.game = game;
		this.content = content;
		this.id = id;

		server.send("initialize");
		server.send(this.id);
		server.send(this.content);
	}

	/**
	 * Weiterleitung des Aufrufes "enable" über das Netzwerk an den Client.
	 * Server lauscht auf den Aufruf "update" des Spielers.
	 * @author Vanessa
	 */
	public void enable(){
		server.send("enable");
		server.send(game.getBoard());
		if (server.listen().equals("update")) {
			game.update();
		}

	}

	/**
	 * Weiterleitung des Aufrufes "disable" über das Netzwerk an den Client.
	 */
	public void disable() {
		server.send("disable");
		server.send(game.getBoard());
	}

	/**
	 * Weiterleitung des Aufrufes "choose" über das Netzwerk an den Client. 
	 * Der Server verarbeitet den String weiter, den er im Anschluss bekommt.
	 * @author Vanessa
	 */
	public Position chooseMeeple() throws SaveException, LoadException{
		server.send("choose");
		String answer = server.receiveString();
		switch (answer) {
		case "choose": return server.receivePosition();
		case "save": 
			String savefile = server.receiveString();
			throw new SaveException(savefile);
		case "load": 
			String loadfile = server.receiveString();
			throw new LoadException(loadfile);
		default: return null;
		}
	} 

	/**
	 * Weiterleitung des Aufrufes "win" über das Netzwerk an den Client.
	 */
	public void win() {
		server.send("win");
		server.send(game.getBoard());
	}

	/**
	 * Weiterleitung des Aufrufes "lose" über das Netzwerk an den Client.
	 */
	public void lose() {
		server.send("lose");
		server.send(game.getBoard());
	}

	/**
	 * Aufruf der Methode update im Spiel.
	 */
	public void update(){
		game.update();
	}

/**
 * Weiterleitung des Aufrufes "message" über das Netzwerk an den Client. Der String message
 * wird direkt mitgeschickt.
 * @author Vanessa
 */
	public void message(String message) {
		server.send("message");
		server.send(message);
		server.send(game.getBoard());
	}

}

package controller.player;

import java.net.Socket;

import controller.game.Game;
import controller.net.Server;
import model.Content;
import model.Position;


public class PlayerRemote implements Player {
	private Server server;
	private Content content;
	private int id;
	private Game game;

	
	public PlayerRemote(Socket socket){
		this.server = new Server(socket, this);
	}
	
	public void initialize(Content content, Game game, int id) {
		this.game = game;
		this.content = content;
		this.id = id;
		
		server.send("initialize");
		server.send(this.id, "string");
		server.send(this.content);
	}

	public void enable(){
		server.send("enable");
		server.send(game.getBoard());
		server.listen();
	}

	public void disable() {
		server.send("disable");
		server.send(game.getBoard());
	}

	public Position chooseMeeple(){
		server.send("choose");
		return server.receivePosition();
	}

	public void win() {
		server.send("win");
		server.send(game.getBoard());
	}

	public void lose() {
		server.send("lose");
		server.send(game.getBoard());
	}

	public void save(String fileName){
		server.send("save");
		game.save(fileName);
	}
	
	public void load(String fileName){
		server.send("load");
		game.load(fileName);
	}

	public void update(){
		game.update();
	}


	public void message(String message) {
		server.send("message");
		server.send(message);
		server.send(game.getBoard());
	}

}

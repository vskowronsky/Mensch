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
	
	@Override
	public void initialize(Content content, Game game, int id) {
		this.game = game;
		this.content = content;
		this.id = id;
		
		server.send("initialize");
		server.send(id);
		server.send(content);
	}

	@Override
	public void enable() {
		server.send("enable");

		server.send(game.getBoard());
		
		server.listen();
	}

	@Override
	public void disable() {
		server.send("disable");
		server.send(game.getBoard());
	}

	@Override
	public Position chooseMeeple() {
		server.send("chooseMeeple");
		return server.receivePosition();
	}

	@Override
	public void win() {
		server.send("win");
		server.send(game.getBoard());
	}

	@Override
	public void lose() {
		server.send("lose");
		server.send(game.getBoard());
	
	}

	
	public void save(String fileName){
		game.save(fileName);
	}
	
	public void load(String fileName){
		game.load(fileName);
	}

	public void update(){
		game.update();
	}


	@Override
	public void diceResult() {
	}


	@Override
	public void throwOwnMeeple() {
	}

	@Override
	public void doubleDiceResult() {
	}

	@Override
	public void enemyResult() {
	}

	@Override
	public void moveNotPossible() {
	}

	@Override
	public void noMoveAtAll() {
	}

	@Override
	public void freeStart() {
	}

	@Override
	public void missedEnemyResult() {
		// TODO Auto-generated method stub
		
	}

}

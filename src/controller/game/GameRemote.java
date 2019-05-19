package controller.game;

import controller.NoMoveException;
import controller.net.Client;
import controller.player.Player;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
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
    					case "chooseMeeple" : chooseMeeple(); break;
    					case "win": win(); break;
    					case "lose": lose(); break;
					}
				});

		client.start();
		 */
		listen();
	}

	@Override
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
		}
	}

	/*public void listen(){
	process(client.listen());
}
	 */
	public void listen(){
		client.start();
	}

	/**
	 * Wenn set empfangen wird, wird ein Spieler erstellt
	 * @param Content c, entweder Kreuz oder Kreis
	 * @param int id.
	 */
	//public void set(Content c, int id){
	//	player = new PlayerGUI(c, this, id);
	//}

	/**
	 * Wenn "set" empfangen wird, wird ein Spieler erstellt und der Client gestartet (wartet auf Anweisungen)
	 */
	public void initialize(){
		Content c = client.receiveContent();
		int id = client.receiveInt();
		player.initialize(c,this,id);
		listen();
	}


	/**
	 * Setzt das Board von dem Spiel
	 * @param board
	 */
	public void setBoard(Board board){
		this.board = board;
	}


	/**
	 * Durchreichen von dem Befehl "enable", empfängt ein Board und ruft die entsprechende Methode im Spieler auf
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
		player.disable();
		listen();
	}


	/**
	 * Durchreichen von dem Befehl "place",  ruft die entsprechende Methode im Spieler auf
	 * @throws NoMoveException 
	 */
	public void chooseMeeple() throws NoMoveException{
		client.send(player.chooseMeeple());
		listen();
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
	}


	/** Methode sendet "load" an den Server
	 * @see controller.game.Game#load()
	 */
	public void load(String string)  {
		client.send("load");
	}

	/** Get-Methode vom Board
	 * @see controller.game.Game#getBoard()
	 */
	public Board getBoard() {
		return board;
	}


	@Override
	public int dice() {
		return 0;
	}

	@Override
	public void diceMessage() {
	}

	@Override
	public void moveNotPossibleMessage() {
	}

	@Override
	public void ownMeepleMessage() {
	}

	@Override
	public void enemyMessage() {
	}

	@Override
	public Content checkPosition(Position chosenPosition, Content content) {
		return null;
	}

	@Override
	public void missedEnemyMessage() {
		// TODO Auto-generated method stub
		
	}


}

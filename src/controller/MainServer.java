package controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import controller.game.Game;
import controller.game.GameImplementation;
import controller.player.PlayerRemote;

public class MainServer {
	public final static int DEFAULT_PORT = 6789;
	protected int port;
	protected ServerSocket sSocket;
	private Socket[] sockets;
	
	
	
	
	/**
	 * Konstruktor der Klasse MainServer
	 */
	public MainServer(){
		this.port = DEFAULT_PORT;
		sockets = new Socket[4];
		try {
			sSocket = new ServerSocket(port);
		} catch (IOException e) { 
			
		}
	}
	
	/**
	 * Akzeptieren von zwei Clients und Erzeugung eines Spieles
	 */
	public Game playerAccept() {
		
		try {
			InetAddress ia = InetAddress.getLocalHost();
			System.out.println("Host: " + ia.getHostName());
			System.out.println("Server *" + ia.getHostAddress()	+ "* lauscht auf Port " + port);
			int counter = 0;
			while (counter < 4) {
				System.out.println("Warte auf Spieler");
				Socket cSocket = sSocket.accept();
				sockets[counter] = cSocket;
				counter ++;
				System.out.println("Spieler angemeldet");
			}
			return new GameImplementation(new PlayerRemote(sockets[0]), new PlayerRemote(sockets[1]),
					new PlayerRemote(sockets[2]), new PlayerRemote(sockets[3]));
			
		} 
		catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Main Methode um einen Server zu starten
	 * @param args
	 */
	public static void main(String[] args){
		MainServer server = new MainServer();
		Game game = server.playerAccept();
		game.start();
	}
}

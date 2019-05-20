package controller;

import controller.game.GameRemote;
import controller.net.Client;
import controller.player.PlayerKI;

public class MainNetworkKI {
	public static void main(String[] args){
		
		String host = "localhost";
		int port = 6789;
		Client c = new Client(host, port);
		new GameRemote(c, new PlayerKI());
	}
	
}

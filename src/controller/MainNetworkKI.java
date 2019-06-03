package controller;

import controller.game.GameRemote;
import controller.net.Client;
import controller.player.PlayerKI;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainNetworkKI extends Application {
	
	
	
	
	
	
	public static void main(String[] args){
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		String host = "localhost";
		int port = 6789;
		Client c = new Client(host, port);
		new GameRemote(c, new PlayerKI());
	}
	
}

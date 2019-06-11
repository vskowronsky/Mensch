package controller;

import controller.game.GameRemote;
import controller.net.Client;
import controller.player.PlayerGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainNetworkGUI extends Application{




	@Override
	public void start(Stage primaryStage) throws Exception {
		String host = "localhost";
		int port = 6789;
		Client c = new Client(host, port);
		new GameRemote(c, new PlayerGUI());
	}

	public static void main(String[] args) {
		launch(args);

	}
}

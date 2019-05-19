package controller;

import controller.game.Game;
import controller.game.GameImplementation;
import controller.player.PlayerCUI;
import controller.player.PlayerKI;

public class MainCUI {
	public static void main(String[] args) {
		Game game = new GameImplementation(new PlayerKI(), new PlayerKI(),new PlayerKI(),new PlayerKI());
		
		
	}
}
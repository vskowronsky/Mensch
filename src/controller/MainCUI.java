package controller;

import controller.game.Game;
import controller.game.GameImplementation;
import controller.player.PlayerCUI;
import controller.player.PlayerKI;

public class MainCUI {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Game game = new GameImplementation(new PlayerCUI(), new PlayerKI(),new PlayerKI(),new PlayerKI());
		
		
	}
}
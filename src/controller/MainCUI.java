package controller;

import controller.game.Game;
import controller.game.GameImplementation;
import controller.player.PlayerCUI;
import model.Board;
import model.Dice;

public class MainCUI {
	public static void main(String[] args) {
		Game game = new GameImplementation(new PlayerCUI(), new PlayerCUI(),new PlayerCUI(),new PlayerCUI());
		game.load();
		Board board = new Board();
		System.out.println(board.toString());
		
	}
}
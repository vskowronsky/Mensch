package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import controller.game.Game;
import model.Content;
import model.Position;

public class PlayerCUI implements Player {
	private Content content;
	private Game game;
	private int id;

	public PlayerCUI() {
		this.content = Content.FREE;
		this.game = null;
		this.id = -1;
	}

	@Override
	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	@Override
	public void enable() {
		System.out.println("NEUE RUNDE!");
		int sl = -1;
		System.out.println(game.getBoard());
		do {
			System.out.println("Sie sind dran, Spieler " + id);
			System.out.println(
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 f�r Setzen, 1 f�r Speichern, 2 f�r Laden");
			sl = readInt();

		} while (sl < 0 || sl > 2);
		if (sl == 0)
			update();
		if (sl == 1)
			save();
		if (sl == 2) {
			load();
			update();
		}
	}

	private int readInt() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = "";
			Integer string_to_int;
			eingabe = input.readLine();
			string_to_int = new Integer(eingabe);
			return string_to_int.intValue();
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public void disable() {
		System.out.println("Ein Spielstein wurde gesetzt. Der Spielzug ist beendet.");
	}

	@Override
	public void win() {
		System.out.println("Spieler" + id + "hat gewonnen!");
	}

	@Override
	public void lose() {
		System.out.println("Spieler" + id + "hat verloren!");
	}

	@Override
	public Position chooseMeeple() {
		int chosen = -1;
		diceResult();
		System.out.println("W�hlen Sie eine Spielfigur aus.");

		while (chosen == -1) {
			chosen = readInt();
			if (chosen != -1) {
				Position chosenPosition = new Position(chosen);
				if (this.content == game.checkPosition(chosenPosition, content)) {
					return chosenPosition;
				}else {
					System.out.println("Bitte w�hlen Sie ein Feld mit einer Ihrer Figuren aus.");
					chosen = -1;
				}
				
			} else {
			System.out.println("Eingabe nicht korrekt. Bitte eine Spielfigur w�hlen.");
			}
		}
		return null;
	}

	private void update() {
		game.update();
			
	}

	private void save() {
		game.save();
	}

	private void load() {
		
		game.load();
		
	}

	public void diceResult() {
		System.out.println("Sie haben eine " + game.dice() + " gew�rfelt.");
	}
	
	public void moveOverrun() {
		System.out.println("Spielzug nicht m�glich. Sie m�ssen neu setzen.");
	}
	
	public void throwOwnMeeple() {
		System.out.println("Sie k�nnen sich nicht selber vom Spielbrett werfen.");
	}
	
	public void doubleDiceResult() {
		System.out.println("Sie d�rfen nochmal w�rfeln. Sie haben eine " + game.dice() + " gew�rfelt");
	}
	
	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}
	
}

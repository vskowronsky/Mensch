package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;

import controller.exceptions.NoMoveException;
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

	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	public void enable() {
		System.out.println("NEUE RUNDE!");
		int sl = -1;
		System.out.println(game.getBoard());
		do {
			System.out.println("Sie sind dran, Spieler " + id);
			System.out.println(
					"Wollen Sie eine Spielfigur setzen, einen Spielstand speichern oder einen Spielstand laden? \n 0 für Setzen, 1 für Speichern, 2 für Laden");
			sl = readInt();

		} while (sl < 0 || sl > 2);
		if (sl == 0) {
			update();
		}
		if (sl == 1) {
			save();
			update();
		}
		if (sl == 2) {
			load();
			update();
		}
	}

	private String readString() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			return eingabe;
		}catch (Exception e) {
			return null;
		}
	}

	private String enterFileName() {
		return (readString());
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

	public Position chooseMeeple(){
		int chosen = -1;
		
			
		System.out.println("Wählen Sie eine Spielfigur aus.");

		while (chosen == -1) {
			chosen = readInt();
			if (chosen != -1) {
				return new Position(chosen);
			} else {
				System.out.println("Eingabe nicht korrekt. Bitte eine Spielfigur wählen.");
			}
		}
		return null;
	} 

	private void update() {
		game.update();
	}

	private void save() {
		System.out.println("Bitte geben Sie der zu speichernden Datei einen Namen.");
		game.save(enterFileName());
	}

	private void load() {
		//		int name = -1;
		//		while (name != -1) {
		System.out.println("Bitte geben Sie den Namen der zu ladenden Datei ein.");
		//			if ((enterFileName()) {
		game.load(enterFileName());
		//		} else {
		//			System.out.println("Der Name der zu ladenen Datei existiert nicht. Bitte gegen Sie ihn erneut ein.");
		//			name = -1;
		//		}
		//		}
		//		
	}

	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
		System.out.println(game.getBoard());
	}

	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
		System.out.println(game.getBoard());
	}

	public void lose() {
		System.out.println("Spieler " + id + " hat verloren!");
	}

	public void message(String message) {
		System.out.println(message);
	}
}

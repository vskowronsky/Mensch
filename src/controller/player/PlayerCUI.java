package controller.player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;

import controller.exceptions.NoMoveException;
import controller.game.Game;
import model.Content;
import model.Position;

/**
 * Klasse erzeugt einen Spieler für das Command User Interface.
 *
 */
public class PlayerCUI implements Player {
	private Content content;
	private Game game;
	private int id;

	public PlayerCUI() {
		this.game = null;
		this.id = -1;
	}

	/**
	 * Initialisiert einen Spieler mit den übergebenen Parametern.
	 * @author Laura, Vanessa
	 */
	public void initialize(Content content, Game game, int id) {
		this.content = content;
		this.game = game;
		this.id = id;
	}

	/**
	 * Der Spieler wird aktiviert und gefragt, was er machen möchte.
	 * @author Laura, Vanessa
	 */
	public void enable() {
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

	/**
	 * Methode um einen über die Konsole eingegeben int zu verarbeiten.
	 * Rückgabe -1 bei eine ungültigen Eingabe.
	 * @return int, der eingegeben wurde, sonst -1 
	 */
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

	/**
	 * Aufforderung an den Spieler eine Position auszuwählen. 
	 * Erneute Aufforderung eine Position zu wählen, wenn die erste Eingabe falsch war.
	 *@author Vanessa
	 */
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

	/**
	 * Aufrauf der Methode update im Spiel.
	 */
	private void update() {
		game.update();
	}

	/**
	 * Methode um einen Spielstand zu speichern. Dazu muss der zu speichernden Datei ein
	 * Name gegeben werden.
	 * @author Vanessa
	 */
	private void save() {
		System.out.println("Bitte geben Sie der zu speichernden Datei einen Namen.");
		game.save(readString());
	}

	/**
	 * Methode um einen Spielstand zu laden. Die zu ladene Datei muss über ihren Namen
	 * aufgerufen werden.
	 * @author Vanessa
	 */
	private void load() {
		System.out.println("Bitte geben Sie den Namen der zu ladenden Datei ein.");
		game.load(readString());	
	}

	/**
	 * Read-Methode für einen String.
	 * @return Ein String
	 * @author Vanessa
	 */
	private String readString() {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String eingabe = input.readLine();
			return eingabe;
		}catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Mitteilung, dass der Spielzug beendet wurde. Das Board wird einmal ausgegeben.
	 * @author Laura, Vanessa
	 */
	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
		System.out.println(game.getBoard());
	}

	/**
	 * Mitteilung, dass der Spieler gewonnen hat. Das Board wird einmal ausgegeben.
	 * @author Laura, Vanessa
	 */
	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
		System.out.println(game.getBoard());
	}

	/**
	 * Mitteilung, dass der Spieler verloren hat.
	 * @author Laura, Vanessa
	 */
	public void lose() {
		System.out.println("Spieler " + id + " hat verloren!");
	}

	/**
	 * Ausgabe, der Nachricht, die vom Board geschickt wurde.
	 * @author Vanessa
	 */
	public void message(String message) {
		System.out.println(message);
	}
}

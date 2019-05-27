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

	@Override
	public Position chooseMeeple() throws NoMoveException {
		int chosen = -1;
		diceResult();

		System.out.println("Wählen Sie eine Spielfigur aus.");

		while (chosen == -1) {
			chosen = readInt();
			if (chosen != -1) {
				Position chosenPosition = new Position(chosen);
				if (this.content == game.checkPosition(chosenPosition, content)) {
					return chosenPosition;
				}else {
					System.out.println("Bitte wählen Sie ein Feld mit einer Ihrer noch bewegbaren Figuren aus.");
					chosen = -1;
				}

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

	@Override
	public void disable() {
		System.out.println("Der Spielzug ist beendet.");
	}

	@Override
	public void win() {
		System.out.println("Spieler " + id + " hat gewonnen!");
		System.out.println(game.getBoard());
	}

	@Override
	public void lose() {
		System.out.println("Spieler " + id + " hat verloren!");
	}

	public void diceResult() {
		System.out.println("Sie haben eine " + game.dice() + " gewürfelt.");
	}

	public void moveNotPossible() {
		System.out.println("Spielzug nicht möglich. Wählen Sie eine andere Figur.");
	}

	public void throwOwnMeeple() {
		System.out.println("Sie können sich nicht selber vom Spielbrett werfen.");
	}

	public void doubleDiceResult() {
		System.out.println("Sie dürfen nochmal würfeln. Sie haben eine " + game.dice() + " gewürfelt");
	}

	public void enemyResult() {
		System.out.println("Eine Figur wurde geworfen.");
	}
	
	public void missedEnemyResult() {
		System.out.println("Sie haben verpasst einen Gegner zu schlagen. Dafür wurde Ihre Figur zurück ins Haus gesetzt.");
	}

	public void noMoveAtAll() {
		System.out.println("Kein Zug ist möglich. Der nächste Spiel ist dran.");
	}

	public void freeStart() {
		System.out.println("Das Startfeld war belegt und musste vorrangig gespielt werden.");
	}
}

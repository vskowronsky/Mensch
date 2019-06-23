package controller.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.BoardSet;
import model.PersistenceObject;
import model.Position;

/**
 * Klasse zum Managen von Speichern und Laden des Spiels in eine "dateiname".ser-Datei
*/
public class SaveLoad {
	/*
	 * Methode, um ein PersistenceObjcet in der Datei "dateiname".ser zu speichern.
	 * @param po Objekt, das gespeichert werden soll.
	 * @param fileName Name, den die Datei bekommen soll.
	 */
	public static void save(PersistenceObject po, String fileName) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName+".ser"));
			oos.writeObject(po);
			oos.close();
			System.out.println("Das Board wurde gespeichert:");
			System.out.println(po.getBoard());
			System.out.println("Der Status wurde gespeichert " + po.getStatus());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Methode, um ein PersistenceObject aus der Datei "dateiname".ser zu laden.
	 * @param fileName Name der Datei, die geladen werden soll.
	 * @return Das geladene PersistenceObject.
	 */
	public static PersistenceObject load(String fileName){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName+".ser"));
			PersistenceObject po = (PersistenceObject) ois.readObject();
			ois.close();
			return po;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
  }

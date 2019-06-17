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
 * 
 * Class for managing to safe and to load the game into file TicTacToe.ser. Consists only of two static methods.
*/
public class SaveLoad {
	/**
	 * Static method for saving a PersistenceObject into the file Mensch.ser.
	 * @param po the object to safe
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
	 * Static method for loading a PersistenceObject from the file Mensch.ser.
	 * @return the loaded PersistenceObject.
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

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
	 * Static method for saving a PersistenceObject into the file TicTacToe.ser.
	 * @param po the object to safe
	 */
	public static void save(PersistenceObject po) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Mensch.ser"));
			oos.writeObject(po);
			oos.close();
			System.out.println("Das Board wurde gespeichert:");
			System.out.println(po.getBoard());
			System.out.println("Der Status wurde gespeichert " + po.getStatus());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Static method for saving a PersistenceObject into a readable csv File Mensch.csv.
	 * @param po the object to safe
	 
	
	public static void saveCSV(PersistenceObject po){
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Mensch.csv"));
			for(int row = 0 ; row < 3; row++){
				for(int column = 0; column < 3; column++){
					bw.write(po.getBoard().getIndex(new Position(index)).toString() + ",");
				}
				bw.newLine();
			}
			bw.write(po.getStatus() + "");

			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
	
	/**
	 * Static method for loading a PersistenceObject from the file Mensch.ser.
	 * @return the loaded PersistenceObject.
	 */
	public static PersistenceObject load(){
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Mensch.ser"));
			PersistenceObject po = (PersistenceObject) ois.readObject();
			ois.close();
			return po;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Static method for loading a PersistenceObject from the file TicTacToe.csv.
	 * @return the loaded PersistenceObject.
	 
	public static PersistenceObject loadCSV(){
		BoardSet board = new BoardSet();
		int status = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader("Mensch.csv"));
			for(int row = 0; row < 3; row++){
				String s = br.readLine();
				String[] split = s.split(",");
				for(int column = 0; column < split.length; column++){
					if(split[column].equals("CIRCLE")){
						board.setCircle(new Position(row,column));
					}
					if(split[column].equals("CROSS")){
						board.setCross(new Position(row, column));
					}
				}
			}
			status = (new Integer(br.readLine())).intValue();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PersistenceObject po = new PersistenceObject(status,board);
		return po;
		
	}
	*/
  }

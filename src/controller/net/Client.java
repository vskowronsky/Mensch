package controller.net;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import javafx.concurrent.Service;
import javafx.concurrent.Task;
import model.Board;
import model.Content;
import model.Position;

/**
 * 
 * Klasse Client erweitert Service<String> um einen Hintergrundprozess starten zu k�nnen
 *
 */
public class Client extends Service<String>
{

	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Socket socket;

	/**
	 * Konstruktor des Client. Erzeugt einen Socket um sich mit dem Server zu verbinden
	 * und generiert den Ein - und Ausgabestrom
	 * @param Der Server als String, entweder "localhost" oder die IP als String
	 * @param Der Port als Integer
	 */
	public Client(String host, int port){
		try {
			socket = new Socket(host, port);
			in =  new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode zum Lauschen auf eine Nachricht. Wird mit der Methode createTask als 
	 * Hintergrundprozess gestartet.
	 * @return String als Nachricht.
	 */
	public String listen(){
		System.out.println("Warte auf Anweisung");
		String s = receiveString();
		return s;
	}
	
	/**
	 * Methode zum Beenden der Kommunikation
	 */
	public void end(){
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Empf�ngt einen String und gibt diesen zur�ck
	 * @return Vom Server geschickte Nachricht.
	 */
	public String receiveString(){
		try{
			String s = (String) in.readObject();
			System.out.println("Empfange Nachricht " + s);
			return s;
		}  catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Methode um ein Objekt vom Typ Content zu empfangen
	 * @return Ein Content
	 */
	public Content receiveContent(){
		try {
			Content c = (Content) in.readObject();
			System.out.println("Empfange Content " + c);
			return c;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Methode um ein int zu empfangen.
	 * @return Gibt den empfangenen int zur�ck
	 */
	public int receiveID(){
		int id = 0;
		try {
			id= in.readInt();
			System.out.println("Empfange id " + id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	/**
	 * Methode um ein Board zu empfangen
	 * @return Gibt das empfangene Board zur�ck. Null, wenn ein Fehler auftritt.
	 */
	public Board receiveBoard(){
		try {
			Board board = (Board) in.readObject();
			System.out.println("Empfange Board ");
			return board;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Methode um ein String zu senden.
	 * @param Die zu sendene Nachricht als String
	 */
	public void send(String string){
		try {
			System.out.println("Sende Nachricht " + string);
			out.writeObject(string);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Methode um eine Position zu versenden
	 * @param Die zu sendende Position.
	 */
	public void send(Position position){
		try {
			
			System.out.println("Sende Position " + position.getIndex());
			out.reset();
			out.writeObject(position);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/** 
	 * �berschriebene Methode um einen Hintergrundprozess zu starten. 
	 * @see javafx.concurrent.Service#createTask()
	 */
	protected Task<String> createTask() {
		return new Task<String>() {
			protected String call(){
				return listen();
			}
        };
	}
}


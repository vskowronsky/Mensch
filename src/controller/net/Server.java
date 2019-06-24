package controller.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.player.PlayerRemote;
import model.Board;
import model.Content;
import model.Position;

public class Server {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	@SuppressWarnings("unused")
	private PlayerRemote player;

	/**
	 * Konstruktor um eine Verbindung mit einem Client zu erzeugen
	 * @param Der Socket von dem Client
	 * @param Der zugehörige Player zu dem Client
	 */
	public Server(Socket cSocket, PlayerRemote player) {
		this.player = player;
		try {
			out = new ObjectOutputStream(cSocket.getOutputStream());
			in = new ObjectInputStream(cSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode um auf eine Nachricht vom Client zu warten und zu empfangen
	 */
	public String listen(){	
		System.out.println("Server lauscht.");
		return  receiveString();
	}

	/**
	 * Methode um eine Position zu empfangen
	 * @return Die empfangene Position
	 */
	public Position receivePosition(){		
		try {
			Position position = (Position) in.readObject();
			if (position == null) {
				receiveString();
			}
			return position;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode um einen String zu empfangen
	 * @return Die empfangene Nachricht als String
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
	 * Methode um einen Content zu senden
	 * @param Das zu sendende Content
	 */
	public void send(Content content){
		try{
			System.out.println("Sende Content " + content);
			out.writeObject(content);
			out.flush();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Methode um einen int zu senden.
	 * @param Das zu versendende int.
	 */
	public void send(int number){
		try{
			System.out.println("Sende " + number);
			out.writeInt(number);
			out.flush();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Methode um ein Board zu versenden
	 * @param Das zu versendende Board.
	 */
	public void send(Board board){
		try {
			//			System.out.println("Sende Board " + board);
			out.reset();
			out.writeObject(board);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode um eine Nachricht zu versenden
	 * @param Die zu versendende Nachricht als String
	 */
	public void send(String message){
		try {
			System.out.println("Sende Nachricht " + message);
			out.reset();
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
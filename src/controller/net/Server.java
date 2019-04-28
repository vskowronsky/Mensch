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

	
	private PlayerRemote player;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	
	public Server (Socket csocket, PlayerRemote player){
		this.player = player;
		
		try {
			out = new ObjectOutputStream(csocket.getOutputStream());
			in = new ObjectInputStream(csocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void listen(){
		String s = receiveString();
		System.out.println(s);
		switch(s){
			case "update" : player.update(); break;
			case "save" : player.save(); break;
			case "load" : player.load(); break;
		}
		
	}
	
	
	
	public Position receivePosition(){
		try{
			Position position = (Position) in.readObject();
			System.out.println("Empfange Position " + position);
			return position;
		} catch(ClassNotFoundException e){
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String receiveString(){
		String s;
		try {
			s = (String) in.readObject();
			System.out.println("Empfange Nachricht " + s);
			return s;
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void send(Content content){
		System.out.println("Sende Content" + content);
		try {
			out.writeObject(content);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(int id){
		System.out.println("Sende id" + id);
		try {
			out.writeInt(id);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void send(Board board){
		System.out.println("Sende Board " + board);
		try {
			out.writeObject(board);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void send(String message){
		System.out.println("Sende Nachricht " + message);
		try{	
			out.writeObject(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




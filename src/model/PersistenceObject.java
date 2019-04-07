package model;

import java.io.Serializable;

import controller.game.Status;

public class PersistenceObject implements Serializable {
	private static final long serialVersionUID = -2592952362343836403L;
	private Status status;
	private BoardSet board;
	
	public PersistenceObject(Status status, BoardSet board) {
		this.status = status;
		this.board = board;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public BoardSet getBoard() {
		return board;
	}
}

package model;

import java.io.Serializable;

import controller.game.Status;

public class PersistenceObject implements Serializable {
	private static final long serialVersionUID = -2592952362343836403L;
	private Status status;
	private BoardSet board;
	
	/**
	 * Konstruktur der Klasse.
	 * @param status; aktueller Status des Spiels
	 * @param board; aktuelles Board
	 */
	public PersistenceObject(Status status, BoardSet board) {
		this.status = status;
		this.board = board;
	}
	
	/**
	 * Get-Methode für den Status
	 * @return status als Status
	 */
	public Status getStatus() {
		return status;
	}
	
	/**
	 * Get-Methode für das Board
	 * @return das Board
	 */
	public BoardSet getBoard() {
		return board;
	}
}

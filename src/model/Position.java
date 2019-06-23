package model;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = -3110015954264308884L;
	private int index;

	
	/**
	 * Konstruktor der Position mit einen gegebenen Index
	 * @param index als int
	 * @author Laura
	 */
	public Position (int index) {
		this.index = index;
	}

	/**
	 * Get-Methode für den Index der Position
	 * @return index als int
	 * @author Laura
	 */
	public int getIndex() {
		return index;
	}
}

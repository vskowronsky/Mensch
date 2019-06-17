package model;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = -3110015954264308884L;
	
		private int index;

	public Position (int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}

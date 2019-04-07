package model;

public class Position {
	private int index;

	public Position (int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	public String toString() {
		return "Position: " + index;
	}
	public boolean equals(Position p) {
		return p.getIndex() == index;
	}
}

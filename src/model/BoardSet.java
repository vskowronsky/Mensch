package model;

//import java.io.Serializable;

public class BoardSet extends Board /*implements Serializable*/ {

	//private static final long serialVersionUID = -6925781465692944476L;
	Position newPosition;
	public BoardSet() {
		super();
	}
	
	public boolean setMeeple(Position position, Content content, int diceValue) {
		if (position.getIndex() + diceValue >= 40) {
			board[(position.getIndex() + diceValue) - 40] = content;
			newPosition = new Position((position.getIndex() + diceValue) - 40);
		}else {
		board[position.getIndex() + diceValue] = content;
		newPosition = new Position(position.getIndex() + diceValue);
		}
		
		if(diceValue != 0) {
		board[position.getIndex()] = Content.FREE;
		}
		
		return true;
	}
	}
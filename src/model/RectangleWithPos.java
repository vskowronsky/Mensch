package model;

import javafx.scene.shape.Rectangle;

public class RectangleWithPos extends Rectangle{
	private int position;

	public RectangleWithPos(double eins, double zwei, double drei, double vier, int i) {
		super(eins, zwei, drei, vier);
		this.position = i;

	}

	public int getPosition()
	{return this.position;}

}

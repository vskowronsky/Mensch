package view;

import javafx.scene.shape.Rectangle;

public class RectangleWithPos extends Rectangle{
	private int position;

	public RectangleWithPos(double xCoord, double yCoord, double xWidth, double yWidth, int i) {
		super(xCoord, yCoord, xWidth, yWidth);
		this.position = i;

	}

	public int getPosition()
	{return this.position;}

}

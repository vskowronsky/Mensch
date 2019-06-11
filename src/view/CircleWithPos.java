package view;

import javafx.scene.shape.Circle;

public class CircleWithPos extends Circle {
	private int position;

	public CircleWithPos(double centerX, double centerY, double radius, int i) {
		super(centerX, centerY, radius);
		this.position = i;
	}

	public int getPosition(){
		return this.position;
	}

}

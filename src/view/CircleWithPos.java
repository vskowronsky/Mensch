package view;

import javafx.scene.shape.Circle;

/**
 * Erweiterung der JavaFX-Klasse Circle um einen int-Wert. 
 * Der int-Wert dient als Index des Objektes "Circle" in einem Array.
 * @author Vanessa
 */
public class CircleWithPos extends Circle {
	private int position;

	/**
	 * Konstruktor der Klasse. 
	 * @param centerX X-Koordinate des Mittelpunktes des Kreises
	 * @param centerY Y-Koordinate des Mittelpunktes des Kreises
	 * @param radius Radius des Kreises
	 * @param i Index des Kreises
	 */
	public CircleWithPos(double centerX, double centerY, double radius, int i) {
		super(centerX, centerY, radius);
		this.position = i;
	}

	public int getPosition(){
		return this.position;
	}

}

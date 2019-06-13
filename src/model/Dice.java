package model;

import java.io.Serializable;
import java.util.Random;

public class Dice implements Serializable {
/**
	 * 
	 */
private static final long serialVersionUID = -7817119889713205449L;
private Random random;

public Dice() {
	random = new Random();
}

/**
 * Der W�rfel wird mit einer randomisierten Zahl gesetzt. Da random bei 0 anf�ngt zu z�hlen, muss immer +1 gerechnet werden, 
 * da unsere W�rfelzahlen von 1 bis 6 gehen.
 */
public int throwDice() {
	int randomValue;
	randomValue = random.nextInt(6);
	int diceValue;
	diceValue = randomValue +1;
	return diceValue;
}

}

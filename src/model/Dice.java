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
public int throwDice() {
	int randomValue;
	randomValue = random.nextInt(6);
	int diceValue;
	diceValue = randomValue +1;
	return diceValue;
}

}

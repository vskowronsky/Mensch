package model;

import java.util.Random;

public class Dice {
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

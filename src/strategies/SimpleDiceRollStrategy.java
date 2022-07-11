package strategies;

import models.Dice;

import java.util.Random;

public class SimpleDiceRollStrategy implements DiceRollStrategies {
    @Override
    public int roll() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}

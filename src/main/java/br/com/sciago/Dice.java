package br.com.sciago;

import java.util.random.RandomGenerator;

public final class Dice {

    private Dice() {
    }

    public static int rollD20() {
        return RandomGenerator.getDefault().nextInt(1, 21);
    }
}

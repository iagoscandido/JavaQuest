package br.com.sciago.model;

import lombok.Getter;

import java.util.random.RandomGenerator;

public enum Die {
    NO_DIE(1),
    D2(2),
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20);

    @Getter
    private final int sides;

    Die(int sides) {
        this.sides = sides;
    }

    public int roll() {
        return RandomGenerator.getDefault().nextInt(1, sides + 1);
    }

    public int noRoll() {
        return 1;
    }
}
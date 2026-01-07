package br.com.sciago;

public class Adventurer extends Creature {
    private final int proficiencyBonus = 2;

    public Adventurer(String name, AbilityScores abilityScores) {
        super(name, abilityScores);
    }

    @Override
    public int getAttackBonus() {
        return super.getAttackBonus() + proficiencyBonus;
    }
}

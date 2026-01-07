package br.com.sciago;

public class Creature {
    private final String name;
    private final AbilityScores abilityScores;
    private int maxHp;
    private int currentHp;

    public Creature(String name, AbilityScores abilityScores) {
        this.name = name;
        this.abilityScores = abilityScores;
        this.currentHp = calculateMaxHp(abilityScores.constitutionModifier());
        this.maxHp = currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    private int calculateMaxHp(int constitutionModifier) {
        int maxHp = 10 + constitutionModifier;

        if (maxHp <= 0)
            throw new IllegalArgumentException("[%d] hp value is invalid. hp must be an positive integer."
                    .formatted(maxHp));

        return maxHp;
    }

    public void takeDamage(int damage) {
        this.currentHp = Math.max(0, this.currentHp - damage);
    }

    public boolean isBloodied() {
        return (this.currentHp * 2) <= this.maxHp;
    }

    public String getSummary() {
        return """
                 %s | HP: %d/%d
                STR: %d (%+d) | DEX: %d (%+d) | CON: %d (%+d)
                INT: %d (%+d) | WIS: %d (%+d) | CHA: %d (%+d)
                """.formatted(
                name, this.getCurrentHp(), this.getMaxHp(),
                abilityScores.strength(), abilityScores.strengthModifier(),
                abilityScores.dexterity(), abilityScores.dexterityModifier(),
                abilityScores.constitution(), abilityScores.constitutionModifier(),
                abilityScores.intelligence(), abilityScores.intelligenceModifier(),
                abilityScores.wisdom(), abilityScores.wisdomModifier(),
                abilityScores.charisma(), abilityScores.charismaModifier());
    }
}

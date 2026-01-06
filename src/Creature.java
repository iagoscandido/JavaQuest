public class Creature {
    private final String name;
    private final AbilityScores abilityScores;

    public Creature(String name, AbilityScores abilityScores) {
        this.name = name;
        this.abilityScores = abilityScores;
    }

    public String getSummary() {
        String summary = String.format("%s | STR: %d (%+d) | DEX: %d (%+d) | CON: %d (%+d) | INT: %d (%+d) | WIS: %d " +
                        "(%+d) | CHA: %d (%+d)", name,
                abilityScores.strength(), abilityScores.strengthModifier(),
                abilityScores.dexterity(), abilityScores.dexterityModifier(),
                abilityScores.constitution(), abilityScores.constitutionModifier(),
                abilityScores.intelligence(), abilityScores.intelligenceModifier(),
                abilityScores.wisdom(), abilityScores.wisdomModifier(),
                abilityScores.charisma(), abilityScores.charismaModifier());

        return summary;
    }
}

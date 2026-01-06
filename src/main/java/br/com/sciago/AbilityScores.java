package br.com.sciago;

public record AbilityScores(int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
    public AbilityScores {
        validateAbilityScore("strength", strength);
        validateAbilityScore("dexterity", dexterity);
        validateAbilityScore("constitution", constitution);
        validateAbilityScore("intelligence", intelligence);
        validateAbilityScore("wisdom", wisdom);
        validateAbilityScore("charisma", charisma);
    }

    public int strengthModifier() {
        return calculateModifier(strength);
    }

    public int dexterityModifier() {
        return calculateModifier(dexterity);
    }

    public int constitutionModifier() {
        return calculateModifier(constitution);
    }

    public int intelligenceModifier() {
        return calculateModifier(intelligence);
    }

    public int wisdomModifier() {
        return calculateModifier(wisdom);
    }

    public int charismaModifier() {
        return calculateModifier(charisma);
    }

    private void validateAbilityScore(String name, int value) {
        //    business rule score must be between 1 and 20
        boolean isAbilityScoreValid = value >= 1 && value <= 20;

        String errorMessage = String.format("Atributo [%s] com valor inválido: %d. O Player's Handbook (2024) define " +
                "que scores de aventureiros devem estar entre 1 e 20.\n", name, value);

        if (!isAbilityScoreValid)
            throw new IllegalArgumentException(errorMessage);
    }

    //    business rule mod = (Score - 10) / 2 rounded down.
    private int calculateModifier(int score) {
        return Math.floorDiv(score - 10, 2);
    }
}

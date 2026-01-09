package br.com.sciago;

import lombok.Getter;

public enum Weapon {
    UNARMED_STRIKE("Unarmed Strike", Die.NO_DIE, AbilityScore.STRENGTH),
    DAGGER("Dagger", Die.D4, AbilityScore.DEXTERITY),
    SHORTBOW("Shortbow", Die.D6, AbilityScore.DEXTERITY),
    LONGSWORD("Longsword", Die.D8, AbilityScore.STRENGTH);

    @Getter
    private final String name;
    @Getter
    private final Die damageDie;
    @Getter
    private final AbilityScore scalingModifier;

    Weapon(String name, Die damageDie, AbilityScore scalingModifier) {
        this.name = name;
        this.damageDie = damageDie;
        this.scalingModifier = scalingModifier;
    }

    public int rollDamage() {
        return damageDie.roll();
    }

}

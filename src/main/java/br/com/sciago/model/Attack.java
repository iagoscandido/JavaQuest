package br.com.sciago.model;

public record Attack(
        String attackerName,
        int attackerScore,
        int attackerDieRoll,
        int attackerModifier,
        boolean isHit,
        Die damageDie,
        int damageScore,
        int damageRoll,
        int damageModifier,
        Weapon weaponUsed,
        String targetName,
        int targetAc,
        TakeDamage damageResult
) {

}

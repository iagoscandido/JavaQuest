package br.com.sciago;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Creature {
    private final String name;
    private final AbilityScores abilityScores;
    private int maxHp;
    private int currentHp;
    private int armourClass;
    private Weapon equippedWeapon;

    public Creature(String name, AbilityScores abilityScores) {
        this.name = name;
        this.abilityScores = abilityScores;
        this.maxHp = calculateMaxHp(abilityScores.constitutionModifier());
        this.currentHp = maxHp;
        this.armourClass = calculateArmourClass(abilityScores.dexterityModifier());
        this.equippedWeapon = Weapon.UNARMED_STRIKE;
    }

    private int calculateMaxHp(int modifier) {
        int maxHp = 10 + modifier;

        if (maxHp <= 0)
            throw new IllegalArgumentException("[%d] hp value is invalid. hp must be an positive integer."
                    .formatted(maxHp));

        return maxHp;
    }

    private int calculateArmourClass(int modifier) {
        return 10 + modifier;
    }

    public int getAttackBonus() {
        return abilityScores.strengthModifier();
    }

    public void attack(Creature target) {
        String attackMessage = "%s attacks %s".formatted(this.name, target.name);
        System.out.println(attackMessage);
        int rollAttackDice = Die.D20.roll();
        int attackScore = rollAttackDice + getAttackBonus();

        String attackScoreMessage = "attack score: %d (D20: %d + Mod: %d)".formatted(attackScore, rollAttackDice,
                abilityScores.strengthModifier());
        System.out.println(attackScoreMessage);

        if (attackScore >= target.getArmourClass()) {
            String successMessage = "%s hits %s".formatted(this.name, target.name);
            System.out.println(successMessage);
            int damage = calculateDamage();
            String damageRollMessage = "damage dealt: %d (D%d: %d + %s: %d)"
                    .formatted(damage,
                            equippedWeapon.getDamageDie().getSides(),
                            damage - abilityScores.getModifier(equippedWeapon.getScalingModifier()),
                            equippedWeapon.getScalingModifier(),
                            abilityScores.getModifier(equippedWeapon.getScalingModifier())
                    );

            System.out.println(damageRollMessage);
            target.takeDamage(damage);

        } else {
            String missMessage = "%s misses %s [AC: %d]".formatted(this.name, target.name, target.getArmourClass());
            System.out.println(missMessage);
        }
    }

    private int calculateDamage() {
        int weaponDamage = this.equippedWeapon.rollDamage();
        int damageModifier = abilityScores.getModifier(this.equippedWeapon.getScalingModifier());
        return weaponDamage + damageModifier;
    }

    public void takeDamage(int damage) {
        int damageTaken = Math.max(0, damage);

        if (damageTaken == 0) {
            damageTaken = 1;
        }

        this.currentHp -= damageTaken;

        if (this.currentHp <= 0) this.currentHp = 0;

        String message = "%s takes damage: %d | HP: %d/%d".formatted(this.name, damageTaken, this.currentHp, this.maxHp);
        System.out.println(message);
    }

    public boolean isBloodied() {
        return (this.currentHp * 2) <= this.maxHp;
    }

    public boolean isAlive() {
        return this.currentHp > 0;
    }

    public String getSummary() {
        return """
                 %s | HP: %d/%d | AC: %d
                STR: %d (%+d) | DEX: %d (%+d) | CON: %d (%+d)
                INT: %d (%+d) | WIS: %d (%+d) | CHA: %d (%+d)
                """.formatted(
                name, this.getCurrentHp(), this.getMaxHp(), this.getArmourClass(),
                abilityScores.strength(), abilityScores.strengthModifier(),
                abilityScores.dexterity(), abilityScores.dexterityModifier(),
                abilityScores.constitution(), abilityScores.constitutionModifier(),
                abilityScores.intelligence(), abilityScores.intelligenceModifier(),
                abilityScores.wisdom(), abilityScores.wisdomModifier(),
                abilityScores.charisma(), abilityScores.charismaModifier());
    }

    public void equip(Weapon weapon) {
        this.equippedWeapon = Objects.isNull(weapon) ? Weapon.UNARMED_STRIKE : weapon;
    }
}

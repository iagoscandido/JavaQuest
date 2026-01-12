package br.com.sciago.model;

import lombok.Getter;

@Getter
public class Creature {
    private final String name;
    private final AbilityScores abilityScores;
    private int maxHp;
    private int currentHp = 10;
    private int armourClass = 10;
    private Weapon equippedWeapon = Weapon.UNARMED_STRIKE;
    private Condition condition = Condition.CONSCIOUS;
    private int proficiencyBonus = 2;

    public Creature(String name, AbilityScores abilityScores) {
        this.name = name;
        this.abilityScores = abilityScores;
        this.currentHp += abilityScores.constitutionModifier();
        this.maxHp = currentHp;
        this.armourClass = calculateAC();
        this.proficiencyBonus = this.getProficiencyBonus();
    }

    private int calculateAC() {
        return this.armourClass + this.abilityScores.dexterityModifier();
    }

    public Attack attack(Creature target) {
        if (this.condition != Condition.CONSCIOUS) {
            throw new IllegalArgumentException("[%s] condition is not conscious. only conscious can attackRollResult."
                    .formatted(this.condition));
        }
        String attackerName = this.getName();
        Weapon weaponUsed = this.equippedWeapon;

        int attackDieRoll = Die.D20.roll();
        int attackModifier =
                this.abilityScores.getModifier(weaponUsed.getScalingModifier());
        int attackScore = attackDieRoll + attackModifier + this.getProficiencyBonus();

        String targetName = target.getName();
        int targetAc = target.getArmourClass();

        boolean isHit = attackScore >= targetAc;

        if (!isHit)
            return new Attack(attackerName,
                    attackScore,
                    attackDieRoll,
                    attackModifier,
                    isHit,
                    Die.NO_DIE,
                    0,
                    0,
                    0,
                    weaponUsed,
                    targetName,
                    targetAc,
                    null
            );

        Die damageDie = weaponUsed.getDamageDie();
        int damageDieRoll = weaponUsed.rollDamage();
        int damageModifier = this.abilityScores.strengthModifier();
        int damageScore = damageDieRoll + damageModifier;

        TakeDamage takeDamage = target.takeDamage(damageScore);


        return new Attack(attackerName, attackScore, attackDieRoll, attackModifier, isHit,
                damageDie, damageScore,
                damageDieRoll,
                damageModifier, weaponUsed, targetName, targetAc, takeDamage);
    }

    public TakeDamage takeDamage(int damage) {
        int currentHp = this.getCurrentHp();
        int damageTaken = Math.max(0, damage);

        this.currentHp = Math.max(0, currentHp - damageTaken);

        if (this.getCurrentHp() == 0) this.condition = Condition.DEAD;

        return new TakeDamage(damageTaken, this.currentHp, maxHp, condition, name);
    }

    public boolean isAlive() {
        return condition != Condition.DEAD;
    }

    public String getSummary() {
        return """
                %s | HP: %d/%d | AC: %d (%+d) | Condition: %s
                STR: %d (%+d) | DEX: %d (%+d) | CON: %d (%+d)
                INT: %d (%+d) | WIS: %d (%+d) | CHA: %d (%+d)
                """.formatted(
                name, this.getCurrentHp(), this.getMaxHp(), this.getArmourClass(),
                abilityScores.dexterityModifier(), this.getCondition(),
                abilityScores.strength(), abilityScores.strengthModifier(),
                abilityScores.dexterity(), abilityScores.dexterityModifier(),
                abilityScores.constitution(), abilityScores.constitutionModifier(),
                abilityScores.intelligence(), abilityScores.intelligenceModifier(),
                abilityScores.wisdom(), abilityScores.wisdomModifier(),
                abilityScores.charisma(), abilityScores.charismaModifier());
    }

    public void equip(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
}

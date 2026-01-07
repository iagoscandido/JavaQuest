package br.com.sciago;

public class Creature {
    private final String name;
    private final AbilityScores abilityScores;
    private int maxHp;
    private int currentHp;
    private int armourClass;

    public Creature(String name, AbilityScores abilityScores) {
        this.name = name;
        this.abilityScores = abilityScores;
        this.maxHp = calculateMaxHp(abilityScores.constitutionModifier());
        this.currentHp = maxHp;
        this.armourClass = calculateArmourClass(abilityScores.dexterityModifier());
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
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
        int rollAttackDice = Dice.rollD20();
        int attackScore = rollAttackDice + getAttackBonus();

        String attackScoreMessage = "attack score: %d (D20: %d + Mod: %d)".formatted(attackScore, rollAttackDice,
                abilityScores.strengthModifier());
        System.out.println(attackScoreMessage);

        if (attackScore >= target.getArmourClass()) {
            String successMessage = "%s hits %s".formatted(this.name, target.name);
            System.out.println(successMessage);

            int damageDealt = unarmedAttack(abilityScores.strengthModifier());
            target.takeDamage(damageDealt);

        } else {
            String missMessage = "%s misses %s [AC: %d]".formatted(this.name, target.name, target.getArmourClass());
            System.out.println(missMessage);
        }
    }

    public int unarmedAttack(int damageModifier) {
        int damage = calculateUnarmedAttack(damageModifier);
        if (damage < 1) damage = 1;
        return damage;
    }

    private int calculateUnarmedAttack(int damageModifier) {
        return Math.max(0, 1 + damageModifier);
    }

    public void takeDamage(int damage) {
        int rawDamage = Math.max(0, damage);
        int damageTaken = this.currentHp - rawDamage;
        this.currentHp = Math.clamp(damageTaken, 0, this.maxHp);

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
}

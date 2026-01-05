public class Hero extends Entity {
    private int currentExperiencePoints;
    private int experiencePointsToLevelUp;
    private int potions;

    public Hero(String name, int healthPoints, int strength, int defense) {
        super(name, healthPoints, strength, defense, 1);
        this.currentExperiencePoints = 0;
        this.experiencePointsToLevelUp = 10;
        this.potions = 3;
    }

    public int getPotions() {
        return potions;
    }

    public void gainExperiencePoints(int amount) {
        currentExperiencePoints += amount;

        while (currentExperiencePoints >= experiencePointsToLevelUp) {
            levelUp();
        }
    }

    public void levelUp() {
        this.level++;
        this.strength += 2;
        this.maximumHealthPoints += 5;
        this.defense += 1;

//        fully heal when level up
        this.healthPoints = this.maximumHealthPoints;

//        guarantees the exceed exp gained will not be lost
        this.currentExperiencePoints -= this.experiencePointsToLevelUp;
        this.experiencePointsToLevelUp = (int) Math.round(this.experiencePointsToLevelUp * 1.5);

        System.out.printf("PARABÉNS! Você subiu para o nível %d!%n", this.getLevel());
    }

    public void drinkPotion() {
        int previousHealthPoints = this.healthPoints;

        if (this.potions < 1) {
            System.out.println("Você não tem mais poções!");
        }

        if (isHealValid() && this.potions > 0) {
            this.potions--;
            this.healthPoints = Math.min(this.healthPoints + 10, this.maximumHealthPoints);
            int healPoints = this.healthPoints - previousHealthPoints;
            System.out.printf("\n%s consumiu a poção e curou %d\n", this.getName(), healPoints);
        }
    }

    public boolean isHealValid() {
        return this.isAlive() && this.healthPoints < this.maximumHealthPoints;
    }

    @Override
    public String displayStatus() {
        return String.format("Nome: %s | Nível: %d | HP: %d/%d | Força: %d | Defesa: %d | Poções: %d)", this.getName(),
                this.getLevel(),
                this.getHealthPoints(), this.getMaximumHealthPoints(), this.getStrength(), this.getDefense(),
                this.getPotions() );
    }

}


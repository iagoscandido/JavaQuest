public class Hero extends Entity {
    private int currentExperiencePoints;
    private int experiencePointsToLevelUp;
    private int potions;
    private final int maximumPotions;

    public Hero(String name) {
        super(name, 10, 5, 2, 1, 20);
        this.currentExperiencePoints = 0;
        this.experiencePointsToLevelUp = 10;
        this.potions = 3;
        this.maximumPotions = potions;

    }

    public int getPotions() {
        return potions;
    }

    public int getMaximumPotions() {
        return maximumPotions;
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

        String levelUpMessage = String.format("PARABÉNS! Você subiu para o nível %d!%n", this.getLevel());
        System.out.println(levelUpMessage);
    }

    public boolean drinkPotion() {
        int currentHealthPoints = this.healthPoints;
        boolean isPotionAvailable = this.potions > 0;
        boolean isMissingHealthPoints = currentHealthPoints < this.maximumHealthPoints;
        boolean isPotionUseValid = this.isAlive() && isMissingHealthPoints;

        if (!isPotionAvailable) {
            String noPotionsAvailableMessage = "Você não tem mais poções!";
            System.out.println(noPotionsAvailableMessage);
            return false;
        }

        if (!isMissingHealthPoints) {
            String noMissingHealthMessage = "Seu HP já está cheio! Poção não utilizada.";
            System.out.println(noMissingHealthMessage);
            return false;
        }

        if (!isPotionUseValid) {
            return false;
        }

        this.potions--;
        this.healthPoints = Math.min(this.healthPoints + 10, this.maximumHealthPoints);
        int healthPointsRestored = this.healthPoints - currentHealthPoints;

        String potionUsedMessage = String.format("%s curou: %d", this.getName(), healthPointsRestored);

        System.out.println(potionUsedMessage);

        return true;
    }

    @Override
    public String displayStatus() {
        return String.format("Nome: %s | Nível: %d | HP: %d/%d | Força: %d | Defesa: %d | Energia %d/%d |Poções: %d/%d",
                this.getName(),
                this.getLevel(),
                this.getHealthPoints(), this.getMaximumHealthPoints(),
                this.getStrength(),
                this.getDefense(),
                this.getEnergyPoints(), this.getMaximumEnergyPoints(),
                this.getPotions(), this.getMaximumPotions());
    }

    public boolean specialAttack(Entity target) {
        int energyCost = 10;
        String notEnoughEnergyToAttackMessage = String.format("""
                Energia insuficiente para realizar o ataque especial
                energia necessária: %d | energia atual: %d
                """, energyCost, this.energyPoints);

        if (this.energyPoints < energyCost) {
            System.out.println(notEnoughEnergyToAttackMessage);
            return false;
        }

        int damage = this.strength * 2;
        int updatedEnergyPoints = this.energyPoints -= energyCost;
        String specialAttackMessage = "Você realizou um ataque especial";
        String updatedEnergyPointsMessage = String.format("Energia atual: %d/%d", updatedEnergyPoints,
                this.getMaximumEnergyPoints());

        System.out.println(specialAttackMessage);
        System.out.println(updatedEnergyPointsMessage);
        target.takeDamage(damage);
        return true;
    }

}


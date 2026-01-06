public abstract class Entity {
    protected String name;
    protected int healthPoints;
    protected int maximumHealthPoints;
    protected int strength;
    protected int defense;
    protected int level;
    protected int energyPoints;
    protected int maximumEnergyPoints;

    public Entity(String name, int healthPoints, int strength, int defense, int level, int energyPoints) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maximumHealthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
        this.level = level;
        this.energyPoints = energyPoints;
        this.maximumEnergyPoints = energyPoints;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaximumHealthPoints() {
        return maximumHealthPoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public int getMaximumEnergyPoints() {
        return maximumEnergyPoints;
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void attack(Entity target) {
        String attackMessage = String.format("força: %d | %s atacou %s", this.getStrength(), this.getName(),
                target.getName());

        System.out.println(attackMessage);
        target.takeDamage(this.getStrength());
    }

    public void takeDamage(int damage) {
        int damageTaken = damage - this.getDefense();

        if (damageTaken < 1) damageTaken = 1;

        this.healthPoints -= damageTaken;

        String damageTakenMessage = String.format("defesa: %d | %s recebeu %d de dano",
                this.getDefense(),
                this.getName(),
                damageTaken);

        System.out.println(damageTakenMessage);
    }

    public String displayStatus() {
        return String.format(
                "Nome: %s | Nível: %d | HP: %d/%d | Força: %d | Defesa: %d | Energia %d/%d ",
                this.getName(),
                this.getLevel(),
                this.getHealthPoints(),
                this.getMaximumHealthPoints(),
                this.getStrength(),
                this.getDefense(),
                this.getEnergyPoints(),
                this.getMaximumEnergyPoints()
        );
    }

    public void regenerateEnergyPoints() {
        this.energyPoints = Math.min(this.energyPoints + 2, this.maximumEnergyPoints);
    }
}

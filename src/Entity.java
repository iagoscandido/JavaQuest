public abstract class Entity {
    protected String name;
    protected int healthPoints;
    protected int maximumHealthPoints;
    protected int strength;
    protected int defense;
    protected int level;

    public Entity(String name, int healthPoints, int strength, int defense, int level) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maximumHealthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
        this.level = level;
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

    public boolean isAlive() {
        return healthPoints > 0;
    }

    public void attack(Entity target) {
        System.out.printf("%s atacou %s com %d de força%n", this.getName(), target.getName(), this.getStrength());
        target.takeDamage(this.getStrength());
    }

    public void takeDamage(int attackPower) {
        int damage = attackPower - this.getDefense();

        if (damage < 1) {
            damage = 1;
        }

        this.healthPoints -= damage;
        System.out.printf("%S defendeu %d e recebeu %d de dano!%n", this.name , this.defense, damage);
    }

    public String displayStatus() {
        return String.format("Nome: %s | Nível: %d | HP: %d/%d | Força: %d | Defesa: %d ", this.getName(), this.getLevel(),
                this.getHealthPoints(), this.getMaximumHealthPoints(), this.getStrength(), this.getDefense());
    }
}

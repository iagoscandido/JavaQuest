import java.util.Random;

public class Monster extends Entity {
    private final int experiencePointsReward;

    public Monster(String name, int level) {
        super(name,
                5 + (level * 3),
                2 + level,
                1 + level,
                level);
        this.experiencePointsReward = calculateExperiencePointsReward();
    }

    public int getExperiencePointsReward() {
        return experiencePointsReward;
    }

    public int calculateExperiencePointsReward() {
        return (this.level * 10) + (this.strength * 2);
    }

    //    monster can only be um level below or above player's level, minimum monster level is 1.
    public static Monster spawn(int playerLevel) {
        int miniumLevel = Math.max(1, playerLevel - 1);
        int maximumLevel = Math.max(miniumLevel, playerLevel + 2);

        int level = new Random().nextInt(miniumLevel, maximumLevel);

        return new Monster("Slime", level);
    }
}

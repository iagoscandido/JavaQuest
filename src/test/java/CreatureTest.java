import br.com.sciago.AbilityScores;
import br.com.sciago.Creature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

public class CreatureTest {
    private AbilityScores defaultScores;
    private Creature defaultCreature;

    private static IntStream abilityScoreRange() {
        return IntStream.rangeClosed(1, 20);
    }

    @BeforeEach
    void setUp() {
        defaultScores = new AbilityScores(10, 10, 10, 10, 10, 10);
        defaultCreature = new Creature("Grog", defaultScores);
    }

    @ParameterizedTest
    @MethodSource("abilityScoreRange")
    @DisplayName("Should calculate max hp for each constitution score")
    public void shouldCalculateMaxHpWhenCreateCreature(int conScore) {
        AbilityScores scores = new AbilityScores(10, 10, conScore, 10, 10, 10);
        Creature creature = new Creature("Test Creature", scores);

        int expectedMaxHp = 10 + scores.constitutionModifier();
        int actualMaxHp = creature.getMaxHp();

        Assertions.assertEquals(expectedMaxHp, actualMaxHp,
                () -> "failure to score: " + conScore + " (modifier: " + scores.constitutionModifier() + ")");
    }

    @Test
    public void shouldDefineCurrentHpEqualsToMaxHpWhenCreateCreature() {

        int expectedCurrentHp = 10 + defaultScores.constitutionModifier();
        int actualCurrentHp = defaultCreature.getCurrentHp();

        Assertions.assertEquals(expectedCurrentHp,
                actualCurrentHp,
                "currentHp should be equals to expectedCurrentHp");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("Should reduce current HP when takes damage")
    public void shouldReduceCurrentHpWhenDamageIsTaken(int damage) {

        int expectedCurrentHp = defaultCreature.getCurrentHp() - damage;
        defaultCreature.takeDamage(damage);

        int actualCurrentHp = defaultCreature.getCurrentHp();

        Assertions.assertEquals(expectedCurrentHp, actualCurrentHp);
    }

    @Test
    @DisplayName("Should Define HP to zero when damage taken exceeds current HP")
    public void shouldCapHpAtZeroWhenDamageExceedsCurrentHp() {

        int damage = defaultCreature.getCurrentHp() + 1;
        int expectedCurrentHp = 0;

        defaultCreature.takeDamage(damage);

        int actualCurrentHp = defaultCreature.getCurrentHp();

        Assertions.assertEquals(expectedCurrentHp, actualCurrentHp);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8, 9, 10, 11})
    public void shouldReturnTrueIfCurrentHpLessOrEqualThanHalfMaxHp(int damage) {

        defaultCreature.takeDamage(damage);

        Assertions.assertTrue(defaultCreature.isBloodied());

    }
}

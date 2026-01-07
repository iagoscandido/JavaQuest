import br.com.sciago.AbilityScores;
import br.com.sciago.Adventurer;
import br.com.sciago.Creature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AdventurerTest {
    private Adventurer adventurer;
    private Creature creature;

    @BeforeEach
    public void setup() {
        AbilityScores defaultAbilityScores = new AbilityScores(10, 10, 10, 10, 10, 10);
        adventurer = new Adventurer("Bob", defaultAbilityScores);
        creature = new Creature("Slime", defaultAbilityScores);
    }

    @Test
    @DisplayName("Should create adventurer successfully")
    public void createAdventurerSuccessfully() {
        Assertions.assertNotNull(adventurer);
        Assertions.assertSame(Adventurer.class, adventurer.getClass());
    }

    @Test
    @DisplayName("Should create adventurer with bonus attack greater than creature")
    public void shouldCreateAdventureWithBonusAttackGreaterThanCreature() {

        Assertions.assertTrue(adventurer.getAttackBonus() > creature.getAttackBonus(), "Adventurer " +
                "attack bonus: [%s] should be greater than creature attack bonus: [%d]".formatted(adventurer.getAttackBonus(), creature.getAttackBonus()));

    }
}

import br.com.sciago.AbilityScores;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class AbilityScoresTest {
    @BeforeEach
    public void setup() {
        AbilityScores defaultScores = new AbilityScores(10, 10, 10, 10, 10, 10);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 21})
    void shouldThrowsExceptionWhenScoreIsInvalid(int invalidScores) {
        assertThrows(IllegalArgumentException.class, () ->
                        new AbilityScores(invalidScores, 10, 10, 10, 10, 10),
                "Should have thrown IllegalArgumentException: " + invalidScores);
    }

    @ParameterizedTest
    @CsvSource({
            "1,  -5",
            "8,  -1",
            "9,  -1",
            "10,  0",
            "11,  0",
            "12,  1",
            "15,  2",
            "20,  5"
    })
    void shouldCalculateCorrectModifierForEachScore(int score, int expectedModifier) {

        AbilityScores testScores = new AbilityScores(score, score, score, score, score, score);

        assertAll("modifiers",
                () -> assertEquals(expectedModifier, testScores.strengthModifier(), "STR failed"),
                () -> assertEquals(expectedModifier, testScores.dexterityModifier(), "DEX failed"),
                () -> assertEquals(expectedModifier, testScores.constitutionModifier(), "CON failed"),
                () -> assertEquals(expectedModifier, testScores.intelligenceModifier(), "INT failed"),
                () -> assertEquals(expectedModifier, testScores.wisdomModifier(), "WIS failed"),
                () -> assertEquals(expectedModifier, testScores.charismaModifier(), "CHA failed")
        );

    }
}

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AbilityScoresTest {
    @Test
    void shouldThrowsExceptionWhenScoreIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AbilityScores(-1, 0, 21, 1, 1, 1);
        }, "should throw error when value is either less 1 or greater than 20");
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

        AbilityScores testScores = new AbilityScores(score, 10, 10, 10, 10, 10);

        assertEquals(expectedModifier, testScores.strengthModifier(),
                () -> "Failure: Score " + score + " should result in modifier: " + expectedModifier);
    }
}

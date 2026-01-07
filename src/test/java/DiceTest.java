import br.com.sciago.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class DiceTest {
    @RepeatedTest(1000)
    @DisplayName("Should roll 1000 dices and validate if value range is 1 and 20")
    void shouldAlwaysReturnValueBetweenOneAndTwenty() {
        int roll = Dice.rollD20();

        Assertions.assertTrue(roll >= 1 && roll <= 20,
                () -> "Dice rolled out of interval: " + roll);

    }
}

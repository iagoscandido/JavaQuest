import br.com.sciago.Die;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DieTest {
    @DisplayName("Die should roll within its bound")
    @ParameterizedTest
    @EnumSource(Die.class)
    public void dieShouldRollWithinItsBound(Die die) {
        int rolled = die.roll();
        assertTrue(rolled >= 1 && rolled <= die.getSides(),
                () -> "Die: " + die + " rolled invalid value: " + rolled);
    }
}

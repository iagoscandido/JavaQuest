import br.com.sciago.model.Die;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DieTest {
    @DisplayName("Die should roll within its bound")
    @Test
    public void dieShouldRollWithinItsBound() {
        int roll = Die.D20.roll();

        assertTrue(roll >= 1 && roll <= 20);
    }
}

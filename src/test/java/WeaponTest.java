import br.com.sciago.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeaponTest {
    @ParameterizedTest
    @EnumSource(Weapon.class)
    @DisplayName("All weapons should roll its damage correctly")
    public void allWeaponsShouldRollItsDamageCorrectly(Weapon weapon) {

     assertNotNull(weapon.getScalingModifier());
    }
}

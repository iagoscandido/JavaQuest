import br.com.sciago.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatureTest {
    private Creature dummy;
    private Creature testPilot;

    private static IntStream abilityScoreRange() {
        return IntStream.rangeClosed(1, 20);
    }

    @BeforeEach
    void setUp() {
        dummy = new Creature("Dummy", new AbilityScores(1, 1, 1, 1, 1, 1));
        testPilot = new Creature("Test Pilot", new AbilityScores(20, 20, 20, 20, 20, 20));
    }

    @Test
    @DisplayName("Should deal damage if Attack hits")
    void shouldDealDamageIfAttackHits() {
        int dummyHpBeforeAttack = dummy.getCurrentHp();
        testPilot.equip(Weapon.LONGSWORD);

        int minAttackRoll = testPilot.getProficiencyBonus() + testPilot.getAbilityScores().strengthModifier() + 1;
        int maxAttackRoll =
                testPilot.getProficiencyBonus() + testPilot.getAbilityScores().strengthModifier() + Die.D20.getSides();

        int minDamageRoll = testPilot.getAbilityScores().getModifier(AbilityScore.STRENGTH) + 1;
        int maxDamageRoll =
                testPilot.getAbilityScores().getModifier(AbilityScore.STRENGTH)
                        + testPilot.getAbilityScores().getModifier(testPilot.getEquippedWeapon().getScalingModifier());

        int dummyAc = dummy.getArmourClass();
        Attack actualAttack = testPilot.attack(dummy);
        int dummyHpAfterAttack = dummy.getCurrentHp();

        assertTrue(actualAttack.attackerScore() >= dummyAc,
                "%d should be greater or equal targetAC: %d. instead got: %d"
                        .formatted(actualAttack.attackerScore(), dummyAc, actualAttack.attackerScore()));

        assertTrue(actualAttack.damageScore() >= dummyHpAfterAttack,
                "damage: %d should be equal or greater than currentHp: %d. instead got: %d"
                        .formatted(actualAttack.damageScore(), dummyHpAfterAttack, actualAttack.damageScore()));
    }

    @Test
    @DisplayName("Should not change target hp if Attack miss")
    void shouldNoChangeChangeTargetHpIfAttackMiss() {
        int testPilotHpBeforeAttack = testPilot.getCurrentHp();
        dummy.attack(testPilot);
        int testPilotHpAfterAttack = testPilot.getCurrentHp();

        assertEquals(testPilotHpAfterAttack, testPilotHpBeforeAttack, "target hp should not change if attacker " +
                "misses. expected: %d, current: %d".formatted(testPilotHpBeforeAttack, testPilotHpAfterAttack));
    }
}


package br.com.sciago.combat;

import br.com.sciago.model.Attack;
import br.com.sciago.model.Creature;
import br.com.sciago.model.TakeDamage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CombatLogger {
    @Getter
    private List<String> combatLog = new ArrayList<>();

    public void logAttack(Attack attack) {
        String hitOrMiss = attack.isHit()
                ? "Hit! Damage: %d (%s: %d + %s: %+d)".formatted(
                attack.damageScore(),
                attack.damageDie(),
                attack.damageRoll(),
                attack.weaponUsed().getScalingModifier(),
                attack.damageModifier())
                : "Miss!";

        String message = """
                %s attacks %s with %s
                Attack: %d (D20: %d + %s: %+d) vs AC: %d
                %s
                """.formatted(
                attack.attackerName(),
                attack.targetName(),
                attack.weaponUsed().getName(),
                attack.attackerScore(),
                attack.attackerDieRoll(),
                attack.weaponUsed().getScalingModifier(),
                attack.attackerModifier(),
                attack.targetAc(),
                hitOrMiss
        );
        System.out.println(message);
        combatLog.add(message);

        if (attack.isHit() && attack.damageResult() != null) {
            logDamage(attack.damageResult());
        }
    }

    public void logDamage(TakeDamage result) {
        String message = """
                %s takes %d damage | HP: %d/%d | Condition: %s
                """.formatted(
                result.creatureName(),
                result.damageTaken(),
                result.currentHp(),
                result.maxHp(),
                result.condition()
        );
        System.out.println(message);
        combatLog.add(message);
    }

    public void logCombatStart(String heroSummary, String monsterSummary) {
        String message = """
                 === COMBAT STARTS ===
                %s
                %s
                """.formatted(heroSummary, monsterSummary);

        System.out.println(message);
        combatLog.add(message);
    }

    public void logCombatEnd(Creature hero, Creature monster, int turns) {
        String winner = hero.isAlive() ? hero.getName() : monster.getName();
        String loser = hero.isAlive() ? monster.getName() : hero.getName();

        String message = """
                === COMBAT ENDS ===
                %s defeated %s | turns: %d
                %s
                %s
                """.formatted(winner, loser, turns, hero.getSummary(), monster.getSummary());

        System.out.println(message);
        combatLog.add(message);
    }
}

package br.com.sciago.combat;

import br.com.sciago.model.Attack;
import br.com.sciago.model.Creature;
import lombok.Getter;

public class CombatSystem {
    @Getter
    private CombatLogger combatLogger;

    public CombatSystem(CombatLogger combatLogger) {
        this.combatLogger = combatLogger;
    }

    public void startCombat(Creature hero, Creature monster) {
        combatLogger.logCombatStart(hero.getSummary(), monster.getSummary());

        int turns = 0;
        while (hero.isAlive() && monster.isAlive()) {
            turns++;

            Attack heroAttack = hero.attack(monster);
            combatLogger.logAttack(heroAttack);

            if (!monster.isAlive()) {
                break;
            }

            Attack monsterAttack = monster.attack(hero);
            combatLogger.logAttack(monsterAttack);

            if (!hero.isAlive()) {
                break;
            }

        }
        combatLogger.logCombatEnd(hero, monster, turns);
    }
}

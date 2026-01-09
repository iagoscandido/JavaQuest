package br.com.sciago;

import br.com.sciago.combat.CombatLogger;
import br.com.sciago.combat.CombatSystem;
import br.com.sciago.model.AbilityScores;
import br.com.sciago.model.Creature;

public class Main {
    static void main() {

        Creature skeleton = new Creature("Skeleton",
                new AbilityScores(10, 10, 10, 10, 10, 10));
        Creature bob = new Creature("Bob",
                new AbilityScores(10, 10, 10, 10, 10, 10));

        CombatLogger combatLogger = new CombatLogger();
        CombatSystem combatSystem = new CombatSystem(combatLogger);

        combatSystem.startCombat(bob, skeleton);
    }
}

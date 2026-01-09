package br.com.sciago;

public class Main {
    static void main() {
        AbilityScores bobScores = new AbilityScores(Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll());
        Creature bob = new Creature("bob", bobScores);

        AbilityScores slimeScores = new AbilityScores(Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll(),
                Die.D20.roll());
        Creature slime = new Creature("slime", slimeScores);

        System.out.println("Generated:");
        System.out.println(bob.getSummary());
        System.out.println(slime.getSummary());

        int turnsCount = 0;
        System.out.println("======= COMBAT STARTS =======");
        while (bob.isAlive() && slime.isAlive()) {
            turnsCount++;

            bob.attack(slime);
            if (slime.getCurrentHp() == 0) {
                System.out.println("Bob Wins");
                break;
            }

            slime.attack(bob);
            if (bob.getCurrentHp() == 0) {
                System.out.println("Slime Wins");
                break;
            }

            System.out.println(bob.getSummary());
            System.out.println(slime.getSummary());

        }

        System.out.println("======= COMBAT SUMMARY =======");
        System.out.println(bob.getSummary());
        System.out.println(slime.getSummary());
        System.out.println("Total turns: %d".formatted(turnsCount));
    }
}

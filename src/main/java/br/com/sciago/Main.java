package br.com.sciago;

public class Main {
    static void main() {
        AbilityScores bobScores = new AbilityScores(Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20());
        Creature bob = new Creature("bob", bobScores);

        AbilityScores slimeScores = new AbilityScores(Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20(), Dice.rollD20());
        Creature slime = new Creature("slime", slimeScores);

        System.out.println("Bob: " + bob.getSummary());
        System.out.println("Slime: " + slime.getSummary());

        int turnsCount = 0;
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

            System.out.println("Bob: " + bob.getSummary());
            System.out.println("Slime: " + slime.getSummary());

        }
        System.out.println("======= COMBAT SUMMARY =======");
        System.out.println(bob.getSummary());
        System.out.println(slime.getSummary());
        System.out.println(turnsCount);
    }
}

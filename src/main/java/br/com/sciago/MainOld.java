package br.com.sciago;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainOld {
    static void main() {
        Scanner sc = new Scanner(System.in);
//    System.out.print("Informe seu nome: ");
//    String playerName = sc.nextLine();


        String monsterSpawnedSuccessMessage = "Monstro surgiu!";
        String combatStartMessage = "Combate iniciado!";
        String combatEndMessage = "Combate finalizado!";


        String playerName = "testeName";

        Hero player = new Hero(playerName);
        String characterCreatedMessage = String.format("Personagem: %s criado com sucesso!", player.getName());
        System.out.println(characterCreatedMessage);

        Monster monster = Monster.spawn(player.getLevel());

        System.out.println(monsterSpawnedSuccessMessage);
        System.out.println(monster.displayStatus());

        int turn = 0;
        System.out.println(combatStartMessage);
        while (player.isAlive() && monster.isAlive()) {
            turn++;
            String currentTurnMessage = String.format("Turno: %d", turn);
            String endTurnMessage = "Fim do turno";
            String optionsMessage = "Ações: 1 - Atacar | 2 - Beber poção | 3 - Ataque Especial (10 EP)";
            String monsterDefeatedMessage = String.format("%s derrotado! Você recebeu %d Pontos de Experiencia",
                    monster.getName(), monster.getExperiencePointsReward());
            String playerDefeatedMessage = String.format("Você foi derrotado por: %s", monster.getName());

            System.out.println(currentTurnMessage);
            System.out.println("Jogador: " + player.displayStatus());
            System.out.println("Monstro: " + monster.displayStatus());
            System.out.println();
            System.out.println("Turno do Jogador");

            boolean isTurnEnded = false;
            while (!isTurnEnded) {

                System.out.println(optionsMessage);
                int choice = sc.nextInt();

                while (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Opção inválida.");
                    System.out.println(optionsMessage);
                    choice = sc.nextInt();
                }

                switch (choice) {
                    case 1:
                        player.attack(monster);
                        isTurnEnded = true;
                        break;
                    case 2:
                        if (player.drinkPotion()) isTurnEnded = true;
                        break;
                    case 3:
                        if (player.specialAttack(monster)) isTurnEnded = true;
                        break;
                }
            }

            if (!monster.isAlive()) {
                player.gainExperiencePoints(monster.getExperiencePointsReward());
                System.out.printf(monsterDefeatedMessage);
                System.out.println(combatEndMessage);
                break;
            }

            System.out.println("Turno do Monstro");
            monster.attack(player);

            if (!player.isAlive()) {
                System.out.println(playerDefeatedMessage);
                System.out.println(combatEndMessage);
                break;
            }

//        regeneration phase
            player.regenerateEnergyPoints();
            monster.regenerateEnergyPoints();

            System.out.println(endTurnMessage);
        }

        sc.close();
    }
}

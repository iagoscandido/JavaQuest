void main() {
    Scanner sc = new Scanner(System.in);
//    System.out.print("Informe seu nome: ");
//    String playerName = sc.nextLine();

    String playerName = "testeName";

    Hero player = new Hero(playerName, 10, 5, 2);

    System.out.println("\n--- Personagem Criado com Sucesso ---");
    System.out.printf("\nStatus do jogador: %s\n", player.displayStatus());


    Monster monster = Monster.spawn(player.getLevel());
    System.out.printf("\nStatus do Monstro: %s\n", monster.displayStatus());


    System.out.println("\n--- Combate iniciado ---");
    int turn = 0;
    while (player.isAlive() && monster.isAlive()) {
        turn++;
        System.out.println("\n--- Inicio do turno: " + turn + "---");
        System.out.println("Turno do Jogador");
        System.out.println("\nAções: 1 - Atacar | 2 - Beber poção");
        int choice = sc.nextInt();

        while (choice < 1 || choice > 2) {
            System.out.println("Opção inválida.");
            System.out.println("\nAções: 1 - Atacar | 2 - Beber poção");
            choice = sc.nextInt();
        }

        switch (choice) {
            case 1:
                player.attack(monster);
                break;
            case 2:
                player.drinkPotion();
                break;
        }

        if (!monster.isAlive()) {
            System.out.println("\n--- Fim de Jogo! total de turnos: " + turn + " ---");
            System.out.printf("Vitória, XP recebido: %d%n", monster.getExperiencePointsReward());
            player.gainExperiencePoints(monster.getExperiencePointsReward());
            break;
        }

        System.out.println("\nTurno do Monstro");
        monster.attack(player);
        if (!player.isAlive()) {
            System.out.println("\n--- Fim de Jogo! total de turnos: " + turn + " ---");
            System.out.println("Derrota/Game Over");
            break;
        }
        System.out.println("\nStatus atual:");
        System.out.println("\n" + player.displayStatus());
        System.out.println("\n" + monster.displayStatus());
        System.out.println("\n--- Fim do turno: " + turn + " ---");

    }
    sc.close();
}
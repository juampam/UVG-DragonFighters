// Battle.java
package game;

import java.util.Random;
import java.util.Scanner;

public class Battle {
    private Random random = new Random();
    private Combatant player1;
    private Combatant player2;
    private Enemy enemy;
    private Scanner scanner = new Scanner(System.in);

    public Battle() {
        // Empty constructor
    }

    public void startBattle() {
       clearConsole();

        System.out.println("The Battle will start soon!");

        // Get user instructions to create combatants and select items
        createCombatants();
        clearConsole();
        selectItems(player1);
        clearConsole();
        selectItems(player2);
        clearConsole();
        // Display combatants and start the battle
        System.out.println("Player 1: " + player1.getName());
        System.out.println("Player 2: " + player2.getName());
        System.out.println("Enemy: " + enemy.getName());

        while (player1.getLife() > 0 && player2.getLife() > 0 && enemy.getLife() > 0) {
            executeTurn(player1, enemy);
            if (enemy.getLife() <= 0) {
                System.out.println("Enemy defeated! Players win!");
                break;
            }

            executeTurn(player2, enemy);
            if (player1.getLife() <= 0 && player2.getLife() <= 0) {
                System.out.println("Players defeated! Enemy wins!");
                break;
            }

            enemyTurn(enemy);
            if (player1.getLife() <= 0 && player2.getLife() <= 0) {
                System.out.println("Players defeated! Enemy wins!");
                break;
            }
        }

        // ... Add more logic to handle multiple turns and battles
    }

    private void createCombatants() {
        System.out.println("Create Combatant for Player 1:");
        System.out.println("Name:");
        String thename = scanner.nextLine();
        player1 = createCombatant(thename);
    
        scanner.nextLine();
    
        System.out.println("Create Combatant for Player 2:");
        System.out.println("Name:");
        String p2 = scanner.nextLine();
        player2 = createCombatant(p2);
    
        System.out.println("\nSearching for Enemies");
        enemy = createEnemy();
        System.out.println("Enemy Founded, you will gonna fight with" + "\"" + enemy.getName()+ "\"");
    }

    private Combatant createCombatant(String playerName) {
        System.out.println("Select Combatant Type:");
        System.out.println("1. Warrior");
        System.out.println("2. Explorer");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new Warrior(playerName);
            case 2:
                return new Explorer(playerName);
            default:
                System.out.println("Invalid choice. Defaulting to Warrior.");
                return new Warrior(playerName);
        }
    }

    private Enemy createEnemy() {
        int randomChoice = (int) (Math.random() * 4) + 1; // Generates a random number between 1 and 4
    
        switch (randomChoice) {
            case 1:
                return new FeudalLord();
            case 2:
                return new LegendaryFeudalLord();
            case 3:
                return new Yokai();
            case 4:
                return new LegendaryYokai();
            default:
                System.out.println("Something went wrong. Defaulting to Feudal Lord.");
                return new FeudalLord();
        }
    }
    
    private void selectItems(Combatant player) {
        System.out.println("\nSelect Items for " + player.getName() + ":");
    
        int maxItems;
        if (player instanceof Warrior) {
            System.out.println("1. Bandage");
            System.out.println("2. Sacred Sword");
            maxItems = 2;
        } else if (player instanceof Explorer) {
            System.out.println("1. Bandage");
            System.out.println("2. Sacred Sword");
            System.out.println("3. Invisibility");
            System.out.println("4. Barrier");
            System.out.println("5. Health Potion");
            maxItems = 4;
        } else {
            System.out.println("Invalid Combatant type.");
            return;
        }
    
        for (int i = 0; i < maxItems; i++) {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    player.addItem(new HealingItem("bandage"));
                    break;
                case 2:
                    player.addItem(new AttackItem("Sacred Sword"));
                    break;
                case 3:
                    if (player instanceof Explorer) {
                        player.addItem(new MagicItem("invisibility"));
                    } else {
                        System.out.println("Invalid choice. No items selected.");
                    }
                    break;
                case 4:
                    if (player instanceof Explorer) {
                        player.addItem(new MagicItem("Barrier"));
                    } else {
                        System.out.println("Invalid choice. No items selected.");
                    }
                    break;
                case 5:
                    if (player instanceof Explorer) {
                        player.addItem(new HealingItem("Health Potion"));
                    } else {
                        System.out.println("Invalid choice. No items selected.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. No items selected.");
                    break;
            }
        }
    }
    

    private void executeTurn(Combatant attacker, Enemy enemy) {
        displayStatus(player1, player2, enemy);

        if (attacker == player1) {
            System.out.println("Player 1's Turn:");
            playerTurn(player1);
        } else if (attacker == player2) {
            System.out.println("Player 2's Turn:");
            playerTurn(player2);
        } else {
            System.out.println("Enemy's Turn:");
            enemyTurn(enemy);
        }
        clearConsole();
        displayStatus(player1, player2, enemy);
    }

    private void playerTurn(Combatant player) {
        System.out.println("Select Action:");
        System.out.println("1. Attack");
        System.out.println("2. Use Item");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Select Target:");
                Combatant target = selectTarget(enemy);
                player.attack(target);
                break;
            case 2:
                System.out.println("Select Item:");
                displayInventory(player);
                int itemChoice = scanner.nextInt();
                Item item = getItemFromChoice(player, itemChoice);
                if (item != null) {
                    player.useItem(item, selectTarget(enemy));
                }
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private Item getItemFromChoice(Combatant player, int choice) {
        if (choice >= 1 && choice <= player.getInventorySize()) {
            return player.getItem(choice - 1);
        } else {
            System.out.println("Invalid item choice.");
            return null;
        }
    }

    private void displayInventory(Combatant player) {
        System.out.println("Inventory:");
        for (int i = 0; i < player.getInventorySize(); i++) {
            System.out.println((i + 1) + ". " + player.getItem(i).getName());
        }
    }

    private Combatant selectTarget(Enemy enemy) {
        System.out.println("Select Target:");
        System.out.println("1. " + enemy.getName());
        // Add more targets as needed

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return enemy;
            // Add cases for more targets
            default:
                System.out.println("Invalid choice. Targeting the enemy.");
                return enemy;
        }
    }

    private void enemyTurn(Enemy enemy) {
        enemy.useSamuraiSkill(player1);
    }

    private void displayStatus(Combatant player1, Combatant player2, Enemy enemy) {
        clearConsole();
        System.out.println("\nCurrent Status:");
        System.out.println(player1.getName() + " - Life: " + player1.getLife());
        System.out.println(player2.getName() + " - Life: " + player2.getLife());
        System.out.println(enemy.getName() + " - Life: " + enemy.getLife());
    }
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
    
            String command;
    
            switch (os) {
                case "windows":
                    command = "cmd /c cls";
                    break;
                case "linux":
                case "mac os x":
                case "unix":
                    command = "clear";
                    break;
                default:
                    System.out.println("Unsupported operating system for console clearing.");
                    return;
            }
    
            new ProcessBuilder(command.split(" ")).inheritIO().start().waitFor();
        } catch (final Exception e) {
            // Handle exceptions as needed
            System.out.println("Failed to clear console.");
        }
    }
    
    
}

import game.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Battle battle = new Battle();
        battle.clearConsole();
        System.out.println("       .----.     .----.\n         \\    \\   /    /.--./)\n          '   '. /'   //.''\\\\");
System.out.println("          |    |'    /| |  | |\n   _    _ |    ||    | \\`-' /\n  | '  / |'.   `'   .' /(\"'`");
System.out.println(" .' | .' | \\        /  \\ '---.\n /  | /  |  \\      /    /'\"\"'.\\\n|   `'.  |   '----'    ||     ||\n'   .'|  '/            \\'. __//\n `-'  `--'              `'---' ");
System.out.println(" ____  ____  ____  _____ ____  _        _____ _  _____ _     _____  _____ ____");

int menu = 1;
while (menu == 1) {
    System.out.println("Select an option\n1. Fight\n2. Exit");
    int selector = scanner.nextInt();

    switch (selector) {
        case 1:
            battle.startBattle();
            break;
        case 2:
            System.out.println("Exiting. ");
            menu = 0; 
            break;
        default:
            System.out.println("Invalid option, Senpai. Try again.");
    }
}

// Don't forget to close the scanner when you're done with it
scanner.close();
    
    }
}

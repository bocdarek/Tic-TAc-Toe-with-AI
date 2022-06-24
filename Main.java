package tictactoe;

import java.util.Scanner;

public class Main {

    private final static Scanner sc = CommonScanner.getInstance();

    public static void main(String[] args) {
        Game game = new Game();
        while (true) {
            System.out.print("Input command: ");
            String input = sc.nextLine().trim().toUpperCase();
            String[] commands = Parser.parseCommands(input);
            if (commands == null) {
                continue;
            }
            if (commands[0].equals("EXIT")) {
                break;
            }
            game.play(commands);
        }
    }
}

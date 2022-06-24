package tictactoe;

import java.util.Arrays;
import java.util.List;

public class Parser {

    private static final Messenger msg = Messenger.getInstance();

    private static final String correctInput = "^[123]\\s+[123]$";
    private static final String numbersInput = "^[0-9]+\\s+[0-9]+$";

    private static final List<String> possibleCommands = List.of("USER", "EASY", "MEDIUM");

    public static int[] parseCoordinates(String input) {
        if (input.matches(correctInput)) {
            String[] digits = input.split("\\s+");
            int[] coordinates = new int[2];
            coordinates[0] = Integer.parseInt(digits[0]) - 1;
            coordinates[1] = Integer.parseInt(digits[1]) - 1;
            return coordinates;
        } else if (input.matches(numbersInput)) {
            msg.outOfRangeError();
        } else {
            msg.notNumbersError();
        }
        return null;
    }

    public static String[] parseCommands(String input) {
        String[] commands = input.split("\\s+");
        if (commands[0].equals("EXIT")) {
            return commands;
        }
        if (commands.length < 3) {
            msg.wrongCommandMessage();
            return null;
        }
        if (!commands[0].equals("START")) {
            msg.wrongCommandMessage();
            return null;
        }
        if (!(possibleCommands.contains(commands[1]) && possibleCommands.contains(commands[2]))) {
            msg.wrongCommandMessage();
            return null;
        }
        return Arrays.copyOfRange(commands, 1, 3);
    }
}

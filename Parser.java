package tictactoe;

public class Parser {

    private static final Messenger msg = Messenger.getInstance();

    private static final String correctInput = "^[123]\\s+[123]$";
    private static final String numbersInput = "^[0-9]+\\s+[0-9]+$";

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
}

package tictactoe;

import java.util.Scanner;

public class Player {

    private final char[][] cells;
    private final static Messenger msg = Messenger.getInstance();
    private final static Scanner sc = CommonScanner.getInstance();

    public Player(Board board) {
        cells = board.getCells();
    }

    public void makeMove(char ch) {
        int[] coordinates = takeCoordinates();
        int row = coordinates[0];
        int col = coordinates[1];
        cells[row][col] = ch;
    }

    private int[] takeCoordinates() {
        int[] coordinates;
        boolean isValid = false;
        do {
            coordinates = requestCoordinates();
            if (coordinates != null) {
                isValid = validateCoordinates(coordinates);
            }
        } while (!isValid);
        return coordinates;
    }

    private int[] requestCoordinates() {
        System.out.print("Enter the coordinates: ");
        String input = sc.nextLine().trim();
        return Parser.parseCoordinates(input);
    }

    private boolean validateCoordinates(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        if (cells[row][col] == ' ') {
            return true;
        }
        msg.occupiedCellError();
        return false;
    }
}


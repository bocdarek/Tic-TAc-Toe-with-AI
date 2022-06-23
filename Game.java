package tictactoe;

import java.util.Scanner;

public class Game {

    private final Board board = new Board();
    private final Messenger msg = Messenger.getInstance();
    private final Scanner sc = CommonScanner.getInstance();
    private String initialState;

    public void play() {
        initialState = requestInitialState();
        board.setInitialState(initialState);
        board.display();
        makeMove(takeCoordinates());
        board.display();
        board.evaluate();
    }

    private String requestInitialState() {
        while (true) {
            System.out.print("Enter the cells: ");
            String input = sc.nextLine().trim().toUpperCase();
            if (input.matches("^[XO_]{9}$")) {
                return input;
            }
            msg.initialStateInputError();
        }
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
         if (board.getCells()[row][col] == '_') {
             return true;
         }
         msg.occupiedCellError();
         return false;
     }

    private void makeMove(int[] coordinates) {
        int row = coordinates[0];
        int col = coordinates[1];
        board.getCells()[row][col] = onTheMove();
    }

     private char onTheMove() {
        int x = 0;
        int o = 0;
        for (int i = 0; i < initialState.length(); i++) {
            char ch = initialState.charAt(i);
            if (ch == 'X') {
                x++;
            } else if (ch == 'O') {
                o++;
            }
        }
        return o >= x ? 'X' : 'O';
     }
}

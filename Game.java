package tictactoe;

import java.util.Scanner;

public class Game {

    private final Board board = new Board();
    private final Bot bot = new Bot(board);
    private final static Messenger msg = Messenger.getInstance();
    private final static Scanner sc = CommonScanner.getInstance();

    public void play(String[] modes) {
        board.clear();
        board.display();
        int turn = 0;
        char ch = ' ';
        while (!board.isGameFinished()) {
            ch = ch == 'X' ? 'O' : 'X';
            if (modes[turn % 2].equals("USER")) {
                makeMove(ch);
            } else {
                bot.makeMove(modes[turn % 2], ch);
            }
            board.display();
            turn++;
        }
        board.evaluate();
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
         if (board.getCells()[row][col] == ' ') {
             return true;
         }
         msg.occupiedCellError();
         return false;
     }

    private void makeMove(char ch) {
        int[] coordinates = takeCoordinates();
        int row = coordinates[0];
        int col = coordinates[1];
        board.getCells()[row][col] = ch;
    }
}

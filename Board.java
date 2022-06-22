package tictactoe;

import java.util.Arrays;

public class Board {

    private final char[][] cells = new char[3][3];

    public Board() {
        for (char[] cell : cells) {
            Arrays.fill(cell, ' ');
        }
    }

    public char[][] getCells() {
        return cells;
    }

    public void setInitialState(String input) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = input.charAt(3 * i + j);
            }
        }
    }

    public void display() {
        printDashedLine();
        for (char[] cell : cells) {
            System.out.print("| ");
            for (char c : cell) {
                c = c == '_' ? ' ' : c;
                System.out.print(c + " ");
            }
            System.out.println("|");
        }
        printDashedLine();
    }

    private void printDashedLine() {
        System.out.println("-".repeat(9));
    }
}

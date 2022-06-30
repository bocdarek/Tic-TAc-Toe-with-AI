package tictactoe;

import java.util.Arrays;

public class Board {

    private final char[][] cells = new char[3][3];

    public Board() {
        clear();
    }

    public char[][] getCells() {
        return cells;
    }

    public void clear() {
        for (char[] cell : cells) {
            Arrays.fill(cell, ' ');
        }
    }

    public void setInitialState(String input) {
        for (int i = 0; i < cells.length; i++) {
            cells[0][i] = input.charAt(i);
            cells[1][i] = input.charAt(i + 3);
            cells[2][i] = input.charAt(i + 6);
        }
    }

    public void display() {
        printDashedLine();
        for (char[] cell : cells) {
            System.out.print("| ");
            for (char ch : cell) {
                ch = ch == '_' ? ' ' : ch;
                System.out.print(ch + " ");
            }
            System.out.println("|");
        }
        printDashedLine();
    }

    private void printDashedLine() {
        System.out.println("-".repeat(9));
    }

    public void evaluate() {
        String result = "Game not finished";
        if (isWinner('X')) {
            result = "X wins";
        } else if (isWinner('O')) {
            result = "O wins";
        } else if (isBoardFull()) {
            result = "Draw";
        }
        System.out.println(result);
    }

    public boolean isGameFinished() {
        return isWinner() || isBoardFull();
    }

    public boolean isWinner() {
        return isWinner('X') || isWinner('O');
    }

    public boolean isWinner(char ch) {
        return checkHorizontal(ch) || checkVertical(ch) || checkDiagonal(ch);
    }

    public boolean isDraw() {
        return isBoardFull() && !isWinner();
    }

    private boolean checkHorizontal(char ch) {
        boolean isWinner = false;
        for (char[] row : cells) {
            if (row[0] == ch && row[1] == ch && row[2] == ch) {
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    private boolean checkVertical(char ch) {
        boolean isWinner = false;
        for (int i = 0; i < cells.length; i++) {
            if (cells[0][i] == ch && cells[1][i] == ch && cells[2][i] == ch) {
                isWinner = true;
                break;
            }
        }
        return isWinner;
    }

    private boolean checkDiagonal(char ch) {
        return (cells[0][0] == ch && cells[1][1] == ch && cells[2][2] == ch) ||
                (cells[0][2] == ch && cells[1][1] == ch && cells[2][0] == ch);
    }

    private boolean isBoardFull() {
        for (char[] cell : cells) {
            for (char ch : cell) {
                if (ch == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public Board deepCopy() {
        Board copiedBoard = new Board();
        char[][] copiedCells = copiedBoard.getCells();
        for (int i = 0; i < copiedCells.length; i++) {
            for (int j = 0; j < copiedCells[i].length; j++) {
                copiedCells[i][j] = cells[i][j];
            }
        }
        return copiedBoard;
    }
}

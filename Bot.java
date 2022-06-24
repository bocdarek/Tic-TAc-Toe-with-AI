package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot {

    private final Board board;
    private final char[][] cells;
    private final Random rd = new Random();

    public Bot(Board board) {
        this.board = board;
        this.cells = board.getCells();
    }

    public void makeMove(String level, char ch) {
        if (level.equals("EASY")) {
            System.out.println("Making move level \"easy\"");
            makeEasyMove(ch);
        } else if (level.equals("MEDIUM")) {
            System.out.println("Making move level \"medium\"");
            makeMediumMove(ch);
        }
    }

    private void makeEasyMove(char ch) {
        List<int[]> emptyCells = getEmptyCells();
        int index = rd.nextInt(emptyCells.size());
        int[] choice = emptyCells.get(index);
        int row = choice[0];
        int col = choice[1];
        cells[row][col] = ch;
    }

    private void makeMediumMove(char ch) {
        if (tryWinMedium(ch)) {
            return;
        }
        if (tryBlockMedium(ch)) {
            return;
        }
        makeEasyMove(ch);
    }

    private List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == ' ') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }

    private boolean tryWinMedium(char ch) {
        List<int[]> emptyCells = getEmptyCells();
        for (int[] emptyCell : emptyCells) {
            int row = emptyCell[0];
            int col = emptyCell[1];
            cells[row][col] = ch;
            if (board.isWinner(ch)) {
                return true;
            }
            cells[row][col] = ' ';
        }
        return false;
    }

    private boolean tryBlockMedium(char ch) {
        char ch2 = ch == 'X' ? 'O' : 'X';
        List<int[]> emptyCells = getEmptyCells();
        for (int[] emptyCell : emptyCells) {
            int row = emptyCell[0];
            int col = emptyCell[1];
            cells[row][col] = ch2;
            if (board.isWinner(ch2)) {
                cells[row][col] = ch;
                return true;
            }
            cells[row][col] = ' ';
        }
        return false;
    }
}

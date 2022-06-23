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

    public void makeMove(String level) {
        if (level.equals("easy")) {
            System.out.println("Making move level \"easy\"");
            makeEasyMove();
        }
    }

    private void makeEasyMove() {
        List<int[]> emptyCells = getEmptyCells();
        int index = rd.nextInt(emptyCells.size());
        int[] choice = emptyCells.get(index);
        int row = choice[0];
        int col = choice[1];
        cells[row][col] = 'O';
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
}

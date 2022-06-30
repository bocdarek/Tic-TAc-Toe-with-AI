package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
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
        switch (level) {
            case "EASY":
                System.out.println("Making move level \"easy\"");
                makeEasyMove(ch);
                break;
            case "MEDIUM":
                System.out.println("Making move level \"medium\"");
                makeMediumMove(ch);
                break;
            case "HARD":
                System.out.println("Making move level \"hard\"");
                makeHardMove(ch);
                break;
        }
    }

    private void makeEasyMove(char ch) {
        List<int[]> emptyCells = getEmptyCells(this.board);
        int index = rd.nextInt(emptyCells.size());
        int[] choice = emptyCells.get(index);
        int row = choice[0];
        int col = choice[1];
        cells[row][col] = ch;
    }

    private void makeMediumMove(char ch) {
        if (tryToWin(ch)) {
            return;
        }
        if (tryToBlock(ch)) {
            return;
        }
        makeEasyMove(ch);
    }

    private void makeHardMove(char ch) {
        int index = minMaxAlgorithm(ch, board, true);
        int[] cell = getEmptyCells(this.board).get(index);
        int row = cell[0];
        int col = cell[1];
        cells[row][col] = ch;

    }

    private List<int[]> getEmptyCells(Board board) {
        List<int[]> emptyCells = new ArrayList<>();
        char[][] cells = board.getCells();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == ' ') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }

    private boolean tryToWin(char ch) {
        List<int[]> emptyCells = getEmptyCells(this.board);
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

    private boolean tryToBlock(char ch) {
        char ch2 = ch == 'X' ? 'O' : 'X';
        List<int[]> emptyCells = getEmptyCells(this.board);
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

    private int minMaxAlgorithm(char ch, Board board, boolean isAiOnMove) {
        // evaluation of previous stage
        char prevChar = ch == 'X' ? 'O' : 'X';
        if (board.isWinner(prevChar)) {
            return 10 * (!isAiOnMove ? 1 : -1);
        }
        if (board.isDraw()) {
            return 0;
        }
        // no result - we need to move to the next step
        List<int[]> emptyCells = getEmptyCells(board);
        List<Integer> scores = new ArrayList<>();
        for (int[] cell : emptyCells) {
            Board newBoard = board.deepCopy();
            int row = cell[0];
            int col = cell[1];
            newBoard.getCells()[row][col] = ch;
            char nextChar = ch == 'X' ? 'O' : 'X';
            scores.add(minMaxAlgorithm(nextChar, newBoard, !isAiOnMove));
        }
        int index;
        if (isAiOnMove) {
            index = maxValue(scores);
        } else {
            index = minValueIndex(scores);
        }
        if (board == this.board) {
            return index;
        } else {
            return scores.get(index);
        }
    }

    private int minValueIndex(List<Integer> list) {
        int index = 0;
        int min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < min) {
                index = i;
                min = list.get(i);
            }
        }
        return index;
    }

    private int maxValue(List<Integer> list) {
        int index = 0;
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max) {
                index = i;
                max = list.get(i);
            }
        }
        return index;
    }


}

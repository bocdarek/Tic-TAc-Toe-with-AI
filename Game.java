package tictactoe;

import java.util.Scanner;

public class Game {

    private final Board board = new Board();
    private final Bot bot = new Bot(board);
    private final Player player = new Player(board);

    public void play(String[] modes) {
        board.clear();
        board.display();
        int turn = 0;
        char ch = ' ';
        while (!board.isGameFinished()) {
            ch = ch == 'X' ? 'O' : 'X';
            String mode = modes[turn % 2];
            if (mode.equals("USER")) {
                player.makeMove(ch);
            } else {
                bot.makeMove(mode, ch);
            }
            board.display();
            turn++;
        }
        board.evaluate();
    }
}

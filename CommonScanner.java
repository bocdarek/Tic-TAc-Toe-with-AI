package tictactoe;

import java.util.Scanner;

public class CommonScanner {

    private static Scanner instance = null;

    private CommonScanner(){}

    public static Scanner getInstance() {
        if (instance == null) {
            instance = new Scanner(System.in);
        }
        return instance;
    }
}

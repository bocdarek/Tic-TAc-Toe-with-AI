package tictactoe;

public class Messenger {

    private static Messenger instance = null;

    private Messenger(){}

    public static Messenger getInstance() {
        if (instance == null) {
            instance = new Messenger();
        }
        return instance;
    }

    public void occupiedCellError() {
        System.out.println("This cell is occupied! Choose another one!");
    }

    public void notNumbersError() {
        System.out.println("You should enter numbers!");
    }

    public void outOfRangeError() {
        System.out.println("Coordinates should be from 1 to 3!");
    }

    public void initialStateInputError() {
        System.out.println("Wrong format of initial state! " +
                "Input must contain only 'X', 'O' and '_' and its length must be 9.");

    }
}

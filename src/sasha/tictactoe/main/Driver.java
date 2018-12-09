package sasha.tictactoe.main;

import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        Board board = new Board();
        Random random = new Random();

        board.displayBoard();

        // determine the first move, play random if it is the computer's
        System.out.println(String.format("Who's going to move first? (%d) Computer (%d) User: ",
                Board.COMPUTER_PLAYER, Board.HUMAN_PLAYER));
        int userChoice = board.getScanner().nextInt();
        if (userChoice == Board.COMPUTER_PLAYER) {
            Point initialPoint = new Point(random.nextInt(3), random.nextInt(3));
            board.placeAndMove(initialPoint, Board.COMPUTER_PLAYER);
            board.displayBoard();
        }

        // simulate playing the game
        while (!board.isGameOver()) {
            board.takeHumanInput();

            board.displayBoard();

            if (board.isGameOver())
                break;

            board.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, Board.COMPUTER_PLAYER);
            for (Pair<Point, Integer> pas : board.getRootsAndChildrenScore()) {
                System.out.println(String.format("Point: %s, Score: %d", pas.key.toString(), pas.value));
            }

            board.placeAndMove(board.returnBestMove(), Board.COMPUTER_PLAYER);
            board.displayBoard();
        }

        // determine the winner
        if (board.hasXWon()) {
            System.out.println("Computer wins!");
        } else if (board.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }
}

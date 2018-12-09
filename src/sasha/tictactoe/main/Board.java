package sasha.tictactoe.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

    public static final int COMPUTER_PLAYER = 1;
    public static final int HUMAN_PLAYER = 2;

    private Scanner scanner = new Scanner(System.in);
    private int[][] board = new int[3][3];

    private List<Pair<Point, Integer>> rootsAndChildrenScore = new ArrayList<>();

    /* Placeholder constructor */
    public Board() {
        super();
    }

    public int evaluateBoard() {
        // TODO: Check if it is right, revise basically
        int score = 0;

        // check all the rows
        for (int i = 0; i < 3; i++) {
            int blank = 0;
            int X = 0;
            int O = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    blank++;
                } else if (board[i][j] == Board.COMPUTER_PLAYER) {
                    X++;
                } else {
                    O++;
                }
            }
            score += changeInScore(X, O);
        }

        // check all the columns
        for (int j = 0; j < 3; j++) {
            int blank = 0;
            int X = 0;
            int O = 0;

            for (int i = 0; i < 3; i++) {
                if (board[i][j] == 0) {
                    blank++;
                } else if (board[i][j] == Board.COMPUTER_PLAYER) {
                    X++;
                } else {
                    O++;
                }
            }
            score += changeInScore(X, O);
        }

        int blank = 0;
        int X = 0;
        int O = 0;

        // check diagonal (first)
        for (int i = 0, j = 0; i < 3; i++, j++) {
            if (board[i][j] == 0) {
                blank++;
            } else if (board[i][j] == Board.COMPUTER_PLAYER) {
                X++;
            } else {
                O++;
            }
        }

        score += changeInScore(X, O);

        blank = 0;
        X = 0;
        O = 0;

        // check diagonal (second)
        for (int i = 2, j = 0; i >= 0; i--, j++) {
            if (board[i][j] == 0) {
                blank++;
            } else if (board[i][j] == Board.COMPUTER_PLAYER) {
                X++;
            } else {
                O++;
            }
        }

        score += changeInScore(X, O);

        return score;
    }

    private int changeInScore(int X, int O) {
        int change;
        if (X == 3) {
            change = 100;
        } else if (X == 2 && O == 0) {
            change = 1;
        } else {
            change = 0;
        }
        return change;
    }

    public int alphaBetaMinimax(int alpha, int beta, int depth, int turn) {
        // TODO: Implement or refactor to AlphaBetaMinmax class
        return 0;
    }

    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }

    public boolean hasXWon() {
        return (isDiagonalWin(Board.COMPUTER_PLAYER) || isRowOrColumnWin(Board.COMPUTER_PLAYER));
    }

    public boolean hasOWon() {
        return (isDiagonalWin(Board.HUMAN_PLAYER) || isRowOrColumnWin(Board.HUMAN_PLAYER));
    }

    private boolean isDiagonalWin(int player) {
        return ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player) ||
                (board[0][2] == board[1][1]) && board[0][2] == board[2][0] && board[0][2] == player);
    }

    private boolean isRowOrColumnWin(int player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player) ||
                (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getAvailableStates() {
        List<Point> availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAndMove(Point point, int player) {
        board[point.getxCoord()][point.getyCoord()] = player;   // player = 1 for X, 2 for O
    }

    public Point returnBestMove() {
        int MAX = Integer.MIN_VALUE;
        int bestIndex = -1;

        for (int i = 0; i < rootsAndChildrenScore.size(); i++) {
            if (MAX < rootsAndChildrenScore.get(i).value) {
                MAX = rootsAndChildrenScore.get(i).value;
                bestIndex = i;
            }
        }

        return rootsAndChildrenScore.get(bestIndex).key;
    }

    void takeHumanInput() {
        System.out.println("Your move (type x and then y coordinate): ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        Point point = new Point(x, y);
        placeAndMove(point, HUMAN_PLAYER);
    }


    public void displayBoard() {
        System.out.println();


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.println(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void resetBoardAtPoint(Point point) {
        board[point.getxCoord()][point.getyCoord()] = 0;
    }

    /* ---------- GETTERS AND SETTERS -------------- */
    public Scanner getScanner() {
        return scanner;
    }

    public List<Pair<Point, Integer>> getRootsAndChildrenScore() {
        return rootsAndChildrenScore;
    }

    public static void main(String[] args) {
        // TESTING, REMOVE BEFORE SUBMISSION
        Board b = new Board();
        Pair<Point, Integer> pair = new Pair(new Point(1, 1), Integer.valueOf(10));
        b.getRootsAndChildrenScore().add(pair);
        b.getRootsAndChildrenScore().clear();
        System.out.println("RootsAndChildrenScore: " + b.getRootsAndChildrenScore());
    }
}

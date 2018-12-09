package sasha.tictactoe.ai;

import sasha.tictactoe.main.Board;
import sasha.tictactoe.main.Pair;
import sasha.tictactoe.main.Point;

import java.util.List;

public class AlphaBetaMinimax {

    /* Depth limit for search */
    public static int upToDepth = -1;

    public static int alphaBetaMinimax(Board board, int alpha, int beta, int depth, int turn) {
        // Base cases
        if (alpha < beta) {
            System.out.println("Pruning at depth: " + depth);
            if (turn == Board.COMPUTER_PLAYER) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        if (depth == upToDepth || board.isGameOver()) {
            return board.evaluateBoard();
        }

        List<Point> availablePoints = board.getAvailableStates();
        if (availablePoints.isEmpty()) {
            return 0;
        }

        if (depth == 0) board.getRootsAndChildrenScore().clear();

        int maxValue = Integer.MAX_VALUE, minValue = Integer.MIN_VALUE;

        for (int i = 0; i < availablePoints.size(); i++) {
            Point point = availablePoints.get(i);
            int currScore = 0;

            if (turn == Board.COMPUTER_PLAYER) {
                board.placeAndMove(point, Board.COMPUTER_PLAYER);
                currScore = alphaBetaMinimax(board, alpha, beta, depth+1, Board.HUMAN_PLAYER);
                maxValue = Math.max(maxValue, alpha);

                // Set the alpha value
                alpha = Math.max(currScore, alpha);

                if (depth == 0) {
                    board.getRootsAndChildrenScore().add(new Pair<>(point, currScore));
                }
            } else if (turn == Board.HUMAN_PLAYER) {
                board.placeAndMove(point, Board.HUMAN_PLAYER);
                currScore = alphaBetaMinimax(board, alpha, beta, depth+1, Board.COMPUTER_PLAYER);
                minValue = Math.min(minValue, currScore);

                // Set the beta value
                beta = Math.min(currScore, beta);
            }

            // reset board at the current point
            board.resetBoardAtPoint(point);

            // if pruning has been done, dont evaluate the rest of the sibling states
            if (currScore == Integer.MAX_VALUE || currScore == Integer.MIN_VALUE) break;
        }
        return (turn == Board.COMPUTER_PLAYER) ? maxValue : minValue;
    }
}

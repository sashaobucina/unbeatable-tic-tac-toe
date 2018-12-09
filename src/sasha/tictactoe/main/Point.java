package sasha.tictactoe.main;

public class Point {

    private static final String INVALID_POINT_MSG = "Coordinate given is at an invalid position!";

    private int yCoord;
    private int xCoord;

    public Point(int x, int y) {
        xCoord = x;
        yCoord = y;
    }

    public void setyCoord(int yCoord) {
        if (isInvalidPoint(yCoord)) throw new IllegalArgumentException(INVALID_POINT_MSG);

        this.yCoord = yCoord;
    }

    public void setxCoord(int xCoord) {
        if (isInvalidPoint(xCoord)) throw new IllegalArgumentException(INVALID_POINT_MSG);

        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    private boolean isInvalidPoint(int coord) {
        return (coord < 0 || coord > 3);
    }

    @Override
    public String toString() {
        return String.format("[%d, %d]", xCoord, yCoord);
    }
}

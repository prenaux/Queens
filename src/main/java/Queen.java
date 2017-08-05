import java.util.ArrayList;

public class Queen {
    private Board board;
    private int r, c, d1, d2;

    Queen(Board aBoard, int aR, int aC) {
        board = aBoard;
        moveTo(aR, aC);
    }

    public Board getBoard() {
        return board;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getD1() {
        return d1;
    }

    public int getD2() {
        return d2;
    }

    public void moveTo(int aR, int aC) {
        r = aR;
        c = aC;
        d1 = board.getDiag1(r, c);
        d2 = board.getDiag2(r, c);
    }

    public void moveToRow(int aR) {
        moveTo(aR, c);
    }

    public boolean equals(Queen aRight) {
        // We don't compare the diagonals since those are always the same for a (r,c) pair.
        return r == aRight.r && c == aRight.c;
    }

    /**
     * Check whether this queen collides with another queen, we don't compare
     * the column as we assumed the queens are never arranged on the same
     * columns to begin with.
     */
    public boolean collidesWithIgnoreColumn(Queen aRight) {
        return r == aRight.r || d1 == aRight.d1 || d2 == aRight.d2;
    }
}

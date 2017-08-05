public class Solver {
    private final Board board;
    // Queens, always arranged by columns
    private final Queen[] queens;

    Solver(int aN) {
        board = new Board(aN);
        this.queens = new Queen[aN];
        for (int c = 0; c < aN; ++c) {
            queens[c] = new Queen(board,-1, c);
        }
    }

    public Board getBoard() {
        return board;
    }

    public int getN() {
        return board.getN();
    }


    /**
     * Returns true if this queens collide with any queen in aQueens in the
     * [aFrom,aTo] range.
     */
    public boolean hasCollision(Queen aQ, int aFrom, int aTo) {
        assert (aFrom >= 0);
        assert (aTo <= (queens.length - 1));
        for (int i = aFrom; i <= aTo; ++i) {
            Queen q = queens[i];
            assert(!aQ.equals(q));
            if (aQ.collidesWithIgnoreColumn(q)) {
                return true;
            }
        }
        return false;
    }

    public boolean moveQueenToFreeRow(Queen q, int startRow) {
        final int lastColToCheck = q.getC() - 1;
        if (lastColToCheck < 0) {
            if (startRow >= getN()) {
                q.moveToRow(-1);
                return false;
            }
            else {
                q.moveToRow(startRow);
                return true;
            }
        }
        else {
            for (int r = startRow; r < getN(); ++r) {
                q.moveToRow(r);
                ++totalIt;
                if (!hasCollision(q, 0, lastColToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int totalIt = 0;

    public void resetTotalIt() {
        totalIt = 0;
    }

    /**
     * Get the number of iterations performed by the solver so far.
     * An iteration is defined as one 'hasCollision' check.
     */
    public int getTotalIt() {
        return totalIt;
    }

    public boolean solve() {
        final int N = getN();
        int currentQI = 0;
        Queen q = queens[currentQI];
        while (true) {
            if (moveQueenToFreeRow(q, q.getR()+1)) {
                if (currentQI == (N - 1))
                    break; // we found a solution
                // move to the next queen
                q = queens[++currentQI];
            } else {
                // backtracking //
                // remove this queen from the board
                q.moveToRow(-1);
                if (currentQI == 0) {
                    // if this is the first queen, we can't iterate anymore, we've found all the solutions
                    return false;
                }
                // move to the previous queen
                q = queens[--currentQI];
            }
        }
        return true;
    }

    public String toString(boolean abPrintGrid) {
        return board.toString(new CellToString() {
            @Override
            public void cellToString(Board board, StringBuilder sb, int r, int c) {
                if (queens[c].getR() == r) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
        }, abPrintGrid);
    }
}

public class Solver {
    private final Board board;
    // Queens, always arranged by columns
    private final Queen[] queens;

    Solver(int aN) {
        board = new Board(aN);
        this.queens = new Queen[aN];
        for (int c = 0; c < aN; ++c) {
            queens[c] = new Queen(board, -1, c);
        }
        resetSolver();
    }

    public Board getBoard() {
        return board;
    }

    public int getN() {
        return board.getN();
    }

    private int currentQI;
    private int currentRow;
    private int totalIt;

    /**
     * Reset the solver to restart from the beginning.
     */
    public void resetSolver(int r) {
        totalIt = 0;
        startRow(r);
    }
    public void resetSolver() {
        resetSolver(0);
    }

    public void startRow(int r) {
        currentRow = r;
        currentQI = 1;
        for (Queen q : queens) {
            q.moveToRow(-1);
        }
        queens[0].moveToRow(currentRow);
    }

    /**
     * Get the number of iterations performed by the solver so far.
     * An iteration is defined as one 'hasCollision' check.
     */
    public int getTotalIt() {
        return totalIt;
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
            assert (!aQ.equals(q));
            if (aQ.collidesWithIgnoreColumn(q)) {
                return true;
            }
        }
        return false;
    }

    public boolean moveQueenToFreeRow(Queen q, int startRow) {
        final int lastColToCheck = q.getC() - 1;
        assert (lastColToCheck >= 0);
        // TODO: Here we could skip at least 3 collision check by checking the immediate left column directly first.
        for (int r = startRow; r < getN(); ++r) {
            q.moveToRow(r);
            ++totalIt;
            if (!hasCollision(q, 0, lastColToCheck)) {
                return true;
            }
        }
        return false;
    }

    public boolean solveRow(int r) {
        final int N = getN();
        Queen q = queens[currentQI];
        while (true) {
            if (moveQueenToFreeRow(q, q.getR() + 1)) {
                if (currentQI == (N - 1))
                    break; // we found a solution
                // move to the next queen
                q = queens[++currentQI];
            } else {
                // backtracking //
                // remove this queen from the board
                q.moveToRow(-1);
                // move to the previous queen
                q = queens[--currentQI];
                if (currentQI == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve() {
        final int N = getN();
        while (true) {
            if (solveRow(currentRow)) {
                return true;
            }
            ++currentRow;
            startRow(currentRow);
            if (currentRow >= N) {
                return false;
            }
        }
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

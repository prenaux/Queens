/**
 * Utilities to print boards and convert coordinates.
 * <p>
 * <pre>
 *  Coordinates:
 *  - row (r), column (c)
 *  - diag1 (going NE origin a 0,0), diag2 (going SE origin at 7,0)
 *
 *  Number of diagonals = (N*2)-1
 *
 *  Diag1 with N=5 board:
 *     c0	c1	c2	c3	c4
 *  r0	0	1	2	3	4
 *  r1	1	2	3	4	5
 *  r2	2	3	4	5	6
 *  r3	3	4	5	6	7
 *  r4	4	5	6	7	8
 *
 *  Diag2 with N=5 board:
 *     c0	c1	c2	c3	c4
 *  r0	4	5	6	7	8
 *  r1	3	4	5	6	7
 *  r2	2	3	4	5	6
 *  r3	1	2	3	4	5
 *  r4	0	1	2	3	4
 *  </pre>
 */
public class Board {
    private final int N;

    Board(int aN) {
        this.N = aN;
    }

    public int getN() {
        return N;
    }

    public int getNumDiags() {
        return (N * 2) - 1;
    }

    public int getDiag1(int r, int c) {
        return r + c;
    }

    public int getDiag2(int r, int c) {
        return (N - 1 - r) + c;
    }

    public StringBuilder toStringBuilder(CellToString aCellToString, boolean abPrintGrid) {
        StringBuilder sb = new StringBuilder();
        if (abPrintGrid) {
            for (int iC = 0; iC < N; ++iC) {
                sb.append("\tc" + iC);
            }
            sb.append('\n');
        }
        String cellSpacer = abPrintGrid ? "\t" : " ";
        for (int iR = 0; iR < N; ++iR) {
            if (abPrintGrid) {
                sb.append("r" + iR);
            }
            for (int iC = 0; iC < N; ++iC) {
                sb.append(cellSpacer);
                aCellToString.cellToString(this, sb, iR, iC);
                if (!abPrintGrid) {
                    sb.append(";");
                }
            }
            if (abPrintGrid) {
                sb.append('\n');
            }
        }
        return sb;
    }

    public String toString(CellToString aCellToString, boolean abPrintGrid) {
        return toStringBuilder(aCellToString,abPrintGrid).toString();
    }
}

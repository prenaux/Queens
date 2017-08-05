import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    final Board board5 = new Board(5);
    final Board board8 = new Board(8);

    static final String board5_diag1 = " 0; 1; 2; 3; 4; 1; 2; 3; 4; 5; 2; 3; 4; 5; 6; 3; 4; 5; 6; 7; 4; 5; 6; 7; 8;";
    static final String board5_diag2 = " 4; 5; 6; 7; 8; 3; 4; 5; 6; 7; 2; 3; 4; 5; 6; 1; 2; 3; 4; 5; 0; 1; 2; 3; 4;";

	static final String board8_diag1 = " 0; 1; 2; 3; 4; 5; 6; 7; 1; 2; 3; 4; 5; 6; 7; 8; 2; 3; 4; 5; 6; 7; 8; 9; 3; 4; 5; 6; 7; 8; 9; 10; 4; 5; 6; 7; 8; 9; 10; 11; 5; 6; 7; 8; 9; 10; 11; 12; 6; 7; 8; 9; 10; 11; 12; 13; 7; 8; 9; 10; 11; 12; 13; 14;";
	static final String board8_diag2 = " 7; 8; 9; 10; 11; 12; 13; 14; 6; 7; 8; 9; 10; 11; 12; 13; 5; 6; 7; 8; 9; 10; 11; 12; 4; 5; 6; 7; 8; 9; 10; 11; 3; 4; 5; 6; 7; 8; 9; 10; 2; 3; 4; 5; 6; 7; 8; 9; 1; 2; 3; 4; 5; 6; 7; 8; 0; 1; 2; 3; 4; 5; 6; 7;";

    @Before
    public void setUp() throws Exception {
        assertEquals(5, board5.getN());
        assertEquals(9, board5.getNumDiags());
        assertEquals(8, board8.getN());
        assertEquals(15, board8.getNumDiags());
    }

    @Test
    public void getBoard5Diag1() throws Exception {
        String strBoard = board5.toString(new CellToString() {
            @Override
            public void cellToString(Board board, StringBuilder sb, int r, int c) {
                sb.append(board.getDiag1(r, c));
            }
        }, false);
        System.out.println(strBoard);
        assertEquals(board5_diag1, strBoard);
    }

    @Test
    public void getBoard5Diag2() throws Exception {
        String strBoard = board5.toString(new CellToString() {
            @Override
            public void cellToString(Board board, StringBuilder sb, int r, int c) {
                sb.append(board.getDiag2(r, c));
            }
        }, false);
        System.out.println(strBoard);
        assertEquals(board5_diag2, strBoard);
    }

    @Test
    public void getBoard8Diag1() throws Exception {
        String strBoard = board8.toString(new CellToString() {
				@Override
				public void cellToString(Board board, StringBuilder sb, int r, int c) {
					sb.append(board.getDiag1(r, c));
				}
			}, false);
        System.out.println(strBoard);
        assertEquals(board8_diag1, strBoard);
    }

    @Test
    public void getBoard8Diag2() throws Exception {
        String strBoard = board8.toString(new CellToString() {
				@Override
				public void cellToString(Board board, StringBuilder sb, int r, int c) {
					sb.append(board.getDiag2(r, c));
				}
			}, false);
        System.out.println(strBoard);
        assertEquals(board8_diag2, strBoard);
    }
}

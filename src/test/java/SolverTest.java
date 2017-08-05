import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    Solver solver8 = new Solver(8);

    @Before
    public void setUp() throws Exception {
        assertEquals(8, solver8.getN());
    }
}
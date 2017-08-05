import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    final String solver4_starting = " .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver4_solution = " .; .; Q; .; Q; .; .; .; .; .; .; Q; .; Q; .; .;";

    @Test
    public void solve4Start() {
        final Solver solver4 = new Solver(4);
        System.out.println(solver4.toString(true));
        assertEquals(solver4_starting, solver4.toString(false));
    }

    @Test
    public void solve4First() {
        final Solver solver4 = new Solver(4);
        solver4.solve();
        System.out.println(solver4.toString(true));
        assertEquals(solver4_solution, solver4.toString(false));
        assertEquals(24, solver4.getTotalIt());
    }

    @Test
    public void solve4All() {
        final Solver solver4 = new Solver(4);
        int numSolutions = 0;
        while (solver4.solve()) {
            System.out.println("# Solution " + (numSolutions+1) + "\n" + solver4.toString(true));
            assertEquals(solver4_solution, solver4.toString(false));
            ++numSolutions;
        }
        System.out.println("# Num solutions: " + numSolutions);
        assertEquals(36, solver4.getTotalIt());
        assertEquals(1, numSolutions);
        assertEquals(solver4_starting, solver4.toString(false));
    }
}
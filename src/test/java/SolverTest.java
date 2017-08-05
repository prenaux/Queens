import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {
    final String solver4_starting = " Q; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver4_ended = " .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver4_solutions[] = {
            " .; .; Q; .; Q; .; .; .; .; .; .; Q; .; Q; .; .;",
            " .; Q; .; .; .; .; .; Q; Q; .; .; .; .; .; Q; .;"
    };
    final String solver5_starting = " Q; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver5_ended = " .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver8_starting = " Q; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";
    final String solver8_ended = " .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .; .;";

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
        assertEquals(solver4_solutions[0], solver4.toString(false));
        assertEquals(24, solver4.getTotalIt());
    }

    @Test
    public void solve4All() {
        final Solver solver4 = new Solver(4);
        int numSolutions = 0;
        while (solver4.solve()) {
            System.out.println("# Solution " + (numSolutions + 1) + "\n" + solver4.toString(true));
            assertEquals(solver4_solutions[numSolutions], solver4.toString(false));
            ++numSolutions;
        }
        System.out.println("# Num solutions: " + numSolutions);
        assertEquals(56, solver4.getTotalIt());
        assertEquals(2, numSolutions);
        assertEquals(solver4_ended, solver4.toString(false));
    }

    @Test
    public void solve5All() {
        final Solver solver5 = new Solver(5);
        int numSolutions = 0;
        while (solver5.solve()) {
            ++numSolutions;
        }
        System.out.println("# Num solutions: " + numSolutions);
        assertEquals(10, numSolutions);
        assertEquals(solver5_ended, solver5.toString(false));
    }

    @Test
    public void solve8All() {
        final Solver solver8 = new Solver(8);
        int numSolutions = 0;
        while (solver8.solve()) {
            ++numSolutions;
        }
        System.out.println("# Num solutions: " + numSolutions);
        assertEquals(92, numSolutions);
        assertEquals(solver8_ended, solver8.toString(false));
    }
}

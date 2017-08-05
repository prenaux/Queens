import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        final int threads = Runtime.getRuntime().availableProcessors();
        final int N = 8;
        final boolean printSolutions = true;

        System.out.println("# Using " + threads + " threads.");
        ExecutorService exec = Executors.newScheduledThreadPool(threads);

        System.out.println("Solving for N = " + N);

        ArrayList<Future<ArrayList<String>>> futureSolutions = new ArrayList<Future<ArrayList<String>>>();

        for (int i = 0; i < N; ++i) {
            final int row = i;
            futureSolutions.add(exec.submit(new Callable<ArrayList<String>>() {
                public ArrayList<String> call() {
                    ArrayList<String> sols = new ArrayList<String>();
                    Solver solver = new Solver(N);
                    solver.startRow(row);
                    while (solver.solveRow(row)) {
                        sols.add(solver.toString(true));
                    }
                    return sols;
                }
            }));
        }

        int numSolutions = 0;
        for (int i = 0; i < N; ++i) {
            try {
                ArrayList<String> r = futureSolutions.get(i).get();
                for (String sol : r) {
                    if (printSolutions) {
                        System.out.println("# Solution " + (numSolutions + 1) + "\n" + sol);
                    }
                    ++numSolutions;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("# Num solutions: " + numSolutions);

        exec.shutdown();
    }
}

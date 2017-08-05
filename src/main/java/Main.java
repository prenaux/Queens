
public class Main {
    public static void main(String[] args) {
        final int N = 4;

        System.out.println("Hello Queens!");
        System.out.println("Solving for N = " + N);

        final Solver solver = new Solver(N);
        int numSolutions = 0;
        while (solver.solve()) {
            System.out.println("# Solution " + (numSolutions+1) + "\n" + solver.toString(true));
            ++numSolutions;
        }
        System.out.println("# Num iterations: " + solver.getTotalIt());
        System.out.println("# Num solutions: " + numSolutions);
    }
}

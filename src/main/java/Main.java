import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.util.ArrayList;
import java.util.concurrent.*;

public class Main extends AbstractActor {

    public static void main(String[] args) {
        final int threads = Runtime.getRuntime().availableProcessors();
        final int N = 8;
        // Don't print solutions above N=10-ish since they are so many that
        // most of the time spent is to print.
        final boolean printSolutions = (N <= 12) ? true : false;

        System.out.println("# Using " + threads + " threads.");
        ExecutorService exec = Executors.newScheduledThreadPool(threads);

        System.out.println("Solving for N = " + N);

        final ActorSystem actorSystem = ActorSystem.create("QueensSolutionsReceiver");
        final ActorRef solutionHandler = actorSystem.actorOf(SolutionHandler.props(), "SolutionsHandler");

        ArrayList<Future<Integer>> futureSolutions = new ArrayList<Future<Integer>>();

        for (int i = 0; i < N; ++i) {
            final int row = i;
            futureSolutions.add(exec.submit(new Callable<Integer>() {
                public Integer call() {
                    int numSols = 0;
                    Solver solver = new Solver(N);
                    solver.startRow(row);
                    while (solver.solveRow(row)) {
                        if (printSolutions) {
                            String solText = solver.toString(true);
                            solutionHandler.tell(new SolutionHandler.MsgPrintSolution(solText), ActorRef.noSender());
                        }
                        ++numSols;
                    }
                    solutionHandler.tell(new SolutionHandler.MsgSolverDone(numSols, solver.getTotalIt()), ActorRef.noSender());
                    return numSols;
                }
            }));
        }

        for (int i = 0; i < N; ++i) {
            try {
                futureSolutions.get(i).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        solutionHandler.tell(new SolutionHandler.MsgFoundAll(), ActorRef.noSender());

        try {
            SolutionHandler.waitDone();
            actorSystem.terminate();
            exec.shutdown();
            exec.awaitTermination(10, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}

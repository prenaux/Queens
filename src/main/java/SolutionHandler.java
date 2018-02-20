import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;
import akka.japi.pf.FI;
import org.jdeferred.impl.DeferredObject;

public class SolutionHandler extends AbstractActor {
    static public Props props() {
        return Props.create(SolutionHandler.class,
                new Creator<SolutionHandler>() {
                    @Override
                    public SolutionHandler create() throws Exception {
                        return new SolutionHandler();
                    }
                }
        );
    }

    static public class MsgPrintSolution {
        public final String text;

        public MsgPrintSolution(String aText) {
            text = aText;
        }
    }

    static public class MsgSolverDone {
        public final long numSolutions;
        public final long numIt;

        public MsgSolverDone(long aNumSolutions, long aNumIt) {
            numSolutions = aNumSolutions;
            numIt = aNumIt;
        }
    }

    static public class MsgFoundAll {
    }

    private long numPrintedSolutions = 0;
    private long numSolutions = 0;
    private long numIt = 0;

    static private DeferredObject isDone = new DeferredObject();
    static public void waitDone() throws InterruptedException {
        isDone.waitSafely();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(MsgPrintSolution.class, new FI.UnitApply<MsgPrintSolution>() {
                    @Override
                    public void apply(MsgPrintSolution aMsg) throws Exception {
                        ++numPrintedSolutions;
                        System.out.println("\n# Solution " + numPrintedSolutions + "\n" + aMsg.text);
                    }
                })
                .match(MsgSolverDone.class, new FI.UnitApply<MsgSolverDone>() {
                    @Override
                    public void apply(MsgSolverDone aMsg) throws Exception {
                        numSolutions += aMsg.numSolutions;
                        numIt += aMsg.numIt;
                    }
                })
                .match(MsgFoundAll.class, new FI.UnitApply<MsgFoundAll>() {
                    @Override
                    public void apply(MsgFoundAll aMsg) throws Exception {
                        System.out.println("\n# Num iterations: " + numIt);
                        System.out.println("# Num solutions: " + numSolutions);
                        isDone.resolve(true);
                    }
                })
                .build();
    }
}

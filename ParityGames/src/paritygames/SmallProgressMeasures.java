package paritygames;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javafx.util.Pair;
import paritygames.Vertex.Owner;

/**
 *
 * @author ruudandriessen
 */
public class SmallProgressMeasures {

    public enum Strategy {
        RANDOM, INPUT
    }

    public static Pair<List<Vertex>, List<Vertex>> calculate(ParityGame pg, Strategy strategy) throws InvalidStrategyException, IllegalTupleException {
        // Set default tuple size
        dTuple.setTupleSize(pg.maxPriority() + 1);

        // Set max tuple
        dTuple.setMaxTuple(maxTuple(pg));

        // Initialize empty tuple
        dTuple rho = new dTuple();

        boolean canLift = true;
        while (canLift) {
            Vertex v = getLift(pg, strategy);
            lift(v);
        }

        // Calculate results
        return results(pg);
    }

    public static Vertex getLift(ParityGame pg, Strategy strategy) throws InvalidStrategyException {
        Vertex v;
        switch (strategy) {
            case RANDOM:
                v = getRandomLift(pg);
                break;
            default:
                throw new InvalidStrategyException("Strategy: " + strategy + " is not implemented");
        }
        return v;
    }

    public static Vertex getRandomLift(ParityGame pg) {
        // Return a random lift
        int value = ThreadLocalRandom.current().nextInt(0, pg.getVertices().size());
        return pg.getVertices().get(value);
    }

    public static void lift(Vertex v) throws IllegalTupleException {
        if (v.getOwner() == Owner.EVEN) {
            // Initialize new d-tuple to T
            dTuple min = new dTuple();
            min.setTop(true);
            for (Vertex suc : v.getSuccessors()) {

            }
        } else if (v.getOwner() == Owner.ODD) {
            // Initialize new d-tuple to (0,...0)
            dTuple max = new dTuple();
            for (Vertex suc : v.getSuccessors()) {

            }
        }
    }
    
    public static dTuple maxTuple(ParityGame pg) throws IllegalTupleException {
        dTuple maxTuple = new dTuple();
        
        for (Vertex v : pg.getVertices()) {
            // Get priority of each vertex
            int priority = v.getPriority();
            
            if (priority % 2 != 0) {
                // Increase tuple value if priority is odd
                maxTuple.increment(priority);
            }
        }
        return maxTuple;
    }

    public static Pair<List<Vertex>, List<Vertex>> results(ParityGame pg) {
        List<Vertex> vEven = new ArrayList<>(),
                vOdd = new ArrayList<>();

        pg.getVertices().stream().forEach((v) -> {
            if (v.getTuple().isTop()) {
                // Odd won if the value is top
                vOdd.add(v);
            } else {
                // Even won if the value is not top
                vEven.add(v);
            }
        });

        return new Pair(vEven, vOdd);
    }
}

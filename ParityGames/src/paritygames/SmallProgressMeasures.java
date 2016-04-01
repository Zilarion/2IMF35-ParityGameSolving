package paritygames;

import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import paritygames.Vertex.Owner;

/**
 *
 * @author ruudandriessen
 */
public class SmallProgressMeasures {

    public static Pair<List<Vertex>, List<Vertex>> calculate(ParityGame pg, LiftStrategy strategy) throws IllegalTupleException {
        // Set default tuple size
        dTuple.setTupleSize(pg.maxPriority() + 1);

        // Set max tuple
        dTuple.setMaxTuple(maxTuple(pg));

        for (Vertex v : pg.getVertices()) {
            v.setTuple(new dTuple());
        }

        // Initialize empty tuple
        dTuple rho = new dTuple();
        dTuple nRho;
        boolean isStable = false;
        while (true) {
            // Lift the next vertex until stable
            if (strategy.isEnd()) {
                if (isStable) {
                    return results(pg);
                } else {
                    isStable = true;
                }
            }
            Vertex v = strategy.next();
            nRho = lift(v, rho);
            
            if (!rho.lt(nRho)) {
                System.out.println("Vertex: " + v + " not stable..");
                System.out.println(rho + " lt " + nRho + " does not hold");
                isStable = false;
            }
            rho = nRho;
        }
    }

    public static dTuple lift(Vertex v, dTuple rho) throws IllegalTupleException {
        if (v.getOwner() == Owner.EVEN) {
//        System.out.println("Lift: " + v + " / even");
            // Initialize new d-tuple to T
            dTuple min = new dTuple();
            min.setTop(true);

            // Calculate the minimum of all prog(rho, v, w)
            for (Vertex w : v.getSuccessors()) {
                dTuple newTuple = prog(rho, v, w);
//                System.out.println("Prog (" + rho + ", " + v + ", " + w + "): " + newTuple);
                if (newTuple.lt(min)) {
                    min = newTuple;
                }
            }
//            System.out.println("Even minimum: " + min);
            return min;
        } else { // v.getOwner() == Owner.ODD
//            System.out.println("Lift: " + v + " / odd");
            // Initialize new d-tuple to (0,...0)
            dTuple max = new dTuple();

            // Calculate the maximum of all prog(rho, v, w)
            for (Vertex w : v.getSuccessors()) {
                dTuple newTuple = prog(rho, v, w);
//                System.out.println("Prog (" + rho + ", " + v + ", " + w + "): " + newTuple);
                if (newTuple.gt(max)) {
                    max = newTuple;
                }
            }
//            System.out.println("Odd maximum: " + max);
            return max;
        }
    }

    public static dTuple prog(dTuple rho, Vertex v, Vertex w) throws IllegalTupleException {
        dTuple result = new dTuple();
        int vPriority = v.getPriority();
        dTuple wRho = w.getTuple();
        if (v.getOwner() == Owner.EVEN) {
            for (int i = vPriority + 1; i < dTuple.size(); i++) {
                // Minimize everything behind the priority value
                result.set(i, 0);
            }
            for (int i = 0; i <= vPriority; i++) {
                result.set(i, wRho.get(i));
            }
        } else { // v.getOwner() == Owner.ODD
            for (int i = vPriority + 1; i < dTuple.size(); i++) {
                // Minimize everything behind the priority value
                result.set(i, 0);
            }
            for (int i = 0; i < vPriority; i++) {
                result.set(i, wRho.get(i));
            }
            result.increment(vPriority);
        }
        return result;
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

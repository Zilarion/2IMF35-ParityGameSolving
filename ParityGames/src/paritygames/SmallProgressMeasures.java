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

        dTuple nRho, rho;
        // Check if rho [= Lift(u, v) holds for all v in vertices
        while (!pg.isStable()) {
            // Lift the next vertex until stable
            Vertex v = strategy.next();
            
            if (v == null) {
                break;
            }
            // nRho = lift(v, rho)
            rho = v.getTuple();
            nRho = lift(v, rho);
            
            // Update stableness of vertex based on if u [= Lift(v, rho) holds
            if (rho.lt(nRho)) {
                v.setStable(false);
                strategy.lifted(v);
            } else {
                v.setStable(true);
            }
            
            // rho = nRho
            v.setTuple(new dTuple(nRho));
        }
        return results(pg);
    }

    public static dTuple lift(Vertex v, dTuple rho) throws IllegalTupleException {
        if (v.getOwner() == Owner.EVEN) {
            // Initialize new d-tuple to T
            dTuple min = new dTuple();
            min.setTop(true);

            // Calculate the minimum of all prog(rho, v, w)
            for (Vertex w : v.getSuccessors()) {
                dTuple newTuple = prog(v, w);
                if (newTuple.lt(min)) {
                    min = newTuple;
                }
            }
            return rho.gt(min) ? rho : min;
        } else { // v.getOwner() == Owner.ODD
            // Initialize new d-tuple to (0,...0)
            dTuple max = new dTuple();

            // Calculate the maximum of all prog(rho, v, w)
            for (Vertex w : v.getSuccessors()) {
                dTuple newTuple = prog(v, w);
                if (newTuple.gt(max)) {
                    max = newTuple;
                }
            }
            return rho.gt(max) ? rho : max;
        }
    }

    public static dTuple prog(Vertex v, Vertex w) throws IllegalTupleException {
        dTuple result = new dTuple();
        int vPriority = v.getPriority();
        dTuple wRho = w.getTuple();
        
        if (vPriority % 2 == 0) { // Even priority
            for (int i = vPriority + 1; i < dTuple.size(); i++) {
                // Minimize everything behind the priority value
                result.set(i, 0);
            }
            for (int i = 0; i <= vPriority; i++) {
                result.set(i, wRho.get(i));
            }
        } else { // Odd priority
            for (int i = vPriority + 1; i < dTuple.size(); i++) {
                // Minimize everything behind the priority value
                result.set(i, 0);
            }
            for (int i = 0; i <= vPriority; i++) {
                result.set(i, wRho.get(i));
            }
            result.increment(vPriority);
        }
        if (wRho.isTop()) {
            result.setTop(true);
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

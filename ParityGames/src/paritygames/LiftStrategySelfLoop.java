package paritygames;

import paritygames.Vertex.Owner;

/**
 *
 * @author ruudandriessen
 */
public class LiftStrategySelfLoop extends LiftStrategy {
    int current;
    int[] order;
    int amountOfSelfLoops;
    
    public LiftStrategySelfLoop(ParityGame pg) {
        super(pg);
        current = 0;
        amountOfSelfLoops = 0;
        order = new int[pg.getVertices().size()];
        
        order = fillSelfLoops(order, pg);
    }
    
    private int[] fillSelfLoops(int[] order, ParityGame pg) {
        // Count self loops where even quickly raises the value to T
        for (Vertex v : pg.getVertices()) {
            for (Vertex suc : v.getSuccessors()) {
                if (v.getID() == suc.getID() && v.getOwner() == Owner.ODD && v.getPriority() % 2 == 1) {
                    amountOfSelfLoops++;
                }
            }
        }
        
        int index = amountOfSelfLoops;
        int slIndex = 0;
        // Fill order
        for (Vertex v : pg.getVertices()) {
            // Check for selfloop
            boolean hasLoop = false;
            for (Vertex suc : v.getSuccessors()) {
                if (v.getID() == suc.getID()) {
                    hasLoop = true;
                }
            }
            // Fill based on whether this vertex has a self loop
            if (hasLoop) {
                order[slIndex] = v.getID();
                slIndex++;
            } else {
                order[index] = v.getID();
                index++;
            }
        }
        return order;
    }
    
    @Override
    public Vertex next() {
        Vertex v = pg.getVertices().get(order[current]);
        if (current < amountOfSelfLoops) {
            if (!v.getTuple().isTop()) {
                return v;
            } else {
                current++;
                return next();
            }
        }
        current++;
        if (current > order.length - 1) {
            current = 0;
        }
        return v;
    }
}

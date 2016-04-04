package paritygames;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author ruudandriessen
 */
public class LiftStrategyPredecessor extends LiftStrategy {

    Queue<Vertex> queue;
    boolean queued[];
    
    public LiftStrategyPredecessor(ParityGame pg) {
        super(pg);
        queue = new ArrayDeque<>();
        queued = new boolean[pg.getVertices().size()];
        
        for (Vertex v: pg.getVertices()) {
            queue.add(v);
            queued[v.getID()] = true;
        }
    }
    
    @Override
    public void lifted(Vertex v) {
        for (Vertex w : v.getPredecessors()) {
            if (!queued[w.getID()] && !w.getTuple().isTop()) {
                queue.add(w);
                queued[w.getID()] = true;
            }
        }
    }
    
    @Override
    public Vertex next() {
        if (queue.isEmpty()) {
            return null;
        } else {
            Vertex v = queue.poll();
            queued[v.getID()] = false;
            return v;
        }
    }

}

package paritygames;

import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author ruudandriessen
 */
public class LiftStrategyRandomlyOrdered extends LiftStrategy {

    int current;
    int[] order;
    
    public LiftStrategyRandomlyOrdered(ParityGame pg) {
        super(pg);
        current = 0;
        order = new int[pg.getVertices().size()];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        order = shuffle(order);
    }
    
    private int[] shuffle(int[] shuffle) {
        // Fisher-yates shuffle
        for (int i = shuffle.length - 1; i > 1; i--) {
            int r = ThreadLocalRandom.current().nextInt(0, shuffle.length - 1);
            int old = shuffle[i];
            shuffle[i] = shuffle[r];
            shuffle[i] = old;
        }
        return shuffle;
    }
    
    @Override
    public Vertex next() {
        Vertex v = pg.getVertices().get(order[current]);
        current++;
        if (current > order.length - 1) {
            current = 0;
        }
        return v;
    }
    
    @Override
    public void lifted(Vertex v) {
    }
}

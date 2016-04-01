package paritygames;

/**
 *
 * @author ruudandriessen
 */
public class LiftStrategyOrdered extends LiftStrategy {

    int current;
    int loops;
    
    public LiftStrategyOrdered(ParityGame pg) {
        super(pg);
        current = 0;
        loops = 0;
    }
    
    @Override
    public Vertex next() {
        Vertex v = pg.getVertices().get(current);
        current++;
        if (current > pg.getVertices().size() - 1) {
            current = 0;
            loops++;
        }
        return v;
    }
    
    public boolean isEnd() {
        return current == pg.getVertices().size() - 1  && loops > 1;
    }

}

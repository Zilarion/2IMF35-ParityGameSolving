package paritygames;

/**
 *
 * @author ruudandriessen
 */
public class LiftStrategyOrdered extends LiftStrategy {

    int current;
    
    public LiftStrategyOrdered(ParityGame pg) {
        super(pg);
        current = 0;
    }
    
    @Override
    public Vertex next() {
        Vertex v = pg.getVertices().get(current);
        current++;
        return v;
    }

}

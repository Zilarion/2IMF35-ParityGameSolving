package paritygames;

/**
 *
 * @author ruudandriessen
 */
public abstract class LiftStrategy {
    protected final ParityGame pg;
    
    LiftStrategy (ParityGame pg) {
        this.pg = pg;
    }
    
    public abstract Vertex next();
    
    public abstract void lifted(Vertex v);
}
package paritygames;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hein
 */
public class ParityGame {

    private int maxPriority;
    private List<Vertex> vertices;

    public ParityGame() {
        this.vertices = new ArrayList<>();
    }

    public List<Vertex> getVertices() {
        return this.vertices;
    }
    
    public int maxPriority() {
        return maxPriority;
    }
    
    public int getStateSize() {
        return this.vertices.size();
    }

    public void addVertex(Vertex v) {
        if (v.getPriority() > maxPriority) {
            maxPriority = v.getPriority();
        }
        this.vertices.add(v);
    }
    
    @Override
    public String toString() {
        String result = "";
        for (Vertex s : this.vertices) {
            result += s.toString() + "\n";
        }
        return result;
    }
}

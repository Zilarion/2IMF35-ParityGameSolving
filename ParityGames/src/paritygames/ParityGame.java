package paritygames;

import java.util.ArrayList;
import paritygames.Vertex.Owner;

/**
 *
 * @author Hein
 */
public class ParityGame {

    private int maxPriority;
    private ArrayList<Vertex> vertices;

    public ParityGame() {
        this.vertices = new ArrayList();
        this.maxPriority = -1;
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }
    
    public Vertex getVertex(int id) {
        return this.vertices.get(id);
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
        for (int i=0; i < this.vertices.size(); i++) {
            Vertex s = this.vertices.get(i);
            result += s.toString() + "\n";
        }
        return result;
    }
    
    public String toDot() {
        String vertices = "";
        String edges = "";
        for (Vertex v : this.vertices) {
            if (v.getOwner() == Owner.ODD) {
                vertices += v.getID() + " [shape=box, label=\"id: " + v.getID() + " | prio: " + v.getPriority() + "\"];\n";
            } else {
                vertices += v.getID() + " [shape=diamond, label=\"id: " + v.getID() + " | prio: " + v.getPriority() + "\"];\n";
            }
            for (Vertex suc : v.getSuccessors()) {
                edges += v.getID() + " -> " + suc + ";\n";
            }
        }
        String graph = "digraph G {\n" + vertices + edges + "}";
        return graph;
    }
}

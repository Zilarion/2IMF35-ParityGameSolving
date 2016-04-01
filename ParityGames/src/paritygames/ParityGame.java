/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paritygames;

import java.util.HashSet;

/**
 *
 * @author Hein
 */
public class ParityGame {

    private final int maxID;
    private final HashSet<Edge> edgeSet;
    private final HashSet<Vertex> nodeSet;

    public ParityGame(String x) {
        this.maxID = Integer.parseInt(x);
        this.edgeSet = new HashSet<>();
        this.nodeSet = new HashSet<>(maxID+1);
    }

    public int getMaxID() {
        return this.maxID;
    }
    
    public HashSet<Edge> getEdges() {
        return this.edgeSet;
    }
    
    public int getEdgeCount() {
        return this.edgeSet.size();
    }

    public HashSet<Vertex> getStates() {
        return this.nodeSet;
    }
    
    public int getStateSize() {
        return this.nodeSet.size();
    }

    public void addNode(Vertex n) {
        this.nodeSet.add(n);
    }
    
    @Override
    public String toString() {
        String result = "";
        for (Vertex s : this.nodeSet) {
            result += s.toString() + "\n";
        }
        return result;
    }

    public String toDot() {
        String edges = "";
        for (Edge edge : this.edgeSet) {
            edges += edge.getStart() + " -> " + edge.getEnd() + "[label=" + edge.getLabel() + "];\n";
        }
        String graph = "digraph G {\n" + edges + "}";
        return graph;
    }
}

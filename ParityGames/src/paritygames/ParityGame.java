/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paritygames;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hein
 */
public class ParityGame {

    private final int maxID;
    private final List<Vertex> vertices;

    public ParityGame(String x) {
        this.maxID = Integer.parseInt(x);
        this.vertices = new ArrayList<>(maxID+1);
    }

    public int getMaxID() {
        return this.maxID;
    }

    public List<Vertex> getStates() {
        return this.vertices;
    }
    
    public int getStateSize() {
        return this.vertices.size();
    }

    public void addVertex(Vertex n) {
        this.vertices.add(n);
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

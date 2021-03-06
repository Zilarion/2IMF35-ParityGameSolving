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
public class Vertex {
    private final int id;
    private final int priority;
    private final Owner owner;
    private final List<Vertex> successors, predecessors;
    private final String name;
    private dTuple tuple;
    private boolean stable;
    public static int numStable = 0;
    
    public enum Owner {
        ODD, EVEN
    }
    
    public Vertex(int id, int priority, Owner owner, String n) {
        this.id = id;
        this.priority = priority;
        this.owner = owner;
        this.successors = new ArrayList<>();
        this.predecessors = new ArrayList<>();
        this.name = n;
        this.stable = false;
    }
    
    public int getID() {
        return this.id;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public Owner getOwner() {
        return owner;
    }
    
    public List<Vertex> getSuccessors() {
        return successors;
    }
    
    public List<Vertex> getPredecessors() {
        return predecessors;
    }
    
    public void addPredecessor(Vertex v) {
        this.predecessors.add(v);
    } 
    
    public void addSuccessor(Vertex v) {
        this.successors.add(v);
    } 
    
    public void setTuple(dTuple tuple) {
        this.tuple = tuple;
    }
    
    public dTuple getTuple() {
        return tuple;
    }
    
    public void setStable(boolean value) {
        if (stable != value) { 
            if (value) {
                numStable++;
            } else {
                numStable--;
            }
        }
        stable = value;
    }
    
    public boolean stable() {
        return stable;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!Vertex.class.isAssignableFrom(obj.getClass())) return false;
        final Vertex n = (Vertex) obj;
        return (this.id == n.id);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }
    
    @Override
    public String toString() {
        return "" + id;// + priority + owner + successors + name;
    }
}

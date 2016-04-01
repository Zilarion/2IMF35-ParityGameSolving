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
    private final List<Vertex> successors;
    private final String name;
    private dTuple tuple;
    
    public enum Owner {
        ODD, EVEN
    }
    
    public Vertex(int id, int priority, Owner owner, String n) {
        this.id = id;
        this.priority = priority;
        this.owner = owner;
        this.successors = new ArrayList<>();
        this.name = n;
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
    
    public void addSuccessor(Vertex v) {
        this.successors.add(v);
    } 
    
    public dTuple getTuple() {
        return tuple;
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
        String id = "ID: " + Integer.toString(this.id) + "\n";
        String priority = "Priority: " + Integer.toString(this.priority) + "\n";
        String owner = "Owner: " + this.owner + "\n";
        String successors = "Successors : ";
        for (Vertex s : this.successors) {
            successors += s.getID() + " ";
        }
        successors += "\n";
        String name = "Name: " + this.name + "\n";
        return id + priority + owner + successors + name;
    }
}

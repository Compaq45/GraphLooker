package com.example.GraphLooker.Model;

import java.util.HashMap;

public class UndirectedGraphNode {
    private boolean visited;
    private Long wayCost;
    private String name;
    private HashMap<UndirectedGraphNode, Long> ribs;

    public void AddRib(UndirectedGraphNode node, Long cost){
        ribs.put(node,cost);
    }

    public UndirectedGraphNode(String name) {
        ribs = new HashMap<>();
        this.setName(name);
        wayCost = (long) -1;
    }

    public void Clear() {
        setVisited(false);
        setWayCost(-1);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public long getWayCost() {
        return wayCost;
    }

    public void setWayCost(long wayCost) {
        if( this.wayCost != null) this.wayCost = wayCost;
        else this.wayCost = wayCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<UndirectedGraphNode, Long> GetRibs() {
        return ribs;
    }

}

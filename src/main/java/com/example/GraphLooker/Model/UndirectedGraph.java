package com.example.GraphLooker.Model;

import java.util.ArrayList;
import java.util.LinkedList;

public class UndirectedGraph {
    private ArrayList<UndirectedGraphNode> vertexes;

    public UndirectedGraph() {
        vertexes = new ArrayList<>();
    }

    public boolean AddVertex(UndirectedGraphNode vertex){
        for (var curVert:
             vertexes) {
            if (curVert.getName().equals(vertex.getName())) return false;
        }
        vertexes.add(vertex);
        return true;
    }

    public boolean AddRib(UndirectedGraphNode from, UndirectedGraphNode to, Long cost) {
        if(!(vertexes.contains(from) && vertexes.contains(to))) return false;
        from.AddRib(to, cost);
        to.AddRib(from, cost);
        return true;
    }

    public Long GetShortestWay(String from, String to) {
        UndirectedGraphNode startVertex = GetVertexByName(from);
        UndirectedGraphNode endVertex = GetVertexByName(to);
        if (startVertex == null || endVertex == null) return (long) -1;
        var queue = new LinkedList<UndirectedGraphNode>();
        queue.add(startVertex);
        startVertex.setWayCost(0);
        do {
            UndirectedGraphNode currentNode = queue.pollFirst();
            for (var lookingHope:
                 currentNode.GetRibs().keySet()) {
                if(lookingHope.getWayCost()<0) {
                    lookingHope.setWayCost(currentNode.GetRibs().get(lookingHope) + currentNode.getWayCost());
                    queue.add(lookingHope);
                }
                else if (lookingHope.getWayCost() > currentNode.getWayCost() + currentNode.GetRibs().get(lookingHope)) {
                    lookingHope.setWayCost(currentNode.getWayCost() + currentNode.GetRibs().get(lookingHope));
                    lookingHope.setVisited(false);
                    if (!queue.contains(lookingHope))
                        queue.add(lookingHope);
                }
            }
            currentNode.setVisited(true);
        } while (!queue.isEmpty());
        return endVertex.getWayCost();
    }

    private UndirectedGraphNode GetVertexByName(String name) {
        for (var vert:
                vertexes) {
            if (vert.getName().equals(name)) return vert;
        }
        return null;
    }

    private void Clear() {
        for (var vert:
                vertexes) {
            vert.setVisited(false);
            vert.setWayCost(-1);
        }
    }

    public UndirectedGraphNode FindVertexByName(String name){
        for (var res:
             vertexes) {
            if (res.getName().equals(name)) return res;
        }
        return null;
    }
}

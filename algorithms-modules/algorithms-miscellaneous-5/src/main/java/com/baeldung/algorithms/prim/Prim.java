package com.baeldung.algorithms.prim;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.util.Pair;

public class Prim {

    private List<Vertex> graph;

    public Prim(List<Vertex> graph){
        this.graph = graph;
    }

    public void run(){
        if (graph.size() > 0){
            graph.get(0).setVisited(true);
        }
        while (isDisconnected()){
            var nextMinimum = new Edge(Integer.MAX_VALUE);
            var nextVertex = graph.get(0);
            for (var vertex : graph){
                if (vertex.isVisited()){
                    var candidate = vertex.nextMinimum();
                    if (candidate.getValue().getWeight() < nextMinimum.getWeight()){
                        nextMinimum = candidate.getValue();
                        nextVertex = candidate.getKey();
                    }
                }
            }
            nextMinimum.setIncluded(true);
            nextVertex.setVisited(true);
        }
    }

    private boolean isDisconnected(){
        for (var vertex : graph){
            if (!vertex.isVisited()){
                return true;
            }
        }
        return false;
    }

    public String originalGraphToString(){
        var sb = new StringBuilder();
        for (var vertex : graph){
            sb.append(vertex.originalToString());
        }
        return sb.toString();
    }

    public void resetPrintHistory(){
        for (var vertex : graph){
            var it = vertex.getEdges().entrySet().iterator();
            while (it.hasNext()) {
                var pair = it.next();
                pair.getValue().setPrinted(false);
            }
        }
    }

    public String minimumSpanningTreeToString(){
        var sb = new StringBuilder();
        for (var vertex : graph){
            sb.append(vertex.includedToString());
        }
        return sb.toString();
    }

}

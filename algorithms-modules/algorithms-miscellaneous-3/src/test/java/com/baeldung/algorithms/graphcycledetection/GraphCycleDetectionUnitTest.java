package com.baeldung.algorithms.graphcycledetection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.baeldung.algorithms.graphcycledetection.domain.Graph;
import com.baeldung.algorithms.graphcycledetection.domain.Vertex;

class GraphCycleDetectionUnitTest {

    @Test
    public void givenGraph_whenCycleExists_thenReturnTrue() {

        var vertexA = new Vertex("A");
        var vertexB = new Vertex("B");
        var vertexC = new Vertex("C");
        var vertexD = new Vertex("D");

        var graph = new Graph();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexB, vertexC);
        graph.addEdge(vertexC, vertexA);
        graph.addEdge(vertexD, vertexC);

        assertTrue(graph.hasCycle());
    }

    @Test
    void givenGraph_whenNoCycleExists_thenReturnFalse() {

        var vertexA = new Vertex("A");
        var vertexB = new Vertex("B");
        var vertexC = new Vertex("C");
        var vertexD = new Vertex("D");

        var graph = new Graph();
        graph.addVertex(vertexA);
        graph.addVertex(vertexB);
        graph.addVertex(vertexC);
        graph.addVertex(vertexD);

        graph.addEdge(vertexA, vertexB);
        graph.addEdge(vertexB, vertexC);
        graph.addEdge(vertexA, vertexC);
        graph.addEdge(vertexD, vertexC);

        assertFalse(graph.hasCycle());
    }
}

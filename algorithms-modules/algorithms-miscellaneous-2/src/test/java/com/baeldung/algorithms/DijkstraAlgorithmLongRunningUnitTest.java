package com.baeldung.algorithms;

import com.baeldung.algorithms.ga.dijkstra.Dijkstra;
import com.baeldung.algorithms.ga.dijkstra.Graph;
import com.baeldung.algorithms.ga.dijkstra.Node;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class DijkstraAlgorithmLongRunningUnitTest {

    @Test
    public void whenSPPSolved_thenCorrect() {

        var nodeA = new Node("A");
        var nodeB = new Node("B");
        var nodeC = new Node("C");
        var nodeD = new Node("D");
        var nodeE = new Node("E");
        var nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 15);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 2);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        var graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeA);

        var shortestPathForNodeB = Arrays.asList(nodeA);
        var shortestPathForNodeC = Arrays.asList(nodeA);
        var shortestPathForNodeD = Arrays.asList(nodeA, nodeB);
        var shortestPathForNodeE = Arrays.asList(nodeA, nodeB, nodeD);
        var shortestPathForNodeF = Arrays.asList(nodeA, nodeB, nodeD);

        for (var node : graph.getNodes()) {
            switch (node.getName()) {
            case "B":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeB));
                break;
            case "C":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeC));
                break;
            case "D":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeD));
                break;
            case "E":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeE));
                break;
            case "F":
                assertTrue(node
                  .getShortestPath()
                  .equals(shortestPathForNodeF));
                break;
            }
        }
    }
}

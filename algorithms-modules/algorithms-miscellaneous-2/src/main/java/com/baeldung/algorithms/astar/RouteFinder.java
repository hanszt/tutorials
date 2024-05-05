package com.baeldung.algorithms.astar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;


public final class RouteFinder<T extends GraphNode> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteFinder.class);

    private final Graph<T> graph;
    private final Scorer<T> nextNodeScorer;
    private final Scorer<T> targetScorer;

    public RouteFinder(Graph<T> graph, Scorer<T> nextNodeScorer, Scorer<T> targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<T> findRoute(T from, T to) {
        Map<T, RouteNode<T>> allNodes = new HashMap<>();
        Queue<RouteNode<T>> openSet = new PriorityQueue<>();

        var start = new RouteNode<T>(from, null, 0d, targetScorer.computeCost(from, to));
        allNodes.put(from, start);
        openSet.add(start);

        while (!openSet.isEmpty()) {
            LOGGER.debug("Open Set contains: {}", openSet.stream().map(RouteNode::getCurrent).collect(Collectors.toSet()));
            var next = openSet.poll();
            LOGGER.debug("Looking at node: {}", next);
            if (next.getCurrent().equals(to)) {
                LOGGER.debug("Found our destination!");

                List<T> route = new ArrayList<>();
                var current = next;
                do {
                    route.addFirst(current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);

                LOGGER.debug("Route: {}", route);
                return route;
            }

            graph.getConnections(next.getCurrent()).forEach(connection -> {
                var newScore = next.getRouteScore() + nextNodeScorer.computeCost(next.getCurrent(), connection);
                var nextNode = allNodes.getOrDefault(connection, new RouteNode<>(connection));
                allNodes.put(connection, nextNode);

                if (nextNode.getRouteScore() > newScore) {
                    nextNode.setPrevious(next.getCurrent());
                    nextNode.setRouteScore(newScore);
                    nextNode.setEstimatedScore(newScore + targetScorer.computeCost(connection, to));
                    openSet.add(nextNode);
                    LOGGER.debug("Found a better route to node: " + nextNode);
                }
            });
        }

        throw new IllegalStateException("No route found");
    }

}

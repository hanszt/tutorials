package com.baeldung.algorithms.prim;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PrimUnitTest {

    @Test
     void givenAGraph_whenPrimRuns_thenPrintMST() {
        var prim = new Prim(createGraph());
        System.out.println(prim.originalGraphToString());
        System.out.println("----------------");
        prim.run();
        System.out.println();
        prim.resetPrintHistory();
        System.out.println(prim.minimumSpanningTreeToString());
    }

    public static List<Vertex> createGraph() {
        List<Vertex> graph = new ArrayList<>();
        var a = new Vertex("A");
        var b = new Vertex("B");
        var c = new Vertex("C");
        var d = new Vertex("D");
        var e = new Vertex("E");
        var ab = new Edge(2);
        a.addEdge(b, ab);
        b.addEdge(a, ab);
        var ac = new Edge(3);
        a.addEdge(c, ac);
        c.addEdge(a, ac);
        var bc = new Edge(2);
        b.addEdge(c, bc);
        c.addEdge(b, bc);
        var be = new Edge(5);
        b.addEdge(e, be);
        e.addEdge(b, be);
        var cd = new Edge(1);
        c.addEdge(d, cd);
        d.addEdge(c, cd);
        var ce = new Edge(1);
        c.addEdge(e, ce);
        e.addEdge(c, ce);
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        return graph;
    }

}

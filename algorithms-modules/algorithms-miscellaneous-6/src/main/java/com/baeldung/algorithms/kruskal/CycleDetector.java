package com.baeldung.algorithms.kruskal;

import java.util.ArrayList;
import java.util.List;

public final class CycleDetector {

    List<DisjointSetInfo> nodes;

    public CycleDetector(int totalNodes) {
        initDisjointSets(totalNodes);
    }

    public boolean detectCycle(Integer u, Integer v) {
        var rootU = pathCompressionFind(u);
        var rootV = pathCompressionFind(v);
        if (rootU.equals(rootV)) {
            return true;
        }
        unionByRank(rootU, rootV);
        return false;
    }

    private void initDisjointSets(int totalNodes) {
        nodes = new ArrayList<>(totalNodes);
        for (var i = 0; i < totalNodes; i++) {
            nodes.add(new DisjointSetInfo(i));
        }
    }

    private Integer find(Integer node) {
        var parent = nodes.get(node).getParentNode();
        if (parent.equals(node)) {
            return node;
        } else {
            return find(parent);
        }
    }

    private Integer pathCompressionFind(Integer node) {
        var setInfo = nodes.get(node);
        var parent = setInfo.getParentNode();
        if (parent.equals(node)) {
            return node;
        } else {
            var parentNode = find(parent);
            setInfo.setParentNode(parentNode);
            return parentNode;
        }
    }

    private void union(Integer rootU, Integer rootV) {
        var setInfoU = nodes.get(rootU);
        setInfoU.setParentNode(rootV);
    }

    private void unionByRank(int rootU, int rootV) {
        var setInfoU = nodes.get(rootU);
        var setInfoV = nodes.get(rootV);
        var rankU = setInfoU.getRank();
        var rankV = setInfoV.getRank();
        if (rankU < rankV) {
            setInfoU.setParentNode(rootV);
        } else {
            setInfoV.setParentNode(rootU);
            if (rankU == rankV) {
                setInfoU.setRank(rankU + 1);
            }
        }
    }

}

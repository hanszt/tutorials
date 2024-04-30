package com.baeldung.algorithms.minimax;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int noOfBones;
    private boolean isMaxPlayer;
    private int score;
    private List<Node> children;

    public Node(final int noOfBones, final boolean isMaxPlayer) {
        this.noOfBones = noOfBones;
        this.isMaxPlayer = isMaxPlayer;
        children = new ArrayList<>();
    }

    int getNoOfBones() {
        return noOfBones;
    }

    boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    int getScore() {
        return score;
    }

    void setScore(final int score) {
        this.score = score;
    }

    List<Node> getChildren() {
        return children;
    }

    void addChild(final Node newNode) {
        children.add(newNode);
    }

}

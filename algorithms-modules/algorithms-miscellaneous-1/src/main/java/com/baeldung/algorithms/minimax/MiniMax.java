package com.baeldung.algorithms.minimax;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public final class MiniMax {

    private static final Comparator<Node> BY_SCORE_COMPARATOR = Comparator.comparing(Node::getScore);
    private final Tree tree;

    private MiniMax(final int noOfBones) {
        tree = new Tree();
        final var root = new Node(noOfBones, true);
        tree.setRoot(root);
        constructTree(root);
    }

    public static MiniMax constructTree(final int noOfBones) {
        return new MiniMax(noOfBones);
    }

    public Tree getTree() {
        return tree;
    }

    private void constructTree(final Node parentNode) {
        final var listOfPossibleHeaps = GameOfBones.getPossibleStates(parentNode.getNoOfBones());
        final var isChildMaxPlayer = !parentNode.isMaxPlayer();
        for (int n : listOfPossibleHeaps) {
            final var newNode = new Node(n, isChildMaxPlayer);
            parentNode.addChild(newNode);
            if (newNode.getNoOfBones() > 0) {
                constructTree(newNode);
            }
        }
    }

    public boolean checkWin() {
        final var root = tree.getRoot();
        checkWin(root);
        return root.getScore() == 1;
    }

    private void checkWin(final Node node) {
        final var children = node.getChildren();
        final var isMaxPlayer = node.isMaxPlayer();
        for (Node child : children) {
            if (child.getNoOfBones() == 0) {
                child.setScore(isMaxPlayer ? 1 : -1);
            } else {
                checkWin(child);
            }
        }
        final var bestChild = findBestChild(isMaxPlayer, children);
        node.setScore(bestChild.getScore());
    }

    private Node findBestChild(final boolean isMaxPlayer, final List<Node> children) {
        return children.stream()
          .max(isMaxPlayer ? BY_SCORE_COMPARATOR : BY_SCORE_COMPARATOR.reversed())
          .orElseThrow(NoSuchElementException::new);
    }
}

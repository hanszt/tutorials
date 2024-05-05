package com.baeldung.algorithms.reversingtree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TreeReverserUnitTest {

    @Test
    void givenTreeWhenReversingRecursivelyThenReversed() {
        var reverser = new TreeReverser();

        var treeNode = createBinaryTree();

        reverser.reverseRecursive(treeNode);

        assertEquals("4 7 9 6 2 3 1", reverser.toString(treeNode)
            .trim());
    }

    @Test
    void givenTreeWhenReversingIterativelyThenReversed() {
        var reverser = new TreeReverser();

        var treeNode = createBinaryTree();

        reverser.reverseIterative(treeNode);

        assertEquals("4 7 9 6 2 3 1", reverser.toString(treeNode)
            .trim());
    }

    private TreeNode createBinaryTree() {

        var leaf1 = new TreeNode(1);
        var leaf2 = new TreeNode(3);
        var leaf3 = new TreeNode(6);
        var leaf4 = new TreeNode(9);

        var nodeRight = new TreeNode(7, leaf3, leaf4);
        var nodeLeft = new TreeNode(2, leaf1, leaf2);

        var root = new TreeNode(4, nodeLeft, nodeRight);

        return root;
    }
}

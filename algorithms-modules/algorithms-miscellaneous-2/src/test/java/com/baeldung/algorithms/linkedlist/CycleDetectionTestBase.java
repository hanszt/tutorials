package com.baeldung.algorithms.linkedlist;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

public class CycleDetectionTestBase {


    public static Collection<Object[]> getLists() {
        return Arrays.asList(new Object[][] { 
            { createList(), false }, 
            { createListWithLoop(), true },
            { createListWithFullCycle(), true }, 
            { createListWithSingleNodeInCycle(), true } 
        });
    }

    public static Node<Integer> createList() {
        var root = Node.createNewNode(10, null);

        for (var i = 9; i >= 1; --i) {
            var current = Node.createNewNode(i, root);
            root = current;
        }

        return root;
    }

    public static Node<Integer> createListWithLoop() {
        var node = createList();
        createLoop(node);
        return node;
    }

    public static Node<Integer> createListWithFullCycle() {
        var head = createList();
        var tail = Node.getTail(head);
        tail.next = head;
        return head;
    }

    public static Node<Integer> createListWithSingleNodeInCycle() {
        var head = createList();
        var tail = Node.getTail(head);
        tail.next = tail;
        return head;
    }

    public static void createLoop(Node<Integer> root) {
        var tail = Node.getTail(root);

        var middle = root;
        for (var i = 1; i <= 4; i++) {
            middle = middle.next;
        }

        tail.next = middle;
    }

}

package com.baeldung.algorithms.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class LinkedListReversalUnitTest {
    @Test
    void givenLinkedList_whenIterativeReverse_thenOutputCorrectResult() {
        var head = constructLinkedList();
        var node = head;
        for (var i = 1; i <= 5; i++) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
        var reversal = new LinkedListReversal();
        node = reversal.reverseList(head);
        for (var i = 5; i >= 1; i--) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
    }

    @Test
    void givenLinkedList_whenRecursiveReverse_thenOutputCorrectResult() {
        var head = constructLinkedList();
        var node = head;
        for (var i = 1; i <= 5; i++) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
        var reversal = new LinkedListReversal();
        node = reversal.reverseListRecursive(head);
        for (var i = 5; i >= 1; i--) {
            assertNotNull(node);
            assertEquals(i, node.getData());
            node = node.getNext();
        }
    }

    private ListNode constructLinkedList() {
        ListNode head = null;
        ListNode tail = null;
        for (var i = 1; i <= 5; i++) {
            var node = new ListNode(i);
            if (head == null) {
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
        }
        return head;
    }
}

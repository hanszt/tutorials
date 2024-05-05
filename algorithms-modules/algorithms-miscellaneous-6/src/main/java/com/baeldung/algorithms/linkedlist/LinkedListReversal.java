package com.baeldung.algorithms.linkedlist;

public class LinkedListReversal {

    ListNode reverseList(ListNode head) {
        ListNode previous = null;
        var current = head;
        while (current != null) {
            var nextElement = current.getNext();
            current.setNext(previous);
            previous = current;
            current = nextElement;
        }
        return previous;
    }

    ListNode reverseListRecursive(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.getNext() == null) {
            return head;
        }
        var node = reverseListRecursive(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return node;
    }

}

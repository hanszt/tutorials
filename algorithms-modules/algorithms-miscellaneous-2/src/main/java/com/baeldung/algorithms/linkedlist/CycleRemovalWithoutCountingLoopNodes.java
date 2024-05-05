package com.baeldung.algorithms.linkedlist;

public class CycleRemovalWithoutCountingLoopNodes {

    public static <T> boolean detectAndRemoveCycle(Node<T> head) {
        var result = CycleDetectionByFastAndSlowIterators.detectCycle(head);

        if (result.cycleExists) {
            removeCycle(result.node, head);
        }

        return result.cycleExists;
    }

    private static <T> void removeCycle(Node<T> meetingPointParam, Node<T> head) {
        var loopNode = meetingPointParam;
        var it = head;

        while (loopNode.next != it.next) {
            it = it.next;
            loopNode = loopNode.next;
        }

        loopNode.next = null;
    }

}

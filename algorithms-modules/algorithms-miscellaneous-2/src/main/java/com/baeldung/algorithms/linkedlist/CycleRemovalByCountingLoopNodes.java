package com.baeldung.algorithms.linkedlist;

public class CycleRemovalByCountingLoopNodes {

    public static <T> boolean detectAndRemoveCycle(Node<T> head) {
        var result = CycleDetectionByFastAndSlowIterators.detectCycle(head);

        if (result.cycleExists) {
            removeCycle(result.node, head);
        }

        return result.cycleExists;
    }

    private static <T> void removeCycle(Node<T> loopNodeParam, Node<T> head) {
        var cycleLength = calculateCycleLength(loopNodeParam);
        var cycleLengthAdvancedIterator = head;
        var it = head;

        for (var i = 0; i < cycleLength; i++) {
            cycleLengthAdvancedIterator = cycleLengthAdvancedIterator.next;
        }

        while (it.next != cycleLengthAdvancedIterator.next) {
            it = it.next;
            cycleLengthAdvancedIterator = cycleLengthAdvancedIterator.next;
        }

        cycleLengthAdvancedIterator.next = null;
    }

    private static <T> int calculateCycleLength(Node<T> loopNodeParam) {
        var loopNode = loopNodeParam;
        var length = 1;

        while (loopNode.next != loopNodeParam) {
            length++;
            loopNode = loopNode.next;
        }

        return length;
    }

}

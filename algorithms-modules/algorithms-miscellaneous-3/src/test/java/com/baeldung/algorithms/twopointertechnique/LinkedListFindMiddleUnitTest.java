package com.baeldung.algorithms.twopointertechnique;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class LinkedListFindMiddleUnitTest {

    LinkedListFindMiddle linkedListFindMiddle = new LinkedListFindMiddle();

    @Test
    void givenLinkedListOfMyNodes_whenLinkedListFindMiddle_thenCorrect() {

        var head = createNodesList(8);

        assertThat(linkedListFindMiddle.findMiddle(head)).isEqualTo("4");

        head = createNodesList(9);

        assertThat(linkedListFindMiddle.findMiddle(head)).isEqualTo("5");
    }

    private static MyNode<String> createNodesList(int n) {

        var head = new MyNode<String>("1");
        var current = head;

        for (var i = 2; i <= n; i++) {
            var newNode = new MyNode<String>(String.valueOf(i));
            current.setNext(newNode);
            current = newNode;
        }

        return head;
    }

}

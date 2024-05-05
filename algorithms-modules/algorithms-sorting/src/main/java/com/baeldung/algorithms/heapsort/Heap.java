package com.baeldung.algorithms.heapsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap<E extends Comparable<E>> {

    private List<E> elements = new ArrayList<>();

    public static <E extends Comparable<E>> List<E> sort(Iterable<E> elements) {
        var heap = of(elements);

        List<E> result = new ArrayList<>();

        while (!heap.isEmpty()) {
            result.add(heap.pop());
        }

        return result;
    }

    public static <E extends Comparable<E>> Heap<E> of(E... elements) {
        return of(Arrays.asList(elements));
    }

    public static <E extends Comparable<E>> Heap<E> of(Iterable<E> elements) {
        var result = new Heap<E>();
        for (var element : elements) {
            result.add(element);
        }
        return result;
    }

    public void add(E e) {
        elements.add(e);
        var elementIndex = elements.size() - 1;
        while (!isRoot(elementIndex) && !isCorrectChild(elementIndex)) {
            var parentIndex = parentIndex(elementIndex);
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
        }
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("You cannot pop from an empty heap");
        }

        var result = elementAt(0);

        var lasElementIndex = elements.size() - 1;
        swap(0, lasElementIndex);
        elements.remove(lasElementIndex);

        var elementIndex = 0;
        while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
            var smallerChildIndex = smallerChildIndex(elementIndex);
            swap(elementIndex, smallerChildIndex);
            elementIndex = smallerChildIndex;
        }

        return result;
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
    
    private boolean isRoot(int index) {
        return index == 0;
    }

    private int smallerChildIndex(int index) {
        var leftChildIndex = leftChildIndex(index);
        var rightChildIndex = rightChildIndex(index);
        
        if (!isValidIndex(rightChildIndex)) {
            return leftChildIndex;
        }

        if (elementAt(leftChildIndex).compareTo(elementAt(rightChildIndex)) < 0) {
            return leftChildIndex;
        }

        return rightChildIndex;
    }

    private boolean isLeaf(int index) {
        return !isValidIndex(leftChildIndex(index));
    }

    private boolean isCorrectParent(int index) {
        return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
    }

    private boolean isCorrectChild(int index) {
        return isCorrect(parentIndex(index), index);
    }

    private boolean isCorrect(int parentIndex, int childIndex) {
        if (!isValidIndex(parentIndex) || !isValidIndex(childIndex)) {
            return true;
        }

        return elementAt(parentIndex).compareTo(elementAt(childIndex)) < 0;
    }

    private boolean isValidIndex(int index) {
        return index < elements.size();
    }

    private void swap(int index1, int index2) {
        var element1 = elementAt(index1);
        var element2 = elementAt(index2);
        elements.set(index1, element2);
        elements.set(index2, element1);
    }

    private E elementAt(int index) {
        return elements.get(index);
    }

    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    private int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int rightChildIndex(int index) {
        return 2 * index + 2;
    }

}

package com.baeldung.algorithms.minheapmerge;

public final class MinHeap {

    HeapNode[] heapNodes;

    public MinHeap(HeapNode[] heapNodes) {
        this.heapNodes = heapNodes;
        heapifyFromLastLeafsParent();
    }

    void heapifyFromLastLeafsParent() {
        var lastLeafsParentIndex = getParentNodeIndex(heapNodes.length);
        while (lastLeafsParentIndex >= 0) {
            heapify(lastLeafsParentIndex);
            lastLeafsParentIndex--;
        }
    }

    void heapify(int index) {
        var leftNodeIndex = getLeftNodeIndex(index);
        var rightNodeIndex = getRightNodeIndex(index);
        var smallestElementIndex = index;
        if (leftNodeIndex < heapNodes.length && heapNodes[leftNodeIndex].element < heapNodes[index].element) {
            smallestElementIndex = leftNodeIndex;
        }
        if (rightNodeIndex < heapNodes.length && heapNodes[rightNodeIndex].element < heapNodes[smallestElementIndex].element) {
            smallestElementIndex = rightNodeIndex;
        }
        if (smallestElementIndex != index) {
            swap(index, smallestElementIndex);
            heapify(smallestElementIndex);
        }
    }

    int getParentNodeIndex(int index) {
        return (index - 1) / 2;
    }

    int getLeftNodeIndex(int index) {
        return (2 * index + 1);
    }

    int getRightNodeIndex(int index) {
        return (2 * index + 2);
    }

    HeapNode getRootNode() {
        return heapNodes[0];
    }

    void heapifyFromRoot() {
        heapify(0);
    }

    void swap(int i, int j) {
        var temp = heapNodes[i];
        heapNodes[i] = heapNodes[j];
        heapNodes[j] = temp;
    }
    
    static int[] merge(int[][] array) {
        var heapNodes = new HeapNode[array.length];
        var resultingArraySize = 0;

        for (var i = 0; i < array.length; i++) {
            var node = new HeapNode(array[i][0], i);
            heapNodes[i] = node;
            resultingArraySize += array[i].length;
        }

        var minHeap = new MinHeap(heapNodes);
        var resultingArray = new int[resultingArraySize];

        for (var i = 0; i < resultingArraySize; i++) {
            var root = minHeap.getRootNode();
            resultingArray[i] = root.element;

            if (root.nextElementIndex < array[root.arrayIndex].length) {
                root.element = array[root.arrayIndex][root.nextElementIndex++];
            } else {
                root.element = Integer.MAX_VALUE;
            }
            minHeap.heapifyFromRoot();
        }
        return resultingArray;
    }
}

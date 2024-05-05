package com.baeldung.algorithms.binarysearch;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchUnitTest {

    int[] sortedArray = { 0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9 };
    int key = 6;
    int expectedIndexForSearchKey = 7;
    int low = 0;
    int high = sortedArray.length - 1;
    List<Integer> sortedList = List.of(0, 1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 9);
    
    @Test
    void givenASortedArrayOfIntegers_whenBinarySearchRunIterativelyForANumber_thenGetIndexOfTheNumber() {
        var binSearch = new BinarySearch();
        assertEquals(expectedIndexForSearchKey, binSearch.runBinarySearchIteratively(sortedArray, key, low, high));
    }

    @Test
    void givenASortedArrayOfIntegers_whenBinarySearchRunRecursivelyForANumber_thenGetIndexOfTheNumber() {
        var binSearch = new BinarySearch();
        assertEquals(expectedIndexForSearchKey, binSearch.runBinarySearchRecursively(sortedArray, key, low, high));
    }
    
    @Test
    void givenASortedArrayOfIntegers_whenBinarySearchRunUsingArraysClassStaticMethodForANumber_thenGetIndexOfTheNumber() {
        var binSearch = new BinarySearch();
        assertEquals(expectedIndexForSearchKey, binSearch.runBinarySearchUsingJavaArrays(sortedArray, key));
    }
    
    @Test
    void givenASortedListOfIntegers_whenBinarySearchRunUsingCollectionsClassStaticMethodForANumber_thenGetIndexOfTheNumber() {
        var binSearch = new BinarySearch();
        assertEquals(expectedIndexForSearchKey, binSearch.runBinarySearchUsingJavaCollections(sortedList, key));
    }
    
}

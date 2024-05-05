package com.baeldung.algorithms.kthsmallest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import java.util.random.RandomGenerator;

import static com.baeldung.algorithms.kthsmallest.KthSmallest.*;
import static org.junit.jupiter.api.Assertions.*;

class KthSmallestUnitTest {

    @Nested
    class Exceptions {

        @Test
        void when_at_least_one_list_is_null_then_an_exception_is_thrown() {

            Executable executable1 = () -> findKthSmallestElement(1, null, null);
            Executable executable2 = () -> findKthSmallestElement(1, new int[]{2}, null);
            Executable executable3 = () -> findKthSmallestElement(1, null, new int[]{2});

            assertThrows(IllegalArgumentException.class, executable1);
            assertThrows(IllegalArgumentException.class, executable2);
            assertThrows(IllegalArgumentException.class, executable3);
        }

        @Test
        void when_at_least_one_list_is_empty_then_an_exception_is_thrown() {

            Executable executable1 = () -> findKthSmallestElement(1, new int[]{}, new int[]{2});
            Executable executable2 = () -> findKthSmallestElement(1, new int[]{2}, new int[]{});
            Executable executable3 = () -> findKthSmallestElement(1, new int[]{}, new int[]{});

            assertThrows(IllegalArgumentException.class, executable1);
            assertThrows(IllegalArgumentException.class, executable2);
            assertThrows(IllegalArgumentException.class, executable3);
        }

        @Test
        void when_k_is_smaller_than_0_then_an_exception_is_thrown() {
            Executable executable1 = () -> findKthSmallestElement(-1, new int[]{2}, new int[]{2});
            assertThrows(IllegalArgumentException.class, executable1);
        }

        @Test
        void when_k_is_smaller_than_1_then_an_exception_is_thrown() {
            Executable executable1 = () -> findKthSmallestElement(0, new int[]{2}, new int[]{2});
            assertThrows(IllegalArgumentException.class, executable1);
        }

        @Test
        void when_k_bigger_then_the_two_lists_then_an_exception_is_thrown() {
            Executable executable1 = () -> findKthSmallestElement(6, new int[]{1, 5, 6}, new int[]{2, 5});
            assertThrows(NoSuchElementException.class, executable1);
        }

    }

    @Nested
    class K_is_smaller_than_the_size_of_list1_and_the_size_of_list2 {

        @Test
        public void when_k_is_1_then_the_smallest_element_is_returned_from_list1() {
            var result = findKthSmallestElement(1, new int[]{2, 7}, new int[]{3, 5});
            assertEquals(2, result);
        }

        @Test
        public void when_k_is_1_then_the_smallest_element_is_returned_list2() {
            var result = findKthSmallestElement(1, new int[]{3, 5}, new int[]{2, 7});
            assertEquals(2, result);
        }

        @Test
        public void when_kth_element_is_smallest_element_and_occurs_in_both_lists() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(1, list1, list2);
            assertEquals(1, result);
        }

        @Test
        public void when_kth_element_is_smallest_element_and_occurs_in_both_lists2() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(2, list1, list2);
            assertEquals(1, result);
        }

        @Test
        public void when_kth_element_is_largest_element_and_occurs_in_both_lists_1() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(5, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void when_kth_element_is_largest_element_and_occurs_in_both_lists_2() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(6, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void when_kth_element_and_occurs_in_both_lists() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{0, 2, 3};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(2, result);
        }

        @Test
        public void and_kth_element_is_in_first_list() {
            var list1 = new int[]{1, 2, 3, 4};
            var list2 = new int[]{1, 3, 4, 5};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(2, result);
        }

        @Test
        public void and_kth_is_in_second_list() {
            var list1 = new int[]{1, 3, 4, 4};
            var list2 = new int[]{1, 2, 4, 5};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(2, result);
        }

        @Test
        public void and_elements_in_first_list_are_all_smaller_than_second_list() {
            var list1 = new int[]{1, 3, 7, 9};
            var list2 = new int[]{11, 12, 14, 15};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(7, result);
        }

        @Test
        public void and_elements_in_first_list_are_all_smaller_than_second_list2() {
            var list1 = new int[]{1, 3, 7, 9};
            var list2 = new int[]{11, 12, 14, 15};
            var result = findKthSmallestElement(4, list1, list2);
            assertEquals(9, result);
        }

        @Test
        public void and_only_elements_from_second_list_are_part_of_result() {
            var list1 = new int[]{11, 12, 14, 15};
            var list2 = new int[]{1, 3, 7, 9};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(7, result);
        }

        @Test
        public void and_only_elements_from_second_list_are_part_of_result2() {
            var list1 = new int[]{11, 12, 14, 15};
            var list2 = new int[]{1, 3, 7, 9};
            var result = findKthSmallestElement(4, list1, list2);
            assertEquals(9, result);
        }
    }

    @Nested
    class K_is_bigger_than_the_size_of_at_least_one_of_the_lists {

        @Test
        public void k_is_smaller_than_list1_and_bigger_than_list2() {
            var list1 = new int[]{1, 2, 3, 4, 7, 9};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(5, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void k_is_bigger_than_list1_and_smaller_than_list2() {
            var list1 = new int[]{1, 2, 3};
            var list2 = new int[]{1, 2, 3, 4, 7, 9};
            var result = findKthSmallestElement(5, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void when_k_is_bigger_than_the_size_of_both_lists_and_elements_in_second_list_are_all_smaller_than_first_list() {
            var list1 = new int[]{9, 11, 13, 55};
            var list2 = new int[]{1, 2, 3, 7};
            var result = findKthSmallestElement(6, list1, list2);
            assertEquals(11, result);
        }

        @Test
        public void when_k_is_bigger_than_the_size_of_both_lists_and_elements_in_second_list_are_all_bigger_than_first_list() {
            var list1 = new int[]{1, 2, 3, 7};
            var list2 = new int[]{9, 11, 13, 55};
            var result = findKthSmallestElement(6, list1, list2);
            assertEquals(11, result);
        }

        @Test
        public void when_k_is_bigger_than_the_size_of_both_lists() {
            var list1 = new int[]{3, 7, 9, 11, 55};
            var list2 = new int[]{1, 2, 3, 7, 13};
            var result = findKthSmallestElement(7, list1, list2);
            assertEquals(9, result);
        }

        @Test
        public void when_k_is_bigger_than_the_size_of_both_lists_and_list1_has_more_elements_than_list2() {
            var list1 = new int[]{3, 7, 9, 11, 55, 77, 100, 200};
            var list2 = new int[]{1, 2, 3, 7, 13};
            var result = findKthSmallestElement(11, list1, list2);
            assertEquals(77, result);
        }

        @Test
        public void max_test() {
            var list1 = new int[]{100, 200};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(4, list1, list2);
            assertEquals(100, result);
        }

        @Test
        public void max_test2() {
            var list1 = new int[]{100, 200};
            var list2 = new int[]{1, 2, 3};
            var result = findKthSmallestElement(5, list1, list2);
            assertEquals(200, result);
        }

        @Test
        public void when_k_is_smaller_than_the_size_of_both_lists_and_kth_element_in_list2() {
            var list1 = new int[]{1, 2, 5};
            var list2 = new int[]{1, 3, 4, 7};
            var result = findKthSmallestElement(4, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void when_k_is_smaller_than_the_size_of_both_lists_and_kth_element_is_smallest_in_list2() {
            var list1 = new int[]{1, 2, 5};
            var list2 = new int[]{3, 4, 7};
            var result = findKthSmallestElement(3, list1, list2);
            assertEquals(3, result);
        }

        @Test
        public void when_k_is_smaller_than_the_size_of_both_lists_and_kth_element_is_smallest_in_list23() {
            var list1 = new int[]{3, 11, 27, 53, 90};
            var list2 = new int[]{4, 20, 21, 100};
            var result = findKthSmallestElement(5, list1, list2);
            assertEquals(21, result);
        }
    }


    @Test
    void random() {

        var random = new Random(0);
        var length1 = (Math.abs(random.nextInt())) % 1000 + 1;
        var length2 = (Math.abs(random.nextInt())) % 1000 + 1;

        var list1 = sortedRandomIntArrayOfLength(length1, random);
        var list2 = sortedRandomIntArrayOfLength(length2, random);

        var k = (Math.abs(random.nextInt()) % (length1 + length2)) + 1;

        var result = findKthSmallestElement(k, list1, list2);

        var result2 = getKthElementSorted(list1, list2, k);

        var result3 = getKthElementMerge(list1, list2, k);

        assertEquals(result2, result);
        assertEquals(result2, result3);
    }

    private int[] sortedRandomIntArrayOfLength(int length, RandomGenerator random) {
        return random.ints(length)
                .sorted()
                .toArray();
    }
}
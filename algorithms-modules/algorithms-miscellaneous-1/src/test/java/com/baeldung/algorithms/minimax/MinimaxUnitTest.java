package com.baeldung.algorithms.minimax;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimaxUnitTest {

    @Test
    void givenMiniMax_whenConstructTree_thenNotNullTree() {
        final var miniMax = MiniMax.constructTree(6);
        final var gameTree = miniMax.getTree();
        assertNotNull(gameTree);
    }

    @Test
    void givenMiniMax_whenCheckWin_thenComputeOptimal() {
        final var miniMax1 = MiniMax.constructTree(6);
        final var result1 = miniMax1.checkWin();
        assertTrue(result1);
        final var miniMax2 = MiniMax.constructTree(8);
        final var result2 = miniMax2.checkWin();
        assertFalse(result2);
    }
}

package com.baeldung.algorithms.mcts;


import com.baeldung.algorithms.mcts.montecarlo.MonteCarloTreeSearch;
import com.baeldung.algorithms.mcts.montecarlo.State;
import com.baeldung.algorithms.mcts.montecarlo.UCT;
import com.baeldung.algorithms.mcts.tictactoe.Board;
import com.baeldung.algorithms.mcts.tictactoe.Position;
import com.baeldung.algorithms.mcts.tree.Tree;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.InstantSource;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class MCTSUnitTest {

    private static final int LEVEL = 3;
    private final Random random = new Random(0);

    private final Instant ref = Instant.EPOCH;
    private final long refNanos = System.nanoTime();
    private final InstantSource instantSource = () -> ref.plusNanos(System.nanoTime() - refNanos);

    private final Tree gameTree = new Tree();
    private final MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(LEVEL, random, instantSource);

    @Test
    void givenStats_whenGetUCTForNode_thenUCTMatchesWithManualData() {
        double uctValue = 15.79;
        assertEquals(UCT.uctValue(600, 300, 20), uctValue, 0.01);
    }

    @Test
    void givenInitBoardState_whenGetAllPossibleStates_thenNonEmptyList() {
        State initState = gameTree.getRoot().getState();
        List<State> possibleStates = initState.getAllPossibleStates();
        assertEquals(9, possibleStates.size());
    }

    @Test
    void givenInitBoardState_print_board_after_play() {
        State initState = gameTree.getRoot().getState();
        List<State> possibleStates = initState.getAllPossibleStates();

        assertFalse(possibleStates.isEmpty());
    }


    @Test
    void givenEmptyBoard_whenPerformMove_thenLessAvailablePositions() {
        Board board = new Board();
        int initAvailablePositions = board.getEmptyPositions().size();
        board.performMove(Board.P1, new Position(1, 1));
        int availablePositions = board.getEmptyPositions().size();
        assertTrue(initAvailablePositions > availablePositions);
    }

    @Test
    void givenEmptyBoard_whenSimulateInterAIPlay_thenGameDraw() {
        Board board = new Board();

        int player = Board.P1;
        int totalMoves = Board.DEFAULT_BOARD_SIZE * Board.DEFAULT_BOARD_SIZE;
        for (int i = 0; i < totalMoves; i++) {
            board = mcts.findNextMove(board, player);
            if (board.checkStatus() != -1) {
                break;
            }
            player = LEVEL - player;
        }
        int winStatus = board.checkStatus();
        assertEquals(Board.DRAW, winStatus);
    }

    @Test
    void givenEmptyBoard_whenLevel1VsLevel3_thenLevel3WinsOrDraw() {
        Board board = new Board();
        MonteCarloTreeSearch mcts1 = new MonteCarloTreeSearch(1, random, instantSource);
        MonteCarloTreeSearch mcts3 = new MonteCarloTreeSearch(3, random, instantSource);

        int player = Board.P1;
        int totalMoves = Board.DEFAULT_BOARD_SIZE * Board.DEFAULT_BOARD_SIZE;
        for (int i = 0; i < totalMoves; i++) {
            if (player == Board.P1) {
                board = mcts3.findNextMove(board, player);
            } else {
                board = mcts1.findNextMove(board, player);
            }

            if (board.checkStatus() != -1) {
                break;
            }
            player = LEVEL - player;
        }
        int winStatus = board.checkStatus();
        assertTrue(winStatus == Board.DRAW || winStatus == Board.P1);
    }

}

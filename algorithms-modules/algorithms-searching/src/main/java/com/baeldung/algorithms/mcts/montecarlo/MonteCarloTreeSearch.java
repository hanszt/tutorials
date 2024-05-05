package com.baeldung.algorithms.mcts.montecarlo;

import java.time.InstantSource;
import java.util.List;
import java.util.random.RandomGenerator;

import com.baeldung.algorithms.mcts.tictactoe.Board;
import com.baeldung.algorithms.mcts.tree.Node;
import com.baeldung.algorithms.mcts.tree.Tree;

public final class MonteCarloTreeSearch {

    private static final int WIN_SCORE = 10;
    private final int level;
    private int opponent;

    private final RandomGenerator random;
    private final InstantSource instantSource;

    public MonteCarloTreeSearch(int level, RandomGenerator random, InstantSource instantSource) {
        this.random = random;
        this.instantSource = instantSource;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    private int getMillisForCurrentLevel() {
        return 2 * (this.level - 1) + 1;
    }

    public Board findNextMove(Board board, int playerNo) {
        var start = instantSource.millis();
        var end = start + 60L * getMillisForCurrentLevel();

        opponent = 3 - playerNo;
        var tree = new Tree();
        var rootNode = tree.getRoot();
        rootNode.getState().setBoard(board);
        rootNode.getState().setPlayerNo(opponent);

        while (instantSource.millis() < end) {
            // Phase 1 - Selection
            var promisingNode = selectPromisingNode(rootNode);
            // Phase 2 - Expansion
            if (promisingNode.getState().getBoard().checkStatus() == Board.IN_PROGRESS) {
                expandNode(promisingNode);
            }

            // Phase 3 - Simulation
            var nodeToExplore = promisingNode;
            if (!promisingNode.getChildArray().isEmpty()) {
                nodeToExplore = promisingNode.getRandomChildNode(random);
            }
            var playoutResult = simulateRandomPlayOut(nodeToExplore);
            // Phase 4 - Update
            backPropagation(nodeToExplore, playoutResult);
        }

        var winnerNode = rootNode.getChildWithMaxScore();
        tree.setRoot(winnerNode);
        return winnerNode.getState().getBoard();
    }

    private Node selectPromisingNode(Node rootNode) {
        var node = rootNode;
        while (!node.getChildArray().isEmpty()) {
            node = UCT.findBestNodeWithUCT(node);
        }
        return node;
    }

    private void expandNode(Node node) {
        var possibleStates = node.getState().getAllPossibleStates();
        possibleStates.forEach(state -> {
            var newNode = new Node(state);
            newNode.setParent(node);
            newNode.getState().setPlayerNo(node.getState().getOpponent());
            node.getChildArray().add(newNode);
        });
    }

    private void backPropagation(Node nodeToExplore, int playerNo) {
        var tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.getState().incrementVisit();
            if (tempNode.getState().getPlayerNo() == playerNo) {
                tempNode.getState().addScore(WIN_SCORE);
            }
            tempNode = tempNode.getParent();
        }
    }

    private int simulateRandomPlayOut(Node node) {
        var tempNode = new Node(node);
        var tempState = tempNode.getState();
        var boardStatus = tempState.getBoard().checkStatus();

        if (boardStatus == opponent) {
            tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
            return boardStatus;
        }
        while (boardStatus == Board.IN_PROGRESS) {
            tempState.togglePlayer();
            tempState.randomPlay(random);
            boardStatus = tempState.getBoard().checkStatus();
        }

        return boardStatus;
    }

}

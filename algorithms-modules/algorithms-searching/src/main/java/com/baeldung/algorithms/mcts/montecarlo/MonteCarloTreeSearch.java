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
        long start = instantSource.millis();
        long end = start + 60L * getMillisForCurrentLevel();

        opponent = 3 - playerNo;
        Tree tree = new Tree();
        Node rootNode = tree.getRoot();
        rootNode.getState().setBoard(board);
        rootNode.getState().setPlayerNo(opponent);

        while (instantSource.millis() < end) {
            // Phase 1 - Selection
            Node promisingNode = selectPromisingNode(rootNode);
            // Phase 2 - Expansion
            if (promisingNode.getState().getBoard().checkStatus() == Board.IN_PROGRESS) {
                expandNode(promisingNode);
            }

            // Phase 3 - Simulation
            Node nodeToExplore = promisingNode;
            if (!promisingNode.getChildArray().isEmpty()) {
                nodeToExplore = promisingNode.getRandomChildNode(random);
            }
            int playoutResult = simulateRandomPlayOut(nodeToExplore);
            // Phase 4 - Update
            backPropagation(nodeToExplore, playoutResult);
        }

        Node winnerNode = rootNode.getChildWithMaxScore();
        tree.setRoot(winnerNode);
        return winnerNode.getState().getBoard();
    }

    private Node selectPromisingNode(Node rootNode) {
        Node node = rootNode;
        while (!node.getChildArray().isEmpty()) {
            node = UCT.findBestNodeWithUCT(node);
        }
        return node;
    }

    private void expandNode(Node node) {
        List<State> possibleStates = node.getState().getAllPossibleStates();
        possibleStates.forEach(state -> {
            Node newNode = new Node(state);
            newNode.setParent(node);
            newNode.getState().setPlayerNo(node.getState().getOpponent());
            node.getChildArray().add(newNode);
        });
    }

    private void backPropagation(Node nodeToExplore, int playerNo) {
        Node tempNode = nodeToExplore;
        while (tempNode != null) {
            tempNode.getState().incrementVisit();
            if (tempNode.getState().getPlayerNo() == playerNo) {
                tempNode.getState().addScore(WIN_SCORE);
            }
            tempNode = tempNode.getParent();
        }
    }

    private int simulateRandomPlayOut(Node node) {
        Node tempNode = new Node(node);
        State tempState = tempNode.getState();
        int boardStatus = tempState.getBoard().checkStatus();

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

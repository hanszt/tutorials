package com.baeldung.algorithms.mcts.montecarlo;

import com.baeldung.algorithms.mcts.tictactoe.Board;
import com.baeldung.algorithms.mcts.tictactoe.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;

public class State {
    private Board board;
    private int playerNo;
    private int visitCount;
    private double winScore;

    public State() {
        board = new Board();
    }

    public State(State state) {
        this.board = new Board(state.getBoard());
        this.playerNo = state.getPlayerNo();
        this.visitCount = state.getVisitCount();
        this.winScore = state.getWinScore();
    }

    public State(Board board) {
        this.board = new Board(board);
    }

    Board getBoard() {
        return board;
    }

    void setBoard(Board board) {
        this.board = board;
    }

    int getPlayerNo() {
        return playerNo;
    }

    void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    int getOpponent() {
        return 3 - playerNo;
    }

    public int getVisitCount() {
        return visitCount;
    }

    double getWinScore() {
        return winScore;
    }

    void setWinScore(double winScore) {
        this.winScore = winScore;
    }

    public List<State> getAllPossibleStates() {
        List<State> possibleStates = new ArrayList<>();
        var availablePositions = this.board.getEmptyPositions();
        for (var p : availablePositions) {
            var newState = new State(this.board);
            newState.setPlayerNo(3 - this.playerNo);
            newState.getBoard().performMove(newState.getPlayerNo(), p);
            possibleStates.add(newState);
        }
        return possibleStates;
    }

    void incrementVisit() {
        this.visitCount++;
    }

    void addScore(double score) {
        if (this.winScore != Integer.MIN_VALUE) {
            this.winScore += score;
        }
    }

    void randomPlay(RandomGenerator random) {
        var availablePositions = this.board.getEmptyPositions();
        var totalPossibilities = availablePositions.size();
        var selectRandom = random.nextInt(totalPossibilities);
        this.board.performMove(this.playerNo, availablePositions.get(selectRandom));
    }

    void togglePlayer() {
        this.playerNo = 3 - this.playerNo;
    }

    @Override
    public String toString() {
        return "State{" +
               "board=" + board.boardAsString() +
               ", playerNo=" + playerNo +
               ", visitCount=" + visitCount +
               ", winScore=" + winScore +
               '}';
    }
}

package com.baeldung.algorithms.mcts.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public final class Board {
    private final int[][] boardValues;
    int totalMoves;

    public static final int DEFAULT_BOARD_SIZE = 3;

    public static final int IN_PROGRESS = -1;
    public static final int DRAW = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    public Board() {
        boardValues = new int[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
    }

    public Board(int boardSize) {
        boardValues = new int[boardSize][boardSize];
    }

    public Board(int[][] boardValues) {
        this.boardValues = boardValues;
    }

    public Board(int[][] boardValues, int totalMoves) {
        this.boardValues = boardValues;
        this.totalMoves = totalMoves;
    }

    public Board(Board board) {
        var boardLength = board.getBoardValues().length;
        this.boardValues = new int[boardLength][boardLength];
        var boardValues = board.getBoardValues();
        var n = boardValues.length;
        for (var i = 0; i < n; i++) {
            var m = boardValues[i].length;
            for (var j = 0; j < m; j++) {
                this.boardValues[i][j] = boardValues[i][j];
            }
        }
    }

    public void performMove(int player, Position p) {
        this.totalMoves++;
        boardValues[p.getX()][p.getY()] = player;
    }

    public int[][] getBoardValues() {
        return boardValues;
    }

    public int checkStatus() {
        var boardSize = boardValues.length;
        var maxIndex = boardSize - 1;
        var diag1 = new int[boardSize];
        var diag2 = new int[boardSize];
        
        for (var i = 0; i < boardSize; i++) {
            var row = boardValues[i];
            var col = new int[boardSize];
            for (var j = 0; j < boardSize; j++) {
                col[j] = boardValues[j][i];
            }

            var checkRowForWin = checkForWin(row);
            if(checkRowForWin!=0) {
                return checkRowForWin;
            }

            var checkColForWin = checkForWin(col);
            if(checkColForWin!=0) {
                return checkColForWin;
            }
            
            diag1[i] = boardValues[i][i];
            diag2[i] = boardValues[maxIndex - i][i];
        }

        var checkDia1gForWin = checkForWin(diag1);
        if(checkDia1gForWin!=0) {
            return checkDia1gForWin;
        }

        var checkDiag2ForWin = checkForWin(diag2);
        if(checkDiag2ForWin!=0) {
            return checkDiag2ForWin;
        }
        
        if (!getEmptyPositions().isEmpty()) {
            return IN_PROGRESS;
        } else {
            return DRAW;
        }
    }

    private int checkForWin(int[] row) {
        var previous = row[0];
        for (final var j : row) {
            if (previous != j) {
                return 0;
            }
            previous = j;
        }
        return previous;
    }

    public String boardAsString() {
        return Arrays.stream(boardValues)
                .map(Arrays::toString)
                .collect(joining(lineSeparator()));
    }

    public List<Position> getEmptyPositions() {
        var size = this.boardValues.length;
        List<Position> emptyPositions = new ArrayList<>();
        for (var i = 0; i < size; i++) {
            for (var j = 0; j < size; j++) {
                if (boardValues[i][j] == 0) {
                    emptyPositions.add(new Position(i, j));
                }
            }
        }
        return emptyPositions;
    }

    public void printStatus() {
        switch (this.checkStatus()) {
        case P1:
            System.out.println("Player 1 wins");
            break;
        case P2:
            System.out.println("Player 2 wins");
            break;
        case DRAW:
            System.out.println("Game Draw");
            break;
        case IN_PROGRESS:
            System.out.println("Game In Progress");
            break;
        }
    }
}

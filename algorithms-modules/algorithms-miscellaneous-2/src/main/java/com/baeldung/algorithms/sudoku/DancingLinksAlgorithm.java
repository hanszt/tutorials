package com.baeldung.algorithms.sudoku;

import java.util.Arrays;

public class DancingLinksAlgorithm {
    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int NO_VALUE = 0;
    private static final int CONSTRAINTS = 4;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;
    private static final int COVER_START_INDEX = 1;

    private static int[][] board = {
      {8, 0, 0, 0, 0, 0, 0, 0, 0},
      {0, 0, 3, 6, 0, 0, 0, 0, 0},
      {0, 7, 0, 0, 9, 0, 2, 0, 0},
      {0, 5, 0, 0, 0, 7, 0, 0, 0},
      {0, 0, 0, 0, 4, 5, 7, 0, 0},
      {0, 0, 0, 1, 0, 0, 0, 3, 0},
      {0, 0, 1, 0, 0, 0, 0, 6, 8},
      {0, 0, 8, 5, 0, 0, 0, 1, 0},
      {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    public static void main(String[] args) {
        var solver = new DancingLinksAlgorithm();
        solver.solve(board);
    }

    private void solve(int[][] board) {
        var cover = initializeExactCoverBoard(board);
        var dlx = new DancingLinks(cover);
        dlx.runSolver();
    }

    private int getIndex(int row, int column, int num) {
        return (row - 1) * BOARD_SIZE * BOARD_SIZE + (column - 1) * BOARD_SIZE + (num - 1);
    }

    private boolean[][] createExactCoverBoard() {
        var coverBoard = new boolean[BOARD_SIZE * BOARD_SIZE * MAX_VALUE][BOARD_SIZE * BOARD_SIZE * CONSTRAINTS];

        var hBase = 0;
        hBase = checkCellConstraint(coverBoard, hBase);
        hBase = checkRowConstraint(coverBoard, hBase);
        hBase = checkColumnConstraint(coverBoard, hBase);
        checkSubsectionConstraint(coverBoard, hBase);
        
        return coverBoard;
    }

    private int checkSubsectionConstraint(boolean[][] coverBoard, int hBase) {
        for (var row = COVER_START_INDEX; row <= BOARD_SIZE; row += SUBSECTION_SIZE) {
            for (var column = COVER_START_INDEX; column <= BOARD_SIZE; column += SUBSECTION_SIZE) {
                for (var n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
                    for (var rowDelta = 0; rowDelta < SUBSECTION_SIZE; rowDelta++) {
                        for (var columnDelta = 0; columnDelta < SUBSECTION_SIZE; columnDelta++) {
                            var index = getIndex(row + rowDelta, column + columnDelta, n);
                            coverBoard[index][hBase] = true;
                        }
                    }
                }
            }
        }
        return hBase;
    }

    private int checkColumnConstraint(boolean[][] coverBoard, int hBase) {
        for (var column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
            for (var n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
                for (var row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
                    var index = getIndex(row, column, n);
                    coverBoard[index][hBase] = true;
                }
            }
        }
        return hBase;
    }

    private int checkRowConstraint(boolean[][] coverBoard, int hBase) {
        for (var row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
            for (var n = COVER_START_INDEX; n <= BOARD_SIZE; n++, hBase++) {
                for (var column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
                    var index = getIndex(row, column, n);
                    coverBoard[index][hBase] = true;
                }
            }
        }
        return hBase;
    }

    private int checkCellConstraint(boolean[][] coverBoard, int hBase) {
        for (var row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
            for (var column = COVER_START_INDEX; column <= BOARD_SIZE; column++, hBase++) {
                for (var n = COVER_START_INDEX; n <= BOARD_SIZE; n++) {
                    var index = getIndex(row, column, n);
                    coverBoard[index][hBase] = true;
                }
            }
        }
        return hBase;
    }

    private boolean[][] initializeExactCoverBoard(int[][] board) {
        var coverBoard = createExactCoverBoard();
        for (var row = COVER_START_INDEX; row <= BOARD_SIZE; row++) {
            for (var column = COVER_START_INDEX; column <= BOARD_SIZE; column++) {
                var n = board[row - 1][column - 1];
                if (n != NO_VALUE) {
                    for (var num = MIN_VALUE; num <= MAX_VALUE; num++) {
                        if (num != n) {
                            Arrays.fill(coverBoard[getIndex(row, column, num)], false);
                        }
                    }
                }
            }
        }
        return coverBoard;
    }
}
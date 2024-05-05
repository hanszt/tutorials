package com.baeldung.algorithms.play2048;

import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;

public final class Human {
    private static final Logger LOG = LoggerFactory.getLogger(Human.class);

    public Board makeMove(Board input) {
        // For each move in MOVE
        //   Generate board from move
        //   Generate Score for Board
        // Return board with the best score
        //
        // Generate Score
        //   If Depth Limit
        //     Return Final Score
        //   Total Score = 0
        //   For every empty square in new board
        //     Generate board with "2" in square
        //       Calculate Score
        //       Total Score += (Score * 0.9)
        //     Generate board with "4" in square
        //       Calculate Score
        //       Total Score += (Score * 0.1)
        //
        // Calculate Score
        //   For each move in MOVE
        //     Generate board from move
        //     Generate score for board
        //   Return the best generated score

        return Arrays.stream(Move.values())
                .parallel()
                .map(input::move)
                .filter(board -> !board.equals(input))
                .max(comparingInt(board -> generateScore(board, 0)))
                .orElse(input);
    }

    private int generateScore(Board board, int depth) {
        if (depth >= 3) {
            var finalScore = calculateFinalScore(board);
            LOG.debug("Final score for board {}: {}", board, finalScore);
            return finalScore;
        }

        return board.emptyCells().stream()
                .parallel()
                .flatMap(cell -> Stream.of(new Pair<>(cell, 2), new Pair<>(cell, 4)))
                .mapToInt(move -> {
                    LOG.debug("Simulating move {} at depth {}", move, depth);
                    var newBoard = board.placeTile(move.getFirst(), move.getSecond());
                    var boardScore = calculateScore(newBoard, depth + 1);
                    var calculatedScore = (int) (boardScore * (move.getSecond() == 2 ? 0.9 : 0.1));
                    LOG.debug("Calculated score for board {} and move {} at depth {}: {}", newBoard, move, depth, calculatedScore);
                    return calculatedScore;
                })
                .sum();
    }

    private int calculateScore(Board board, int depth) {
        return EnumSet.allOf(Move.class).stream()
                .parallel()
                .map(board::move)
                .filter(moved -> !moved.equals(board))
                .mapToInt(newBoard -> generateScore(newBoard, depth))
                .max()
                .orElse(0);
    }

    private int calculateFinalScore(Board board) {
        List<int[]> rowsToScore = new ArrayList<>();
        final var boardSize = board.getSize();
        for (var i = 0; i < boardSize; ++i) {
            final var row = new int[boardSize];
            final var col = new int[boardSize];

            for (var j = 0; j < boardSize; ++j) {
                row[j] = board.getCell(new Cell(i, j));
                col[j] = board.getCell(new Cell(j, i));
            }
            rowsToScore.add(row);
            rowsToScore.add(col);
        }

        return rowsToScore.stream()
                .parallel()
                .mapToInt(Human::calculateRowScore)
                .sum();
    }

    private static int calculateRowScore(int[] row) {
        final var preMerged = Arrays.stream(row)
                .filter(value -> value != 0)
                .toArray();

        var numMerges = 0;
        var monotonicityLeft = 0;
        var monotonicityRight = 0;
        for (var i = 0; i < preMerged.length - 1; ++i) {
            final var first = preMerged[i];
            final var second = preMerged[i + 1];
            if (first == second) {
                ++numMerges;
            } else if (first > second) {
                monotonicityLeft += first - second;
            } else {
                monotonicityRight += second - first;
            }
        }

        final var nonZeroCount = (int) Arrays.stream(row).filter(value -> value == 0).count();
        final var sum = Arrays.stream(row).sum();
        final var minMonotonicity = Math.min(monotonicityLeft, monotonicityRight);
        return 1000 + (250 * nonZeroCount) + (750 * numMerges) - (10 * sum) - (50 * minMonotonicity);
    }
}

package com.baeldung.algorithms.maze.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFSMazeSolver {
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<Coordinate> solve(Maze maze) {
        List<Coordinate> path = new ArrayList<>();
        if (explore(maze, maze.getEntry().getX(), maze.getEntry().getY(), path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(Maze maze, int row, int col, List<Coordinate> path) {
        if (maze.isInvalidLocation(row, col) || maze.isWall(row, col) || maze.isExplored(row, col)) {
            return false;
        }

        path.add(new Coordinate(row, col));
        maze.setVisited(row, col, true);

        if (maze.isExit(row, col)) {
            return true;
        }

        for (var direction : DIRECTIONS) {
            var coordinate = getNextCoordinate(row, col, direction[0], direction[1]);
            if (explore(maze, coordinate.getX(), coordinate.getY(), path)) {
                return true;
            }
        }

        path.removeLast();
        return false;
    }

    private Coordinate getNextCoordinate(int row, int col, int i, int j) {
        return new Coordinate(row + i, col + j);
    }
}

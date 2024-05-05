package com.baeldung.algorithms.maze.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {
    private static final int ROAD = 0;
    private static final int WALL = 1;
    private static final int START = 2;
    private static final int EXIT = 3;
    private static final int PATH = 4;

    private final int[][] maze;
    private final boolean[][] visited;
    private final Coordinate start;
    private final Coordinate end;

    public Maze(File maze) throws FileNotFoundException {
        StringBuilder fileText = new StringBuilder();
        try (var input = new Scanner(maze)) {
            while (input.hasNextLine()) {
                fileText.append(input.nextLine()).append("\n");
            }
        }
        String text = fileText.toString();
        if ((text = text.trim()).isEmpty()) {
            throw new IllegalArgumentException("empty lines data");
        }

        var lines = text.split("[\r]?\n");
        this.maze = new int[lines.length][lines[0].length()];
        visited = new boolean[lines.length][lines[0].length()];
        Coordinate start = null;
        Coordinate end = null;
        for (var row = 0; row < getHeight(); row++) {
            if (lines[row].length() != getWidth()) {
                throw new IllegalArgumentException("line " + (row + 1) + " wrong length (was " + lines[row].length() + " but should be " + getWidth() + ")");
            }

            for (var col = 0; col < getWidth(); col++) {
                switch (lines[row].charAt(col)) {
                    case '#' -> this.maze[row][col] = WALL;
                    case 'S' -> {
                        this.maze[row][col] = START;
                        start = new Coordinate(row, col);
                    }
                    case 'E' -> {
                        this.maze[row][col] = EXIT;
                        end = new Coordinate(row, col);
                    }
                    default -> this.maze[row][col] = ROAD;
                }
            }
        }
        this.start = start;
        this.end = end;
    }

    public int getHeight() {
        return maze.length;
    }

    public int getWidth() {
        return maze[0].length;
    }

    public Coordinate getEntry() {
        return start;
    }

    public Coordinate getExit() {
        return end;
    }

    public boolean isExit(int x, int y) {
        return x == end.getX() && y == end.getY();
    }

    public boolean isStart(int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    public boolean isExplored(int row, int col) {
        return visited[row][col];
    }

    public boolean isWall(int row, int col) {
        return maze[row][col] == WALL;
    }

    public void setVisited(int row, int col, boolean value) {
        visited[row][col] = value;
    }

    public boolean isInvalidLocation(int row, int col) {
        return row < 0 || row >= getHeight() || col < 0 || col >= getWidth();
    }

    public void printPath(List<Coordinate> path) {
        var tempMaze = Arrays.stream(maze)
            .map(int[]::clone)
            .toArray(int[][]::new);
        for (var coordinate : path) {
            if (isStart(coordinate.getX(), coordinate.getY()) || isExit(coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = PATH;
        }
        System.out.println(toString(tempMaze));
    }

    public String toString(int[][] maze) {
        var result = new StringBuilder(getWidth() * (getHeight() + 1));
        for (var row = 0; row < getHeight(); row++) {
            for (var col = 0; col < getWidth(); col++) {
                switch (maze[row][col]) {
                    case ROAD -> result.append(' ');
                    case WALL -> result.append('#');
                    case START -> result.append('S');
                    case EXIT -> result.append('E');
                    default -> result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    public void reset() {
        Arrays.stream(visited).forEach(booleans -> Arrays.fill(booleans, false));
    }
}

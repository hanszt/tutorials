package com.baeldung.algorithms.maze.solver;

import com.baeldung.Resources;

import java.io.File;
import java.util.List;

public final class MazeDriver {
    public static void main(String[] args) throws Exception {
        File maze1 = Resources.resourceFile("/maze/maze1.txt");
        File maze2 = Resources.resourceFile("/maze/maze2.txt");

        execute(maze1);
        execute(maze2);
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        dfs(maze);
        bfs(maze);
    }

    private static void bfs(Maze maze) {
        BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }

    private static void dfs(Maze maze) {
        DFSMazeSolver dfs = new DFSMazeSolver();
        List<Coordinate> path = dfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}

package com.baeldung.algorithms.maze.solver;

import com.baeldung.Resources;

import java.io.File;

public final class MazeDriver {
    public static void main(String[] args) throws Exception {
        var maze1 = Resources.resourceFile("/maze/maze1.txt");
        var maze2 = Resources.resourceFile("/maze/maze2.txt");

        execute(maze1);
        execute(maze2);
    }

    private static void execute(File file) throws Exception {
        var maze = new Maze(file);
        dfs(maze);
        bfs(maze);
    }

    private static void bfs(Maze maze) {
        var bfs = new BFSMazeSolver();
        var path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }

    private static void dfs(Maze maze) {
        var dfs = new DFSMazeSolver();
        var path = dfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}

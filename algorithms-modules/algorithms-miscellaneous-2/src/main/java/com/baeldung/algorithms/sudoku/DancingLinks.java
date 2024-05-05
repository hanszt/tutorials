package com.baeldung.algorithms.sudoku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DancingLinks {

    private ColumnNode header;
    private List<DancingNode> answer;

    private void search(int k) {
        if (header.R == header) {
            handleSolution(answer);
        } else {
            var c = selectColumnNodeHeuristic();
            c.cover();

            for (var r = c.D; r != c; r = r.D) {
                answer.add(r);

                for (var j = r.R; j != r; j = j.R) {
                    j.C.cover();
                }

                search(k + 1);

                r = answer.remove(answer.size() - 1);
                c = r.C;

                for (var j = r.L; j != r; j = j.L) {
                    j.C.uncover();
                }
            }
            c.uncover();
        }
    }

    private ColumnNode selectColumnNodeHeuristic() {
        var min = Integer.MAX_VALUE;
        ColumnNode ret = null;
        for (var c = (ColumnNode) header.R; c != header; c = (ColumnNode) c.R) {
            if (c.size < min) {
                min = c.size;
                ret = c;
            }
        }
        return ret;
    }

    private ColumnNode makeDLXBoard(boolean[][] grid) {
        final var COLS = grid[0].length;

        var headerNode = new ColumnNode("header");
        List<ColumnNode> columnNodes = new ArrayList<>();

        for (var i = 0; i < COLS; i++) {
            var n = new ColumnNode(Integer.toString(i));
            columnNodes.add(n);
            headerNode = (ColumnNode) headerNode.hookRight(n);
        }
        headerNode = headerNode.R.C;

        for (var aGrid : grid) {
            DancingNode prev = null;
            for (var j = 0; j < COLS; j++) {
                if (aGrid[j]) {
                    var col = columnNodes.get(j);
                    var newNode = new DancingNode(col);
                    if (prev == null) {
                        prev = newNode;
                    }
                    col.U.hookDown(newNode);
                    prev = prev.hookRight(newNode);
                    col.size++;
                }
            }
        }

        headerNode.size = COLS;

        return headerNode;
    }

    DancingLinks(boolean[][] cover) {
        header = makeDLXBoard(cover);
    }

    public void runSolver() {
        answer = new LinkedList<>();
        search(0);
    }

    private void handleSolution(List<DancingNode> answer) {
        var result = parseBoard(answer);
        printSolution(result);
    }

    private int size = 9;

    private int[][] parseBoard(List<DancingNode> answer) {
        var result = new int[size][size];
        for (var n : answer) {
            var rcNode = n;
            var min = Integer.parseInt(rcNode.C.name);
            for (var tmp = n.R; tmp != n; tmp = tmp.R) {
                var val = Integer.parseInt(tmp.C.name);
                if (val < min) {
                    min = val;
                    rcNode = tmp;
                }
            }
            var ans1 = Integer.parseInt(rcNode.C.name);
            var ans2 = Integer.parseInt(rcNode.R.C.name);
            var r = ans1 / size;
            var c = ans1 % size;
            var num = (ans2 % size) + 1;
            result[r][c] = num;
        }
        return result;
    }

    private static void printSolution(int[][] result) {
        var size = result.length;
        for (var aResult : result) {
            var ret = new StringBuilder();
            for (var j = 0; j < size; j++) {
                ret.append(aResult[j]).append(" ");
            }
            System.out.println(ret);
        }
        System.out.println();
    }
}

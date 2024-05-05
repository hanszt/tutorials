package com.baeldung.algorithms.play2048;

public final class Play2048 {
    private static final int SIZE = 4;
    private static final int INITIAL_NUMBERS = 2;

    public static void main(String[] args) {
        // The board and players
        var board = new Board(SIZE);
        var computer = new Computer();
        var human = new Human();

        // The computer has two moves first
        System.out.println("Setup");
        System.out.println("=====");
        for (var i = 0; i < INITIAL_NUMBERS; ++i) {
            board = computer.makeMove(board);
        }

        printBoard(board);
        do {
            board = human.makeMove(board);
            System.out.println("Human move");
            System.out.println("==========");
            printBoard(board);

            board = computer.makeMove(board);
            System.out.println("Computer move");
            System.out.println("=============");
            printBoard(board);
        } while (!board.emptyCells().isEmpty());

        System.out.println("Final Score: " + board.getScore());

    }

    private static void printBoard(Board board) {
        var topLines = new StringBuilder();
        var midLines = new StringBuilder();
        for (var x = 0; x < board.getSize(); ++x) {
            topLines.append("+--------");
            midLines.append("|        ");
        }
        topLines.append("+");
        midLines.append("|");


        for (var y = 0; y < board.getSize(); ++y) {
            System.out.println(topLines);
            System.out.println(midLines);
            for (var x = 0; x < board.getSize(); ++x) {
                var cell = new Cell(x, y);
                System.out.print("|");
                if (board.isEmpty(cell)) {
                    System.out.print("        ");
                } else {
                    var output = new StringBuilder(Integer.toString(board.getCell(cell)));
                    while (output.length() < 8) {
                        output.append(" ");
                        if (output.length() < 8) {
                            output.insert(0, " ");
                        }
                    }
                    System.out.print(output);
                }
            }
            System.out.println("|");
            System.out.println(midLines);
        }
        System.out.println(topLines);
        System.out.println("Score: " + board.getScore());
    }
}

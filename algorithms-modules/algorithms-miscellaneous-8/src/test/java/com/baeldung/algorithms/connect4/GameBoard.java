package com.baeldung.algorithms.connect4;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final List<List<Piece>> columns;

    private final int rows;

    public GameBoard(int columns, int rows) {
        this.rows = rows;
        this.columns = new ArrayList<>();

        for (var i = 0; i < columns; ++i) {
            this.columns.add(new ArrayList<>());
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns.size();
    }

    public Piece getCell(int x, int y) {
        assert(x >= 0 && x < getColumns());
        assert(y >= 0 && y < getRows());

        var column = columns.get(x);

        return column.size() > y ? column.get(y) : null;
    }

    public boolean move(int x, Piece player) {
        assert(x >= 0 && x < getColumns());

        var column = columns.get(x);

        if (column.size() >= this.rows) {
            throw new IllegalArgumentException("That column is full");
        }

        column.add(player);

        return checkWin(x, column.size() - 1, player);
    }

    private boolean checkWin(int x, int y, Piece player) {
        // Vertical line
        if (checkLine(x, y, 0, -1, player)) {
            return true;
        }

        for (var offset = 0; offset < 4; ++offset) {
            // Horizontal line
            if (checkLine(x - 3 + offset, y, 1, 0, player)) {
                return true;
            }

            // Leading diagonal
            if (checkLine(x - 3 + offset, y + 3 - offset, 1, -1, player)) {
                return true;
            }

            // Trailing diagonal
            if (checkLine(x - 3 + offset, y - 3 + offset, 1, 1, player)) {
                return true;
            }
        }

        return false;
    }

    private boolean checkLine(int x1, int y1, int xDiff, int yDiff, Piece player) {
        for (var i = 0; i < 4; ++i) {
            var x = x1 + (xDiff * i);
            var y = y1 + (yDiff * i);

            if (x < 0 || x > columns.size() - 1) {
                return false;
            }

            if (y < 0 || y > rows - 1) {
                return false;
            }

            if (player != getCell(x, y)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        var result = new StringBuilder();

        for (var y = getRows() - 1; y >= 0; --y) {
            for (var x = 0; x < getColumns(); ++x) {
                var piece = getCell(x, y);

                result.append("|");
                if (piece == null) {
                    result.append(" ");
                } else if (piece == Piece.PLAYER_1) {
                    result.append("X");
                } else if (piece == Piece.PLAYER_2) {
                    result.append("O");
                }
            }

            result.append("|\n");
            result.append("+-".repeat(Math.max(0, getColumns())));
            result.append("+\n");
        }

        return result.toString();
    }
}

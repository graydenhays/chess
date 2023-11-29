package chess;

import java.util.Objects;

public class Position implements ChessPosition {
    public int row;
    public int column;
    @Override
    public int getRow() {
        return row;
    }
    public void setRow(int r) {
        row = r;
    }

    @Override
    public int getColumn() {
        return column;
    }
    public void setColumn(int c) {
        column = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

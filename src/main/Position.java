import chess.ChessPosition;

public class Position implements ChessPosition {
    public int row;
    public int column;
    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }
}

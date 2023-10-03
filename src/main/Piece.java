import chess.*;

import java.util.Collection;

public class Piece implements ChessPiece {
    public ChessPiece.PieceType type;
    public ChessGame.TeamColor color;
    public Position position;
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }
    public void setPosition(int r, int c)   {
        position.row = r;
        position.column = c;
    }
    public void setColor(ChessGame.TeamColor c)   {
        color = c;
    }
    public void setType(ChessPiece.PieceType t)   {
        type = t;
    }
    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}

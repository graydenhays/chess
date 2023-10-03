import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

public class Move implements ChessMove {
    ChessPosition position;
    ChessPiece piece;
    Move(ChessPosition pos, ChessPiece piece){
        this.position = pos;
        this.piece = piece;
    }
    @Override
    public ChessPosition getStartPosition() {
        return this.position;
    }

    @Override
    public ChessPosition getEndPosition() {
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){

        }
        return null;
    }

    @Override
    public ChessPiece.PieceType getPromotionPiece() {
        return null;
    }
}

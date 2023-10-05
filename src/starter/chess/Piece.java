package chess;

import java.util.Collection;
import java.util.HashSet;

public class Piece implements ChessPiece {
    public ChessPiece.PieceType type;
    public ChessGame.TeamColor color;
    public Position position = new Position();
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
        ChessPiece piece = board.getPiece(myPosition);
        Collection<ChessMove> moveBank = new HashSet<>();
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        if(piece == null)   {
            return null;
        }
        if(piece.getPieceType() == ChessPiece.PieceType.PAWN){
            //each possible promotion is a new piece
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.ROOK){
            for(int left = myPosition.getColumn() - 1; left >= 0; left--){
                ChessPosition tempPos = new Position();
                tempPos.setColumn(left);
                tempPos.setRow(row);
                ChessMove move = new Move();
                if(board.getPiece(tempPos) != null){
                    if(board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    break;
                }
                else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            for(int right = myPosition.getColumn() + 1; right < 8; right++){
                ChessPosition tempPos = new Position();
                tempPos.setColumn(right);
                tempPos.setRow(row);
                ChessMove move = new Move();
                if(board.getPiece(tempPos) != null){
                    if(board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    break;
                }
                else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            for(int down = myPosition.getRow() - 1; down >= 0; down--){
                ChessPosition tempPos = new Position();
                tempPos.setColumn(col);
                tempPos.setRow(down);
                ChessMove move = new Move();
                if(board.getPiece(tempPos) != null){
                    if(board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    break;
                }
                else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            for(int up = myPosition.getRow() + 1; up < 8; up++){
                ChessPosition tempPos = new Position();
                tempPos.setColumn(col);
                tempPos.setRow(up);
                ChessMove move = new Move();
                if(board.getPiece(tempPos) != null){
                    if(board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    break;
                }
                else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.QUEEN){

        }
        if(piece.getPieceType() == ChessPiece.PieceType.KING){

        }
        for(ChessMove move : moveBank){
            System.out.println("\nMove---\nStart:\nrow: " + move.getStartPosition().getRow() + "\ncolumn: " + move.getStartPosition().getColumn() + "\nEnd:\nrow: " + move.getEndPosition().getRow() + "\ncolumn: " + move.getEndPosition().getColumn());
        }

        return moveBank;
    }
}

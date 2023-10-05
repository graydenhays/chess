package chess;

import java.util.Collection;
import java.util.HashSet;

public class Game implements ChessGame {
    public TeamColor color;
    public ChessBoard gameBoard = new Board();
    @Override
    public TeamColor getTeamTurn() {
        return color;
    }

    @Override
    public void setTeamTurn(TeamColor team) {
        color = team;
    }

    @Override
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece piece = gameBoard.getPiece(startPosition);
        Collection<ChessMove> allMoves = piece.pieceMoves(gameBoard, startPosition);
        int row = startPosition.getRow();
        int col = startPosition.getColumn();
        Collection<ChessMove> moveBank = new HashSet<>();
        if(isInCheck(color) && piece.getPieceType() != ChessPiece.PieceType.KING)   {
            return moveBank;
        }
        for(ChessMove move : allMoves)  {
            ChessPiece tempPiece = gameBoard.getPiece(move.getEndPosition());
            if(tempPiece == null || tempPiece.getTeamColor() != color){
                moveBank.add(move);
            }
        }
        return moveBank;
    }

    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPos = move.getStartPosition();
        ChessPosition endPos = move.getEndPosition();
        if(gameBoard.getPiece(endPos) == null){
            gameBoard.addPiece(endPos, gameBoard.getPiece(startPos));
            gameBoard.removePiece(startPos);
        }
        else {
            throw new InvalidMoveException();
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos = findKing(teamColor);
        ChessPosition tempPos = new Position();
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                tempPos.setRow(r);
                tempPos.setColumn(c);
                ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                if(tempPiece != null && tempPiece.getTeamColor() != teamColor){
                    for(ChessMove move : tempPiece.pieceMoves(gameBoard, tempPos)){
                        if(move.getEndPosition() == kingPos){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPosition kingPos = findKing(teamColor);
        ChessPiece king = gameBoard.getPiece(kingPos);
        return isInCheck(teamColor) && king.pieceMoves(gameBoard, kingPos).isEmpty();
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        ChessPosition tempPos = new Position();
        for(int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tempPos.setRow(r);
                tempPos.setColumn(c);
                ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                if(tempPiece != null && tempPiece.getTeamColor() == teamColor && !tempPiece.pieceMoves(gameBoard, tempPos).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
    public ChessPosition findKing(TeamColor teamColor)    {
        ChessPosition position = new Position();
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                position.setRow(r);
                position.setColumn(c);
                ChessPiece temp = gameBoard.getPiece(position);
                if(temp != null && temp.getPieceType() == ChessPiece.PieceType.KING && temp.getTeamColor() == teamColor){
                    return position;
                }
            }
        }
        return null;
    }
    @Override
    public void setBoard(ChessBoard board) {
        this.gameBoard = board;
    }

    @Override
    public ChessBoard getBoard() {
        return gameBoard;
    }
}

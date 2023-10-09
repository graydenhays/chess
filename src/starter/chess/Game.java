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
        Collection<ChessMove> moveBank = new HashSet<>();
        if(isInCheck(piece.getTeamColor()))   {
            if(piece.getPieceType() != ChessPiece.PieceType.KING){
                //find piece putting king in check
                ChessPosition kingPos = findKing(piece.getTeamColor(), gameBoard);
                ChessPosition attackingPos = new Position();
                for(int r = 0; r < 8; r++)  {
                    for(int c = 0; c < 8; c++)  {
                        ChessPosition tempPos = new Position();
                        tempPos.setRow(r);
                        tempPos.setColumn(c);
                        ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                        if(tempPiece != null && tempPiece.getTeamColor() != piece.getTeamColor()){
                            for(ChessMove move : tempPiece.pieceMoves(gameBoard, tempPos)){
                                if(move.getEndPosition().getRow() == kingPos.getRow() && move.getEndPosition().getColumn() == kingPos.getColumn()){
                                    attackingPos = tempPos;
                                    r = 8;
                                    c = 8;
                                }
                            }
                        }
                    }
                }
                for(ChessMove move : allMoves){
                    if(move.getEndPosition().getRow() == attackingPos.getRow() && move.getEndPosition().getColumn() == attackingPos.getColumn()){
                        moveBank.add(move);
                    }
                    else if(!willCauseCheck(move, piece.getTeamColor())) {
                        moveBank.add(move);
                    }
                }
                return moveBank;
            }
            //can the king escape ------->>>>> how to account for pawns???
            for(ChessMove move: allMoves)   {
                if(!willCauseCheck(move, piece.getTeamColor())) {
                    moveBank.add(move);
                }
            }
            return moveBank;
        }
        //King cannot move into check
        if(piece.getPieceType() == ChessPiece.PieceType.KING){
            Collection<ChessMove> invalidMoves = new HashSet<>();
            for(int r = 0; r < 8; r++)  {
                for(int c = 0; c < 8; c++)  {
                    ChessPosition tempPos = new Position();
                    tempPos.setRow(r);
                    tempPos.setColumn(c);
                    ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                    if(tempPiece != null && tempPiece.getTeamColor() != piece.getTeamColor()){
                        //pawns
                        if(tempPiece.getPieceType() == ChessPiece.PieceType.PAWN){
                            ChessPosition tL = new Position();
                            tL.setColumn(tempPos.getColumn() - 1);
                            if(tempPiece.getTeamColor() == TeamColor.WHITE){
                                tL.setRow(tempPos.getRow() + 1);
                            }
                            else {
                                tL.setRow(tempPos.getRow() - 1);
                            }
                            ChessMove topLeft = new Move();
                            topLeft.setStartPosition(tempPos);
                            topLeft.setEndPosition(tL);
                            invalidMoves.add(topLeft);

                            ChessPosition tR = new Position();
                            tR.setColumn(tempPos.getColumn() + 1);
                            if(tempPiece.getTeamColor() == TeamColor.WHITE){
                                tR.setRow(tempPos.getRow() + 1);
                            }
                            else {
                                tR.setRow(tempPos.getRow() - 1);
                            }
                            ChessMove topRight = new Move();
                            topRight.setStartPosition(tempPos);
                            topRight.setEndPosition(tL);
                            invalidMoves.add(topRight);
                        }
                        //besides pawns
                        for(ChessMove move : tempPiece.pieceMoves(gameBoard, tempPos)){
                            for (ChessMove kingMove : allMoves){
                                if(kingMove.getEndPosition().getColumn() == move.getEndPosition().getColumn() && kingMove.getEndPosition().getRow() == move.getEndPosition().getRow()){
                                    invalidMoves.add(move);
                                }
                            }
                        }
                    }
                }
            }
            for(ChessMove move : allMoves){
                boolean cleared = true;
                for(ChessMove wrongMove : invalidMoves){
                    if(move.getEndPosition().getColumn() == wrongMove.getEndPosition().getColumn() && move.getEndPosition().getRow() == wrongMove.getEndPosition().getRow()){
                        cleared = false;
                    }
                }
                if(cleared)
                    moveBank.add(move);
            }
            return moveBank;
        }
        for(ChessMove move: allMoves)   {
            if(!willCauseCheck(move, piece.getTeamColor())) {
                moveBank.add(move);
            }
        }
        return moveBank;
    }
    public boolean willCauseCheck(ChessMove move, ChessGame.TeamColor teamColor)   {
        ChessBoard newBoard = new Board();
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(c);
                tempPos.setRow(r);
                ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                if(gameBoard.getPiece(tempPos) != null){
                    newBoard.addPiece(tempPos, tempPiece);
                }
            }
        }
        ChessPosition startPos = move.getStartPosition();
        ChessPosition endPos = move.getEndPosition();
        ChessPiece piece = newBoard.getPiece(startPos);

        newBoard.addPiece(endPos, newBoard.getPiece(startPos));
        newBoard.removePiece(startPos);
        if(move.getPromotionPiece() != null){
            piece.setType(move.getPromotionPiece());
        }
        //findKing logic
        ChessPosition kingPos = findKing(teamColor, newBoard);
        if(kingPos == null) {
            return false;
        }
        //isInCheck
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                ChessPosition tempPos = new Position();
                tempPos.setRow(r);
                tempPos.setColumn(c);
                ChessPiece tempPiece = newBoard.getPiece(tempPos);
                if(tempPiece != null && tempPiece.getTeamColor() != teamColor){
                    for(ChessMove tempMove : tempPiece.pieceMoves(newBoard, tempPos)){
                        if(tempMove.getEndPosition().getRow() == kingPos.getRow() && tempMove.getEndPosition().getColumn() == kingPos.getColumn()){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPosition startPos = move.getStartPosition();
        ChessPosition endPos = move.getEndPosition();
        ChessPiece piece = gameBoard.getPiece(startPos);
        if(piece.pieceMoves(gameBoard, startPos).contains(move) && piece.getTeamColor() == color && !willCauseCheck(move, piece.getTeamColor())){
            gameBoard.addPiece(endPos, gameBoard.getPiece(startPos));
            gameBoard.removePiece(startPos);
            if(move.getPromotionPiece() != null){
                piece.setType(move.getPromotionPiece());
            }
            if(piece.getTeamColor() == TeamColor.WHITE) {
                color = TeamColor.BLACK;
            }
            else {
                color = TeamColor.WHITE;
            }
        }
        else {
            throw new InvalidMoveException();
        }
    }

    @Override
    public boolean isInCheck(TeamColor teamColor) {
        ChessPosition kingPos = findKing(teamColor, gameBoard);
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                ChessPosition tempPos = new Position();
                tempPos.setRow(r);
                tempPos.setColumn(c);
                ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                if(tempPiece != null && tempPiece.getTeamColor() != teamColor){
                    for(ChessMove move : tempPiece.pieceMoves(gameBoard, tempPos)){
                        if(move.getEndPosition().getRow() == kingPos.getRow() && move.getEndPosition().getColumn() == kingPos.getColumn()){
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
        ChessPosition kingPos = findKing(teamColor, gameBoard);
        ChessPiece king = gameBoard.getPiece(kingPos);
        return isInStalemate(teamColor) && isInCheck(teamColor);
        //return isInCheck(teamColor) && validMoves(kingPos).isEmpty();
    }

    @Override
    public boolean isInStalemate(TeamColor teamColor) {
        ChessPosition tempPos = new Position();
        for(int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                tempPos.setRow(r);
                tempPos.setColumn(c);
                ChessPiece tempPiece = gameBoard.getPiece(tempPos);
                if(tempPiece != null && tempPiece.getTeamColor() == teamColor && !validMoves(tempPos).isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
    public ChessPosition findKing(TeamColor teamColor, ChessBoard board)    {
        ChessPosition position = new Position();
        for(int r = 0; r < 8; r++)  {
            for(int c = 0; c < 8; c++)  {
                position.setRow(r);
                position.setColumn(c);
                ChessPiece temp = board.getPiece(position);
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

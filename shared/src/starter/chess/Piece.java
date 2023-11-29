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
            if(piece.getTeamColor() == ChessGame.TeamColor.BLACK) {
                //promotion
                if (myPosition.getRow() == 1) {
                    for(ChessPiece.PieceType pType : ChessPiece.PieceType.values()){
                        if(pType != PieceType.KING && pType != PieceType.PAWN) {
                            ChessPosition tempPos = new Position();
                            tempPos.setRow(myPosition.getRow() - 1);
                            tempPos.setColumn(myPosition.getColumn());
                            ChessMove move = new Move();
                            if (board.getPiece(tempPos) == null) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(pType);
                                moveBank.add(move);
                            }
                            //capture
                            if (myPosition.getColumn() > 0) {
                                ChessPosition capturePos = new Position();
                                capturePos.setRow(myPosition.getRow() - 1);
                                capturePos.setColumn(myPosition.getColumn() - 1);
                                ChessMove capture = new Move();
                                if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                                    capture.setStartPosition(myPosition);
                                    capture.setEndPosition(capturePos);
                                    capture.setPromotionPiece(pType);
                                    moveBank.add(capture);
                                }
                            }
                            if (myPosition.getColumn() < 7) {
                                ChessPosition capturePos = new Position();
                                capturePos.setRow(myPosition.getRow() - 1);
                                capturePos.setColumn(myPosition.getColumn() + 1);
                                ChessMove capture = new Move();
                                if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                                    capture.setStartPosition(myPosition);
                                    capture.setEndPosition(capturePos);
                                    capture.setPromotionPiece(pType);
                                    moveBank.add(capture);
                                }
                            }
                        }
                    }
                }
                else {
                    ChessPosition tempPos = new Position();
                    tempPos.setRow(myPosition.getRow() - 1);
                    tempPos.setColumn(myPosition.getColumn());
                    ChessMove move = new Move();
                    if (board.getPiece(tempPos) == null) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    //capture
                    if(myPosition.getColumn() > 0) {
                        ChessPosition capturePos = new Position();
                        capturePos.setRow(myPosition.getRow() - 1);
                        capturePos.setColumn(myPosition.getColumn() - 1);
                        ChessMove capture = new Move();
                        if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                            capture.setStartPosition(myPosition);
                            capture.setEndPosition(capturePos);
                            capture.setPromotionPiece(null);
                            moveBank.add(capture);
                        }
                    }
                    if(myPosition.getColumn() < 7) {
                        ChessPosition capturePos = new Position();
                        capturePos.setRow(myPosition.getRow() - 1);
                        capturePos.setColumn(myPosition.getColumn() + 1);
                        ChessMove capture = new Move();
                        if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                            capture.setStartPosition(myPosition);
                            capture.setEndPosition(capturePos);
                            capture.setPromotionPiece(null);
                            moveBank.add(capture);
                        }
                    }
                    //double move
                    if (myPosition.getRow() == 6) {
                        ChessPosition tempPoss = new Position();
                        tempPoss.setRow(myPosition.getRow() - 2);
                        tempPoss.setColumn(myPosition.getColumn());
                        ChessMove bigMove = new Move();
                        if (board.getPiece(tempPoss) == null && board.getPiece(tempPos) == null) {
                            bigMove.setStartPosition(myPosition);
                            bigMove.setEndPosition(tempPoss);
                            bigMove.setPromotionPiece(null);
                            moveBank.add(bigMove);
                        }
                    }
                }
            }
            if(piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                //promotion
                if (myPosition.getRow() == 6) {
                    for(ChessPiece.PieceType pType : ChessPiece.PieceType.values()){
                        if(pType != PieceType.KING && pType != PieceType.PAWN) {
                            ChessPosition tempPos = new Position();
                            tempPos.setRow(myPosition.getRow() + 1);
                            tempPos.setColumn(myPosition.getColumn());
                            ChessMove move = new Move();
                            if (board.getPiece(tempPos) == null) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(pType);
                                moveBank.add(move);
                            }
                            //capture
                            if (myPosition.getColumn() > 0) {
                                ChessPosition capturePos = new Position();
                                capturePos.setRow(myPosition.getRow() + 1);
                                capturePos.setColumn(myPosition.getColumn() - 1);
                                ChessMove capture = new Move();
                                if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                                    capture.setStartPosition(myPosition);
                                    capture.setEndPosition(capturePos);
                                    capture.setPromotionPiece(pType);
                                    moveBank.add(capture);
                                }
                            }
                            if (myPosition.getColumn() < 7) {
                                ChessPosition capturePos = new Position();
                                capturePos.setRow(myPosition.getRow() + 1);
                                capturePos.setColumn(myPosition.getColumn() + 1);
                                ChessMove capture = new Move();
                                if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                                    capture.setStartPosition(myPosition);
                                    capture.setEndPosition(capturePos);
                                    capture.setPromotionPiece(pType);
                                    moveBank.add(capture);
                                }
                            }
                        }
                    }
                }
                else {
                    ChessPosition tempPos = new Position();
                    tempPos.setRow(myPosition.getRow() + 1);
                    tempPos.setColumn(myPosition.getColumn());
                    ChessMove move = new Move();
                    if (board.getPiece(tempPos) == null) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                    //capture
                    if(myPosition.getColumn() > 0) {
                        ChessPosition capturePos = new Position();
                        capturePos.setRow(myPosition.getRow() + 1);
                        capturePos.setColumn(myPosition.getColumn() - 1);
                        ChessMove capture = new Move();
                        if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                            capture.setStartPosition(myPosition);
                            capture.setEndPosition(capturePos);
                            capture.setPromotionPiece(null);
                            moveBank.add(capture);
                        }
                    }
                    if(myPosition.getColumn() < 7) {
                        ChessPosition capturePos = new Position();
                        capturePos.setRow(myPosition.getRow() + 1);
                        capturePos.setColumn(myPosition.getColumn() + 1);
                        ChessMove capture = new Move();
                        if (board.getPiece(capturePos) != null && board.getPiece(capturePos).getTeamColor() != color) {
                            capture.setStartPosition(myPosition);
                            capture.setEndPosition(capturePos);
                            capture.setPromotionPiece(null);
                            moveBank.add(capture);
                        }
                    }
                    //double move
                    if (myPosition.getRow() == 1) {
                        ChessPosition tempPoss = new Position();
                        tempPoss.setRow(myPosition.getRow() + 2);
                        tempPoss.setColumn(myPosition.getColumn());
                        ChessMove bigMove = new Move();
                        if (board.getPiece(tempPoss) == null && board.getPiece(tempPos) == null) {
                            bigMove.setStartPosition(myPosition);
                            bigMove.setEndPosition(tempPoss);
                            bigMove.setPromotionPiece(null);
                            moveBank.add(bigMove);
                        }
                    }
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.KNIGHT){
            if(myPosition.getColumn() > 0 && myPosition.getRow() > 1) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 1);
                tempPos.setRow(myPosition.getRow() - 2);
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getColumn() > 1 && myPosition.getRow() > 0) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 2);
                tempPos.setRow(myPosition.getRow() - 1);
                ChessMove move2 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move2.setStartPosition(myPosition);
                        move2.setEndPosition(tempPos);
                        move2.setPromotionPiece(null);
                        moveBank.add(move2);
                    }
                } else {
                    move2.setStartPosition(myPosition);
                    move2.setEndPosition(tempPos);
                    move2.setPromotionPiece(null);
                    moveBank.add(move2);
                }
            }
            if(myPosition.getColumn() > 0 && myPosition.getRow() < 6) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 1);
                tempPos.setRow(myPosition.getRow() + 2);
                ChessMove move6 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move6.setStartPosition(myPosition);
                        move6.setEndPosition(tempPos);
                        move6.setPromotionPiece(null);
                        moveBank.add(move6);
                    }
                } else {
                    move6.setStartPosition(myPosition);
                    move6.setEndPosition(tempPos);
                    move6.setPromotionPiece(null);
                    moveBank.add(move6);
                }
            }
            if(myPosition.getColumn() > 1 && myPosition.getRow() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 2);
                tempPos.setRow(myPosition.getRow() + 1);
                ChessMove move8 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move8.setStartPosition(myPosition);
                        move8.setEndPosition(tempPos);
                        move8.setPromotionPiece(null);
                        moveBank.add(move8);
                    }
                } else {
                    move8.setStartPosition(myPosition);
                    move8.setEndPosition(tempPos);
                    move8.setPromotionPiece(null);
                    moveBank.add(move8);
                }
            }
            if(myPosition.getColumn() < 7 && myPosition.getRow() < 6) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 1);
                tempPos.setRow(myPosition.getRow() + 2);
                ChessMove move3 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move3.setStartPosition(myPosition);
                        move3.setEndPosition(tempPos);
                        move3.setPromotionPiece(null);
                        moveBank.add(move3);
                    }
                } else {
                    move3.setStartPosition(myPosition);
                    move3.setEndPosition(tempPos);
                    move3.setPromotionPiece(null);
                    moveBank.add(move3);
                }
            }
            if(myPosition.getColumn() < 6 && myPosition.getRow() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 2);
                tempPos.setRow(myPosition.getRow() + 1);
                ChessMove move4 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move4.setStartPosition(myPosition);
                        move4.setEndPosition(tempPos);
                        move4.setPromotionPiece(null);
                        moveBank.add(move4);
                    }
                } else {
                    move4.setStartPosition(myPosition);
                    move4.setEndPosition(tempPos);
                    move4.setPromotionPiece(null);
                    moveBank.add(move4);
                }
            }
            if(myPosition.getColumn() < 7 && myPosition.getRow() > 1) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 1);
                tempPos.setRow(myPosition.getRow() - 2);
                ChessMove move5 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move5.setStartPosition(myPosition);
                        move5.setEndPosition(tempPos);
                        move5.setPromotionPiece(null);
                        moveBank.add(move5);
                    }
                } else {
                    move5.setStartPosition(myPosition);
                    move5.setEndPosition(tempPos);
                    move5.setPromotionPiece(null);
                    moveBank.add(move5);
                }
            }
            if(myPosition.getColumn() < 6 && myPosition.getRow() > 1) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 2);
                tempPos.setRow(myPosition.getRow() - 1);
                ChessMove move7 = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move7.setStartPosition(myPosition);
                        move7.setEndPosition(tempPos);
                        move7.setPromotionPiece(null);
                        moveBank.add(move7);
                    }
                } else {
                    move7.setStartPosition(myPosition);
                    move7.setEndPosition(tempPos);
                    move7.setPromotionPiece(null);
                    moveBank.add(move7);
                }
            }
        }
        if(piece.getPieceType() == ChessPiece.PieceType.BISHOP){
            boolean loopBreak = false;
            for(int left = myPosition.getColumn() - 1; left >= 0; left--){
                for(int up = myPosition.getRow() + 1; up < 8; up++){
                    if(!loopBreak && left >= 0){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(left);
                        tempPos.setRow(up);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            left--;
                        }
                    }
                }
            }
            loopBreak = false;
            for(int left = myPosition.getColumn() - 1; left >= 0; left--){
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(!loopBreak && left >= 0){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(left);
                        tempPos.setRow(down);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            left--;
                        }
                    }
                }
            }
            loopBreak = false;
            for(int right = myPosition.getColumn() + 1; right < 8; right++){
                for(int up = myPosition.getRow() + 1; up < 8; up++){
                    if(!loopBreak && right < 8){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(right);
                        tempPos.setRow(up);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            right++;
                        }
                    }
                }
            }
            loopBreak = false;
            for(int right = myPosition.getColumn() + 1; right < 8; right++){
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(!loopBreak && right < 8){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(right);
                        tempPos.setRow(down);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            right++;
                        }
                    }
                }
            }
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
            boolean loopBreak = false;
            for(int left = myPosition.getColumn() - 1; left >= 0; left--){
                for(int up = myPosition.getRow() + 1; up < 8; up++){
                    if(!loopBreak && left >= 0){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(left);
                        tempPos.setRow(up);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            left--;
                        }
                    }
                }
                break;
            }
            loopBreak = false;
            for(int left = myPosition.getColumn() - 1; left >= 0; left--){
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(!loopBreak && left >= 0){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(left);
                        tempPos.setRow(down);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            left--;
                        }
                    }
                }
                break;
            }
            loopBreak = false;
            for(int right = myPosition.getColumn() + 1; right < 8; right++){
                for(int up = myPosition.getRow() + 1; up < 8; up++){
                    if(!loopBreak && right < 8){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(right);
                        tempPos.setRow(up);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            right++;
                        }
                    }
                }
                break;
            }
            loopBreak = false;
            for(int right = myPosition.getColumn() + 1; right < 8; right++){
                for(int down = myPosition.getRow() - 1; down >= 0; down--){
                    if(!loopBreak && right < 8){
                        ChessPosition tempPos = new Position();
                        tempPos.setColumn(right);
                        tempPos.setRow(down);
                        ChessMove move = new Move();
                        if (board.getPiece(tempPos) != null) {
                            if (board.getPiece(tempPos).getTeamColor() != color) {
                                move.setStartPosition(myPosition);
                                move.setEndPosition(tempPos);
                                move.setPromotionPiece(null);
                                moveBank.add(move);
                            }
                            loopBreak = true;
                            break;
                        } else {
                            move.setStartPosition(myPosition);
                            move.setEndPosition(tempPos);
                            move.setPromotionPiece(null);
                            moveBank.add(move);
                            right++;
                        }
                    }
                }
                break;
            }
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
        if(piece.getPieceType() == ChessPiece.PieceType.KING){
            if(myPosition.getRow() > 0 && myPosition.getColumn() > 0) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 1);
                tempPos.setRow(myPosition.getRow() - 1);
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getRow() > 0 && myPosition.getColumn() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 1);
                tempPos.setRow(myPosition.getRow() - 1);
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getRow() < 7 && myPosition.getColumn() > 0) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 1);
                tempPos.setRow(myPosition.getRow() + 1);
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getRow() < 7 && myPosition.getColumn() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 1);
                tempPos.setRow(myPosition.getRow() + 1);
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getRow() > 0) {
                ChessPosition tempPos = new Position();
                tempPos.setRow(myPosition.getRow() - 1);
                tempPos.setColumn(myPosition.getColumn());
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getRow() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setRow(myPosition.getRow() + 1);
                tempPos.setColumn(myPosition.getColumn());
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getColumn() > 0) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() - 1);
                tempPos.setRow(myPosition.getRow());
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
            if(myPosition.getColumn() < 7) {
                ChessPosition tempPos = new Position();
                tempPos.setColumn(myPosition.getColumn() + 1);
                tempPos.setRow(myPosition.getRow());
                ChessMove move = new Move();
                if (board.getPiece(tempPos) != null) {
                    if (board.getPiece(tempPos).getTeamColor() != color) {
                        move.setStartPosition(myPosition);
                        move.setEndPosition(tempPos);
                        move.setPromotionPiece(null);
                        moveBank.add(move);
                    }
                } else {
                    move.setStartPosition(myPosition);
                    move.setEndPosition(tempPos);
                    move.setPromotionPiece(null);
                    moveBank.add(move);
                }
            }
        }
        /*
        System.out.println("Start:\nrow: " + myPosition.getRow() + "\ncolumn: " + myPosition.getColumn());
        for(ChessMove move : moveBank){
            System.out.println("\nMove---" + "\nEnd:\nrow: " + move.getEndPosition().getRow() + "\ncolumn: " + move.getEndPosition().getColumn());
        }
        */
        return moveBank;
    }
}

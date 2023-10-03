import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

public class Board implements ChessBoard {
    public ChessPiece[][] board = new Piece[8][8];
    @Override
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()][position.getColumn()] = piece;
    }
    @Override
    public ChessPiece getPiece(ChessPosition position) {
        return board[position.getRow()][position.getColumn()];
    }
    @Override
    public void resetBoard() {
        for(ChessPiece p : board[1]){
            p.setType(ChessPiece.PieceType.PAWN);
            p.setColor(ChessGame.TeamColor.BLACK);
        }
        for(ChessPiece p : board[6]){
            p.setType(ChessPiece.PieceType.PAWN);
            p.setColor(ChessGame.TeamColor.WHITE);
        }
        for(int i = 0; i < board[0].length; i++){
            if(i == 0 || i == 7){
                board[0][i].setType(ChessPiece.PieceType.ROOK);
                board[7][i].setType(ChessPiece.PieceType.ROOK);
            }
            if(i == 1 || i == 6){
                board[0][i].setType(ChessPiece.PieceType.KNIGHT);
                board[7][i].setType(ChessPiece.PieceType.KNIGHT);
            }
            if(i == 2 || i == 5){
                board[0][i].setType(ChessPiece.PieceType.BISHOP);
                board[7][i].setType(ChessPiece.PieceType.BISHOP);
            }
            if(i == 3){
                board[0][i].setType(ChessPiece.PieceType.QUEEN);
                board[7][i].setType(ChessPiece.PieceType.QUEEN);
            }
            if(i == 4){
                board[0][i].setType(ChessPiece.PieceType.KING);
                board[7][i].setType(ChessPiece.PieceType.KING);
            }
            board[0][i].setColor(ChessGame.TeamColor.BLACK);
            board[7][i].setColor(ChessGame.TeamColor.WHITE);
        }
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board[0].length; c++) {
                board[r][c].setPosition(r,c);
            }
        }
    }
}

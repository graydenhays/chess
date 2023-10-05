package chess;

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
    public void removePiece(ChessPosition position)   {
        board[position.getRow()][position.getColumn()] = null;
    }
    //add a move piece function
    @Override
    public void resetBoard() {
        for(int i = 0; i < board[1].length; i++){
            ChessPiece p = new Piece();
            p.setType(ChessPiece.PieceType.PAWN);
            p.setColor(ChessGame.TeamColor.WHITE);
            board[1][i] = p;
            board[1][i].setPosition(1,i);
        }
        for(int i = 0; i < board[6].length; i++){
            ChessPiece p = new Piece();
            p.setType(ChessPiece.PieceType.PAWN);
            p.setColor(ChessGame.TeamColor.BLACK);
            board[6][i] = p;
            board[6][i].setPosition(6,i);
        }
        for(int i = 0; i < board[0].length; i++){
            ChessPiece pTop = new Piece();
            ChessPiece pBottom = new Piece();
            if(i == 0 || i == 7){
                pTop.setType(ChessPiece.PieceType.ROOK);
                pBottom.setType(ChessPiece.PieceType.ROOK);
            }
            if(i == 1 || i == 6){
                pTop.setType(ChessPiece.PieceType.KNIGHT);
                pBottom.setType(ChessPiece.PieceType.KNIGHT);
            }
            if(i == 2 || i == 5){
                pTop.setType(ChessPiece.PieceType.BISHOP);
                pBottom.setType(ChessPiece.PieceType.BISHOP);
            }
            if(i == 3){
                pTop.setType(ChessPiece.PieceType.QUEEN);
                pBottom.setType(ChessPiece.PieceType.QUEEN);
            }
            if(i == 4){
                pTop.setType(ChessPiece.PieceType.KING);
                pBottom.setType(ChessPiece.PieceType.KING);
            }
            pTop.setColor(ChessGame.TeamColor.WHITE);
            pBottom.setColor(ChessGame.TeamColor.BLACK);
            board[0][i] = pTop;
            board[7][i] = pBottom;
            board[0][i].setPosition(0,i);
            board[7][i].setPosition(7,i);
        }
        for(int r = 2; r < board.length - 2; r++){
            for(int c = 0; c < board[0].length; c++){
                board[r][c] = null;
            }
        }
    }
}

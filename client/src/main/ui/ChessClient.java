package ui;

import chess.*;
import com.google.gson.Gson;

import java.io.PrintStream;
import java.util.Arrays;

import static ui.EscapeSequences.*;

public class ChessClient {
    public static char[] headerList = {' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', ' '};
    private State state = State.SIGNEDOUT;
    private String userName = null;
    private String password = null;
    private String email = null;
    private static int squareCounter = 0;
    private final ChessFacade server;
    private final String serverUrl;
    private String authToken;

    public ChessClient(String serverUrl) {
        server = new ChessFacade(serverUrl);
        this.serverUrl = serverUrl;
    }

    public String eval(String input) {
        try {
            var tokens = input.toLowerCase().split(" ");
            var cmd = (tokens.length > 0) ? tokens[0] : "help";
            var params = Arrays.copyOfRange(tokens, 1, tokens.length);
            return switch (cmd) {
                case "login" -> logIn(params);
                case "register" -> registerUser(params);
                case "logout" -> logOut();
                case "create" -> createGame(params);
                case "list" -> listGames();
                case "join" -> joinGame(params);
                case "observe" -> observeGame(params);
                case "quit" -> "quit";
                default -> help();
            };
        } catch (Throwable ex) {
            return ex.getMessage();
        }
    }

    public String help() {
        if (state == State.SIGNEDOUT) {
            return """
                    register <USERNAME> <PASSWORD> <EMAIL> - to create an account
                    login <USERNAME> <PASSWORD> - to play chess
                    quit - playing chess
                    help - with possible commands
                    """;
        }
        return """
                create <NAME> - a game
                list - games
                join <ID> [WHITE|BLACK|<empty>] - a game
                observe <ID> - a game
                logout - when you are done
                quit - playing chess
                help - with possible commands
                """;
    }
    public String observeGame(String... params) throws ResponseException {
        assertSignedIn();
        int gameID = Integer.parseInt(params[0]);
        server.observeGame(gameID, authToken);
        return userName + " started observing game" + gameID;
    }
    public String joinGame(String... params) throws ResponseException {
        assertSignedIn();
        int gameID = Integer.parseInt(params[0]);
        String playerColor = params[1];
        if (params.length == 2) {
            server.joinGame(gameID, playerColor, authToken);
            return userName + " joined game " + gameID;
        }
        throw new ResponseException(400, "Expected: <gameID> <playerColor>");
    }
    public String listGames() throws ResponseException {
        assertSignedIn();
        var games = server.listGames(authToken);
        var result = new StringBuilder();
        int i = 1;
        for (var game : games) {
            result.append(i).append(" --- ")
                    .append("Game Name: ").append(game.getGameName())
                    .append(", Players: ").append(game.getBlackUsername()).append(" ").append(game.getWhiteUsername())
                    .append(", Game ID: ").append(game.getGameID()).append("\n");
            i++;
        }
        return result.toString();
    }
    public String createGame(String... params) throws ResponseException {
        assertSignedIn();
        String gameName = params[0];
        if (params.length == 1) {
            server.createGame(gameName, authToken);
            return "Created game " + gameName;
        }
        throw new ResponseException(400, "Expected: <gameName>");
    }
    public String logOut() throws ResponseException {
        assertSignedIn();
        server.logOut(authToken);
        setAuthToken(null);
        state = State.SIGNEDOUT;
        return String.format("%s logged out", userName);
    }
    public String registerUser(String... params) throws ResponseException {
        if (params.length > 2) {
            userName = params[0];
            password = params[1];
            email = params[2];
            setAuthToken(server.register(userName,password,email));
            state = State.SIGNEDIN;
            return String.format("You just registered %s.", userName);
        }
        throw new ResponseException(400, "Expected: <yourname> <password> <email>");
    }
    public String logIn(String... params) throws ResponseException  {
        if (params.length >= 1) {
            userName = params[0];
            password = params[1];
            setAuthToken(server.logIn(userName, password));
            state = State.SIGNEDIN;
            return String.format("You signed in as %s.", userName);
        }
        throw new ResponseException(400, "Expected: <yourname>");
    }
    private void assertSignedIn() throws ResponseException {
        if (state == State.SIGNEDOUT) {
            throw new ResponseException(400, "You must sign in");
        }
    }
    public static void drawChessBoard(PrintStream out, Board board) {
        board.resetBoard();
        drawHeader(out);
        for (int boardRow = 0; boardRow < 8; ++boardRow) {
            drawEdgeSquare(boardRow + 1, out);
            drawRow(out, board, boardRow);
            drawEdgeSquare(boardRow + 1, out);
            setBlack(out);
            out.println();
        }
        drawHeader(out);
    }
    public static void drawInverseChessBoard(PrintStream out, Board board) {
        board.resetBoard();
        drawInverseHeader(out);
        for (int boardRow = 7; boardRow >= 0; --boardRow) {
            drawEdgeSquare(boardRow + 1, out);
            drawInverseRow(out, board, boardRow);
            drawEdgeSquare(boardRow + 1, out);
            setBlack(out);
            out.println();
        }
        drawInverseHeader(out);
    }
    private static void drawEdgeSquare(int boardRow, PrintStream out) {
        setHeaderColor(out);
        out.print(" " + (9 - boardRow) + " ");
        setBlack(out);
        out.print(SET_BG_COLOR_BLACK);
    }
    private static void drawHeader(PrintStream out) {
        setHeaderColor(out);
        for (int boardCol = 0; boardCol < 10; ++boardCol) {
            setHeaderColor(out);
            out.print(" " + headerList[boardCol] + " ");
        }
        setBlack(out);
        out.print(SET_BG_COLOR_BLACK);
        out.println();
    }
    private static void drawInverseHeader(PrintStream out)  {
        setHeaderColor(out);
        for (int boardCol = 9; boardCol >= 0; --boardCol) {
            setHeaderColor(out);
            out.print(" " + headerList[boardCol] + " ");
        }
        setBlack(out);
        out.print(SET_BG_COLOR_BLACK);
        out.println();
    }

    private static void setHeaderColor(PrintStream out) {
        out.print(SET_BG_COLOR_RED);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void drawRow(PrintStream out, Board board, int row) {
        for (int boardCol = 0; boardCol < 8; ++boardCol) {
            setSquareColor(out);
            Position pos = new Position();
            pos.setRow(row);
            pos.setColumn(boardCol);
            ChessPiece piece = board.getPiece(pos);
            if(piece != null) {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    setWhite(out);
                } else {
                    setBlack(out);
                }
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
                    out.print(" P ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
                    out.print(" B ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
                    out.print(" N ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
                    out.print(" R ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
                    out.print(" Q ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KING) {
                    out.print(" K ");
                }
            }
            else {
                out.print("   ");
            }
        }
        squareCounter++;
    }
    private static void drawInverseRow(PrintStream out, Board board, int row) {
        for (int boardCol = 7; boardCol >= 0; --boardCol) {
            setSquareColor(out);
            Position pos = new Position();
            pos.setRow(row);
            pos.setColumn(boardCol);
            ChessPiece piece = board.getPiece(pos);
            if(piece != null) {
                if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                    setWhite(out);
                } else {
                    setBlack(out);
                }
                if (piece.getPieceType() == ChessPiece.PieceType.PAWN) {
                    out.print(" P ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.BISHOP) {
                    out.print(" B ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KNIGHT) {
                    out.print(" N ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.ROOK) {
                    out.print(" R ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.QUEEN) {
                    out.print(" Q ");
                }
                if (piece.getPieceType() == ChessPiece.PieceType.KING) {
                    out.print(" K ");
                }
            }
            else {
                out.print("   ");
            }
        }
        squareCounter++;
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setSquareColor(PrintStream out) {
        if(squareCounter % 2 == 0) {
            out.print(SET_BG_COLOR_DARK_GREEN);
        }
        else {
            out.print(SET_BG_COLOR_LIGHT_GREY);
        }
        squareCounter++;
    }

    public State getState() {
        return state;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getUserName() {
        return userName;
    }
}

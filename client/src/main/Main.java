// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import chess.Board;
import ui.Repl;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.ChessClient.*;
import static ui.EscapeSequences.*;

public class Main {
    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        var serverUrl = "http://localhost:8080";
        /*
        if (args.length == 1) {
            serverUrl = args[0];
        }
        */
        new Repl(serverUrl).run();

        Board board = new Board();
        drawChessBoard(out, board);
        System.out.println("\n");
        drawInverseChessBoard(out, board);

        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_WHITE);
    }

}
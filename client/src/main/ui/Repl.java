package ui;

import java.util.Scanner;
import static ui.EscapeSequences.*;

public class Repl {
    private final ChessClient client;

    public Repl(String serverUrl) {
        client = new ChessClient(serverUrl);
    }

    public void run() {
        System.out.println(SET_TEXT_BOLD + "Welcome to 240 chess. Type Help to get started."); //change to own

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                result = client.eval(line);
                System.out.print(result);
            } catch (Throwable e) {
                System.out.print(e.getMessage());
            }
        }
        System.out.println();
    }
    private void printPrompt() {
        if(client.getState() == State.SIGNEDIN) {
            System.out.print("\n" + SET_TEXT_COLOR_WHITE + "(" + client.getUserName() + ") >>> ");
        }
        else {
            System.out.print("\n" + SET_TEXT_COLOR_WHITE + ">>> ");
        }
    }
}

import Handlers.*;
import dataAccess.DataAccessException;
import dataAccess.Database;
import spark.*;

import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        new Server().run();
    }
    private void run()  {
        try {
            Database db = new Database();
            db.configureDatabase();
        } catch (SQLException | DataAccessException e) {
            throw new RuntimeException(e);
        }

        Spark.port(8080);
        Spark.externalStaticFileLocation("src/web");

        Spark.post("/user", (req, res) -> (new RegisterHandler()).handleRequest(req, res));
        Spark.delete("/db", (req, res) -> (new ClearHandler()).handleRequest(req, res));
        Spark.post("/session", (req, res) -> (new LoginHandler()).handleRequest(req, res));
        Spark.delete("/session", (req, res) -> (new LogoutHandler()).handleRequest(req, res));
        Spark.get("/game", (req, res) -> (new ListGamesHandler()).handleRequest(req, res));
        Spark.post("/game", (req, res) -> (new CreateGameHandler()).handleRequest(req, res));
        Spark.put("/game", (req, res) -> (new JoinGameHandler()).handleRequest(req, res));
    }
}

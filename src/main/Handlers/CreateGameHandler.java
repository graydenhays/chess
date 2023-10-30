package Handlers;

import Models.GameModel;
import Services.CreateGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class CreateGameHandler {
    public Object handleRequest(Request req, Response res) {
        CreateGameService service = new CreateGameService();
        int status = 200;
        String message = "";
        String authToken = req.headers("authorization");

        GameModel game = new Gson().fromJson(req.body(), GameModel.class);

        if(game.getGameName() == null)  {
            status = 400;
            message = "Error: Bad request";
        }
        else {
            status = service.createGame(game, authToken);
        }

        //errors
        if(status == 401)   {
            message = "Error: unauthorized";
        }
        else if(status == 500)   {
            message = "Error: description";
        }

        if(status == 200)   {
            res.type("application/json");
            res.status(status);
            res.body(new Gson().toJson(Map.of("gameID", game.getGameID())));
            return new Gson().toJson(Map.of("gameID", game.getGameID()));
        }
        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message", message)));
        return new Gson().toJson(Map.of("message", message));
    }
}

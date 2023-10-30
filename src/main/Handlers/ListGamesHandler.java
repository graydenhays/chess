package Handlers;

import Responses.ListGamesResponse;
import Services.ListGamesService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class ListGamesHandler {
    public Object handleRequest(Request req, Response res) {
        ListGamesService service = new ListGamesService();
        String message = "";
        String authToken = req.headers("authorization");

        ListGamesResponse response = service.listGames(authToken);

        //errors
        if(response.getStatus() == 401)   {
            message = "Error: unauthorized";
        }
        else if(response.getStatus() == 500)   {
            message = "Error: description";
        }

        if(response.getStatus() == 200)   {
            res.type("application/json");
            res.body(new Gson().toJson(response));
            res.status(response.getStatus());
            return new Gson().toJson(response);
        }
        res.type("application/json");
        res.status(response.getStatus());
        res.body(new Gson().toJson(Map.of("message", message)));
        return new Gson().toJson(Map.of("message", message));
    }
}

package Handlers;

import Requests.JoinGameRequest;
import Responses.JoinGameResponse;
import Services.JoinGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class JoinGameHandler {
    public Object handleRequest(Request req, Response res) {

        JoinGameService service = new JoinGameService();
        String authToken = req.headers("authorization");

        JoinGameRequest request = new Gson().fromJson(req.body(), JoinGameRequest.class);
        request.setAuthToken(authToken);
        JoinGameResponse response = service.joinGame(request);

        //errors
        if(response.getStatus() == 401)   {
            response.setMessage("Error: unauthorized");
        }
        else if(response.getStatus() == 403)  {
            response.setMessage("Error: already taken");
        }
        else if(response.getStatus() == 500)   {
            response.setMessage("Error: description");
        }

        if(response.getStatus() != 200) {
            res.type("application/json");
            res.status(response.getStatus());
            res.body(new Gson().toJson(Map.of("message", response.getMessage())));
            return new Gson().toJson(Map.of("message", response.getMessage()));
        }

        res.type("application/json");
        res.status(response.getStatus());
        return new Gson().toJson(response);
    }
}

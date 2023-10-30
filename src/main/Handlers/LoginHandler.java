package Handlers;

import Models.User;
import Responses.LoginResponse;
import Services.LoginService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class LoginHandler {
    public Object handleRequest(Request req, Response res) {
        LoginService service = new LoginService();
        String message = "";

        User user = new Gson().fromJson(req.body(), User.class);

        LoginResponse response = service.login(user);

        //errors
        if(response.getStatus() == 401)   {
            message = "Error: unauthorized";
        }
        else if(response.getStatus() == 500)   {
            message = "Error: description";
        }

        if(response.getStatus() == 200)   {
            res.body(new Gson().toJson(response));
            res.type("application/json");
            res.status(response.getStatus());
            return new Gson().toJson(response);
        }

        res.type("application/json");
        res.status(response.getStatus());
        res.body(new Gson().toJson(Map.of("message", message)));
        return new Gson().toJson(Map.of("message", message));
    }
}

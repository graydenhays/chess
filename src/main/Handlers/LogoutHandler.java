package Handlers;

import Services.LogoutService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class LogoutHandler {
    public Object handleRequest(Request req, Response res) {
        LogoutService service = new LogoutService();
        int status = 200;
        String message = "";
        String authToken = req.headers("authorization");

        status = service.logout(authToken);

        //errors
        if(status == 401)   {
            message = "Error: unauthorized";
        }
        else if(status == 500)   {
            message = "Error: description";
        }

        res.type("application/json");
        res.status(status);
        res.body(new Gson().toJson(Map.of("message", message)));
        return new Gson().toJson(Map.of("message", message));
    }
}

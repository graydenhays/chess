package Handlers;
import Models.User;
import Responses.RegisterResponse;
import Services.RegisterService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Map;

public class RegisterHandler {
    public Object handleRequest(Request req, Response res) {
        RegisterService service = new RegisterService();
        String message = "";

        User user = new Gson().fromJson(req.body(), User.class);

        RegisterResponse response = new RegisterResponse();

        if(user.getUsername() == null || user.getPassword() == null || user.getEmail() == null)  {
            response.setStatus(400);
            message = "Error: Bad request";
        }
        else {
            response = service.register(user);
        }

        //errors
        if(response.getStatus() == 403)   {
            message = "Error: already taken";
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

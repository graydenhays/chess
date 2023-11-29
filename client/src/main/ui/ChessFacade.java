package ui;

import Models.GameModel;
import Models.ModelSerializer;
import Requests.*;
import Responses.CreateGameResponse;
import Responses.ListGamesResponse;
import Responses.LoginResponse;
import Responses.RegisterResponse;
import com.google.gson.Gson;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChessFacade {
    private final String serverUrl;

    public ChessFacade(String url) {
        serverUrl = url;
    }
    public String logIn(String username, String password) throws ResponseException {
        var path = "/session";
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        LoginResponse response = this.makeRequest("POST", path, request, LoginResponse.class);
        return response.getAuthToken();
    }
    public void logOut(String authToken) throws ResponseException {
        var path = "/session";
        LogoutRequest request = new LogoutRequest();
        request.setAuthorization(authToken);
        this.makeRequest("DELETE", path, request, null);
    }
    public void clear() throws ResponseException {
        var path = "/db";
        this.makeRequest("DELETE", path, null, null);
    }
    public void createGame(String gameName, String authToken) throws ResponseException  {
        var path = "/game";
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setAuthorization(authToken);
        this.makeRequest("POST", path, request, CreateGameResponse.class);
    }

    public String register(String username, String password, String email) throws ResponseException  {
        var path = "/user";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setEmail(email);
        RegisterResponse response = this.makeRequest("POST", path, request, RegisterResponse.class);
        return response.getAuthToken();
    }
    public void joinGame(int gameID, String playerColor, String authToken) throws ResponseException  {
        var path = "/game";
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(gameID);
        request.setPlayerColor(playerColor);
        request.setAuthToken(authToken);
        this.makeRequest("PUT", path, request, null);
    }
    public void observeGame(int gameID, String authToken) throws ResponseException  {
        var path = "/game";
        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(gameID);
        request.setAuthToken(authToken);
        this.makeRequest("PUT", path, request, null);
    }

    public Collection<GameModel> listGames(String authToken) throws ResponseException {
        var path = "/game";
        ListGamesRequest request = new ListGamesRequest();
        request.setAuthorization(authToken);
        var response = this.makeRequest("GET", path, request, ListGamesResponse.class);
        return response.getGames();
    }

    public <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws ResponseException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);
            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            var temp = new Gson().toJson(request);
            var auth = new Gson().fromJson(temp, GenericAuthToken.class);
            if(auth != null)
                http.addRequestProperty("authorization", auth.getAuthentication());
            if(!Objects.equals(http.getRequestMethod(), "GET"))  {
                http.addRequestProperty("Content-Type", "application/json");

                String reqData = new Gson().toJson(request);
                try (OutputStream reqBody = http.getOutputStream()) {
                    reqBody.write(reqData.getBytes());
                }
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException, ResponseException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new ResponseException(status, "failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = ModelSerializer.deserialize(reader, responseClass);
                }
            }
        }
        return response;
    }

    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}

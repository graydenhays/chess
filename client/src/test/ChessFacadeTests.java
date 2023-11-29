import Requests.*;
import Responses.*;
import org.junit.jupiter.api.*;
import ui.ChessFacade;
import ui.ResponseException;

public class ChessFacadeTests {
    ChessFacade server = new ChessFacade("http://localhost:8080");
    String username = "username";
    String password = "password";
    String email = "email.com";
    String gameName = "newGame";
    private static String authToken;
    @BeforeEach
    public void prep() throws ResponseException {
        server.clear();
        var path = "/user";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setEmail(email);
        RegisterResponse response = server.makeRequest("POST", path, request, RegisterResponse.class);
        setAuthToken(response.getAuthToken());
    }
    @Test
    public void RegisterFailure() throws ResponseException {
        String username2 = "badName";
        Assertions.assertThrows(ResponseException.class, () -> server.register(username2, null, null));
    }
    @Test
    public void LoginFailure() throws ResponseException {
        String username2 = "badName";
        String password2 = "badPassword";
        Assertions.assertThrows(ResponseException.class, () -> server.logIn(username2,password2));
    }
    @Test
    public void LogOutFailure() throws ResponseException {
        setAuthToken(null);
        Assertions.assertThrows(ResponseException.class, () -> server.logOut(authToken));
    }
    @Test
    public void CreateGameFailure() throws ResponseException {
        setAuthToken(null);
        Assertions.assertThrows(ResponseException.class, () -> server.createGame(gameName, authToken));
    }
    @Test
    public void JoinGameFailure() throws ResponseException {
        int badGameID = -14;
        String badPlayerColor = "GREEN";
        Assertions.assertThrows(ResponseException.class, () -> server.joinGame(badGameID, badPlayerColor,authToken));
    }
    @Test
    public void ObserveGameFailure() throws ResponseException {
        int badGameID = -14;
        Assertions.assertThrows(ResponseException.class, () -> server.observeGame(badGameID, authToken));
    }
    @Test
    public void ListGamesFailure() throws ResponseException {
        setAuthToken(null);
        Assertions.assertThrows(ResponseException.class, () -> server.listGames(authToken));
    }
    @Test
    public void RegisterSuccess() throws ResponseException {
        var path = "/user";
        String username2 = "name";
        String password2 = "word";
        String email2 = "mail";
        RegisterRequest request = new RegisterRequest();
        request.setUsername(username2);
        request.setPassword(password2);
        request.setEmail(email2);
        RegisterResponse response = server.makeRequest("POST", path, request, RegisterResponse.class);
        Assertions.assertEquals(response.getUsername(), username2);
        Assertions.assertEquals(response.getStatus(), 200);
    }
    @Test
    public void LoginSuccess() throws ResponseException {
        var path = "/session";
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        LoginResponse response = server.makeRequest("POST", path, request, LoginResponse.class);
        Assertions.assertEquals(response.getUsername(), username);
        Assertions.assertEquals(response.getStatus(), 200);
    }
    @Test
    public void LogOutSuccess() throws ResponseException {
        var path = "/session";
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        LoginResponse response = server.makeRequest("POST", path, request, LoginResponse.class);
        setAuthToken(response.getAuthToken());

        LogoutRequest req = new LogoutRequest();
        req.setAuthorization(authToken);
        Assertions.assertNull(server.makeRequest("DELETE", path, req, null));
    }
    @Test
    public void CreateGameSuccess() throws ResponseException {
        var path = "/game";
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setAuthorization(authToken);
        CreateGameResponse response = server.makeRequest("POST", path, request, CreateGameResponse.class);
        Assertions.assertEquals(response.getGameID(), 1);
        Assertions.assertEquals(response.getStatus(), 200);
    }
    @Test
    public void JoinGameSuccess() throws ResponseException {
        var path = "/game";
        CreateGameRequest req = new CreateGameRequest();
        req.setGameName(gameName);
        req.setAuthorization(authToken);
        server.makeRequest("POST", path, req, CreateGameResponse.class);

        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(1);
        request.setPlayerColor("WHITE");
        request.setAuthToken(authToken);
        JoinGameResponse response = server.makeRequest("PUT", path, request, JoinGameResponse.class);
        Assertions.assertEquals(response.getGameID(), 1);
        Assertions.assertEquals(response.getPlayerColor(), "WHITE");
        Assertions.assertEquals(response.getStatus(), 200);
    }
    @Test
    public void ObserveGameSuccess() throws ResponseException {
        var path = "/game";
        CreateGameRequest req = new CreateGameRequest();
        req.setGameName(gameName);
        req.setAuthorization(authToken);
        server.makeRequest("POST", path, req, CreateGameResponse.class);

        JoinGameRequest request = new JoinGameRequest();
        request.setGameID(1);
        request.setAuthToken(authToken);
        JoinGameResponse response = server.makeRequest("PUT", path, request, JoinGameResponse.class);
        Assertions.assertEquals(response.getGameID(), 1);
        Assertions.assertNull(response.getPlayerColor());
        Assertions.assertEquals(response.getStatus(), 200);
    }
    @Test
    public void ListGamesSuccess() throws ResponseException {
        var path = "/game";
        CreateGameRequest request = new CreateGameRequest();
        request.setGameName(gameName);
        request.setAuthorization(authToken);
        server.makeRequest("POST", path, request, CreateGameResponse.class);

        ListGamesRequest req = new ListGamesRequest();
        req.setAuthorization(authToken);
        var resp = server.makeRequest("GET", path, req, ListGamesResponse.class);
        var gameList = resp.getGames();

        Assertions.assertEquals(resp.getStatus(), 200);
        Assertions.assertNotNull(gameList);
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        ChessFacadeTests.authToken = authToken;
    }
}

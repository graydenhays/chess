package passoffTests.serverTests;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.GameModel;
import Models.User;
import Requests.JoinGameRequest;
import Responses.LoginResponse;
import Responses.RegisterResponse;
import Services.*;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

public class MyUnitTests {
    AuthDAO authDAO = new AuthDAO();
    GameDAO gameDAO = new GameDAO();
    UserDAO userDAO = new UserDAO();

    @BeforeEach
    public void prep() {
        ClearService service = new ClearService();
        service.clear();
    }
    @Test
    public void ClearSuccess() throws DataAccessException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Billy Jean");

        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");

        authDAO.Insert(auth);
        userDAO.Insert(user);

        ClearService service = new ClearService();

        service.clear();

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Find(authToken));
    }
    @Test
    public void CreateGameSuccess() throws DataAccessException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameName("New Game");

        CreateGameService service = new CreateGameService();

        Assertions.assertEquals(200, service.createGame(game, authToken));
    }
    @Test
    public void CreateGameFailure() throws DataAccessException {
        String authToken = "jsdafj9823r8239";

        GameModel game = new GameModel();
        game.setGameName("New Game");

        CreateGameService service = new CreateGameService();

        Assertions.assertEquals(401, service.createGame(game, authToken));
    }
    @Test
    public void JoinGameSuccess() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");

        userDAO.Insert(user);

        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("George Michael");

        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameID(123);
        gameDAO.Insert(game);

        JoinGameRequest request = new JoinGameRequest();
        request.setPlayerColor("black");
        request.setGameID(123);
        request.setAuthToken(auth.getAuthToken());

        JoinGameService service = new JoinGameService();

        Assertions.assertEquals(200, service.joinGame(request).getStatus());
    }
    @Test
    public void JoinGameFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");

        userDAO.Insert(user);

        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("George Michael");

        JoinGameRequest request = new JoinGameRequest();
        request.setPlayerColor("black");
        request.setGameID(123);
        request.setAuthToken(auth.getAuthToken());

        GameModel game = new GameModel();
        game.setGameID(123);
        gameDAO.Insert(game);

        gameDAO.ClaimSpot("John", game, "black");

        JoinGameService service = new JoinGameService();

        Assertions.assertEquals(401, service.joinGame(request).getStatus());
    }
    @Test
    public void ListGamesSuccess() throws DataAccessException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("George Michael");
        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameName("New Game");
        game.setGameID(123);

        GameModel game2 = new GameModel();
        game.setGameName("New Game2");
        game.setGameID(125);

        gameDAO.Insert(game);
        gameDAO.Insert(game2);

        ListGamesService service = new ListGamesService();

        Assertions.assertEquals(200, service.listGames(auth.getAuthToken()).getStatus());

    }
    @Test
    public void ListGamesFailure() throws DataAccessException {
        String auth = "ajl;ksdjfkas;ldfkjm";

        GameModel game = new GameModel();
        game.setGameName("New Game");
        game.setGameID(123);

        GameModel game2 = new GameModel();
        game.setGameName("New Game2");
        game.setGameID(125);

        gameDAO.Insert(game);
        gameDAO.Insert(game2);

        ListGamesService service = new ListGamesService();

        Assertions.assertEquals(401, service.listGames(auth).getStatus());
    }
    @Test
    public void LoginSuccess() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");
        userDAO.Insert(user);

        LoginService service = new LoginService();

        LoginResponse response = service.login(user);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(user.getUsername(), response.getUsername());
        Assertions.assertNotNull(response.getAuthToken());
    }
    @Test
    public void LoginFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");

        LoginService service = new LoginService();

        LoginResponse response = service.login(user);

        Assertions.assertEquals(401, response.getStatus());
    }
    @Test
    public void LogoutSuccess() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");
        userDAO.Insert(user);

        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("George Michael");
        authDAO.Insert(auth);

        LogoutService service = new LogoutService();

        Assertions.assertEquals(200, service.logout(authToken));
    }
    @Test
    public void LogoutFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");
        userDAO.Insert(user);

        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("George Michael");

        LogoutService service = new LogoutService();

        Assertions.assertEquals(401, service.logout(authToken));
    }
    @Test
    public void RegisterSuccess() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");

        RegisterService service = new RegisterService();

        RegisterResponse response = service.register(user);

        Assertions.assertNotNull(userDAO.Find(user.getUsername()));
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(user.getUsername(), response.getUsername());
        Assertions.assertNotNull(response.getAuthToken());
    }
    @Test
    public void RegisterFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael");
        user.setPassword("Careless Whisper");
        user.setEmail("crazysaxman@mygmail.com");
        userDAO.Insert(user);

        RegisterService service = new RegisterService();

        RegisterResponse response = service.register(user);

        Assertions.assertEquals(403, response.getStatus());
    }
}

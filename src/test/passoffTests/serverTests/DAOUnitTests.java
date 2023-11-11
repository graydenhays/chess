package passoffTests.serverTests;

import DataAccess.AuthDAO;
import DataAccess.GameDAO;
import DataAccess.UserDAO;
import Models.AuthToken;
import Models.GameModel;
import Models.User;
import Services.*;
import dataAccess.DataAccessException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

public class DAOUnitTests {
    AuthDAO authDAO = new AuthDAO();
    GameDAO gameDAO = new GameDAO();
    UserDAO userDAO = new UserDAO();

    @BeforeEach
    public void prep() {
        ClearService service = new ClearService();
        service.clear();
    }
    @Test
    public void gameClearSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("BillyJean");

        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        authDAO.Insert(auth);
        userDAO.Insert(user);

        GameModel game = new GameModel();
        game.setGameName("Jazzy");

        CreateGameService service = new CreateGameService();
        service.createGame(game, authToken);

        gameDAO.Clear();

        Assertions.assertThrows(DataAccessException.class, () -> gameDAO.Find(1));
    }
    @Test
    public void InsertGameSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameName("NewGame");

        CreateGameService service = new CreateGameService();

        service.createGame(game, authToken);

        Assertions.assertEquals(game, gameDAO.Find(1));
    }

    @Test
    public void InsertGameFailure() throws DataAccessException {
        GameModel game = new GameModel();
        game.setGameName("@@@ New Game 2397 32y8rqhadfs !@#$%^");
        game.setWhiteUsername("#*@^#*^&@(");

        Assertions.assertThrows(DataAccessException.class, () -> gameDAO.Insert(game));
    }
    @Test
    public void FindGameSuccess() throws DataAccessException, SQLException {
        GameModel game = new GameModel();
        game.setGameName("TestingTesting");
        int gameID = gameDAO.Insert(game);

        Assertions.assertEquals(game.getGameName(), gameDAO.Find(gameID).getGameName());
    }
    @Test
    public void FindGameFailure() throws DataAccessException, SQLException {
        GameModel game = new GameModel();
        game.setGameID(123);

        Assertions.assertThrows(DataAccessException.class, () -> gameDAO.Find(123));
    }
    @Test
    public void FindAllSuccess() throws DataAccessException, SQLException {

        GameModel game = new GameModel();
        game.setGameName("NewGame");
        game.setGameID(123);

        GameModel game2 = new GameModel();
        game.setGameName("NewGame2");
        game.setGameID(125);

        gameDAO.Insert(game);
        gameDAO.Insert(game2);

        Assertions.assertEquals(2, gameDAO.FindAll().size());
    }
    @Test
    public void ClaimSpotSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameName("NewGame");

        int gameID = gameDAO.Insert(game);

        game.setGameID(gameID);

        gameDAO.ClaimSpot("Jimmy", game, "BLACK");

        Assertions.assertEquals("Jimmy", gameDAO.Find(gameID).getBlackUsername());
    }
    @Test
    public void ClaimSpotFailure() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);

        GameModel game = new GameModel();
        game.setGameName("NewGame");
        game.setGameID(123);

        CreateGameService service = new CreateGameService();
        service.createGame(game, authToken);

        gameDAO.ClaimSpot("Jimmy", game, "BLACK");

        Assertions.assertThrows(DataAccessException.class, () -> gameDAO.ClaimSpot("James", game, "BLACK"));
    }
    @Test
    public void authClearSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("BillyJean");

        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        authDAO.Insert(auth);
        userDAO.Insert(user);

        authDAO.Clear();

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Find(authToken));
    }
    @Test
    public void FindAuthSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("BillyJean");

        authDAO.Insert(auth);

        Assertions.assertEquals(auth.getUsername(), authDAO.Find(authToken).getUsername());
    }
    @Test
    public void FindAuthFailure() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("BillyJean");

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Find(authToken));
    }
    @Test
    public void InsertAuthSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);

        Assertions.assertEquals(auth.getUsername(), authDAO.Find(authToken).getUsername());
    }

    @Test
    public void InsertAuthFailure() throws DataAccessException {
        String authToken = "jsdafj9823r8239 !@#^%^&$*(#@(3209i430909230r-5934-";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Insert(auth));
    }

    @Test
    public void RemoveAuthSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        authDAO.Insert(auth);
        authDAO.Remove(authToken);

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Find(authToken));
    }

    @Test
    public void RemoveAuthFailure() throws DataAccessException {
        String authToken = "jsdafj9823r8239 !@#^%^&$*(#@(3209i430909230r-5934-";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("Jimmy");

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.Remove(authToken));
    }
    @Test
    public void CreateAuthSuccess() throws DataAccessException, SQLException {
        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        userDAO.Insert(user);

        AuthToken authToken = authDAO.CreateAuth(user.getUsername());

        Assertions.assertEquals(authToken.getUsername(), authDAO.Find(authToken.getAuthToken()).getUsername());
    }

    @Test
    public void CreateAuthFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael &*^(*&)(");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        Assertions.assertThrows(DataAccessException.class, () -> authDAO.CreateAuth(user.getUsername()));
    }
    @Test
    public void userClearSuccess() throws DataAccessException, SQLException {
        String authToken = "jsdafj9823r8239";
        AuthToken auth = new AuthToken();
        auth.setAuthToken(authToken);
        auth.setUsername("BillyJean");

        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        authDAO.Insert(auth);
        userDAO.Insert(user);

        userDAO.Clear();

        Assertions.assertThrows(DataAccessException.class, () -> userDAO.Find(user.getUsername()));
    }
    @Test
    public void FindUserSuccess() throws DataAccessException, SQLException {
        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        userDAO.Insert(user);

        Assertions.assertEquals(user.getUsername(), userDAO.Find(user.getUsername()).getUsername());
    }
    @Test
    public void FindUserFailure() throws DataAccessException, SQLException {
        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        Assertions.assertThrows(DataAccessException.class, () -> userDAO.Find(user.getUsername()));
    }
    @Test
    public void InsertUserSuccess() throws DataAccessException, SQLException {
        User user = new User();
        user.setUsername("GeorgeMichael");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        userDAO.Insert(user);

        Assertions.assertEquals(user.getUsername(), userDAO.Find(user.getUsername()).getUsername());
    }

    @Test
    public void InsertUserFailure() throws DataAccessException {
        User user = new User();
        user.setUsername("George Michael 239y78 hwiaps *#@&#*");
        user.setPassword("CarelessWhisper");
        user.setEmail("crazysaxman@mygmail.com");

        Assertions.assertThrows(DataAccessException.class, () -> userDAO.Insert(user));
    }
}

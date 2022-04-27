package oogasalad.engine.model.ai.moveSelection;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.engine.Choice;
import oogasalad.engine.model.engine.Engine;
import oogasalad.engine.model.engine.Oracle;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.model.player.AIPlayerFactory;
import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.Player;
import oogasalad.engine.model.player.PlayerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreeSearcherBugTest {
  private final String CHECKERS_CONFIG = "src/test/resources/games/AI-Bug-Checkers/config.json";

  GameParser parser = new GameParser(new File(CHECKERS_CONFIG));
  private Oracle myOracle;
  private Board myStartBoard;
  private PlayerManager myPlayerManager;
  private Game myGame;
  private Engine myEngine;

  @BeforeEach
  void setup() throws FileNotFoundException {
    myOracle = new Oracle(parser.readRules(), parser.readNumberOfPlayers());
    myStartBoard = parser.parseBoard();
    myGame = new Game(myStartBoard);
    myPlayerManager = new PlayerManager();
    AIPlayerFactory factory = new AIPlayerFactory();
    Player playerTwo = factory.makeAIPlayer(Difficulty.EASY, WinType.TOTAL, 1, myOracle, null);
    myPlayerManager.addPlayer(0, new HumanPlayer(myOracle, null, null));
    myPlayerManager.addPlayer(1, playerTwo);
    myEngine = new Engine(myGame, myPlayerManager, myOracle, null);
  }

  @Test
  void testAITurn() throws FileNotFoundException {
    int playerTurn = myGame.getBoard().getPlayer();
    assertEquals(0, playerTurn);

    // after player 0 plays, the AI throws an error
    assertDoesNotThrow(() -> playRandomMove());

    Board board = myGame.getBoard();

    // the turn should go back to the next player with moves,
    // which in this case is 0
    assertEquals(0, board.getPlayer());
  }

  void playRandomMove() {
    Board board = myGame.getBoard();
    Stream<Choice> choices = myOracle.getValidChoices(board);
    Choice choice = choices.findAny().get();

    myEngine.playTurn(myPlayerManager.getPlayer(board.getPlayer()), choice);

  }
}

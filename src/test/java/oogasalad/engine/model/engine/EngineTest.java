package oogasalad.engine.model.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.driver.Game;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.model.player.HumanPlayer;
import oogasalad.engine.model.player.PlayerManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jake Heller
 */
public class EngineTest {

  private final String CHECKERS_CONFIG = "data/games/checkers/config.json";
  private final String TTT_CONFIG = "data/games/tictactoe/config.json";
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
    myPlayerManager.addPlayer(0, new HumanPlayer(myOracle, null, null));
    myPlayerManager.addPlayer(1, new HumanPlayer(myOracle, null, null));

    myEngine = new Engine(myGame, myPlayerManager, myOracle, null);
  }

  @Test
  void testDraw() throws FileNotFoundException {
    myStartBoard = new Board(3,3);
    parser = new GameParser(new File(TTT_CONFIG));
    myOracle = new Oracle(parser.readRules(), parser.readNumberOfPlayers());
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        myStartBoard = myStartBoard.placeNewPiece(i,j,0,0);
      }
    }
    assertTrue(myOracle.isDraw(myStartBoard));
    assertEquals(0, myOracle.getWinner(myStartBoard));
  }
}

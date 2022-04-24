package oogasalad.engine.model.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.driver.Game;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

/**
 * Test class for the CreateJSONFile class.
 */
class CreateJSONFileTest {
  PositionState[][] positionStates = new PositionState[4][4];
  private Board TestBoard = new Board(positionStates);
  private Game TestGame= new Game(TestBoard, null);
  private GameParser TestGameParser= new GameParser(null);
  private Controller TestController = new Controller(TestBoard, TestGameParser);
  private CreateJSONFile TestJsonCreator= new CreateJSONFile(TestController);
  /**
   * Test for createFile method
   */
  @Test
  void createFile() {
    FileWriter JSONFile = TestJsonCreator.createFile();
    assertNotNull(JSONFile);
  }
}
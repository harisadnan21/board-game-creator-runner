package oogasalad.engine.model.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.PositionState;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the Game class
 * @author Haris Adnan
 */
class GameTest {

  PositionState[][] positionStates = new PositionState[4][4];
  private Board TestBoard = new Board(positionStates);
  private Game TestGame= new Game(TestBoard, null);

  GameTest() throws FileNotFoundException {
  }

  /**
   * Test for setBoard method
   */
  @Test
  void setBoard() {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    assertEquals(TestGame.getBoard(), newTestBoard);
  }
  /**
   * Test for getBoard method
   */
  @Test
  void getBoard() {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    assertEquals(TestGame.getBoard(), newTestBoard);
  }
  /**
   * Test for back method
   */
  @Test
  void back() throws BoardHistoryException {
    Board prevBoard = TestGame.getBoard();
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.back();
    assertEquals(TestGame.getBoard(), prevBoard);
  }
  /**
   * Test for forward method
   */
  @Test
  void forward() throws BoardHistoryException {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.back();
    TestGame.forward();
    assertEquals(TestGame.getBoard(), newTestBoard);
  }
  /**
   * Test for backByAmount method
   */
  @Test
  void backByAmount() {
    Board prevBoard = TestGame.getBoard();
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    Board newTestBoard2 = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.setBoard(newTestBoard2);
    assertEquals(TestGame.getBoard(), prevBoard);
  }
  /**
   * Test for forwardByAmount method
   */
  @Test
  void forwardByAmount() throws BoardHistoryException {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.backByAmount(2);
    TestGame.forwardByAmount(2);
    assertEquals(TestGame.getBoard(), newTestBoard);
  }
}
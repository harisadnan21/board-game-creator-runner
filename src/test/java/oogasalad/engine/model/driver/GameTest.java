package oogasalad.engine.model.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.PositionState;
import org.junit.jupiter.api.Test;

/**
 * Class that tests the Game class
 */
class GameTest {
//  GameParser parser = new GameParser(new File("data/checkers/config.json"));
//  Board TestBoard = parser.parseBoard();
//  BoardView boardView = new BoardView(TestBoard.getHeight(), TestBoard.getWidth(), 350, 350);
//  Controller controller = new Controller(TestBoard);

  PositionState[][] positionStates = new PositionState[4][4];
  private Board TestBoard = new Board(positionStates);
  private Game TestGame= new Game(TestBoard, null);

  GameTest() throws FileNotFoundException {
  }

  @Test
  void setBoard() {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    assertEquals(TestGame.getBoard(), newTestBoard);
  }

  @Test
  void getBoard() {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    assertEquals(TestGame.getBoard(), newTestBoard);
  }

  @Test
  void back() throws BoardHistoryException {
    Board prevBoard = TestGame.getBoard();
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.back();
    assertEquals(TestGame.getBoard(), prevBoard);
  }

  @Test
  void forward() throws BoardHistoryException {
    PositionState[][] positionStates = new PositionState[4][4];
    Board newTestBoard = new Board(positionStates);
    TestGame.setBoard(newTestBoard);
    TestGame.back();
    TestGame.forward();
    assertEquals(TestGame.getBoard(), newTestBoard);
  }

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
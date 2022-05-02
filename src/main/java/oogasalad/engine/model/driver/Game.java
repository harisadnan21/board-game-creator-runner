package oogasalad.engine.model.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;

/**
 * Game class that sets up the current board and contains history of all the previous boards. Class
 * has functions that can change the boards in the class, so that the undo method works in the view.
 * Class shows encapsulation as the variables assigned at the start are private and the user does not
 * have to be concerned about how methods are implemented at all. The get methods always return an
 * immutable board and the class shows inheritance heirarchy and polymorphism as it extends Observable<Board>.
 *
 * Git links: https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/22a97c1895c1971f4dcd4adc0e9fb4e3b97d952b
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/cf86b10db074222daf8e94511c09e519bb68fa04
 * https://coursework.cs.duke.edu/compsci308_2022spring/oogasalad_OOGABOOGA/-/commit/73dab24dfe6366445b1f1f478e526d30ba0304c2
 * @author: Jake Heller, Haris Adnan
 */
public class Game extends Observable<Board> {

  //all private variables
  private Board myBoard;
  private List<Board> myBoardHistory;
  private int backInHistory;

  /**
   * The first constructor for the Game class, sets the starting board
   *
   * @param startingBoard : the initial board to be set for the game
   */
  public Game(Board startingBoard) {
    this();
    myBoard = startingBoard;
  }

  /**
   * the second constructor for the Game Class, sets the value of the backInHistory variable and
   * assigns myBoardHistory as a new arraylist.
   */
  public Game() {
    myBoardHistory = new ArrayList<Board>();
    backInHistory = 0;
  }

  /**
   * Method sets the board to be the initial board in the paramerter.
   *
   * @param initialBoard : the starting board that is set as the first board for reset
   */
  public void reset(Board initialBoard) {
    Board oldBoard = myBoard;
    myBoard = initialBoard;
    myBoardHistory.clear();
    backInHistory = 0;
    update(oldBoard, myBoard);
  }

  /**
   * removes all boards after the current board from history and sets the param Board as the current
   * board for the game
   *
   * @param board : the board that is set as the current board
   */
  public void setBoard(Board board) {
    if (backInHistory != 0) {
      for (int i = 0; i < backInHistory; i++) {
        myBoardHistory.remove(myBoardHistory.size() - 1);
      }
    }
    myBoardHistory.add(myBoard);
    Board oldBoard = myBoard;
    myBoard = board;
    update(oldBoard, myBoard);
    backInHistory = 0;
  }

  /**
   * returns an immutable board that is the current board
   *
   * @return : the current board
   */
  public Board getBoard() {
    return myBoard;
  }

  /**
   * function saves the current board and sets the previous board as the current board, goes back
   * one board in history.
   */
  public void back() throws BoardHistoryException {
    if (myBoardHistory.size() == 0) {
      throw new BoardHistoryException(
          "You have gone too far back in the history, no board to show");
    } else {
      backInHistory++;
      Board oldBoard = myBoard;
      myBoard = myBoardHistory.get(myBoardHistory.size() - backInHistory);
      myBoardHistory.add(myBoard);
      update(oldBoard, myBoard);

    }

  }

  /**
   * function sets the current board as the previous booard (adds it to the history of boards ) and
   * then makes the new board the current board.
   */
  public void forward() throws BoardHistoryException {
    if (backInHistory == 0) {
      throw new BoardHistoryException("you have gone too far up in history, no board to show");
    } else {
      myBoardHistory.add(myBoard);
      backInHistory--;
      Board oldBoard = myBoard;
      myBoard = myBoardHistory.get(myBoardHistory.size() - 1 - backInHistory);
      update(oldBoard, myBoard);
    }

  }

  /**
   * function goes Number boards back in History of Boards and makes this the current board
   *
   * @param number
   */
  public void backByAmount(int number) throws BoardHistoryException {
    for (int i = 0; i < number; i++) {
      back();
    }
  }

  //function updates the Board changed property by using the notifyListeners method.
  private void update(Board oldBoard, Board newBoard) {
    notifyListeners("Board changed", oldBoard, newBoard);
  }

  /**
   * function goes number boards forward in the history of boards and makes this the current board
   *
   * @param number
   * @throws BoardHistoryException
   */
  public void forwardByAmount(int number) throws BoardHistoryException {
    for (int i = 0; i < number; i++) {
      forward();
    }
  }


}

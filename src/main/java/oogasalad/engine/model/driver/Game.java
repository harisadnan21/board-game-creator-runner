package oogasalad.engine.model.driver;

import java.util.Stack;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;

/**
 * Game class that sets up the current board and contains history of all the previous boards.
 * @author: Jake Heller, Haris Adnan
 */
public class Game {

  private Board myBoard;
  private Consumer<Board> myUpdateView;
  private Board
  private Stack<Board> myBoardHistory;

  public Game(Board startingBoard, Consumer<Board> updateView) {
    myBoard = startingBoard;
    myBoardHistory = new Stack<>();
    myUpdateView = updateView;
  }

  public void setBoard(Board board) {
    myBoardHistory.push(myBoard);
    myBoard = board;
    myUpdateView.accept(board);
  }

  public Board getBoard() {
    return myBoard;
  }

  /**
   * function saves the current board and sets the previous board as the current board
   */
  public void back(){
    myBoardHistory.push(myBoard);
  }

  /**
   * function sets the current board as the previous booard (adds it to the history of boards ) and
   * then makes the new board the current board.
   */
  public void forward(){
    myBoardHistory.push(myBoard);
  }

}

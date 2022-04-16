package oogasalad.engine.model.driver;

import java.util.Stack;
import java.util.function.Consumer;
import oogasalad.engine.model.board.Board;

public class Game {

  private Board myBoard;
  private Consumer<Board> myUpdateView;

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
}

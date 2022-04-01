package oogasalad.engine.model;

import java.util.Stack;
import oogasalad.engine.model.board.Board;

public class Game {

  private Board myBoard;

  private Stack<Board> myBoardHistory;

  public Game(Board startingBoard) {
    myBoard = startingBoard;
    myBoardHistory = new Stack<>();
  }

  public void setBoard(Board board) {
    myBoardHistory.push(myBoard);
    myBoard = board;
  }

  public Board getBoard() {
    return myBoard;
  }

  public getPotentiallyLegalActions()
}

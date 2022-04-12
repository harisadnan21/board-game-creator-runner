package oogasalad.engine.model.driver;

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
  //TODO: change this to board record
  public Board getBoard() {
    return myBoard;
  }

//  public getPotentiallyLegalActions()
}

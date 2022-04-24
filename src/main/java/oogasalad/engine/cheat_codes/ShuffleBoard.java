package oogasalad.engine.cheat_codes;

import java.util.Random;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.PositionState;

public class ShuffleBoard implements CheatCode{
  private Board returnBoard;
  private Random r;

  @Override
  public Board accept(Board board, Controller controller) {
    returnBoard = new Board(board.getHeight(), board.getWidth());
    r = new Random();
    placePieces(board);
    return(returnBoard);
  }

  private void placePieces(Board board) {
    for(PositionState state : board){
      int row = generateRandom(board.getHeight());
      int col = generateRandom(board.getWidth());
      while(true){
        if(returnBoard.isEmpty(row, col)) {
          returnBoard = returnBoard.placeNewPiece(row, col, state.type(), state.player());
          break;
        }
        row = generateRandom(board.getHeight());
        col = generateRandom(board.getWidth());
      }
    }
  }

  private int generateRandom(int maxPlusOne){
    return r.nextInt(maxPlusOne);
  }
}

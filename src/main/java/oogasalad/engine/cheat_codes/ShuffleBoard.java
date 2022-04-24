package oogasalad.engine.cheat_codes;

import java.util.Random;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.PositionState;
import oogasalad.engine.model.engine.Engine;

public class ShuffleBoard implements CheatCode{
  private Board returnBoard;

  @Override
  public Board accept(Board board, Engine engine) {
    returnBoard = new Board(board.getHeight(), board.getWidth());
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
    Random r = new Random();
    return r.nextInt(maxPlusOne);
  }
}

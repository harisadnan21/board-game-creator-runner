package oogasalad.engine.model.conditions.board_conditions;

import javafx.util.Pair;
import oogasalad.engine.model.OutOfBoardException;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;

public class BoardFull implements BoardCondition{

  /**
   * @param board
   * @throws OutOfBoardException
   */
  @Override
  public boolean isTrue(Board board) throws OutOfBoardException {
    for(Pair<Position, Piece> pair : board){
      if(pair.getValue() == null){
        return false;
      }
    }
    return true;
  }


}

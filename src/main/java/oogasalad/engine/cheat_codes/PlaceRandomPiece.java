package oogasalad.engine.cheat_codes;

import java.util.List;
import java.util.Random;
import oogasalad.engine.controller.Controller;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.PositionState;

public class PlaceRandomPiece implements CheatCode{
  private final Random r = new Random();
  @Override
  public Board accept(Board board, Controller controller) {
    List<PositionState> empty = (board.getPositionStatesStream().filter(PositionState::isEmpty).toList());
    List<PositionState> taken = (board.getPositionStatesStream().filter(PositionState::isPresent).toList());
    PositionState copy = taken.get(generateRandom(taken.size()));
    if(empty!=null) {
      return board.placePiece(new PositionState(empty.get(generateRandom(empty.size())).position(), copy.piece()));
    }
    return board;

  }
  private int generateRandom(int maxPlusOne){
    return r.nextInt(maxPlusOne);
  }
}

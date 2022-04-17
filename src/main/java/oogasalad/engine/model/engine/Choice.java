package oogasalad.engine.model.engine;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.move.Move;

public record Choice(Position position, Move move) {


  public boolean isValidChoice(Board board) {
    return move.isValid(board, position);
  }
}

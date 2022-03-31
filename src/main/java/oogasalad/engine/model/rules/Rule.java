package oogasalad.engine.model.rules;

import oogasalad.engine.model.Piece;
import oogasalad.engine.model.board.Board;

public interface Rule {
  default boolean checkValidity(Piece piece, Board board){
    return false;
  }
}

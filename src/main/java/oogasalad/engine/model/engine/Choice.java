package oogasalad.engine.model.engine;

import oogasalad.engine.model.ai.AIChoice;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.move.Move;

public record Choice(Position position, Move move) implements AIChoice {


  public boolean isValidChoice(Board board) {
    return move.isValid(board, position);
  }

  @Override // return the board that would result if this choice was made
  public Board getResultingBoard() {
    return null;
  }
}

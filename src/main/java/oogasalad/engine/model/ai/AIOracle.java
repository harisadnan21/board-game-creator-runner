package oogasalad.engine.model.ai;

import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.engine.Choice;
import org.jooq.lambda.Seq;

public interface AIOracle {
  Seq<Choice> getChoices(Board board, int player);

  boolean isWinningState(Board board);

}

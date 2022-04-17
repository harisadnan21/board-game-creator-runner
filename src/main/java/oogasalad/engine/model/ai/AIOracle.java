package oogasalad.engine.model.ai;

import io.vavr.control.Either;
import io.vavr.control.Option;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import org.jooq.lambda.tuple.Tuple2;

public interface AIOracle {
  Set<Choice> getChoices(Board board, int player);

  Boolean isWinningState(Board board);

}

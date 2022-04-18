package oogasalad.engine.model.ai.evaluation.constant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.player.Player;
import org.assertj.core.util.Arrays;
import org.jooq.lambda.Seq;
import org.junit.jupiter.api.Test;

class ConstantTest {
  private static Constant constant1 = new Constant(1);
  private static Constant constant2 = new Constant(10);
  private static Constant constant3 = new Constant(-10);

  @Test
  void evaluate() {
    for(int player: Arrays.array(Piece.PLAYER_ONE, Piece.PLAYER_TWO)) {
      Stream<Board> randomBoards = getRandomBoards(5);
      randomBoards.forEach(board -> assertEquals(1, constant1.evaluate(board, player)));

      randomBoards = getRandomBoards(5);
      randomBoards.forEach(board -> assertEquals(10, constant2.evaluate(board, player)));

      randomBoards = getRandomBoards(5);
      randomBoards.forEach(board -> assertEquals(-10, constant3.evaluate(board, player)));
    }
  }


  @Test
  void noErrors() {
    for(int player: Arrays.array(Piece.PLAYER_ONE, Piece.PLAYER_TWO)) {
      Stream<Board> randomBoards = getRandomBoards(2);
      randomBoards.forEach(board -> assertDoesNotThrow(() -> constant1.evaluate(board, player)));

      randomBoards = getRandomBoards(2);
      randomBoards.forEach(board -> assertDoesNotThrow(() -> constant2.evaluate(board, player)));

      randomBoards = getRandomBoards(2);
      randomBoards.forEach(board -> assertDoesNotThrow(() -> constant3.evaluate(board, player)));
    }
  }

  private Stream<Board> getRandomBoards(int i) {
    return Seq.range(0,i).map(integer -> new Board(integer+2, integer+2));
  }


}
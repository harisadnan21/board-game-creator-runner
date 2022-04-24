package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import oogasalad.engine.model.board.Piece;
import org.junit.jupiter.api.Test;

class AIPlayerTest {

  @Test
  void noErrors() {
    assertDoesNotThrow(() -> new AIPlayer(Piece.PLAYER_ONE, null));
    assertDoesNotThrow(() -> new AIPlayer(Piece.PLAYER_TWO, null));
  }

  @Test
  void chooseAction() {
  }

  @Test
  void chooseMove() {
  }

  @Test
  void onCellSelect() {
  }

  @Test
  void testChooseAction() {
  }

  @Test
  void testChooseMove() {
  }

  @Test
  void testOnCellSelect() {
  }
}
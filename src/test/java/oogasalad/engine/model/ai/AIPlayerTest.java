package oogasalad.engine.model.ai;

import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.board.Piece;
import org.junit.jupiter.api.Test;

class AIPlayerTest {

  @Test
  void noErrors() {
    assertDoesNotThrow(() -> new AIPlayer(Piece.PLAYER_ONE, null, Difficulty.EASY, null, null, WinType.TOTAL, null));
    assertDoesNotThrow(() -> new AIPlayer(Piece.PLAYER_TWO, null, Difficulty.EASY, null, null, WinType.TOTAL, null));
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
}
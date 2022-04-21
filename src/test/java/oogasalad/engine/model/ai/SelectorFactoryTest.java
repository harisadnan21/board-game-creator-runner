package oogasalad.engine.model.ai;

import static oogasalad.engine.model.ai.SelectorFactory.*;
import static org.junit.jupiter.api.Assertions.*;

import oogasalad.engine.model.ai.enums.Difficulty;
import oogasalad.engine.model.ai.enums.WinType;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.engine.Oracle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

class SelectorFactoryTest {

  @Test
  void makeSelectors() {
    for(Difficulty difficulty: Difficulty.values()) {
      for(int player: new int[]{Piece.PLAYER_ONE, Piece.PLAYER_ONE}) {
        assertDoesNotThrow(() -> makeSelector(difficulty, WinType.TOTAL, player, null, null));
        assertDoesNotThrow(() -> makeSelector(difficulty, WinType.PATTERN, player, null, null));
      }
    }

  }

  @Test
  void makeSelectorEasy() {
    assertDoesNotThrow(() -> makeSelector(Difficulty.EASY, WinType.TOTAL, Piece.PLAYER_ONE, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.EASY, WinType.TOTAL, Piece.PLAYER_TWO, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.EASY, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
  }

  @Test
  void makeSelectorMedium() {
    assertDoesNotThrow(() -> makeSelector(Difficulty.MEDIUM, WinType.TOTAL, Piece.PLAYER_ONE, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.MEDIUM, WinType.TOTAL, Piece.PLAYER_TWO, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.MEDIUM, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
  }

  @Test
  void makeSelectorHard() {
    assertDoesNotThrow(() -> makeSelector(Difficulty.HARD, WinType.TOTAL, Piece.PLAYER_ONE, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.HARD, WinType.TOTAL, Piece.PLAYER_TWO, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.HARD, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
  }

  @Test
  void makeSelectorExpert() {
    assertDoesNotThrow(() -> makeSelector(Difficulty.EXPERT, WinType.TOTAL, Piece.PLAYER_ONE, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.EXPERT, WinType.TOTAL, Piece.PLAYER_TWO, null, null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.EXPERT, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
  }

  @Test
  void makeSelectorRandom() {
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 1), null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.PATTERN, Piece.PLAYER_ONE, new Oracle(null, null, null, 2), null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.PATTERN, Piece.PLAYER_TWO, new Oracle(null, null, null, 2), null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.TOTAL, Piece.PLAYER_ONE, new Oracle(null, null, null, 1), null) );
    assertDoesNotThrow(() -> makeSelector(Difficulty.RANDOM, WinType.PATTERN, Piece.PLAYER_TWO, new Oracle(null, null, null, 2), null) );
  }

}
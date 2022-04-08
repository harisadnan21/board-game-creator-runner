package oogasalad.engine.model.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionStateTest {

  public static final PositionState POSITION_STATE_1 = new PositionState(new Position(1, 1), 0
  );
  public static final PositionState POSITION_STATE_2 = new PositionState(new Position(2, 2),
      0);
  public static final PositionState POSITION_STATE_3 = new PositionState(new Position(3, 3), 1
  );
  public static final PositionState POSITION_STATE_4 = new PositionState(new Position(4, 4),
      1);

  @Test
  void position() {
    Assertions.assertNotNull(POSITION_STATE_1.position());
    Assertions.assertNotNull(POSITION_STATE_2.position());
    Assertions.assertNotNull(POSITION_STATE_3.position());
    Assertions.assertNotNull(POSITION_STATE_4.position());
  }

  @Test
  void player() {
    Assertions.assertNotNull(POSITION_STATE_1.piece());
    Assertions.assertNotNull(POSITION_STATE_2.piece());
    Assertions.assertNotNull(POSITION_STATE_3.piece());
    Assertions.assertNotNull(POSITION_STATE_4.piece());
  }

  @Test
  void pieceType() {
    Assertions.assertNotNull(POSITION_STATE_1.pieceType());
    Assertions.assertNotNull(POSITION_STATE_2.pieceType());
    Assertions.assertNotNull(POSITION_STATE_3.pieceType());
    Assertions.assertNotNull(POSITION_STATE_4.pieceType());
  }
}
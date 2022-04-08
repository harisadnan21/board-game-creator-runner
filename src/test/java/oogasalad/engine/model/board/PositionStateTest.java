package oogasalad.engine.model.board;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PositionStateTest {

  public static final PositionState POSITION_STATE_1 = new PositionState(new Position(1, 1), Piece.EMPTY
  );
  public static final PositionState POSITION_STATE_2 = new PositionState(new Position(2, 2),
      Piece.EMPTY);
  public static final PositionState POSITION_STATE_3 = new PositionState(new Position(3, 3), Piece.EMPTY
  );
  public static final PositionState POSITION_STATE_4 = new PositionState(new Position(4, 4),
      Piece.EMPTY);

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
    Assertions.assertNotNull(POSITION_STATE_1.type());
    Assertions.assertNotNull(POSITION_STATE_2.type());
    Assertions.assertNotNull(POSITION_STATE_3.type());
    Assertions.assertNotNull(POSITION_STATE_4.type());
  }
}
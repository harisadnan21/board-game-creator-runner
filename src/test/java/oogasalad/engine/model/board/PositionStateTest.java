package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PositionStateTest {

  public static final PositionState POSITION_STATE_1 = new PositionState(new Position(1, 1), "red",
      "regular");
  public static final PositionState POSITION_STATE_2 = new PositionState(new Position(2, 2),
      "yellow", "king");
  public static final PositionState POSITION_STATE_3 = new PositionState(new Position(3, 3), "blue",
      "queen");
  public static final PositionState POSITION_STATE_4 = new PositionState(new Position(4, 4),
      "purple", "");

  @BeforeAll
  void setup(){
  }

  @Test
  void position() {
    Assertions.assertNotNull(POSITION_STATE_1.position());
    Assertions.assertNotNull(POSITION_STATE_2.position());
    Assertions.assertNotNull(POSITION_STATE_3.position());
    Assertions.assertNotNull(POSITION_STATE_4.position());
  }

  @Test
  void player() {
    Assertions.assertNotNull(POSITION_STATE_1.player());
    Assertions.assertNotNull(POSITION_STATE_2.player());
    Assertions.assertNotNull(POSITION_STATE_3.player());
    Assertions.assertNotNull(POSITION_STATE_4.player());
  }

  @Test
  void pieceType() {
    Assertions.assertNotNull(POSITION_STATE_1.pieceType());
    Assertions.assertNotNull(POSITION_STATE_2.pieceType());
    Assertions.assertNotNull(POSITION_STATE_3.pieceType());
    Assertions.assertNotNull(POSITION_STATE_4.pieceType());
  }
}
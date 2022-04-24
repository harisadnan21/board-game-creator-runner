package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import oogasalad.engine.model.board.cells.PositionState;
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
  void i() {
    assertNotNull(POSITION_STATE_1.i());
    assertNotNull(POSITION_STATE_2.i());
    assertNotNull(POSITION_STATE_3.i());
    assertNotNull(POSITION_STATE_4.i());
    assertInstanceOf(Integer.class, POSITION_STATE_1.i());
    assertInstanceOf(Integer.class, POSITION_STATE_2.i());
    assertInstanceOf(Integer.class, POSITION_STATE_3.i());
    assertInstanceOf(Integer.class, POSITION_STATE_4.i());
  }

  @Test
  void j() {
    assertNotNull(POSITION_STATE_1.j());
    assertNotNull(POSITION_STATE_2.j());
    assertNotNull(POSITION_STATE_3.j());
    assertNotNull(POSITION_STATE_4.j());
    assertInstanceOf(Integer.class, POSITION_STATE_1.j());
    assertInstanceOf(Integer.class, POSITION_STATE_2.j());
    assertInstanceOf(Integer.class, POSITION_STATE_3.j());
    assertInstanceOf(Integer.class, POSITION_STATE_4.j());
  }
  @Test
  void position() {
    assertNotNull(POSITION_STATE_1.position());
    assertNotNull(POSITION_STATE_2.position());
    assertNotNull(POSITION_STATE_3.position());
    assertNotNull(POSITION_STATE_4.position());
    assertInstanceOf(Position.class, POSITION_STATE_1.position());
    assertInstanceOf(Position.class, POSITION_STATE_2.position());
    assertInstanceOf(Position.class, POSITION_STATE_3.position());
    assertInstanceOf(Position.class, POSITION_STATE_4.position());
  }

  @Test
  void player() {
    assertNotNull(POSITION_STATE_1.piece());
    assertNotNull(POSITION_STATE_2.piece());
    assertNotNull(POSITION_STATE_3.piece());
    assertNotNull(POSITION_STATE_4.piece());
    assertInstanceOf(Integer.class, POSITION_STATE_1.player());
    assertInstanceOf(Integer.class, POSITION_STATE_2.player());
    assertInstanceOf(Integer.class, POSITION_STATE_3.player());
    assertInstanceOf(Integer.class, POSITION_STATE_4.player());
  }

  @Test
  void pieceType() {
    assertNotNull(POSITION_STATE_1.type());
    assertNotNull(POSITION_STATE_2.type());
    assertNotNull(POSITION_STATE_3.type());
    assertNotNull(POSITION_STATE_4.type());
    assertInstanceOf(Integer.class, POSITION_STATE_1.type());
    assertInstanceOf(Integer.class, POSITION_STATE_2.type());
    assertInstanceOf(Integer.class, POSITION_STATE_3.type());
    assertInstanceOf(Integer.class, POSITION_STATE_4.type());
  }

  @Test
  void with() {
    Piece piecet = new Piece(100, Piece.PLAYER_TWO);
    Piece piecef = new Piece(100, Piece.PLAYER_ONE);
    var posState1 = POSITION_STATE_1.with(piecet);
    var posState2 = POSITION_STATE_2.with(piecet);
    var posState3 = POSITION_STATE_3.with(piecet);
    var posState4 = POSITION_STATE_4.with(piecet);
  }

  @Test
  void testWith() {
  }

  @Test
  void withEmpty() {
  }
}
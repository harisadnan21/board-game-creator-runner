package oogasalad.engine.model.ai.evaluation.patterns;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.junit.jupiter.api.Test;

class PatternTest {

  @Test
  void includes() {
  }

  @Test
  void testIncludes() {
  }

  @Test
  void getPositionStates() {
  }

  @Test
  void getPositions() {
  }

  @Test
  void getPieceAtPositions() {
  }

  @Test
  void getSize() {
  }

  @Test
  void testEquals() {
  }

  @Test
  void canEqual() {
  }

  @Test
  void testHashCode() {
  }

  @Test
  void testToString() {
  }

  @Test
  void testIncludes1() {
  }

  @Test
  void pieceAt() {
  }

  @Test
  void testGetPositionStates() {
  }

  @Test
  void testGetPositions() {
  }

  @Test
  void testGetPieceAtPositions() {
  }

  @Test
  void testGetSize() {
  }

  @Test
  void compareTo() {
  }

  @Test
  void testEquals1() {
  }

  @Test
  void testHashCode1() {
  }

  @Test
  void forPlayer() {
    Pattern pattern1 = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    Pattern pattern2 = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_TWO))));
    assertEquals(Piece.PLAYER_ONE, pattern1.forPlayer());
    assertEquals(Piece.PLAYER_TWO, pattern2.forPlayer());
  }
}
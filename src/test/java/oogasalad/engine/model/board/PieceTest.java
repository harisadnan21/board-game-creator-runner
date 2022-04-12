package oogasalad.engine.model.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PieceTest {

  Piece myPiece = new Piece(-1,-1);

  @Test
  void testEquals() {
    assertTrue(myPiece.equals(Piece.EMPTY));
    Piece piece1 = new Piece(1,2);
    Piece piece2 = new Piece(1,2);
    assertTrue(piece1.equals(piece2));
    assertFalse(myPiece.equals(piece1));
    assertFalse(myPiece.equals(new Piece(2,2)));
  }

  @Test
  void testClass() {
    Object o = new Piece(1,1);
    assertTrue(o.getClass() == Piece.class);
  }
}

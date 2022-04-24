package oogasalad.engine.model.ai.evaluation.patterns;

import static org.junit.jupiter.api.Assertions.*;

import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import java.util.Collection;
import java.util.List;
import oogasalad.engine.model.board.Piece;
import oogasalad.engine.model.board.Position;
import oogasalad.engine.model.board.PositionState;
import org.junit.jupiter.api.Test;

class PatternTest {


  @Test
  void getPositionStates() {
    Position position1 = new Position(0,0);
    Piece piece1 = new Piece(1, Piece.PLAYER_ONE);

    Position position2 = new Position(1,0);
    Piece piece2 = new Piece(1, Piece.PLAYER_ONE);

    Position position3 = new Position(0,4);
    Piece piece3 = new Piece(1, Piece.PLAYER_ONE);

    Position position4 = new Position(2,4);
    Piece piece4 = new Piece(2, Piece.PLAYER_ONE);

    Pattern pattern4 = new Pattern(
        List.of(
            new PositionState(position1, piece1),
            new PositionState(position2, piece2),
            new PositionState(position3, piece3),
            new PositionState(position4, piece4))
    );



    assertNotNull(pattern4.getPositionStates());

    assertEquals(4, pattern4.getPositionStates().size());

    PositionState positionState1 = new PositionState(position1, piece1);

    PositionState positionState2 = new PositionState(position2, piece2);

    PositionState positionState3 = new PositionState(position3, piece3);

    PositionState positionState4 = new PositionState(position4, piece4);

    assertEquals(TreeSet.of(positionState1, positionState2, positionState3, positionState4), pattern4.getPositionStates());
  }

  @Test
  void getPositions() {
    Position position1 = new Position(0,0);
    Piece piece1 = new Piece(1, Piece.PLAYER_ONE);

    Position position2 = new Position(1,0);
    Piece piece2 = new Piece(1, Piece.PLAYER_ONE);

    Position position3 = new Position(0,4);
    Piece piece3 = new Piece(1, Piece.PLAYER_ONE);

    Position position4 = new Position(2,4);
    Piece piece4 = new Piece(2, Piece.PLAYER_ONE);

    Pattern pattern4 = new Pattern(
        List.of(
            new PositionState(position1, piece1),
            new PositionState(position2, piece2),
            new PositionState(position3, piece3),
            new PositionState(position4, piece4))
    );



    assertInstanceOf(SortedSet.class, pattern4.getPositions());
    assertNotNull(pattern4.getPositions());
    assertEquals(TreeSet.of(position1, position2, position3, position4), pattern4.getPositions());
  }

  @Test
  void getPieceAtPositions() {
  }


  @Test
  void pieceAt() {
    Position position1 = new Position(0,0);
    Piece piece1 = new Piece(1, Piece.PLAYER_ONE);

    Position position2 = new Position(1,0);
    Piece piece2 = new Piece(1, Piece.PLAYER_ONE);

    Position position3 = new Position(0,4);
    Piece piece3 = new Piece(1, Piece.PLAYER_ONE);

    Pattern pattern3 = new Pattern(
        List.of(
            new PositionState(position1, piece1),
            new PositionState(position2, piece2),
            new PositionState(position3, piece3))
    );

    assertEquals(piece1, pattern3.pieceAt(position1));
    assertEquals(piece2, pattern3.pieceAt(position2));
    assertEquals(piece2, pattern3.pieceAt(position3));
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
    Pattern pattern1 =  new Pattern(
        List.of(
            new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))
    ));
    assertEquals(1, pattern1.getSize());

    Pattern pattern2 =  new Pattern(
        List.of(
            new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE)),
            new PositionState(new Position(0,4), new Piece(1, Piece.PLAYER_ONE)))
    );

    assertEquals(2, pattern2.getSize());
    Pattern pattern3 = new Pattern(
        List.of(
            new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE)),
            new PositionState(new Position(1,0), new Piece(1, Piece.PLAYER_ONE)),
            new PositionState(new Position(0,4), new Piece(1, Piece.PLAYER_ONE)))
    );
    assertEquals(3, pattern3.getSize());
  }

  @Test
  void compareTo() {
  }

  @Test
  void testEquals1() {

    Pattern pattern1a = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    Pattern pattern1b = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    assertEquals(pattern1a, pattern1b);

    Pattern pattern2a = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_TWO))));
    Pattern pattern2b = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_TWO))));
    assertEquals(pattern2a, pattern2b);

    assertNotEquals(pattern1a, pattern2a);
    assertNotEquals(pattern1a, pattern2b);

    assertNotEquals(pattern1b, pattern2a);
    assertNotEquals(pattern1b, pattern2b);
  }

  @Test
  void testHashCode1() {
    Pattern pattern1a = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    Pattern pattern1b = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    assertEquals(pattern1a.hashCode(), pattern1b.hashCode());

    Pattern pattern2a = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_TWO))));
    Pattern pattern2b = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_TWO))));
    assertEquals(pattern2a.hashCode(), pattern2b.hashCode());

    assertNotEquals(pattern1a.hashCode(), pattern2a.hashCode());
    assertNotEquals(pattern1a.hashCode(), pattern2b.hashCode());

    assertNotEquals(pattern1b.hashCode(), pattern2a.hashCode());
    assertNotEquals(pattern1b.hashCode(), pattern2b.hashCode());
  }

  @Test
  void forPlayer() {
    Pattern pattern1 = new Pattern(List.of(new PositionState(new Position(0,0), new Piece(1, Piece.PLAYER_ONE))));
    Pattern pattern2 = new Pattern(List.of(new PositionState(new Position(4,4), new Piece(4, Piece.PLAYER_TWO))));

    assertEquals(Piece.PLAYER_ONE, pattern1.forPlayer());

    assertEquals(Piece.PLAYER_TWO, pattern2.forPlayer());
  }
}
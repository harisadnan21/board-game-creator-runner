package oogasalad.builder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for GameConfiguration Class
 *
 * @author Shaan Gondalia
 */
public class GameConfigurationTest {

  private static final int HEIGHT = 8;
  private static final int WIDTH = 10;
  private static final String PIECE_NAME = "checker";
  private static final String TYPE = "piece";
  private static final String EMPTY = "empty";
  private static final String PROPERTY_NAME = "test property name";
  private static final String PROPERTY_VALUE = "test property value";
  private static final int X = 5;
  private static final int Y = 7;

  private BuilderModel game;

  @BeforeEach
  void setUp(){
    game = new GameConfiguration();
  }

  @Test
  void testGameElementNotFound() {
    assertThrows(ElementNotFoundException.class, () -> game.findElementInfo(TYPE, PIECE_NAME));
    game.addGameElement(TYPE, PIECE_NAME, Set.of(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE)));
    assertThrows(ElementNotFoundException.class, () -> game.findElementInfo(TYPE, "not the name"));
  }

  @Test
  void testGameElementFound() throws ElementNotFoundException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    game.addGameElement(TYPE, PIECE_NAME, properties);
    ElementRecord record = game.findElementInfo(TYPE, PIECE_NAME);
    assertEquals(PIECE_NAME, record.name());
    for (Property prop : record.properties()){
      assertEquals(PROPERTY_NAME, prop.name());
      assertEquals(PROPERTY_VALUE, prop.value());
    }
  }

  @Test
  void testNullBoard(){
    assertThrows(NullBoardException.class, () -> game.placeBoardPiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(NullBoardException.class, () -> game.clearBoardCell(HEIGHT+1, WIDTH + 1));
    assertThrows(NullBoardException.class, () -> game.findBoardPieceAt(HEIGHT+1, WIDTH + 1));
  }

  @Test
  void testOutOfBounds(){
    game.makeBoard(WIDTH, HEIGHT);
    assertThrows(IndexOutOfBoundsException.class, () -> game.placeBoardPiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> game.clearBoardCell(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.findBoardPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.placeBoardPiece(-1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> game.clearBoardCell(-1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.findBoardPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement() throws OccupiedCellException, NullBoardException {
    game.makeBoard(WIDTH, HEIGHT);
    game.placeBoardPiece(X, Y, PIECE_NAME);
    assertEquals(PIECE_NAME, game.findBoardPieceAt(X, Y));
  }

  @Test
  void testEmpty() throws OccupiedCellException, NullBoardException {
    game.makeBoard(WIDTH, HEIGHT);
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, game.findBoardPieceAt(i, j));
      }
    }
    game.placeBoardPiece(X, Y, PIECE_NAME);
    game.clearBoardCell(X, Y);
    assertEquals(EMPTY, game.findBoardPieceAt(X, Y));
  }

  @Test
  void testOccupiedCell() throws OccupiedCellException, NullBoardException {
    game.makeBoard(WIDTH, HEIGHT);
    game.placeBoardPiece(X, Y, PIECE_NAME);
    assertThrows(OccupiedCellException.class, () -> game.placeBoardPiece(X, Y, PIECE_NAME));
  }

}

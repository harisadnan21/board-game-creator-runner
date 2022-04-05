package oogasalad.builder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;

import oogasalad.builder.model.property.GenericProperty;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
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
  private static final String PIECE = "piece";
  private static final String PIECE_NAME = "moveTopRight";
  private static final String RULE = "rule";
  private static final String RULE_NAME = "knightMoveTopRight";
  private static final String EMPTY = "empty";
  private static final String PLAYER = "player";
  private static final int X = 5;
  private static final int Y = 7;
  private static final String IMAGE = "image";
  private static final String ACTION_NAME = "moveTopRight";
  private static final String PIECE_IMAGE = "normal.png";
  private static final String PIECE_PLAYER = "white";
  private static final String CONDITION_NAME = "atTopRight";

  private static final String PIECES = "pieces";
  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";

  private BuilderModel game;

  @BeforeEach
  void setUp(){
    game = new GameConfiguration();
  }

  @Test
  void testGameElementNotFound() {
    assertThrows(ElementNotFoundException.class, () -> game.findElementInfo(PIECE, PIECE_NAME));
    assertThrows(ElementNotFoundException.class, () -> game.findElementInfo(PIECE, "not the name"));
  }

  @Test
  void testGameElementFound()
      throws ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    Collection<GenericProperty> properties = new HashSet<>();
    properties.add(new GenericProperty(String.class, IMAGE, PIECE_IMAGE));
    properties.add(new GenericProperty(String.class, PLAYER, PIECE_PLAYER));
    game.addGameElement(PIECE, PIECE_NAME, properties);

    ElementRecord record = game.findElementInfo(PIECE, PIECE_NAME);
    assertEquals(PIECE_NAME, record.name());
    assertEquals(properties, record.properties());
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

  @Test
  void testSerialization()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    game.makeBoard(WIDTH, HEIGHT);

    Collection<GenericProperty> properties = new HashSet<>();
    properties.add(new GenericProperty(Collection.class, PIECES, PIECE_NAME));
    properties.add(new GenericProperty(Collection.class, ACTIONS, ACTION_NAME));
    properties.add(new GenericProperty(Collection.class, CONDITIONS, CONDITION_NAME));
    game.addGameElement(RULE, RULE_NAME, properties);

    properties = new HashSet<>();
    properties.add(new GenericProperty(String.class, IMAGE, PIECE_IMAGE));
    properties.add(new GenericProperty(String.class, PLAYER, PIECE_PLAYER));

    game.addGameElement(PIECE, PIECE_NAME, properties);
    String json = game.toJSON();
    assertEquals(WIDTH * HEIGHT, countMatches(json, EMPTY));

    game.placeBoardPiece(X, Y, PIECE_NAME);
    json = game.toJSON();
    assertEquals(WIDTH * HEIGHT - 1, countMatches(json, EMPTY));
    System.out.println(json);
    assertEquals(3, countMatches(json, PIECE_NAME));
  }

  @Test
  void testSerializationException() {
    assertThrows(NullBoardException.class, () -> game.toJSON());
    game.makeBoard(WIDTH, HEIGHT);
  }

  @Test
  void testLoad() throws OccupiedCellException {
    // TODO: Change test when loading is implemented
    game = game.fromJSON(EMPTY);
  }

  private int countMatches(String str, String target) {
    int lastIndex = 0;
    int count = 0;

    while (lastIndex != -1) {
      lastIndex = str.indexOf(target, lastIndex);
      if (lastIndex != -1) {
        count++;
        lastIndex += target.length();
      }
    }
    return count;
  }

}

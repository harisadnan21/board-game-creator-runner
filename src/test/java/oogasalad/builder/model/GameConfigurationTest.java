package oogasalad.builder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;

import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import org.json.JSONObject;
import org.json.JSONTokener;
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
  private static final String PIECE_NAME = "thisIsAPiece";
  private static final String RULE = "rule";
  private static final String RULE_NAME = "knightMoveTopRight";
  private static final int EMPTY = -1;
  private static final String EMPTY_STRING = "empty";
  private static final String PLAYER = "player";

  private static final int X = 5;
  private static final int Y = 7;
  private static final String IMAGE = "image";
  private static final String ID = "id";
  private static final String ACTION_NAME = "moveTopRight";
  private static final String PIECE_IMAGE = "normal.png";
  private static final int PIECE_PLAYER = 0;
  private static final int PIECE_ID = 100;
  private static final String CONDITION_NAME = "atTopRight";

  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";
  private static final String TEST_LOAD_FILENAME = "data/tests/testLoad.json";

  private Collection<Property> properties;
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
    addPiece();
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
  void testOutOfBounds() throws MissingRequiredPropertyException, InvalidTypeException {
    game.makeBoard(WIDTH, HEIGHT);
    addPiece();
    assertThrows(IndexOutOfBoundsException.class, () -> game.placeBoardPiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> game.clearBoardCell(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.findBoardPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.placeBoardPiece(-1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> game.clearBoardCell(-1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> game.findBoardPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    game.makeBoard(WIDTH, HEIGHT);
    assertThrows(ElementNotFoundException.class, () -> game.placeBoardPiece(X, Y, PIECE_NAME));
    addPiece();

    game.placeBoardPiece(X, Y, PIECE_NAME);
    assertEquals(PIECE_NAME, game.findBoardPieceAt(X, Y));
  }

  @Test
  void testEmpty()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    addPiece();
    game.makeBoard(WIDTH, HEIGHT);
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY_STRING, game.findBoardPieceAt(i, j));
      }
    }
    game.placeBoardPiece(X, Y, PIECE_NAME);
    game.clearBoardCell(X, Y);
    assertEquals(EMPTY_STRING, game.findBoardPieceAt(X, Y));
  }

  @Test
  void testSerialization()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    game.makeBoard(WIDTH, HEIGHT);

    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    game.addGameElement(RULE, RULE_NAME, properties);

    addPiece();

    String json = game.toJSON();
    assertEquals(WIDTH * HEIGHT, countMatches(json, Integer.toString(EMPTY)));

    game.placeBoardPiece(X, Y, PIECE_NAME);
    json = game.toJSON();
    assertEquals(WIDTH * HEIGHT - 1, countMatches(json, Integer.toString(EMPTY)));
    assertEquals(1, countMatches(json, PIECE_NAME));
    assertEquals(2, countMatches(json, Integer.toString(PIECE_ID)));
  }

  @Test
  void testSerializationException() {
    assertThrows(NullBoardException.class, () -> game.toJSON());
    game.makeBoard(WIDTH, HEIGHT);
  }

  @Test
  void testLoad() throws OccupiedCellException, FileNotFoundException {
    // TODO: Change test when loading is implemented
    InputStream is = null;
    is = new DataInputStream(new FileInputStream(TEST_LOAD_FILENAME));
    JSONTokener tokener = new JSONTokener(is);
    JSONObject object = new JSONObject(tokener);
    game.fromJSON(object.toString());
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

  private void addPiece() throws MissingRequiredPropertyException, InvalidTypeException {
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(IMAGE, PIECE_IMAGE));
    properties.add(PropertyFactory.makeProperty(PLAYER, PIECE_PLAYER));
    properties.add(PropertyFactory.makeProperty(ID, PIECE_ID));
    game.addGameElement(PIECE, PIECE_NAME, properties);
  }

}

package oogasalad.builder.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import javafx.stage.Stage;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

/**
 * Tests for BuilderController Class
 *
 * @author Shaan Gondalia
 */
public class ControllerTest extends DukeApplicationTest {

  private static final int HEIGHT = 8;
  private static final int WIDTH = 10;
  private static final String PIECE_NAME = "checker";
  private static final String PIECE_IMAGE = "normal.png";
  private static final int PIECE_PLAYER = 0;
  private static final int PIECE_ID = 100;
  private static final String PIECE_TYPE = "piece";
  private static final String RULE_TYPE = "rule";
  private static final String RULE_NAME = "jump";
  private static final String EMPTY = "empty";
  private static final String TEST_FILENAME = "data/test.json";
  private static final int X = 5;
  private static final int Y = 7;

  private static final String PLAYER = "player";

  private static final String IMAGE = "image";
  private static final String ID = "id";
  private static final String ACTION_NAME = "moveTopRight";
  private static final String CONDITION_NAME = "atTopRight";

  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";

  private BuilderController controller;
  private Collection<Property> properties;

  @Override
  public void start(Stage stage) {
    controller = new BuilderController(stage);
  }

  @Test
  void testGameElementNotFound() {
    assertThrows(ElementNotFoundException.class, () -> controller.getElementProperties(PIECE_TYPE, PIECE_NAME));
    assertThrows(ElementNotFoundException.class, () -> controller.getElementProperties(PIECE_TYPE, "not the name"));
  }

  @Test
  void testGameElementFound()
      throws ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    addPiece();
    controller.update(PIECE_TYPE, PIECE_NAME, properties);
    Collection<Property> newProperties = controller.getElementProperties(PIECE_TYPE, PIECE_NAME);
    assertEquals(properties,newProperties);
  }

  @Test
  void testNull(){
    assertThrows(NullBoardException.class, () -> controller.placePiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(NullBoardException.class, () -> controller.clearCell(HEIGHT+1, WIDTH + 1));
    assertThrows(NullBoardException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
  }

  @Test
  void testOutOfBounds() throws MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(WIDTH, HEIGHT);
    addPiece();
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(-1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(-1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(WIDTH, HEIGHT);
    addPiece();
    controller.placePiece(X, Y, PIECE_NAME);
    assertEquals(PIECE_NAME, controller.findPieceAt(X, Y));
  }

  @Test
  void testEmpty()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(WIDTH, HEIGHT);
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, controller.findPieceAt(i, j));
      }
    }
    addPiece();
    controller.placePiece(X, Y, PIECE_NAME);
    controller.clearCell(X, Y);
    assertEquals(EMPTY, controller.findPieceAt(X, Y));
  }

  @Test
  void testOccupiedCell()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(WIDTH, HEIGHT);
    addPiece();
    controller.placePiece(X, Y, PIECE_NAME);
    assertThrows(OccupiedCellException.class, () -> controller.placePiece(X, Y, PIECE_NAME));
  }

  @Test
  void testSave()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    controller.makeBoard(WIDTH, HEIGHT);
    addPiece();
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    controller.update(RULE_TYPE, RULE_NAME, properties);
    File file = new File(TEST_FILENAME);
    controller.save(file);
  }

  @Test
  void testSaveException() {
    File file = new File(TEST_FILENAME);
    assertThrows(NullBoardException.class, () -> controller.save(file));
    controller.makeBoard(WIDTH, HEIGHT);
  }

  @Test
  void testLoad() throws OccupiedCellException {
    // TODO: Change test when loading is implemented
    File file = new File(TEST_FILENAME);
    controller.load(file);
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
    controller.update(PIECE_TYPE, PIECE_NAME, properties);
  }

  private void addRule() throws MissingRequiredPropertyException, InvalidTypeException {
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    controller.update(RULE_TYPE, RULE_NAME, properties);
  }


}

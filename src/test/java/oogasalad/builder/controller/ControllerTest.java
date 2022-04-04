package oogasalad.builder.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javafx.stage.Stage;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.view.BuilderView;
import org.junit.jupiter.api.BeforeEach;
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
  private static final String PIECE_TYPE = "piece";
  private static final String RULE_TYPE = "rule";
  private static final String RULE_NAME = "jump";
  private static final String EMPTY = "empty";
  private static final String PROPERTY_NAME = "test property name";
  private static final String PROPERTY_VALUE = "test property value";
  private static final String TEST_FILENAME = "data/test.json";
  private static final int X = 5;
  private static final int Y = 7;

  private BuilderController controller;

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
  void testGameElementFound() throws ElementNotFoundException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    controller.update(PIECE_TYPE, PIECE_NAME, properties);
    Collection<Property> newProperties = controller.getElementProperties(PIECE_TYPE, PIECE_NAME);
    for (Property prop : newProperties){
      assertEquals(PROPERTY_NAME, prop.name());
      assertEquals(PROPERTY_VALUE, prop.value());
    }
  }

  @Test
  void testNull(){
    assertThrows(NullBoardException.class, () -> controller.placePiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(NullBoardException.class, () -> controller.clearCell(HEIGHT+1, WIDTH + 1));
    assertThrows(NullBoardException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
  }

  @Test
  void testOutOfBounds(){
    controller.makeBoard(WIDTH, HEIGHT);
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(HEIGHT+1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(-1, WIDTH + 1, PIECE_NAME));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(-1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement() throws OccupiedCellException, NullBoardException {
    controller.makeBoard(WIDTH, HEIGHT);
    controller.placePiece(X, Y, PIECE_NAME);
    assertEquals(PIECE_NAME, controller.findPieceAt(X, Y));
  }

  @Test
  void testEmpty() throws OccupiedCellException, NullBoardException {
    controller.makeBoard(WIDTH, HEIGHT);
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, controller.findPieceAt(i, j));
      }
    }
    controller.placePiece(X, Y, PIECE_NAME);
    controller.clearCell(X, Y);
    assertEquals(EMPTY, controller.findPieceAt(X, Y));
  }

  @Test
  void testOccupiedCell() throws OccupiedCellException, NullBoardException {
    controller.makeBoard(WIDTH, HEIGHT);
    controller.placePiece(X, Y, PIECE_NAME);
    assertThrows(OccupiedCellException.class, () -> controller.placePiece(X, Y, PIECE_NAME));
  }

  @Test
  void testSave()
      throws OccupiedCellException, NullBoardException, ElementNotFoundException {
    controller.makeBoard(WIDTH, HEIGHT);

    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    properties.add(new Property(String.class, PROPERTY_NAME, PROPERTY_VALUE));
    controller.update(PIECE_TYPE, PIECE_NAME, properties);
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

}

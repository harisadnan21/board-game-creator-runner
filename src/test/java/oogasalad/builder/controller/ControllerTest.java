package oogasalad.builder.controller;

import javafx.stage.Stage;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.callback.ClearCellBackgroundCallback;
import oogasalad.builder.view.callback.ClearCellCallback;
import oogasalad.builder.view.callback.ColorCellBackgroundCallback;
import oogasalad.builder.view.callback.FindCellBackgroundCallback;
import oogasalad.builder.view.callback.FindPieceAtCallback;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.GetHeightCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.GetWidthCallback;
import oogasalad.builder.view.callback.LoadCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;
import oogasalad.builder.view.callback.SaveCallback;
import oogasalad.builder.view.callback.UpdateGameElementCallback;
import org.jooq.impl.QOM.Ge;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for BuilderController Class
 *
 * @author Shaan Gondalia
 */
public class ControllerTest extends DukeApplicationTest {

  private static final int HEIGHT = 8;
  private static final int WIDTH = 10;
  private static final String PIECE_NAME = "checker";
  private static final String PIECE_IMAGE = "data/images/back.png";
  private static final int PIECE_PLAYER = 0;
  private static final int PIECE_ID = 100;
  private static final String PIECE_TYPE = "piece";
  private static final String RULE_TYPE = "rule";
  private static final String RULE_NAME = "jump";
  private static final int RULE_REP_X = 1;
  private static final int RULE_REP_Y = 2;
  private static final String REPRESENTATIVE_X = "representativeX";
  private static final String REPRESENTATIVE_Y = "representativeY";
  private static final String EMPTY = "empty";
  private static final String TEST_SAVE_DIRECTORY = "data/tests/save/";
  private static final String TEST_SAVE_EXCEPTION_FILENAME = "data/tests/exception/";
  private static final String TEST_LOAD_DIRECTORY = "data/tests/load/";
  private static final int X = 5;
  private static final int Y = 7;

  private static final String PLAYER = "player";

  private static final String IMAGE = "image";
  private static final String ID = "id";
  private static final String ACTION_NAME = "moveTopRight";
  private static final String CONDITION_NAME = "atTopRight";

  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";
  private static final String BLACK = "0x000000ff";
  private static final String WHITE = "0xffffffff";
  public static final String BAD_PATH_TO_DIRECTORY = "bad/path/to/directory";
  private static final String IS_PERSISTENT = "isPersistent";

  private BuilderController controller;
  private Collection<Property> properties;

  @Override
  public void start(Stage stage) {
    controller = new BuilderController(new BuilderView(stage));
  }

  @Test
  void testGameElementNotFound() {
    assertThrows(ElementNotFoundException.class, () -> controller.getElementProperties(new GetElementPropertiesCallback(PIECE_TYPE, PIECE_NAME)));
    assertThrows(ElementNotFoundException.class, () -> controller.getElementProperties(new GetElementPropertiesCallback(PIECE_TYPE, "not the name")));
  }

  @Test
  void testGameElementFound()
      throws ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    addPiece();
    controller.update(new UpdateGameElementCallback(PIECE_TYPE, PIECE_NAME, properties));
    Collection<Property> newProperties = controller.getElementProperties(new GetElementPropertiesCallback(PIECE_TYPE, PIECE_NAME));
    assertEquals(properties,newProperties);
  }

  @Test
  void testNull(){
    assertThrows(NullBoardException.class, () -> controller.placePiece(new PlacePieceCallback(HEIGHT+1, WIDTH + 1, PIECE_NAME)));
    assertThrows(NullBoardException.class, () -> controller.clearCell(new ClearCellCallback(HEIGHT+1, WIDTH + 1)));
    assertThrows(NullBoardException.class, () -> controller.findPieceAt(new FindPieceAtCallback(HEIGHT+1, WIDTH + 1)));
  }

  @Test
  void testDimensions() {
    assertThrows(NullBoardException.class, () -> controller.getWidth(new GetWidthCallback()));
    assertThrows(NullBoardException.class, () -> controller.getHeight(new GetHeightCallback()));
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    assertEquals(WIDTH, controller.getWidth(new GetWidthCallback()));
    assertEquals(HEIGHT, controller.getHeight(new GetHeightCallback()));
  }

  @Test
  void testOutOfBounds() throws MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(new PlacePieceCallback(HEIGHT+1, WIDTH + 1, PIECE_NAME)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(new ClearCellCallback(HEIGHT+1, WIDTH + 1)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(new FindPieceAtCallback(+1, WIDTH + 1)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.placePiece(new PlacePieceCallback(-1, WIDTH + 1, PIECE_NAME)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.clearCell(new ClearCellCallback(-1, WIDTH + 1)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(new FindPieceAtCallback(-1, WIDTH + 1)));
  }

  @Test
  void testPiecePlacement()
      throws NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    controller.placePiece(new PlacePieceCallback(X, Y, PIECE_NAME));
    assertEquals(PIECE_NAME, controller.findPieceAt(new FindPieceAtCallback(X, Y)));
  }

  @Test
  void testEmpty()
      throws NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException {
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, controller.findPieceAt(new FindPieceAtCallback(i, j)));
      }
    }
    addPiece();
    controller.placePiece(new PlacePieceCallback(X, Y, PIECE_NAME));
    controller.clearCell(new ClearCellCallback(X, Y));
    assertEquals(EMPTY, controller.findPieceAt(new FindPieceAtCallback(X, Y)));
  }

  @Test
  void testRequiredProperties() {
    controller.getRequiredProperties(new GetPropertiesCallback(PIECE_TYPE));
  }

  @Test
  void testGetElementNames() {
    Collection<String> names = controller.getElementNames(new GetElementNamesCallback(PIECE_TYPE));
    assertTrue(names.isEmpty());
    addPiece();
    names = controller.getElementNames(new GetElementNamesCallback(PIECE_TYPE));
    for (String name : names) {
      assertEquals(PIECE_NAME, name);
    }
  }

  @Test
  void testGetElementPropertyByKeyNotFound() {
    assertThrows(ElementNotFoundException.class, () -> controller.getElementPropertyByKey(new GetElementPropertyByKeyCallback(PIECE_TYPE, PIECE_NAME, IMAGE)));
  }

  @Test
  void testGetElementPropertyByKey() {
    addPiece();
    String image = controller.getElementPropertyByKey(new GetElementPropertyByKeyCallback(PIECE_TYPE, PIECE_NAME, IMAGE));
    assertEquals(PIECE_IMAGE, image);
  }

  @Test
  void testSaveFileNotFound() {
    File file = new File(BAD_PATH_TO_DIRECTORY);
    assertThrows(RuntimeException.class, () -> controller.save(new SaveCallback(file)));
  }

  @Test
  void testLoadFileNotFound() {
    File file = new File(BAD_PATH_TO_DIRECTORY);
    assertThrows(RuntimeException.class, () -> controller.load(new LoadCallback(file)));
  }

  @Test
  void testSave()
      throws NullBoardException, ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException {
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    properties.add(PropertyFactory.makeProperty(REPRESENTATIVE_X, RULE_REP_X));
    properties.add(PropertyFactory.makeProperty(REPRESENTATIVE_Y, RULE_REP_Y));
    properties.add(PropertyFactory.makeProperty(IS_PERSISTENT, 0));
    controller.update(new UpdateGameElementCallback(RULE_TYPE, RULE_NAME, properties));
    File file = new File(TEST_SAVE_DIRECTORY);
    controller.save(new SaveCallback(file));
  }

  @Test
  void testSaveException() {
    File file = new File(TEST_SAVE_EXCEPTION_FILENAME);
    assertThrows(NullBoardException.class, () -> controller.save(new SaveCallback(file)));
  }

  @Test
  void testLoad() {
    // TODO: Change test when loading is implemented
    File file = new File(TEST_LOAD_DIRECTORY);
    controller.load(new LoadCallback(file));
    file = new File(TEST_SAVE_EXCEPTION_FILENAME);
    controller.save(new SaveCallback(file));
  }

  @Test
  void testColoring() {
    controller.makeBoard(new MakeBoardCallback(WIDTH, HEIGHT));
    controller.colorCellBackground(new ColorCellBackgroundCallback(X, Y, BLACK));
    assertEquals(BLACK, controller.findCellBackground(new FindCellBackgroundCallback(X, Y)));
    controller.clearCellBackground(new ClearCellBackgroundCallback(X, Y));
    assertEquals(WHITE, controller.findCellBackground(new FindCellBackgroundCallback(X, Y)));
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
    controller.update(new UpdateGameElementCallback(PIECE_TYPE, PIECE_NAME, properties));
  }

  private void addRule() throws MissingRequiredPropertyException, InvalidTypeException {
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    controller.update(new UpdateGameElementCallback(RULE_TYPE, RULE_NAME, properties));
  }


}

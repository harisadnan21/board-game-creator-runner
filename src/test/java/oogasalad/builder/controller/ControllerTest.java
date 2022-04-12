package oogasalad.builder.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javafx.stage.Stage;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.exception.ElementNotFoundException;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.exception.NullBoardException;
import oogasalad.builder.model.exception.OccupiedCellException;
import oogasalad.builder.model.property.PropertyFactory;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.callback.*;
import oogasalad.engine.model.action.Place;
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
  private final Method getElementProperties = controller.getClass().getDeclaredMethod("getElementProperties", GetElementPropertiesCallback.class);
  private final Method update = controller.getClass().getDeclaredMethod("update", UpdateGameElementCallback.class);
  private final Method placePiece = controller.getClass().getDeclaredMethod("placePiece", PlacePieceCallback.class);
  private final Method clearCell = controller.getClass().getDeclaredMethod("clearCell", ClearCellCallback.class);
  private final Method makeBoard = controller.getClass().getDeclaredMethod("makeBoard", MakeBoardCallback.class);
  private final Method save = controller.getClass().getDeclaredMethod("save", SaveCallback.class);

  public ControllerTest() throws NoSuchMethodException {
  }

  @Override
  public void start(Stage stage) {
    controller = new BuilderController(new BuilderView(stage));
    Arrays.stream(controller.getClass().getDeclaredMethods()).forEach(m -> m.setAccessible(true));
  }

  @Test
  void testGameElementNotFound() {
    assertThrows(ElementNotFoundException.class, () -> getElementProperties.invoke(controller, new GetElementPropertiesCallback(PIECE_TYPE, PIECE_NAME)));
    assertThrows(ElementNotFoundException.class, () -> getElementProperties.invoke(controller, new GetElementPropertiesCallback(PIECE_TYPE, "not the name")));
  }

  @Test
  void testGameElementFound()
          throws ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException, InvocationTargetException, IllegalAccessException {
    addPiece();
    update.invoke(controller, new UpdateGameElementCallback(PIECE_TYPE, PIECE_NAME, properties));
    Collection<Property> newProperties = (Collection<Property>) getElementProperties.invoke(controller, new GetElementPropertiesCallback(PIECE_TYPE, PIECE_NAME));
    assertEquals(properties,newProperties);
  }

  @Test
  void testNull(){
    assertThrows(NullBoardException.class, () -> placePiece.invoke(controller, new PlacePieceCallback(HEIGHT+1, WIDTH + 1, PIECE_NAME)));
    assertThrows(NullBoardException.class, () -> clearCell.invoke(controller, new ClearCellCallback(HEIGHT+1, WIDTH + 1)));
    assertThrows(NullBoardException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
  }

  @Test
  void testOutOfBounds() throws MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    assertThrows(IndexOutOfBoundsException.class, () -> placePiece.invoke(controller, new PlacePieceCallback(HEIGHT+1, WIDTH + 1, PIECE_NAME)));
    assertThrows(IndexOutOfBoundsException.class, () -> clearCell.invoke(controller, new ClearCellCallback(HEIGHT+1, WIDTH + 1)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(HEIGHT+1, WIDTH + 1));
    assertThrows(IndexOutOfBoundsException.class, () -> placePiece.invoke(controller, new PlacePieceCallback(-1, WIDTH + 1, PIECE_NAME)));
    assertThrows(IndexOutOfBoundsException.class, () -> clearCell.invoke(controller, new ClearCellCallback(-1, WIDTH + 1)));
    assertThrows(IndexOutOfBoundsException.class, () -> controller.findPieceAt(-1, WIDTH + 1));
  }

  @Test
  void testPiecePlacement()
          throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    placePiece.invoke(controller, new PlacePieceCallback(X, Y, PIECE_NAME));
    assertEquals(PIECE_NAME, controller.findPieceAt(X, Y));
  }

  @Test
  void testEmpty()
          throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
    for (int i = 0; i < WIDTH; i++) {
      for (int j = 0; j < HEIGHT; j++) {
        assertEquals(EMPTY, controller.findPieceAt(i, j));
      }
    }
    addPiece();
    placePiece.invoke(controller, new PlacePieceCallback(X, Y, PIECE_NAME));
    clearCell.invoke(controller, new ClearCellCallback(X, Y));
    assertEquals(EMPTY, controller.findPieceAt(X, Y));
  }

  @Test
  void testOccupiedCell()
          throws OccupiedCellException, NullBoardException, ElementNotFoundException, MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    placePiece.invoke(controller, new PlacePieceCallback(X, Y, PIECE_NAME));
    assertThrows(OccupiedCellException.class, () -> placePiece.invoke(controller, new PlacePieceCallback(X, Y, PIECE_NAME)));
  }

  @Test
  void testSave()
          throws OccupiedCellException, NullBoardException, ElementNotFoundException, InvalidTypeException, MissingRequiredPropertyException, InvocationTargetException, IllegalAccessException {
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
    addPiece();
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    update.invoke(controller, new UpdateGameElementCallback(RULE_TYPE, RULE_NAME, properties));
    File file = new File(TEST_FILENAME);
    save.invoke(controller, new SaveCallback(file));
  }

  @Test
  void testSaveException() throws InvocationTargetException, IllegalAccessException {
    File file = new File(TEST_FILENAME);
    assertThrows(NullBoardException.class, () -> save.invoke(controller, new SaveCallback(file)));
    makeBoard.invoke(controller, new MakeBoardCallback(WIDTH, HEIGHT));
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

  private void addPiece() throws MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(IMAGE, PIECE_IMAGE));
    properties.add(PropertyFactory.makeProperty(PLAYER, PIECE_PLAYER));
    properties.add(PropertyFactory.makeProperty(ID, PIECE_ID));
    update.invoke(controller, new UpdateGameElementCallback(PIECE_TYPE, PIECE_NAME, properties));
  }

  private void addRule() throws MissingRequiredPropertyException, InvalidTypeException, InvocationTargetException, IllegalAccessException {
    properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(ACTIONS, ACTION_NAME));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, CONDITION_NAME));
    update.invoke(controller, new UpdateGameElementCallback(RULE_TYPE, RULE_NAME, properties));
  }


}

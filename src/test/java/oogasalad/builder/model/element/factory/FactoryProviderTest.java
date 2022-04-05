package oogasalad.builder.model.element.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for FactoryProvider class
 *
 * @author Shaan Gondalia
 */
public class FactoryProviderTest {

  private FactoryProvider provider;
  private static final String INVALID_TYPE = "gibberish";
  private static final String TYPE = "type";
  private static final String IMAGE = "image";
  private static final String PLAYER = "player";
  private static final String ACTION = "action";
  private static final String ACTION_NAME = "moveTopRight";
  private static final String ACTION_TYPE = "move";
  private static final String PIECE = "piece";
  private static final String PIECE_NAME = "moveTopRight";
  private static final String PIECE_IMAGE = "normal.png";
  private static final String PIECE_PLAYER = "white";
  private static final String CONDITION = "condition";
  private static final String CONDITION_NAME = "emptyAtTopRight";
  private static final String CONDITION_TYPE = "isEmpty";
  private static final String WIN_CONDITION = "win condition";
  private static final String WIN_CONDITION_NAME = "winIfCaptured";
  private static final String RULE = "rule";
  private static final String RULE_NAME = "knightMoveTopRight";

  private static final String PIECES = "pieces";
  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";

  private static final String COORDINATE_NAME_ONE = "x";
  private static final String COORDINATE_VALUE_ONE = "1";
  private static final String COORDINATE_NAME_TWO = "y";
  private static final String COORDINATE_VALUE_TWO = "2";

  @BeforeEach
  void setUp(){
    provider = new FactoryProvider();
  }

  @Test
  void testCreateAction() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, TYPE, ACTION_TYPE));
    properties.add(new Property(Integer.class, COORDINATE_NAME_ONE, COORDINATE_VALUE_ONE));
    properties.add(new Property(Integer.class, COORDINATE_NAME_TWO, COORDINATE_VALUE_TWO));
    GameElement action = provider.createElement(ACTION, ACTION_NAME, properties);
    ElementRecord record = action.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(ACTION_NAME, record.name());
  }

  @Test
  void testCreatePiece() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, IMAGE, PIECE_IMAGE));
    properties.add(new Property(String.class, PLAYER, PIECE_PLAYER));
    GameElement piece = provider.createElement(PIECE, PIECE_NAME, properties);
    ElementRecord record = piece.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(PIECE_NAME, record.name());
  }

  @Test
  void testCreateCondition() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(String.class, TYPE, CONDITION_TYPE));
    properties.add(new Property(Integer.class, COORDINATE_NAME_ONE, COORDINATE_VALUE_ONE));
    properties.add(new Property(Integer.class, COORDINATE_NAME_TWO, COORDINATE_VALUE_TWO));
    GameElement condition = provider.createElement(CONDITION, CONDITION_NAME, properties);
    ElementRecord record = condition.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(CONDITION_NAME, record.name());
  }

  @Test
  void testCreateWinCondition() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    GameElement winCondition = provider.createElement(WIN_CONDITION, WIN_CONDITION_NAME, properties);
    ElementRecord record = winCondition.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(WIN_CONDITION_NAME, record.name());
  }

  @Test
  void testCreateRule() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(Collection.class, PIECES, PIECE_NAME));
    properties.add(new Property(Collection.class, ACTIONS, ACTION_NAME));
    properties.add(new Property(Collection.class, CONDITIONS, CONDITION_NAME));
    GameElement rule = provider.createElement(RULE, RULE_NAME, properties);
    ElementRecord record = rule.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(RULE_NAME, record.name());
  }

  @Test
  void testGetRequiredProperties() throws InvalidTypeException {
    Collection<Property> required = provider.getRequiredProperties(RULE);
    //TODO: Add more rigorous tests
  }
  
  @Test
  void testInvalidTypeException() {
    Collection<Property> properties = new HashSet<>();
    assertThrows(InvalidTypeException.class, () -> provider.getRequiredProperties(INVALID_TYPE));
    assertThrows(InvalidTypeException.class, () -> provider.createElement(INVALID_TYPE, RULE_NAME, properties));
  }

}

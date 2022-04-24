package oogasalad.builder.model.element.factory;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import oogasalad.builder.model.property.StringListProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
  private static final String ID = "id";
  private static final String ACTION = "action";
  private static final String ACTION_NAME = "placeTopRight";
  private static final String ACTION_TYPE = "Place";
  private static final String PIECE = "piece";
  private static final String PIECE_NAME = "moveTopRight";
  private static final String PIECE_IMAGE = "normal.png";
  private static final int PIECE_PLAYER = 0;
  private static final int PIECE_ID = 100;
  private static final String CONDITION = "condition";
  private static final String CONDITION_NAME = "emptyAtTopRight";
  private static final String CONDITION_TYPE = "IsEmpty";
  private static final String WIN_CONDITION = "winCondition";
  private static final String WIN_CONDITION_NAME = "Draw the game";
  private static final String WIN_CONDITION_TYPE = "Draw";
  private static final String RULE = "rule";
  private static final String RULE_NAME = "knightMoveTopRight";
  private static final int RULE_REP_X = 1;
  private static final int RULE_REP_Y = 2;

  private static final String PIECES = "pieces";
  private static final String ACTIONS = "actions";
  private static final String CONDITIONS = "conditions";
  private static final String REPRESENTATIVE_X = "representativeX";
  private static final String REPRESENTATIVE_Y = "representativeY";
  private static final String IS_PERSISTENT = "isPersistent";
  private static final String IS_ABSOLUTE = "isAbsolute";


  private static final String COORDINATE_NAME_ONE = "col";
  private static final String COORDINATE_VALUE_ONE = "1";
  private static final String COORDINATE_NAME_TWO = "row";
  private static final String COORDINATE_VALUE_TWO = "2";

  @BeforeEach
  void setUp(){
    provider = new FactoryProvider();
  }

  @Test
  void testCreateAction() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(TYPE, ACTION_TYPE));
    properties.add(PropertyFactory.makeProperty(COORDINATE_NAME_ONE, COORDINATE_VALUE_ONE));
    properties.add(PropertyFactory.makeProperty(COORDINATE_NAME_TWO, COORDINATE_VALUE_TWO));
    properties.add(PropertyFactory.makeProperty(PLAYER, "0"));
    properties.add(PropertyFactory.makeProperty(ID, "0"));
    properties.add(PropertyFactory.makeProperty(IS_ABSOLUTE, 0));
    GameElement action = provider.createElement(ACTION, ACTION_NAME, properties);
    ElementRecord record = action.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(ACTION_NAME, record.name());
  }

  @Test
  void testCreatePiece() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(IMAGE, PIECE_IMAGE));
    properties.add(PropertyFactory.makeProperty(PLAYER, PIECE_PLAYER));
    properties.add(PropertyFactory.makeProperty(ID, PIECE_ID));
    GameElement piece = provider.createElement(PIECE, PIECE_NAME, properties);
    ElementRecord record = piece.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(PIECE_NAME, record.name());
  }

  @Test
  void testCreateCondition() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(TYPE, CONDITION_TYPE));
    properties.add(PropertyFactory.makeProperty(COORDINATE_NAME_ONE, COORDINATE_VALUE_ONE));
    properties.add(PropertyFactory.makeProperty(COORDINATE_NAME_TWO, COORDINATE_VALUE_TWO));
    properties.add(PropertyFactory.makeProperty(IS_ABSOLUTE, 0));
    GameElement condition = provider.createElement(CONDITION, CONDITION_NAME, properties);
    ElementRecord record = condition.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(CONDITION_NAME, record.name());
  }

  @Test
  void testCreateWinCondition() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    Collection<String> conditions = new HashSet<>();
    conditions.add(CONDITION_NAME);
    properties.add(PropertyFactory.makeProperty(CONDITIONS, conditions));
    properties.add(PropertyFactory.makeProperty(TYPE, WIN_CONDITION_TYPE));

    GameElement winCondition = provider.createElement(WIN_CONDITION, WIN_CONDITION_NAME, properties);
    ElementRecord record = winCondition.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(WIN_CONDITION_NAME, record.name());
  }

  @Test
  void testCreateRule() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    Collection<String> pieces = new HashSet<>();
    Collection<String> actions = new HashSet<>();
    Collection<String> conditions = new HashSet<>();
    pieces.add(PIECE_NAME);
    actions.add(ACTION_NAME);
    conditions.add(CONDITION_NAME);
    properties.add(PropertyFactory.makeProperty(PIECES, pieces));
    properties.add(PropertyFactory.makeProperty(ACTIONS, actions));
    properties.add(PropertyFactory.makeProperty(CONDITIONS, conditions));
    properties.add(PropertyFactory.makeProperty(REPRESENTATIVE_X, RULE_REP_X));
    properties.add(PropertyFactory.makeProperty(REPRESENTATIVE_Y, RULE_REP_Y));
    properties.add(PropertyFactory.makeProperty(IS_PERSISTENT, 0));
    GameElement rule = provider.createElement(RULE, RULE_NAME, properties);
    ElementRecord record = rule.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(RULE_NAME, record.name());
  }

  @Test
  void testGetRequiredProperties() throws InvalidTypeException {
    Collection<Property> required = provider.getRequiredProperties(RULE);
    assertTrue(required.contains(new StringListProperty("required-conditions", "", "")));
    // Clear it to make sure it returns a copy and not the real thing
    try {
      required.clear();
    } catch(UnsupportedOperationException e) {
      // Depending on the implementation, this might be unsupported, but we don't want to care either way, just test that nothing changes
    }
    required = provider.getRequiredProperties(RULE);
    assertTrue(required.contains(new StringListProperty("required-conditions", "", "")));
  }
  
  @Test
  void testInvalidTypeException() {
    Collection<Property> properties = new HashSet<>();
    assertThrows(InvalidTypeException.class, () -> provider.getRequiredProperties(INVALID_TYPE));
    assertThrows(InvalidTypeException.class, () -> provider.createElement(INVALID_TYPE, RULE_NAME, properties));
  }

}

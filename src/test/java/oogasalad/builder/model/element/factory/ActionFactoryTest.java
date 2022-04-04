package oogasalad.builder.model.element.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.Action;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ActionFactory Class
 *
 * @author Shaan Gondalia
 */
public class ActionFactoryTest {

  private ActionFactory actionFactory;
  private static final String ACTION_NAME = "moveTopRight";
  private static final String PROPERTY_NAME_TYPE = "type";
  private static final String ACTION_TYPE = "move";
  private static final String PROPERTY_NAME_ONE = "x";
  private static final String PROPERTY_VALUE_ONE = "1";
  private static final String PROPERTY_NAME_TWO = "y";
  private static final String PROPERTY_VALUE_TWO = "2";

  @BeforeEach
  void setUp(){
    actionFactory = new ActionFactory();
  }

  @Test
  void testActionCreated() throws MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(new Property(Integer.class, PROPERTY_NAME_TYPE, ACTION_TYPE));
    properties.add(new Property(Integer.class, PROPERTY_NAME_ONE, PROPERTY_VALUE_ONE));
    properties.add(new Property(Integer.class, PROPERTY_NAME_TWO, PROPERTY_VALUE_TWO));
    Action action = actionFactory.createElement(ACTION_NAME, properties);

    ElementRecord record = action.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(ACTION_NAME, record.name());
  }

}

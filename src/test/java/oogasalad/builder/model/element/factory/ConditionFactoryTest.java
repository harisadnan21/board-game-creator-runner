package oogasalad.builder.model.element.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.element.Condition;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.PropertyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConditionFactory Class
 *
 * @author Shaan Gondalia
 */
public class ConditionFactoryTest {

  private ConditionFactory conditionFactory;
  private static final String CONDITION_NAME = "emptyTopRight";
  private static final String PROPERTY_NAME_TYPE = "type";
  private static final String CONDITION_TYPE = "isEmpty";
  private static final String INVALID_CONDITION_TYPE = "emptyAt";
  private static final String PROPERTY_NAME_ONE = "x";
  private static final String PROPERTY_VALUE_ONE = "1";
  private static final String PROPERTY_NAME_TWO = "y";
  private static final String PROPERTY_VALUE_TWO = "1";

  @BeforeEach
  void setUp(){
    conditionFactory = new ConditionFactory();
  }

  @Test
  void testConditionCreated() throws MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_TYPE, CONDITION_TYPE));
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_ONE, PROPERTY_VALUE_ONE));
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_TWO, PROPERTY_VALUE_TWO));
    Condition condition = conditionFactory.createElement(CONDITION_NAME, properties);

    ElementRecord record = condition.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(CONDITION_NAME, record.name());
  }

  @Test
  void testConditionMissingRequired() {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_TYPE, CONDITION_TYPE));
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_ONE, PROPERTY_VALUE_ONE));
    assertThrows(MissingRequiredPropertyException.class, () ->
        conditionFactory.createElement(CONDITION_NAME, properties));
  }

  @Test
  void testConditionInvalidType() {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_TYPE, INVALID_CONDITION_TYPE));
    properties.add(PropertyFactory.makeProperty(PROPERTY_NAME_ONE, PROPERTY_VALUE_ONE));
    assertThrows(MissingRequiredPropertyException.class, () ->
        conditionFactory.createElement(CONDITION_NAME, properties));
  }

}

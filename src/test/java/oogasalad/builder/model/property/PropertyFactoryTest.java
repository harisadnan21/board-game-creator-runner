package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

/**
 * Test for PropertyFactory Class
 *
 * @author Shaan Gondalia
 */
public class PropertyFactoryTest {

  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE = "value";
  private static final Integer PROPERTY_VALUE_INTEGER = 1;
  private static final Collection<String> PROPERTY_VALUE_COLLECTION = new ArrayList<>();
  private static final String PROPERTY_FORM = "form";

  @Test
  void testCreateProperty() {
    Property property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE, property.valueAsString());
    property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE, property.valueAsString());
  }

  @Test
  void testCreateIntegerProperty() {
    Property property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE_INTEGER);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE_INTEGER, property.value());
    assertEquals(PROPERTY_VALUE_INTEGER.toString(), property.valueAsString());
    property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE_INTEGER, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE_INTEGER, property.value());
    assertEquals(PROPERTY_VALUE_INTEGER.toString(), property.valueAsString());
  }

  @Test
  void testCreateCollectionProperty() {
    Property property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE_COLLECTION);
    assertEquals(PROPERTY_NAME, property.name());
    property = PropertyFactory.makeProperty(PROPERTY_NAME, PROPERTY_VALUE_COLLECTION, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
  }

}

package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test for IntegerProperty Class
 *
 * @author Shaan Gondalia
 */
public class IntegerPropertyTest {

  private static final String PROPERTY_NAME = "propName";
  private static final int PROPERTY_VALUE = 1;
  private static final String PROPERTY_VALUE_STRING = "1";
  private static final String PROPERTY_FORM = "form";


  @Test
  void testCreateProperty() {
    Property property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE_STRING, property.valueAsString());
  }

  @Test
  void testCreatePropertyFromString() {
    Property property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE_STRING, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE_STRING, property.valueAsString());
  }

  @Test
  void testWith() {
    Property property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property newProperty = property.with(PROPERTY_NAME, PROPERTY_VALUE_STRING, PROPERTY_VALUE_STRING, PROPERTY_FORM);
    assertEquals(property, newProperty);
  }

  @Test
  void testEquality() {
    Property property1 = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    assertEquals(property1, property2);
  }

  @Test
  void testHashCode() {
    Property property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    int code = property.hashCode();
  }
}

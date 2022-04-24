package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for String Property Class
 *
 * @author Shaan Gondalia
 */
public class StringPropertyTest {

  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE = "value";
  private static final String DIFFERENT_VALUE = "Different value";
  private static final String PROPERTY_FORM = "form";

  @Test
  void testCreateProperty() {
    Property property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE, property.valueAsString());
  }

  @Test
  void testWith() {
    Property property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property newProperty = property.with(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property withChangedValue = property.with(DIFFERENT_VALUE);
    assertEquals(property, withChangedValue);
    assertFalse(property.fullEquals(withChangedValue));
    assertEquals(withChangedValue.valueAsString(), DIFFERENT_VALUE);
    assertEquals(property, newProperty);
  }

  @Test
  void testEquality() {
    Property property1 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property3 = new StringProperty("Different name", PROPERTY_VALUE, PROPERTY_FORM);
    Property property4 = new StringProperty(PROPERTY_NAME, DIFFERENT_VALUE, PROPERTY_FORM);
    assertEquals(property1, property2);
    assertEquals(property1, property4);
    assertNotEquals(property1, property3);
  }

  @Test
  void testHashCode() {
    Property property1 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, "Different form");
    assertEquals(property1.hashCode(), property2.hashCode());
  }

  @Test
  void testFullEquals() {
    Property property1 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property3 = new StringProperty(PROPERTY_NAME, DIFFERENT_VALUE, PROPERTY_FORM);
    assertTrue(property1.fullEquals(property2));
    assertFalse(property1.fullEquals(property3));
  }

  @Test
  void testShortName() {
    Property property1 = new StringProperty("required-some-namespace-here-" + PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    assertEquals(property1.shortName(), PROPERTY_NAME);
    assertEquals(property2.shortName(), PROPERTY_NAME);

  }
}

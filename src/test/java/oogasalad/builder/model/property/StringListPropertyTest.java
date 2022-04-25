package oogasalad.builder.model.property;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringListPropertyTest {
  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE = "value,otherValue";
  private static final String DIFFERENT_VALUE = "Different value";
  private static final String PROPERTY_FORM = "form";

  @Test
  void testWith() {
    Property<Collection<String>> property = new StringListProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property<Collection<String>> newProperty = property.with(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property<Collection<String>> withChangedValue = property.with(DIFFERENT_VALUE);
    assertEquals(property, withChangedValue);
    assertFalse(property.fullEquals(withChangedValue));
    assertEquals(withChangedValue.valueAsString(), DIFFERENT_VALUE);
    assertEquals(property, newProperty);
  }

  @Test
  void testValueToString() {
    StringListProperty prop = new StringListProperty(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    prop.valueToString(List.of("hi", "test"));
    assertEquals(prop.valueToString(List.of("hi", "test")), "hi,test");
  }

}

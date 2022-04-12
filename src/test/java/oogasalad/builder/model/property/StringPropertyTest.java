package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;

/**
 * Test for String Property Class
 *
 * @author Shaan Gondalia
 */
public class StringPropertyTest {

  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE = "value";
  private static final String PROPERTY_FORM = "form";

  @Test
  void testCreateProperty() {
<<<<<<< HEAD:src/test/java/oogasalad/builder/model/property/StringPropertyTest.java
    Property property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
=======
    Property property = new Property(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
>>>>>>> 10ace8cde828626bc985b218674c2fb030d12ea8:src/test/java/oogasalad/builder/model/property/PropertyTest.java
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE, property.valueAsString());
  }

  @Test
  void testEquality() {
<<<<<<< HEAD:src/test/java/oogasalad/builder/model/property/StringPropertyTest.java
    Property property1 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    Property property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
=======
    Property property1 = new Property(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
    Property property2 = new Property(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
>>>>>>> 10ace8cde828626bc985b218674c2fb030d12ea8:src/test/java/oogasalad/builder/model/property/PropertyTest.java
    assertEquals(property1, property2);
  }

  @Test
  void testHashCode() {
<<<<<<< HEAD:src/test/java/oogasalad/builder/model/property/StringPropertyTest.java
    Property property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
=======
    Property property = new Property(PROPERTY_NAME, PROPERTY_VALUE, PROPERTY_FORM);
>>>>>>> 10ace8cde828626bc985b218674c2fb030d12ea8:src/test/java/oogasalad/builder/model/property/PropertyTest.java
    int code = property.hashCode();
  }
}

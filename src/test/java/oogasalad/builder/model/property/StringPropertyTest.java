package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test for String Property Class
 *
 * @author Shaan Gondalia
 */
public class StringPropertyTest {

  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE = "value";

  private static final String EXPECTED_JSON = "{\"propName\":\"value\"}";


  @Test
  void testCreateProperty() {
    StringProperty property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(PROPERTY_VALUE, property.toString());
  }

  @Test
  void testEquality() {
    StringProperty property1 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    StringProperty property2 = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(property1, property2);
  }

  @Test
  void testSerialization() throws Exception {
    StringProperty property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(EXPECTED_JSON, property.toJSON());
  }

  @Test
  void testLoad() {
    StringProperty property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    property.fromJSON(EXPECTED_JSON);
  }

  @Test
  void testHashCode() {
    StringProperty property = new StringProperty(PROPERTY_NAME, PROPERTY_VALUE);
    int code = property.hashCode();
  }
}

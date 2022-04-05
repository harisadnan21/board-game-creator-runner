package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Test for Integer Property Class
 *
 * @author Shaan Gondalia
 */
public class IntegerPropertyTest {

  private static final String PROPERTY_NAME = "integer";
  private static final int PROPERTY_VALUE = 123;

  private static final String EXPECTED_JSON = "{\"integer\":123}";


  @Test
  void testCreateProperty() {
    IntegerProperty property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(PROPERTY_VALUE, property.value());
    assertEquals(Integer.toString(PROPERTY_VALUE), property.toString());
  }

  @Test
  void testEquality() {
    IntegerProperty property1 = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    IntegerProperty property2 = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(property1, property2);
  }

  @Test
  void testSerialization() throws Exception {
    IntegerProperty property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    assertEquals(EXPECTED_JSON, property.toJSON());
  }

  @Test
  void testLoad() {
    IntegerProperty property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    property.fromJSON(EXPECTED_JSON);
  }

  @Test
  void testHashCode() {
    IntegerProperty property = new IntegerProperty(PROPERTY_NAME, PROPERTY_VALUE);
    int code = property.hashCode();
  }
}

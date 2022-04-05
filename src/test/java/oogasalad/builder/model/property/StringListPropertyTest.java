package oogasalad.builder.model.property;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for StringListProperty Class
 *
 * @author Shaan Gondalia
 */
public class StringListPropertyTest {

  private static final String PROPERTY_NAME = "propName";
  private static final String PROPERTY_VALUE_1 = "value1";
  private static final String PROPERTY_VALUE_2 = "value2";
  private static final String EXPECTED_JSON = "{\"propName\":[\"value2\",\"value1\"]}";

  private Collection<String> properties;

  @BeforeEach
  void setUp() {
    properties = new HashSet<>();
    properties.add(PROPERTY_VALUE_1);
    properties.add(PROPERTY_VALUE_2);
  }

  @Test
  void testCreateProperty() {
    StringListProperty property = new StringListProperty(PROPERTY_NAME, properties);
    assertEquals(PROPERTY_NAME, property.name());
    assertEquals(properties, property.value());
  }

  @Test
  void testEquality() {
    StringListProperty property1 = new StringListProperty(PROPERTY_NAME, properties);
    StringListProperty property2 = new StringListProperty(PROPERTY_NAME, properties);
    assertEquals(property1, property2);
  }

  @Test
  void testSerialization() throws Exception {
    StringListProperty property = new StringListProperty(PROPERTY_NAME, properties);
    assertEquals(EXPECTED_JSON, property.toJSON());
  }

  @Test
  void testLoad() {
    StringListProperty property = new StringListProperty(PROPERTY_NAME, properties);
    property.fromJSON(EXPECTED_JSON);
  }

  @Test
  void testHashCode() {
    StringListProperty property = new StringListProperty(PROPERTY_NAME, properties);
    int code = property.hashCode();
  }
}

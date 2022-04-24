package oogasalad.builder.model.element.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.element.MetaData;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for MetaDataFactory Class
 *
 * @author Shaan Gondalia
 */
public class MetaDataFactoryTest {

  private MetaDataFactory metadataFactory;
  private static final String GAME_NAME = "checkers";
  private static final String PROPERTY_DESCRIPTION_TYPE = "description";
  private static final String PROPERTY_AUTHOR_TYPE = "author";
  private static final String AUTHOR = "ur";
  private static final String DESCRIPTION = "this is a checkers game";

  @BeforeEach
  void setUp(){
    metadataFactory = new MetaDataFactory();
  }

  @Test
  void testConditionCreated() throws MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(PROPERTY_DESCRIPTION_TYPE, DESCRIPTION));
    properties.add(PropertyFactory.makeProperty(PROPERTY_AUTHOR_TYPE, AUTHOR));
    MetaData metadata = metadataFactory.createElement(GAME_NAME, properties);

    ElementRecord record = metadata.toRecord();
    assertEquals(properties, record.properties());
    assertEquals(GAME_NAME, record.name());
  }

  @Test
  void testConditionMissingRequired() {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(PROPERTY_DESCRIPTION_TYPE, DESCRIPTION));
    assertThrows(MissingRequiredPropertyException.class, () ->
        metadataFactory.createElement(GAME_NAME, properties));
  }

}

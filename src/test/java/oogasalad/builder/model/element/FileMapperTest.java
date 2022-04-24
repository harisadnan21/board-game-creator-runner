package oogasalad.builder.model.element;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for FileMapper
 *
 * @author Shaan Gondalia
 */
public class FileMapperTest {

  private static final String IMAGE = "image";
  private static final String PLAYER = "player";
  private static final String ID = "id";
  private static final String PIECE_NAME = "moveTopRight";
  private static final String PIECE_IMAGE = "data/tests/normal.png";
  private static final String EXPECTED_PIECE_IMAGE = "/resources/normal.png";
  private static final String TESTS_DIRECTORY = "data/tests/";
  private static final int PIECE_PLAYER = 0;
  private static final int PIECE_ID = 100;

  private FileMapper fileMapper;

  @BeforeEach
  void setUp(){
    fileMapper = new FileMapper();
  }

  @Test
  void testReMapProperty() throws InvalidTypeException, MissingRequiredPropertyException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(IMAGE, PIECE_IMAGE));
    properties.add(PropertyFactory.makeProperty(PLAYER, PIECE_PLAYER));
    properties.add(PropertyFactory.makeProperty(ID, PIECE_ID));

    Collection<Property> newProperties = fileMapper.reMapProperties(properties);
    for (Property property : newProperties) {
      if (property.name().equals(IMAGE)) {
        assertEquals(EXPECTED_PIECE_IMAGE, property.value());
      }
    }
  }

  @Test
  void testCopyFiles() throws InvalidTypeException, MissingRequiredPropertyException, IOException {
    Collection<Property> properties = new HashSet<>();
    properties.add(PropertyFactory.makeProperty(IMAGE, PIECE_IMAGE));
    properties.add(PropertyFactory.makeProperty(PLAYER, PIECE_PLAYER));
    properties.add(PropertyFactory.makeProperty(ID, PIECE_ID));

    Collection<Property> newProperties = fileMapper.reMapProperties(properties);

    fileMapper.copyFiles(new File(TESTS_DIRECTORY));


  }

}

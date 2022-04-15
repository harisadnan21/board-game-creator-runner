package oogasalad.builder.model.element;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import oogasalad.builder.model.property.Property;

/**
 * Class that is responsible for mapping files from the user's filesystem to the game subdirectory.
 *
 * @author Shaan Gondalia
 */
public class FileMapper {

  public Map<String, String> fileNameMap;

  /**
   * Creates a new collection of properties that is the same as the original properties, except for
   * the file paths which are mapped to filepaths relative to the game configuration sub-directory.
   *
   * @param originalProperties the original properties that will be remapped
   *
   * @return a collection of new properties that have been remapped
   */
  public Collection<Property> reMapProperties(Collection<Property> originalProperties) {
    return null;
  }

  /**
   * Copies the original files to a new directory.
   *
   * @param directory The new directory to copy the game configuration resources to
   */
  public void copyFiles(File directory) {

  }

}

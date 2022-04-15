package oogasalad.builder.model.element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;

/**
 * Class that is responsible for mapping files from the user's filesystem to the game subdirectory.
 *
 * @author Shaan Gondalia
 */
public class FileMapper {

  private static final String RESOURCES_PATH = "/resources/";
  // TODO: Replace magic values with properties file
  private static final String[] PROPERTIES_TO_REMAP = {"image"};
  private final Map<String, String> fileNameMap;

  /**
   * Creates a new FileMapper object, which remaps and stores data about where resource files are
   * located.
   */
  public FileMapper() {
    fileNameMap = new HashMap<>();
  }

  /**
   * Creates a new collection of properties that is the same as the original properties, except for
   * the file paths which are mapped to filepaths relative to the game configuration sub-directory.
   *
   * @param originalProperties the original properties that will be remapped
   *
   * @return a collection of new properties that have been remapped
   */
  public Collection<Property> reMapProperties(Collection<Property> originalProperties) {
    Collection<Property> newProperties = new HashSet<>();
    for (Property property : originalProperties) {
      if (Arrays.asList(PROPERTIES_TO_REMAP).contains(property.name())) {
        String newPath = reMap(property.valueAsString());
        fileNameMap.put(property.valueAsString(), newPath);
        newProperties.add(PropertyFactory.makeProperty(property.name(), newPath, property.form()));
      }
      else {
        newProperties.add(property);
      }
    }
    return newProperties;
  }

  /**
   * Copies the original files to a new directory.
   *
   * @param directory The new directory to copy the game configuration resources to
   */
  public void copyFiles(File directory) throws IOException {
    for (String oldPath : fileNameMap.keySet()) {
      Files.copy(Path.of(oldPath), Path.of(directory.toString() + fileNameMap.get(oldPath)));
    }
  }

  // Remaps a filePath to the new resources file path
  private String reMap(String filePath) {
    File f = new File(filePath);
    return RESOURCES_PATH + f.getName();
  }

}

package oogasalad.builder.model.element;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.PropertyFactory;
import org.json.JSONObject;

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
        fileNameMap.put(newPath, property.valueAsString());
        newProperties.add(property.with(newPath));
      }
      else {
        newProperties.add(property);
      }
    }
    return newProperties;
  }

  /**
   * Copies the original files to a new directory. Makes the resource directory if it does not
   * already exist.
   *
   * @param directory The new directory to copy the game configuration resources to
   */
  public void copyFiles(File directory) throws IOException {
    File resourceDir = new File(directory.toString() + RESOURCES_PATH);
    resourceDir.mkdir();
    for (String newPath : fileNameMap.keySet()) {
      Files.copy(Path.of(fileNameMap.get(newPath)), Path.of(directory + newPath), StandardCopyOption.REPLACE_EXISTING);
    }
  }

  /**
   * Creates a new collection of properties that is the same as the original properties, except for
   * the file paths which are un-mapped from relative filepaths to the old absolute filepaths.
   *
   * @param originalProperties the properties that have to be unmapped
   * @return a collection of new properties that have been unmapped
   */
  public Collection<Property> unMapProperties(Collection<Property> originalProperties) {
    Collection<Property> newProperties = new HashSet<>();
    for (Property property : originalProperties) {
      if (Arrays.asList(PROPERTIES_TO_REMAP).contains(property.name()) && fileNameMap.containsKey(property.valueAsString())) {
        newProperties.add(property.with(fileNameMap.get(property.valueAsString())));
      }
      else {
        newProperties.add(property);
      }
    }
    return newProperties;
  }

  /**
   * Resolves the relative paths to loaded resource files. Resource files are stored as a relative
   * path from the configuration directory in the JSON configuration. These paths need to be
   * resolved, or converted to absolute paths in the user's computer. This method appends the
   * workingDirectory to all properties that use resource paths (in this case, just images).
   *
   * @param originalProperties the original properties to resolve
   * @param workingDir the absolute path to the game configuration directory
   *
   * @return a collection of new properties that have had their relative paths resolved
   */
  public Collection<Property> resolveResourcePaths(Collection<Property> originalProperties, String workingDir) {
    Collection<Property> newProperties = new HashSet<>();
    for (Property property : originalProperties) {
      if (Arrays.asList(PROPERTIES_TO_REMAP).contains(property.name())) {
        String newPath = workingDir + property.valueAsString();
        newProperties.add(property.with(newPath));
      }
      else {
        newProperties.add(property);
      }
    }
    return newProperties;

  }

  // Remaps a filePath to the new resources file path
  private String reMap(String filePath) {
    File f = new File(filePath);
    return RESOURCES_PATH + f.getName();
  }

}

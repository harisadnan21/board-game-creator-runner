package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 * Reads in needed metadata headers, returning a map from header to value
 *
 * @author Robert Cranston
 */
public class MetadataParser extends AbstractParser<Map<String, String>>{

  private static final List<String> headers = List.of("gameName", "author", "description");
  /**
   * Returns a Map that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  @Override
  public Map<String, String> parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    JSONObject boardObj = findAttribute(root, "metadata");
    return parseInfo(boardObj);

  }

  // Parses the info in the metadata section
  private Map<String, String> parseInfo(JSONObject boardObj) {
    Map<String, String> metadata = new HashMap<>();
    for(String header : headers){
      metadata.put(header,boardObj.getString(header));
    }
    return metadata;
  }
}

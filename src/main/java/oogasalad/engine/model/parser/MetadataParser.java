package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class MetadataParser extends AbstractParser{
  private Map<String, String> metadata = new HashMap<>();
  private List<String> headers = List.of("name", "author", "description");
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
    parseInfo(boardObj);
    return metadata;
  }

  private void parseInfo(JSONObject boardObj) {
    for(String header : headers){
      metadata.put(header,boardObj.getString(header));
    }

  }
}

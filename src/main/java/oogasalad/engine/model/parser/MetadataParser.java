package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import org.json.JSONObject;

public class MetadataParser extends AbstractParser{

  /**
   * Returns a Map that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  @Override
  public Object parse(File configFile) throws FileNotFoundException {
    JSONObject root = fileToJSON(configFile);
    parseInfo();
  }

  private void parseInfo() {

  }
}

package oogasalad.engine.model.parser;


import java.io.File;

/**
 * Abstract parser class that contains some common methods for parsing objects
 *
 * @author Shaan Gondalia
 */
public abstract class AbstractParser<T> implements Parser<T> {

  /**
   * Returns an object that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  public abstract T parse(File configFile);

}

package oogasalad.engine.model.parser;

import java.io.File;

/**
 * Defines the public API for a parser.
 *
 * @author Shaan Gondalia
 */
public interface Parser<T> {

  /**
   * Returns an object that is parsed from a configuration file, throwing errors if the file is
   * malformed or missing required properties.
   *
   * @param configFile the configuration file to parse from
   * @return an object that is parsed form a configuration file
   */
  T parse(File configFile);

}

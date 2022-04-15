package oogasalad.engine.model.parser;

import java.io.File;
import java.util.Collection;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.board.Board;

/**
 * Parser that reads an existing game configuration from a directory. Responsible for creating a
 * board, reading rules, and creating win conditions.
 *
 * @author Shaan Gondalia
 */
public class Parser {

  // TODO: Replace magic value. This must match the magic value found in BuilderController
  private static final String CONFIG_FILENAME = "config.json";
  private final File configFile;

  /**
   * Creates a new Parser from a configuration file
   *
   * @param configFile the configuration file to parse from
   */
  public Parser(File configFile) {
    this.configFile = configFile;
  }

  /**
   * Reads a board from the configuration file attached to the parser
   *
   * @return a board with the correct starting configuration
   */
  public Board parseBoard() {
    return null;
  }

  /**
   * Reads a collection of rules from the configuration file attached to the parser
   *
   * @return a collection of rules
   */
  public Collection<Rule> readRules() {
    return null;
  }

  /**
   * Reads a collection of win condition from the configuration file attached to the parser
   *
   * @return a collection of win conditions
   */
  public Collection<WinCondition> readWinConditions() {
    return null;
  }

}

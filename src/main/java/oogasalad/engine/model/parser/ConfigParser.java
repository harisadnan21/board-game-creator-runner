package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.board.Board;

/**
 * Parser that reads an existing game configuration from a directory. Responsible for creating a
 * board, reading rules, and creating win conditions.
 *
 * TODO: Maybe make this class implement the Parser Interface. This would require there to be a
 * single data structure that encapsulates the board, rules, winconditions, etc. Need to discuss
 * this more with entire team.
 *
 * @author Shaan Gondalia
 */
public class ConfigParser {

  // TODO: Replace magic value. This must match the magic value found in BuilderController
  private static final String CONFIG_FILENAME = "config.json";
  private final File configFile;
  private BoardParser boardParser;

  /**
   * Creates a new Parser from a configuration file
   *
   * @param configFile the configuration file to parse from
   */
  public ConfigParser(File configFile) {
    this.configFile = configFile;
  }

  /**
   * Reads a board from the configuration file attached to the parser
   *
   * @return a board with the correct starting configuration
   */
  public Board parseBoard() throws FileNotFoundException {
    return boardParser.parse(configFile);
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

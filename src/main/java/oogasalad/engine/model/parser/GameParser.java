package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import oogasalad.engine.model.conditions.WinCondition;
import oogasalad.engine.model.move.Rule;
import oogasalad.engine.model.board.Board;

/**
 * Parser that reads an existing game configuration from a directory. Responsible for creating a
 * board, reading rules, and creating win conditions.
 *
 * TODO: Maybe make this class implement the Parser Interface. This would require there to be a
 * TODO: single data structure that encapsulates the board, rules, winconditions, etc. Need to
 * TODO: discuss this more with entire team.
 *
 * @author Shaan Gondalia
 */
public class GameParser {

  private final File configFile;
  private final BoardParser boardParser;
  private final RuleParser ruleParser;


  /**
   * Creates a new Parser from a configuration file
   *
   * @param configFile the configuration file to parse from
   */
  public GameParser(File configFile) {
    this.configFile = configFile;
    boardParser = new BoardParser();
    ruleParser = new RuleParser();
  }

  /**
   * Reads a board from the configuration file attached to the parser
   *
   * TODO: Add Piece parsing to this to get images of pieces from config
   *
   * @return a board with the correct starting configuration
   */
  public Board parseBoard() throws FileNotFoundException {
    return boardParser.parse(configFile);
  }

  /**
   * Reads an array of rules from the configuration file attached to the parser
   *
   * @return an array of rules
   */
  public Collection<Rule> readRules() throws FileNotFoundException {
    return ruleParser.parse(configFile);
  }

  /**
   * Reads a collection of win conditions from the configuration file attached to the parser
   *
   * @return a collection of win conditions
   */
  public Collection<WinCondition> readWinConditions() {
    return new HashSet<>();
  }

}

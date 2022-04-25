package oogasalad.engine.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import oogasalad.engine.model.board.Board;
import oogasalad.engine.model.board.cells.Position;
import oogasalad.engine.model.rule.terminal_conditions.EndRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for WinConditionParser class
 *
 * @author Shaan Gondalia
 */
public class WinConditionParserTest {

  private static final String TEST_PARSE_FILENAME = "data/tests/parser/test.json";

  private WinConditionParser parser;

  @BeforeEach
  public void setUp() {
    parser = new WinConditionParser();
  }

  @Test
  void testParseRules() throws FileNotFoundException {
    Board board = new Board(3,3);

    File file = new File(TEST_PARSE_FILENAME);
    Collection<EndRule> rules = parser.parse(file);
    EndRule player1Wins = null;
    for (EndRule rule: rules) {
      if (rule.getName().equals("Player 1 wins")) {
        player1Wins = rule;
      }
    }
    assertTrue(player1Wins.isValid(board, new Position(0,0)));
    assertEquals(1, player1Wins.getWinner(board));
  }
}

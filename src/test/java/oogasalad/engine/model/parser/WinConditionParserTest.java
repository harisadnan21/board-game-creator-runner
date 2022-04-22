package oogasalad.engine.model.parser;

import java.io.File;
import java.io.FileNotFoundException;
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
    File file = new File(TEST_PARSE_FILENAME);
    parser.parse(file);
  }
}

package oogasalad.engine.model.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for ConditionParser class
 *
 * @author Shaan Gondalia
 */
public class ConditionParserTest {

  private static final String TEST_PARSE_FILENAME = "data/tests/parser/test.json";
  private static final String CONDITION_TO_RESOLVE = "emptyUpRight";

  private ConditionParser parser;

  @BeforeEach
  public void setUp() {
    parser = new ConditionParser();
  }

  @Test
  void testParseConditions() throws FileNotFoundException, ClassNotFoundException,
      InvocationTargetException, NoSuchMethodException, InstantiationException,
      IllegalAccessException {
    File file = new File(TEST_PARSE_FILENAME);
    parser.parse(file);
    parser.resolve(CONDITION_TO_RESOLVE);
  }
}


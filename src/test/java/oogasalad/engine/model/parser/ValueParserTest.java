package oogasalad.engine.model.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValueParserTest {

  @Test
  void parserTest() {
    String s = "-1   ";
    int value = ValueParser.getValue(s, 4);
    assertEquals(-1, value);

    s = "i";
    value = ValueParser.getValue(s, 3);
    assertEquals(3, value);

    s = "-i";
    value = ValueParser.getValue(s, 5);
    assertEquals(-5, value);
  }
}

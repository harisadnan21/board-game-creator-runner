package oogasalad.builder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.HashSet;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.element.Piece;
import oogasalad.builder.model.element.Rule;
import oogasalad.builder.model.element.WinCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests Serialization of Game Elements
 *
 * @author Shaan Gondalia
 */
public class SerializationTest {

  private static final String PIECE_NAME = "checker";
  private static final String RULE_NAME = "rule";
  private static final String WIN_CONDITION_NAME = "winCon";

  private static final String PROPERTY_NAME_1 = "test property name";
  private static final String PROPERTY_NAME_2 = "test property name";
  private static final String PROPERTY_VALUE = "test property value";
  private static final String TEST_JSON = "test";
  private static final String EXPECTED_JSON = "{\"test property name\":\"test property value\"}";
  private Collection<Property> properties;

  @BeforeEach
  void setUp(){
    properties = new HashSet<>();
    properties.add(new Property(String.class, PROPERTY_NAME_1, PROPERTY_VALUE));
    properties.add(new Property(String.class, PROPERTY_NAME_2, PROPERTY_VALUE));
  }

  @Test
  void testSerialization(){
    Piece piece = new Piece(PIECE_NAME, properties);
    Rule rule = new Rule(RULE_NAME, properties);
    WinCondition winCondition = new WinCondition(WIN_CONDITION_NAME, properties);
    assertEquals(EXPECTED_JSON, piece.toJSON());
    assertEquals(EXPECTED_JSON, rule.toJSON());
    assertEquals(EXPECTED_JSON, winCondition.toJSON());
  }

  @Test
  void testParsing(){
    Piece piece = new Piece(PIECE_NAME, properties);
    Rule rule = new Rule(RULE_NAME, properties);
    WinCondition winCondition = new WinCondition(WIN_CONDITION_NAME, properties);
    piece.fromJSON(TEST_JSON);
    rule.fromJSON(TEST_JSON);
    winCondition.fromJSON(TEST_JSON);
  }
}

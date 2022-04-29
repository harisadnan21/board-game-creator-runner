package oogasalad.builder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.property.Property;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Checks whether an empty JSON array is parsed properly in the builder
 *
 * @author Shaan Gondalia
 */
public class LoadEmptyListTest {

  private static final String TEST_FILENAME = "data/tests/empty/config.json";
  private static final String EMPTY_RULE_NAME = "emptyRule";
  private static final String RULE = "rule";
  private static final String CONDITIONS = "conditions";
  public static final String EMPTY = "";

  private BuilderModel game;

  @BeforeEach
  void setUp(){
    game = new GameConfiguration();
  }

  @Test
  void testLoad() throws FileNotFoundException {
    InputStream is = new DataInputStream(new FileInputStream(TEST_FILENAME));
    JSONTokener tokener = new JSONTokener(is);
    JSONObject object = new JSONObject(tokener);
    game.fromJSON(object.toString(), "");
    ElementRecord record = game.findElementInfo(RULE, EMPTY_RULE_NAME);
    for (Property property : record.properties()) {
      if (property.name().equals(CONDITIONS)){
        assertEquals(EMPTY, property.valueAsString());
      }
    }

  }

}

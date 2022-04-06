package oogasalad.builder.model.element;

import java.util.Collection;
import java.util.Set;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.JSONSerializable;
import org.json.JSONObject;


/**
 * Record that describes a Game Element
 *
 * @author Shaan Gondalia
 */
public record ElementRecord(String name, Collection<Property> properties) implements
    JSONSerializable<ElementRecord> {

  private static final String NAME = "name";

  public ElementRecord {
    properties = Set.copyOf(properties);
  }

  /**
   * Converts an ElementRecord into a JSON String
   *
   * @return a JSON string
   */
  @Override
  public String toJSON() {
    JSONObject obj = new JSONObject();
    for (Property property : properties) {
      obj.put(property.name(), property.value());
    }
    obj.put(NAME, name);
    return obj.toString();
  }

  /**
   * Converts a JSON String into an elementRecord
   *
   * @param json the JSON String to convert
   * @return an ElementRecord with the given parameters
   */
  @Override
  public ElementRecord fromJSON(String json) {
    return null;
  }
}
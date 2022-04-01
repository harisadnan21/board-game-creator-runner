package oogasalad.builder.model.parser.adapter;

import oogasalad.builder.controller.Property;
import oogasalad.builder.model.element.ElementRecord;
import org.json.JSONObject;

/**
 * Class that has methods that can convert an ElementRecord to a JSON string and vice versa
 *
 * @author Shaan Gondalia
 */
public class ElementRecordAdapter implements JSONAdapter<ElementRecord> {

  /**
   * Converts an ElementRecord into a JSON String
   *
   * @param elementRecord the ElementRecord to convert
   * @return a JSON string
   */
  @Override
  public String toJSON(ElementRecord elementRecord) {
    JSONObject obj = new JSONObject();
    for (Property property: elementRecord.properties()) {
      obj.put(property.name(), property.value());
    }
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

package oogasalad.builder.model.element;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import oogasalad.builder.controller.Property;


/**
 * Record that describes a Game Element
 *
 * @author Shaan Gondalia
 */
public record ElementRecord(String type, String name, Collection<Property> properties) {
  public ElementRecord {
    properties = Set.copyOf(properties);
  }


}
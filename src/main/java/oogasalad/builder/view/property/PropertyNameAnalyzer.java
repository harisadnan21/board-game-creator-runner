package oogasalad.builder.view.property;

import oogasalad.builder.model.property.Property;

/**
 * Provides utilities for getting the different parts of a property's name
 * Ideally, this would be baked into properties, but time is short, so this is what we're doing
 *
 * @author Ricky Weerts
 */
public class PropertyNameAnalyzer {
  private static final String TYPE_PROPERTY_NAME = "type";
  private static final String REQUIRED = "required";
  private static final String DELIMITER = "-";

  /**
   * Returns whether the property is a type property (meaning, if it should decide which other properties are contained within the GameElement)
   *
   * @param prop the property to check
   * @return whether this property is a type property
   */
  public boolean isTypeProperty(Property prop) {
    return prop.shortName().equals(TYPE_PROPERTY_NAME);
  }

  /**
   * Checks if the property is indicated as required
   *
   * @param prop the property to check
   * @return whether this property is required
   */
  public boolean isRequiredProperty(Property prop) {
    return prop.name().split(DELIMITER)[0].equals(REQUIRED);
  }

  /**
   * Finds the namespace of the property.
   * This excludes the short name and the required indicator
   *
   * @param prop the property to analyze
   * @return the namespace of the property
   */
  public String getPropertyNamespace(Property prop) {
    String[] nameParts = prop.name().split(DELIMITER);
    StringBuilder namespace = new StringBuilder();
    // Ignore the required indicator and the actual name of the property
    for(int i = isRequiredProperty(prop) ? 1 : 0; i < nameParts.length - 1; i++) {
      namespace.append(nameParts[i]).append(DELIMITER);
    }

    // Cutoff the last -
    return namespace.length() == 0 ? "" : namespace.substring(0, namespace.length() - 1);
  }

}

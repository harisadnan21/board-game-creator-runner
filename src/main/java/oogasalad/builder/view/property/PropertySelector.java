package oogasalad.builder.view.property;

import javafx.scene.Node;

/**
 * API that defines a view element that chooses a property.
 *
 * @author Shaan Gondalia
 */
public interface PropertySelector {

  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  Node display();

  /**
   * Returns the actual text input of the user that should be stored in the property
   *
   * @return the text input corresponding to the property value that should be stored
   */
  String getPropertyValue();
}

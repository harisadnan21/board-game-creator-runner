package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import oogasalad.builder.model.property.Property;

/**
 * PropertySelector that allows users to make a choice from a drop-down menu. This is particularly
 * applicable when choosing something like the type of action (which has a preset number of
 * possibilities).
 *
 * @author Shaan Gondalia
 */
public class DropDown implements PropertySelector{

  private static final String LIST_TEXT = "Select Property"; // TODO: Replace Magic Value
  private static final String LIST_DELIMITER = "-";

  private final Property property;
  private final ComboBox<String> list;

  /**
   * Creates a new FileSelector, which displays a button that prompts the user to select a file
   *
   * @param property the property that will be "filled in" by the Field
   */
  public DropDown(Property property){
    this.property = property;
    list = new ComboBox<>();
    //list.setPromptText(resources.getString(LIST_TEXT));
    list.setPromptText(LIST_TEXT); // TODO: Replace magic value with resources file (languages)
    list.getItems().setAll(this.property.value().split(LIST_DELIMITER));
    //list.valueProperty().addListener((observable, oldValue, newValue) -> selection = newValue);
  }

  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  @Override
  public Node display() {
    return list;
  }

  /**
   * Returns the actual text input of the user that should be stored in the property
   *
   * @return the text input corresponding to the property value that should be stored
   */
  @Override
  public String getPropertyValue() {
    return list.getValue();
  }
}

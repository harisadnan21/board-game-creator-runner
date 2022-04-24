package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * PropertySelector that allows users to make a choice from a drop-down menu. This is particularly
 * applicable when choosing something like the type of action (which has a preset number of
 * possibilities).
 *
 * @author Shaan Gondalia
 */
public class DropDown implements PropertySelector{

  private static final String LIST_TEXT_KEY =  "PropertyDropdown-Prompt";
  private static final String LIST_DELIMITER = "-";

  private final Property property;
  private final ComboBox<String> list;

  /**
   * Creates a new FileSelector, which displays a button that prompts the user to select a file
   *
   * @param property the property that will be "filled in" by the Field
   */
  public DropDown(Property property, CallbackDispatcher dispatcher){
    this.property = property;
    list = new ComboBox<>();
    list.setPromptText(ViewResourcesSingleton.getInstance().getString(LIST_TEXT_KEY));
    list.getItems().setAll(this.property.defaultValueAsString().split(LIST_DELIMITER));
    if(!property.defaultValue().toString().equals(property.valueAsString())) {
      list.getSelectionModel().select(property.valueAsString());
    }
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
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    if (list.getValue() == null){
      throw new MissingRequiredPropertyException(property.shortName());
    }
    return property.with(property.shortName(), list.getValue());
  }

  @Override
  public void addListener(ChangeListener updateFields){
    list.valueProperty().addListener(updateFields);
  }
}

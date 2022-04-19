package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.MapValueFactory;
import javax.security.auth.callback.Callback;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.callback.CallbackDispatcher;

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
  public DropDown(Property property, CallbackDispatcher dispatcher){
    this.property = property;
    list = new ComboBox<>();
    //list.setPromptText(resources.getString(LIST_TEXT));
    list.setPromptText(LIST_TEXT); // TODO: Replace magic value with resources file (languages)
    list.getItems().setAll(this.property.defaultValue().toString().split(LIST_DELIMITER)); // FIXME using toString() when maybe I shouldn't
    if(!property.defaultValue().toString().equals(property.valueAsString())) {
      list.getSelectionModel().select(property.valueAsString());
    }
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
   * Returns a populated property with the correct value, name, and form
   *
   * @return a populated property with the correct value, name, and form
   */
  @Override
  public Property getProperty() {
    String[] nameParts = property.name().split("-");
    return new StringProperty(nameParts[nameParts.length - 1], list.getValue(), property.defaultValue().toString(), property.form()); // TODO use withValue()
  }

  @Override
  public void addListener(ChangeListener updateFields){
    list.valueProperty().addListener(updateFields);
  }
}

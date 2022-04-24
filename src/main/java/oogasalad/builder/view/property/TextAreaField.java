package oogasalad.builder.view.property;


import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * A PropertySelector Field that is of type TextArea
 *
 * @author Mike Keohane
 */
public class TextAreaField extends Field {

  private TextArea textArea;

  /**
   * Constructs a TextAreaField by calling the Field and initializing the textArea
   *
   * @param property   - property to be edited using the TextArea
   * @param dispatcher - callback dispatcher to communicate with the controller.
   */
  public TextAreaField(Property property, CallbackDispatcher dispatcher) {
    super(property, dispatcher);
    textArea = new TextArea(property.valueAsString());
    textArea.setWrapText(true);
  }

  @Override
  public Property getProperty() {
    return property().with(property().shortName(), textArea.getText());
  }

  @Override
  public Node display() {
    return textArea;
  }

  @Override
  public void addListener(ChangeListener updateFields) {
  }
}

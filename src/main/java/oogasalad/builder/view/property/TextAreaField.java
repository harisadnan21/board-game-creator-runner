package oogasalad.builder.view.property;


import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;

public class TextAreaField extends Field{
  private TextArea textArea;
  public TextAreaField(Property property, CallbackDispatcher dispatcher){
    super(property, dispatcher);
    textArea = new TextArea(property.valueAsString());
    textArea.setWrapText(true);
    textArea.setId("textArea-"+property.shortName());
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

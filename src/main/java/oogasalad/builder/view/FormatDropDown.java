package oogasalad.builder.view;

import static oogasalad.builder.view.BuilderView.DEFAULT_PROPERTY_PACKAGE;

import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;

public class FormatDropDown extends ComboBox<String> {
  public static final String STYLE_OPTIONS_PATH = "FormatOptions";
  ResourceBundle styleOptions;

  public FormatDropDown(BuilderView view){
    fillDropDown();
    this.setPromptText(ViewResourcesSingleton.getInstance().getString("formatPrompt"));
    this.valueProperty().addListener( (observable, oldValue, newValue) -> view.setFormat(
        styleOptions.getString(ViewResourcesSingleton.getInstance().getString(newValue.replace(" ", "_")))));
  }

  private void fillDropDown(){
    styleOptions = ResourceBundle.getBundle(
        DEFAULT_PROPERTY_PACKAGE + STYLE_OPTIONS_PATH);
    Enumeration keys = styleOptions.getKeys();
    while (keys.asIterator().hasNext()){
      this.getItems().add(ViewResourcesSingleton.getInstance().getString((String) keys.nextElement()));
    }
  }
}

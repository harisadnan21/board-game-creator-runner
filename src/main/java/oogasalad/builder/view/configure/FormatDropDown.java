package oogasalad.builder.view.configure;

import static oogasalad.builder.view.BuilderView.DEFAULT_PROPERTY_PACKAGE;

import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.ViewResourcesSingleton;

/**
 * Class that extends ComboBox and contains the options to change the view format
 *
 * @author Mike Keohane
 */
public class FormatDropDown extends ComboBox<String> {

  ResourceBundle styleOptions;
  private String styleChoice;

  /**
   * Constructs the drop-down and sets the ChangeListener to call view.setFormat()
   *
   * @param view - BuilderView instance
   */
  public FormatDropDown(BuilderView view, String styleChange) {
    styleChoice = styleChange;
    fillDropDown();
    this.setPromptText(ViewResourcesSingleton.getInstance().getString("formatPrompt"));
    this.valueProperty().addListener((observable, oldValue, newValue) -> view.setFormat(
        styleOptions.getString(
            ViewResourcesSingleton.getInstance().getString(newValue.replace(" ", "_")))));
  }

  //fills DropDown from a file
  private void fillDropDown() {
    styleOptions = ResourceBundle.getBundle(
        DEFAULT_PROPERTY_PACKAGE + styleChoice);
    Enumeration keys = styleOptions.getKeys();
    while (keys.asIterator().hasNext()) {
      this.getItems()
          .add(ViewResourcesSingleton.getInstance().getString((String) keys.nextElement()));
    }
  }
}

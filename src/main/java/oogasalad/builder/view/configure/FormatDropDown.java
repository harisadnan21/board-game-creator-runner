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
  private String keyToButton;

  /**
   * Constructs the drop-down and sets the ChangeListener to call view.setFormat()
   *
   * @param view - BuilderView instance
   */
  public FormatDropDown(BuilderView view, String styleChange, String buttonLabel) {
    styleChoice = styleChange;
    keyToButton = buttonLabel;
    fillDropDown();
    this.setPromptText(ViewResourcesSingleton.getInstance().getString(keyToButton));
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

  // allows us to add a format on top of the styling/theme
  public String getFormat() {
    return styleOptions.getString(ViewResourcesSingleton.getInstance().getString(
            this.getValue().replace(" ", "_")));
  }
}

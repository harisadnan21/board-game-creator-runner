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
 * @author Mike Keohane & Thivya Sivarajah
 */
public class FormatDropDown extends ComboBox<String> {

  public static final String FORMAT_RESOURCE_PATH = "FormatOptions";
  public static final String STYLE_KEY = "-choices";
  public static final String DELINIMATOR = "-";
  ResourceBundle styleOptions;
  private String styleChoice;
  private String keyToButton;

  /**
   * Constructs the drop-down and sets the ChangeListener to call view.setFormat()
   *
   * @param styleChange - type of style to be changed 
   */
  public FormatDropDown(String styleChange) {
    styleChoice = styleChange;
    fillDropDown();
  }

  //fills DropDown from a file
  private void fillDropDown() {
    styleOptions = ResourceBundle.getBundle(
        DEFAULT_PROPERTY_PACKAGE + DEFAULT_PROPERTY_PACKAGE);
    String[] formatKeys = ViewResourcesSingleton.getInstance().getString(styleChoice+STYLE_KEY).split(DELINIMATOR);
    for(String key : formatKeys) {
      this.getItems()
          .add(ViewResourcesSingleton.getInstance().getString(key));
    }
    this.setValue(formatKeys[0]);
  }

  // allows us to add a format on top of the styling/theme
  public String getFormat() {
    return styleOptions.getString(ViewResourcesSingleton.getInstance().getString(
            this.getValue().replace(" ", "_")));
  }
}

package oogasalad.builder.view.property;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * PropertySelector that allows users to choose a file to set as the value of a property. For
 * instance, if the user wishes to give a piece an image, the FileSelector will prompt them to
 * choose a file from their filesystem, storing the path to that file in the property.
 *
 * @author Shaan Gondalia & Mike Keohane
 */
public class FileSelector implements PropertySelector {

  private static final String BUTTON_TEXT_KEY = "PropertyFileSelector-ButtonText";
  private static final String FILE_DELIMITER = "/";
  private static final String[] FILE_TYPES = {"*.png", "*.jpg", "*.gif", ".jpeg"};
  private final Property property;
  private String filePath;
  private final Button chooseButton;

  /**
   * Creates a new FileSelector, which displays a button that prompts the user to select a file
   *
   * @param property the property that will be "filled in" by the Field
   */
  public FileSelector(Property property, CallbackDispatcher dispatcher) {
    this.property = property;
    chooseButton = new Button();
    chooseButton.setText(ViewResourcesSingleton.getInstance().getString(BUTTON_TEXT_KEY));
    if (!property.valueAsString().equals(property.defaultValue().toString())){
      chooseButton.setText(property.value().toString().split(FILE_DELIMITER)[property.value().toString().split(
          FILE_DELIMITER).length - 1]);
    }
    chooseButton.setOnAction(e -> chooseFile());
  }

  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  @Override
  public Node display() {
    return chooseButton;
  }

  /**
   * Returns a populated property with the filepath that the user selected
   *
   * @return a populated property with the filepath that the user selected
   */
  @Override
  public Property getProperty() {
    if (filePath == null){
      throw new MissingRequiredPropertyException(property.shortName());
    }
    return property.with(property.shortName(), filePath);
  }

  // Prompts the user to choose a file, storing the chosen file in an instance variable
  private void chooseFile() {
    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
        .add(new ExtensionFilter("Image Files", FILE_TYPES));
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      filePath = file.toString();
      chooseButton.setText(filePath.split(FILE_DELIMITER)[filePath.split(
          FILE_DELIMITER).length - 1]);
    }
  }

  public void addListener(ChangeListener updateFields){
  }
}

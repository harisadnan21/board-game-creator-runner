package oogasalad.builder.view.property;

import java.io.File;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * PropertySelector that allows users to choose a file to set as the value of a property. For
 * instance, if the user wishes to give a piece an image, the FileSelector will prompt them to
 * choose a file from their filesystem, storing the path to that file in the property.
 *
 * @author Shaan Gondalia
 */
public class FileSelector implements PropertySelector {

  private static final String BUTTON_TEXT_KEY = "PropertyFileSelector-ButtonText";

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
    return property.with(property.shortName(), filePath);
  }

  // Prompts the user to choose a file, storing the chosen file in an instance variable
  private void chooseFile() {
    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();
  //  fileChooser.setInitialDirectory(new File("/"));
    fileChooser.getExtensionFilters()
        .add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      filePath = file.toString();
    }
  }

  public void addListener(ChangeListener updateFields){
  }
}

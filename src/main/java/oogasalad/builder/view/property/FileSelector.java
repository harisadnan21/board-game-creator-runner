package oogasalad.builder.view.property;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringProperty;

/**
 * PropertySelector that allows users to choose a file to set as the value of a property. For
 * instance, if the user wishes to give a piece an image, the FileSelector will prompt them to
 * choose a file from their filesystem, storing the path to that file in the property.
 *
 * @author Shaan Gondalia
 */
public class FileSelector implements PropertySelector{

  private static final String BUTTON_TEXT = "Choose File"; // TODO: Replace Magic Value

  private final Property property;
  private String filePath;
  private final Button chooseButton;

  /**
   * Creates a new FileSelector, which displays a button that prompts the user to select a file
   *
   * @param property the property that will be "filled in" by the Field
   */
  public FileSelector(Property property){
    this.property = property;
    chooseButton = new Button();
    //String buttonLabel = resources.getString(labelName);
    chooseButton.setText(BUTTON_TEXT); // TODO: Replace magic value with resources file (languages)
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
    String[] nameParts = property.name().split("-");
    return new StringProperty(nameParts[nameParts.length - 1], filePath, property.form());
  }

  // Prompts the user to choose a file, storing the chosen file in an instance variable
  private void chooseFile() {
    Stage stage = new Stage();
    FileChooser fileChooser = new FileChooser();
    filePath = fileChooser.showOpenDialog(stage).toString();
  }
}

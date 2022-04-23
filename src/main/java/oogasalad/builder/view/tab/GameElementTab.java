package oogasalad.builder.view.tab;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.GameElementList;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.UpdateGameElementCallback;
import oogasalad.builder.view.property.PropertyEditor;

import java.util.Collection;

/**
 * Generic Game element tab, containing common methods for creating/editing elements and displaying
 * them.
 *
 * @author Ricky Weerts, Mike Keohane & Shaan Gondalia
 */
public class GameElementTab extends AbstractTab {
  private static final String VALID_NAME_REGEX = "^[\\w\\d \\t?.\\-/!@#$%^&*()+=\\[\\]{}<>:;_]+$";
  private static final double GAME_ELEMENT_DIVIDER_POSITION = 0.5;
  private GameElementList elementList;
  private TextField nameField;
  private PropertyEditor propertyEditor;
  private VBox rightBox;

  /**
   * Creates a game element tab with the given callback dispatcher and type
   *
   * @param dispatcher the callback dispatcher to communicate with the controller
   * @param type the type of the game element hosted in the tab
   */
  public GameElementTab(CallbackDispatcher dispatcher, String type) {
    super(type, dispatcher);
    getSplitPane().setDividerPositions(GAME_ELEMENT_DIVIDER_POSITION);
  }

  /**
   * Sets up the right side of the split pane to be the corresponding Property Selectors
   *
   * @return Node for the VBox containing the elements
   */
  @Override
  protected Node setupRightSide() {
    rightBox = new VBox();
    propertyEditor = new PropertyEditor(getCallbackDispatcher());
    nameField = new TextField(
        ViewResourcesSingleton.getInstance().getString("defaultName-" + getType()));
    nameField.setId("nameField-"+getType());
    Button createNewButton = makeButton("new-" + getType(), e -> createElement());
    createNewButton.setId("new-" + getType());
    Button savePropertyButton = makeButton(
        "save", e -> saveCurrentElement());
    savePropertyButton.setId("save-" + getType());
    rightBox.getChildren().addAll(
        createNewButton, nameField, propertyEditor, savePropertyButton);
    rightBox.setId("rightGameElementsPane");
    rightBox.getStyleClass().add("rightPane");
    return rightBox;
  }

  /**
   * Sets up the left side of the Split Pane to the elementList
   *
   * @return Node corresponding to the elementList
   */
  @Override
  protected Node setupLeftSide(){
    elementList = new GameElementList(this::elementSelected);
    elementList.setId("elementList-" + getType());
    return elementList;
  }

  // Callback method for creating a game element
  private void createElement() {
    try {
      Collection<Property> properties = getCallbackDispatcher().call(new GetPropertiesCallback(getType()))
          .orElseThrow();
      propertyEditor.setElementPropertyTypeChoice(properties);
    } catch (InvalidTypeException | MissingRequiredPropertyException e) {
      throw new ElementCreationException(getType(), e);
    }
  }

  // Callback method for when an element is selected
  private void elementSelected(String oldElement, String newElement) {
    if (newElement != null) {
      nameField.setText(newElement);
      propertyEditor.setElementProperties(
          getCallbackDispatcher().call(new GetElementPropertiesCallback(getType(), newElement))
              .orElseThrow());
    }
  }

  // Callback method for saving a currently selected game element
  private void saveCurrentElement() {
    String name = nameField.getText();
    if(validateName(name)) {
      getCallbackDispatcher().call(new UpdateGameElementCallback(getType(), name,
              propertyEditor.getElementProperties()));
      elementList.putGameElement(name, propertyEditor.getElementProperties());
    } else {
      new Alert(Alert.AlertType.ERROR, ViewResourcesSingleton.getInstance().getString("InvalidElementName", getType())).showAndWait();
    }
  }

  protected boolean validateName(String name) {
    return name.matches(VALID_NAME_REGEX);
  }

  /**
   * Loads all elements into a tab (read from the model)
   */
  @Override
  public void loadElements() {
    Collection<String> names = getCallbackDispatcher().call(new GetElementNamesCallback(getType())).orElseThrow();
    for (String name : names) {
      Collection<Property> properties = getCallbackDispatcher().call(new GetElementPropertiesCallback(getType(), name)).orElseThrow();
      elementList.putGameElement(name, properties);
    }
  }

}

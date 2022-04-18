package oogasalad.builder.view.tab;

import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.GameElementList;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.UpdateGameElementCallback;
import oogasalad.builder.view.property.PropertyEditor;

import java.util.Collection;

/**
 * @author Ricky Weerts, Mike Keohane
 */
public class GameElementTab extends BasicTab {

  private final CallbackDispatcher callbackDispatcher;

  private GameElementList elementList;
  private TextField nameField;
  private PropertyEditor propertyEditor;
  private VBox rightBox;

  /**
   * Default constructor
   */
  public GameElementTab(CallbackDispatcher dispatcher, String type) {
    super(type);
    this.callbackDispatcher = dispatcher;
  }

  /**
   * Sets up the right side of the split pane to be the corresponding Property Selectors
   * @return Node for the VBox containing the elements
   */
  @Override
  protected Node setupRightSide() {
    rightBox = new VBox();
    propertyEditor = new PropertyEditor();
    nameField = new TextField(
        ViewResourcesSingleton.getInstance().getString("defaultName-" + getType()));
    rightBox.getChildren().addAll(
        makeButton("new-" + getType(), e -> createElement()), nameField, propertyEditor,
        makeButton(
            "save", e -> saveCurrentElement()));
    rightBox.setId("rightGameElementsPane");
    rightBox.getStyleClass().add("rightPane");
    return rightBox;
  }

  /**
   * Sets up the left side of the Split Pane to the elementList
   * @return Node corresponding to the elementList
   */
  @Override
  protected Node setupLeftSide(){
    elementList = new GameElementList(this::elementSelected);
    return elementList;
  }



  // FIXME handle error
  private void createElement() {
    try {
      Collection<Property> properties = callbackDispatcher.call(new GetPropertiesCallback(getType()))
          .orElseThrow();
      propertyEditor.setElementPropertyTypeChoice(properties);
    } catch (InvalidTypeException | MissingRequiredPropertyException e) {
      e.printStackTrace();
    }
  }

  private void elementSelected(String oldElement, String newElement) {
    if (newElement != null) {
      nameField.setText(newElement);
      propertyEditor.setElementProperties(
          callbackDispatcher.call(new GetElementPropertiesCallback(getType(), newElement))
              .orElseThrow());
    }
  }

  private void saveCurrentElement() {
    if (!propertyEditor.hasProperties()) {
      return;
    }
    callbackDispatcher.call(new UpdateGameElementCallback(getType(), nameField.getText(),
        propertyEditor.getElementProperties()));
    elementList.putGameElement(nameField.getText(), propertyEditor.getElementProperties());
  }

}

package oogasalad.builder.view.tab;

import java.util.concurrent.atomic.AtomicReference;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.GameElementList;
import oogasalad.builder.view.property.PropertyEditor;
import oogasalad.builder.view.ViewResourcesSingleton;

import java.util.*;

/**
 *
 */
public class GameElementTab extends BorderPane {


  public int CLICK_PAD = 10;
  // FIXME Remove this
  private BuilderController controller;

  private GameElementList elementList;

  private final String type;
  private TextField nameField;
  private PropertyEditor propertyEditor;
  private VBox rightBox;
  private boolean resizeable;

  /**
   * Default constructor
   */
  public GameElementTab(BuilderController controller, String type) {
    this.controller = controller;
    this.type = type;

    setupClickAndDragResizing();
    setupCenterPane();
    setupRightPane();
    setupTitle();
  }

  private void setupCenterPane() {
    elementList = new GameElementList(this::elementSelected);
    setCenter(elementList);
  }

  private void setupRightPane() {
    rightBox = new VBox();
    propertyEditor = new PropertyEditor();
    nameField = new TextField(ViewResourcesSingleton.getInstance().getString("defaultName-" + type));
    rightBox.getChildren().addAll(
        makeButton("new-" + type, e -> createElement()), nameField, propertyEditor,
        makeButton(
            "save", e -> saveCurrentElement()));
    rightBox.setId("rightGameElementsPane");
    setRight(rightBox);

  }
  private void setupClickAndDragResizing(){
    this.setOnMouseMoved(e -> checkIfEditable(e));
    this.setOnDragDetected(drag -> {resizeRightPane(drag); startFullDrag();});
  }
  public void checkIfEditable(MouseEvent mouse){
    if ((mouse.getX() >= (this.getWidth() - rightBox.getWidth()-CLICK_PAD)) && (mouse.getX() <= (this.getWidth() - rightBox.getWidth() + CLICK_PAD))){
      this.setCursor(Cursor.E_RESIZE);
      resizeable = true;
    }
    else {
      elementList.setCursor(Cursor.DEFAULT);
      this.setCursor(Cursor.DEFAULT);
      resizeable = false;
    }
  }

  public void resizeRightPane(MouseEvent drag){
    if (resizeable){
      rightBox.setPrefWidth(this.getWidth() - drag.getSceneX());

    }
  }

  private void setupTitle() {
    setTop(new TitlePane(type + "Title").toNode());
  }

  // FIXME handle error
  private void createElement() {
    try {
      Collection<Property> properties = controller.getRequiredProperties(type);
      propertyEditor.setElementProperties(properties);
    } catch (InvalidTypeException | MissingRequiredPropertyException e) {
      e.printStackTrace();
    }
  }

  private void elementSelected(String oldElement, String newElement) {
    if (newElement != null) {
      nameField.setText(newElement);
      propertyEditor.setElementProperties(controller.getElementProperties(type, newElement));
    }
  }

  private void saveCurrentElement() {
    controller.update(type, nameField.getText(), propertyEditor.getElementProperties());
    elementList.putGameElement(nameField.getText(), propertyEditor.getElementProperties());
  }

  public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(ViewResourcesSingleton.getInstance().getString(property));
    result.setOnAction(handler);
    return result;
  }

  /**
   * @param element
   * @return
   */
  public void putGameElement(ElementRecord element) {
    // TODO implement here
    //return null;
  }

  /**
   * @param name
   * @return
   */
  public boolean hasGameElement(String name) {
    // TODO implement here
    return false;
  }

}
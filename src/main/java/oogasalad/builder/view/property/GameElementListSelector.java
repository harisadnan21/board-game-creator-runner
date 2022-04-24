package oogasalad.builder.view.property;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.model.property.StringListProperty;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * PropertySelector that lets users choose any number of Game Elements of a certain type and put them in a list
 * Users can delete Game Elements with a button once they've been added to the list
 *
 * @author Ricky Weerts
 */
public class GameElementListSelector implements PropertySelector {
  private static final String IMAGE_PROPERTY_NAME = "image";

  private final CallbackDispatcher callbackDispatcher;
  private final Property property;
  private final String type;

  private final BorderPane pane;
  private final ComboBox<String> addElement;
  private final ListView<String> elementsList;

  /**
   * Creates a GameElementListSelector, which displays an editable list of GameElements
   *
   * @param property the property that will be "filled in"
   * @param type the type of game element contained in the list
   * @param dispatcher the callback dispatcher for getting data about game elements
   */
  protected GameElementListSelector(Property property, String type, CallbackDispatcher dispatcher) {
    this.property = property;
    this.type = type;
    this.callbackDispatcher = dispatcher;
    pane = new BorderPane();
    addElement = new ComboBox<>();
    elementsList = new ListView<>();
    setup();
  }

  // Instantiate the list and selector and put them in the main pane
  private void setup() {
    elementsList.setEditable(true);
    elementsList.getItems().setAll(parseString(property.valueAsString()));
    elementsList.setCellFactory(view -> new ListCell<>() {
      @Override
      protected void updateItem(String elementName, boolean b) {
        super.updateItem(elementName, b);
        setText(null);
        if(elementName == null) {
          setGraphic(null);
          return;
        }
        BorderPane pane = new BorderPane();
        pane.setCenter(new Label(elementName));

        Button deleteButton = new Button("X");
        deleteButton.setOnAction(e -> removeElement(this.getIndex()));
        pane.setRight(deleteButton);

        setGraphic(pane);
      }
    });

    addElement.setPromptText(ViewResourcesSingleton.getInstance().getString("game-element-list-add-" + type));
    addElement.getItems().setAll(callbackDispatcher.call(new GetElementNamesCallback(type)).orElse(List.of()));
    addElement.setOnAction(e -> addGameElement(addElement.getSelectionModel().getSelectedItem()));

    pane.setTop(addElement);
    pane.setCenter(elementsList);
  }

  // Remove the element at the given index of the list
  private void removeElement(int index) {
    elementsList.getItems().remove(index);
  }

  // Adds an element to the end of the list
  private void addGameElement(String element) {
    if(element != null) {
      elementsList.getItems().add(element);
      Platform.runLater(() -> {
        addElement.getSelectionModel().clearSelection();
        addElement.setValue(null);
      });
    }
  }

  /**
   * Returns a JavaFX Node that will be displayed to the user next to the property label
   *
   * @return a Node that will be shown to the user containing UI for entering a property value
   */
  @Override
  public Node display() {
    return pane;
  }

  /**
   * Returns a populated property with the list of properties that the user selected
   *
   * @return a populated property with the list of properties that the user selected
   */
  @Override
  public Property getProperty() {
    return property.with(property.shortName(), String.join(",", elementsList.getItems()));
  }

  // Convert strings separated by commas into a collection of those strings
  private Collection<String> parseString(String toParse) {
    return toParse.isBlank() ? List.of() : List.of(toParse.split(","));
  }

  @Override
  public void addListener(ChangeListener updateFields) {

  }
}

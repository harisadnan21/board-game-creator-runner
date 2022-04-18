package oogasalad.builder.view.property;

import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.GameElementList;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;

import java.io.File;

public class GameElementListSelector implements PropertySelector {
  private final Property property;
  private final String type;
  private final BorderPane pane;
  private final CallbackDispatcher callbackDispatcher;

  protected GameElementListSelector(Property property, String type, CallbackDispatcher dispatcher) {
    this.property = property;
    this.type = type;
    this.callbackDispatcher = dispatcher;
    pane = new BorderPane();
    setup();
  }

  private void setup() {
    ListView<String> listView = new ListView<>();
    listView.setEditable(true);
    listView.getItems().setAll("a", "b", "c");
    listView.setCellFactory(view -> new ListCell<>() {
      @Override
      protected void updateItem(String elementName, boolean b) {
        super.updateItem(elementName, b);
        if(elementName == null) {
          return;
        }
        BorderPane pane = new BorderPane();
        pane.setCenter(new Label(elementName));
        pane.setRight(new Button("X"));
        setText(null);
        setGraphic(pane);
      }
    });
    pane.setCenter(listView);
  }

  @Override
  public Node display() {
    return pane;
  }

  @Override
  public Property getProperty() {
    return property;
  }

  @Override
  public void addListener(ChangeListener updateFields) {

  }
}

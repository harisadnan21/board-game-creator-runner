package oogasalad.builder.view;

import static oogasalad.builder.view.BuilderView.DEFAULT_STYLE_PACKAGE;
import static oogasalad.builder.view.BuilderView.DEFAULT_TAB_FORMAT;

import java.io.File;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.SaveCallback;
import oogasalad.builder.view.callback.UpdateGameElementCallback;
import oogasalad.builder.view.property.PropertyEditor;

/**
 * Creates a popup for the user to edit the metadata then save the file
 *
 * @author Mike Keohane
 */
public class MetaDataAndSavePopup {

  private static final String SAVE_CHOOSER_TITLE_KEY = "SaveChooserTitle";
  public static final String METADATA = "metadata";
  public static final String TITLE = "Title";
  private CallbackDispatcher callbackDispatcher;
  private VBox metaBox;
  private PropertyEditor propertyEditor;
  private Stage metaStage;

  /**
   * Initializes the popup by creating a state and propertyEditor
   *
   * @param callbackDispatcher - CallbackDispatcher to communicate with the controller
   */
  public MetaDataAndSavePopup(CallbackDispatcher callbackDispatcher) {
    this.callbackDispatcher = callbackDispatcher;
    propertyEditor = new PropertyEditor(callbackDispatcher);
    metaStage = new Stage();
    setupMetaDataPopup();
  }

  private void setupMetaBox() {
    metaBox = new VBox();
    metaBox.setId("metaBox");
    Button save = makeButton("save", e -> saveAndCloseMeta());
    save.setId("metaSaveButton");
    metaBox.getChildren().addAll(propertyEditor, save);
    metaBox.getStyleClass().add("rightPane");
  }


  private void setupMetaDataPopup() {
    setupMetaBox();
    metaStage.setTitle(ViewResourcesSingleton.getInstance().getString(METADATA + TITLE));
    Scene metaScene = new Scene(metaBox, 600, 600);
    metaScene.getStylesheets()
        .add(getClass().getResource(DEFAULT_STYLE_PACKAGE + DEFAULT_TAB_FORMAT).toExternalForm());
    metaStage.setScene(metaScene);
    Collection<Property> metaProperties = callbackDispatcher.call(
        new GetPropertiesCallback(METADATA)).orElseThrow();
    propertyEditor.setElementPropertyTypeChoice(metaProperties);
    populateProperties();
    metaStage.show();
  }

  private void populateProperties() {
    if (callbackDispatcher.call(new GetElementNamesCallback(METADATA)).orElseThrow()
        .contains(METADATA)) {
      propertyEditor.setElementProperties(
          callbackDispatcher.call(new GetElementPropertiesCallback(METADATA, METADATA))
              .orElseThrow());
    }
  }

  private void saveAndCloseMeta() {
    callbackDispatcher.call(new UpdateGameElementCallback(METADATA, METADATA,
        propertyEditor.getElementProperties()));
    metaStage.close();
    goToSave();
  }

  private void goToSave() {
    Stage saveStage = new Stage();
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle(
        ViewResourcesSingleton.getInstance().getString(SAVE_CHOOSER_TITLE_KEY));
    File file = directoryChooser.showDialog(saveStage);
    if (file != null){
      callbackDispatcher.call(new SaveCallback(file));
    }

  }

  private Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    String label = ViewResourcesSingleton.getInstance().getString(property);
    result.setText(label);
    result.setOnAction(handler);
    return result;
  }
}

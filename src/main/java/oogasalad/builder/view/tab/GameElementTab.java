package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.UpdateGameElementCallback;

import java.util.*;

/**
 * 
 */
public class GameElementTab extends BorderPane {
    private final CallbackDispatcher callbackDispatcher;

    private GameElementList elementList;

    private final String type;
    private TextField nameField;
    private PropertyEditor propertyEditor;

    /**
     * Default constructor
     */
    public GameElementTab(CallbackDispatcher dispatcher, String type) {
        this.callbackDispatcher = dispatcher;
        this.type = type;

        setupCenterPane();
        setupRightPane();
        setupTitle();
    }

    private void setupCenterPane() {
        elementList = new GameElementList(this::elementSelected);
        setCenter(elementList);
    }

    private void setupRightPane() {
        VBox rightBox = new VBox();
        propertyEditor = new PropertyEditor();

        rightBox.getChildren().addAll(
                makeButton("new-" + type, e -> createElement()),
                nameField = new TextField(ViewResourcesSingleton.getInstance().getString("defaultName-" + type)),
                propertyEditor, makeButton("save", e -> saveCurrentElement())
        );
        rightBox.setId("rightGameElementsPane");
        setRight(rightBox);
    }

    private void setupTitle() {
        setTop(new TitlePane(type+"Title").toNode());
    }

    // FIXME handle error
    private void createElement() {
        try {
            // saveCurrentElement();
            Collection<Property> properties = callbackDispatcher.call(new GetPropertiesCallback(type)).orElseThrow();
            propertyEditor.setElementProperties(properties);
        } catch(InvalidTypeException | MissingRequiredPropertyException e) {
            e.printStackTrace();
        }
    }

    private void elementSelected(String oldElement, String newElement) {
        if(newElement != null) {
            nameField.setText(newElement);
            propertyEditor.setElementProperties(callbackDispatcher.call(new GetElementPropertiesCallback(type, newElement)).orElseThrow());
        }
    }

    private void saveCurrentElement() {
        callbackDispatcher.call(new UpdateGameElementCallback(type, nameField.getText(), propertyEditor.getElementProperties()));
        elementList.putGameElement(nameField.getText(), propertyEditor.getElementProperties());
        propertyEditor.clear();
    }

    public static Button makeButton(String property, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setText(ViewResourcesSingleton.getInstance().getString(property));
        result.setOnAction(handler);
        return result;
    }
}
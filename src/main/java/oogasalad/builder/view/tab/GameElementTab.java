package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.model.element.ElementRecord;
import oogasalad.builder.model.exception.InvalidTypeException;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.PropertyEditor;
import oogasalad.builder.view.ViewResourcesSingleton;

import java.util.*;

/**
 * 
 */
public class GameElementTab extends BorderPane {
    // FIXME Remove this
    private BuilderController controller;

    private final String type;
    private TextField nameField;
    private PropertyEditor propertyEditor;

    /**
     * Default constructor
     */
    public GameElementTab(BuilderController controller, String type) {
        this.controller = controller;
        this.type = type;

        setupRightPane();
        setupTitle();
    }

    private void setupRightPane() {
        VBox rightBox = new VBox();
        propertyEditor = new PropertyEditor();

        rightBox.getChildren().addAll(
                makeButton("new-" + type, e -> createPiece()),
                nameField = new TextField(ViewResourcesSingleton.getInstance().getString("defaultName-" + type)),
                propertyEditor
        );
        rightBox.setId("rightPane");
        setRight(rightBox);
    }

    private void setupTitle() {
        setTop(new TitlePane(type+"Title").toNode());
    }

    // FIXME handle error
    private void createPiece() {
        try {
            if(propertyEditor.hasProperties()) {
                controller.update(type, nameField.getText(), propertyEditor.getElementProperties());
            }
            Collection<Property> properties = controller.getRequiredProperties(type);
            propertyEditor.setElementProperties(properties);
        } catch(InvalidTypeException | MissingRequiredPropertyException e) {
            e.printStackTrace();
        }
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
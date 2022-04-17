package oogasalad.builder.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import oogasalad.builder.model.element.GameElement;
import oogasalad.builder.model.property.Property;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * 
 */
public class GameElementList extends ListView<GameElementList.GameElementData> {
    protected record GameElementData(String name, Collection<Property> properties) {}
    private static final String IMAGE_PROPERTY_NAME = "image";
    private final BiConsumer<String, String> selectElementCallback;

    /**
     * Default constructor
     */
    public GameElementList(BiConsumer<String, String> selectElement) {
        selectElementCallback = selectElement;
        setup();
        this.getStyleClass().add("gameElementList");
    }

    private void setup() {
        setEditable(true);
        setCellFactory(elementData -> new ListCell<>() {
            @Override
            protected void updateItem(GameElementData gameElementData, boolean b) {
                super.updateItem(gameElementData, b);
                if(gameElementData == null) {
                    return;
                }
                setText(gameElementData.name);
                var cell = this;
                gameElementData.properties().stream()
                        .filter(property -> property.name().equals(IMAGE_PROPERTY_NAME) || property.name().endsWith("-" + IMAGE_PROPERTY_NAME))
                        .findFirst()
                        .ifPresent(prop -> {
                            ImageView image = new ImageView(new Image(new File(prop.valueAsString()).toURI().toString()));
                            image.setPreserveRatio(true);
                            //FIXME! MAGIC NUMBER!
                            image.setFitHeight(55);
                            cell.setGraphic(image);
                        });
            }
        });
        getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            selectElementCallback.accept(oldVal == null ? null : oldVal.name(), newVal == null ? null : newVal.name());
        });
    }

    /**
     * @param name
     * @param properties
     * @return
     */
    public void putGameElement(String name, Collection<Property> properties) {
        GameElementData elementData = new GameElementData(name, properties);
        for(int i = 0; i < getItems().size(); i++) {
            if(getItems().get(i).name().equals(name)) {
                getItems().set(i, elementData);
                return;
            }
        }
        getItems().add(elementData);
    }

}
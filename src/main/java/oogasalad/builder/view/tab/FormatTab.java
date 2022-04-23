package oogasalad.builder.view.tab;


import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import oogasalad.builder.view.callback.CallbackDispatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import java.util.ResourceBundle;


public class FormatTab extends AbstractTab{
    public static String FORMAT = "format";
    public static String HELP_RESOURCE_PATH = "/view/";
    public static String ELEMENTS_PACKAGE = "FormatList";
    private StackPane leftDisplay;
    private VBox rightHelpBox;
    private static final ResourceBundle elementNames = ResourceBundle.getBundle(HELP_RESOURCE_PATH + ELEMENTS_PACKAGE);

    public FormatTab(CallbackDispatcher dispatcher) { super(FORMAT, dispatcher);}

    @Override
    protected Node setupRightSide() {
        rightHelpBox = new VBox();

        rightHelpBox.getChildren().add(makeButton("tabFormat-format", e -> displayChangeBackground()));
        rightHelpBox.getChildren().add(makeButton("sunset-format", e -> displayChangeBackground()));
        rightHelpBox.getChildren().add(makeButton("DukevUNC-format", e -> displayChangeBackground()));
        rightHelpBox.getChildren().add(makeButton("normalmode-format", e -> displayChangeBackground()));
        rightHelpBox.getChildren().add(makeButton("presentationmode-format", e -> displayChangeBackground()));
        rightHelpBox.getChildren().add(makeButton("fancymode-format", e -> displayChangeBackground()));
        rightHelpBox.getStyleClass().add("rightPane");
        return rightHelpBox;
        //return null;
    }

    @Override
    protected Node setupLeftSide() {
        leftDisplay = new StackPane();
//        //leftDisplay.getStyleClass().add("helpBox");
        return leftDisplay;
        //return null;
    }

    private void displayChangeBackground(){
        //leftDisplay.getChildren().clear();

    }

    @Override
    public void loadElements() {

    }
}

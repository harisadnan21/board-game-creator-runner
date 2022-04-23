package oogasalad.builder.view.tab;


import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import oogasalad.builder.view.callback.CallbackDispatcher;
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
    private String[] elementNames = {"tabFormat", "Sunset", "DukevUNC"};
    //private static final ResourceBundle elementNames = ResourceBundle.getBundle(HELP_RESOURCE_PATH + ELEMENTS_PACKAGE);

    public FormatTab(CallbackDispatcher dispatcher) { super(FORMAT, dispatcher);}

    @Override
    protected Node setupRightSide() {
        rightHelpBox = new VBox();
//        for (String name : elementNames){
//            rightHelpBox.getChildren().add(makeButton(name, e -> displayChangeBackground()));
//        }
        //rightHelpBox.getStyleClass().add("rightPane");
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

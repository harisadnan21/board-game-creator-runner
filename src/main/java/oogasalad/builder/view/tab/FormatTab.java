package oogasalad.builder.view.tab;


import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import oogasalad.builder.view.callback.CallbackDispatcher;

import javafx.scene.layout.VBox;
import oogasalad.builder.view.callback.SetFormatCallback;

/**
 * @author Thivya
 */
public class FormatTab extends AbstractTab{
    public static String FORMAT = "format";
    public static int PRESENTATION = 0;
    public static int FANCY = 0;
    private StackPane leftDisplay;
    private VBox rightHelpBox;

    public FormatTab(CallbackDispatcher dispatcher) { super(FORMAT, dispatcher);}

    @Override
    protected Node setupRightSide() {
        rightHelpBox = new VBox();

        rightHelpBox.getChildren().add(makeButton("tabFormat-format", e -> displayBackground("tabFormat.css")));
        rightHelpBox.getChildren().add(makeButton("sunset-format", e -> displayBackground("nightTabFormat.css")));
        rightHelpBox.getChildren().add(makeButton("DukevUNC-format", e -> displayBackground("dukeTabFormat.css")));
        rightHelpBox.getChildren().add(makeButton("normalmode-format", e -> presentationMode()));
        rightHelpBox.getChildren().add(makeButton("presentationmode-format", e -> presentationMode()));
        rightHelpBox.getChildren().add(makeButton("fancymode-format", e -> fancyMode()));
        rightHelpBox.getStyleClass().add("rightPane");
        return rightHelpBox;
        //return null;
    }

    @Override
    protected Node setupLeftSide() {
        leftDisplay = new StackPane();
//        //leftDisplay.getStyleClass().add("helpBox");
        return leftDisplay;
    }

    private void displayBackground(String newStyle){
        getCallbackDispatcher().call(new SetFormatCallback(newStyle));
    }

    public void presentationMode(){
        if(PRESENTATION == 0) { PRESENTATION = 1;}

        else{ PRESENTATION = 0;}
        displayBackground("tabFormat.css");
    }

    public void fancyMode(){
        if(FANCY == 0) { FANCY = 1;}

        else{ FANCY = 0;}
        displayBackground("tabFormat.css");
    }

    @Override
    public void loadElements() {

    }
}

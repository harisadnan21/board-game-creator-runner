package oogasalad.builder.view.tab;

import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;

import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetElementNamesCallback;


public class HelpTab extends BasicTab{
  public static String HELP = "help";
  public static String HELP_RESOURCE_PATH = "/elements/help/";
  public static String ELEMENTS_PACKAGE = "ElementsNameList";
  private VBox leftDisplay;
  private ResourceBundle propertyMap;
  private static final ResourceBundle elementNames = ResourceBundle.getBundle(HELP_RESOURCE_PATH + ELEMENTS_PACKAGE);;

  public HelpTab(CallbackDispatcher callbackDispatcher){
    super(HELP, callbackDispatcher);
  }
  @Override
  protected Node setupRightSide() {
    VBox rightHelpBox = new VBox();
    for (String name : elementNames.keySet()){
      rightHelpBox.getChildren().add(makeButton(name, e -> displayHelpForElement(name)));
    }
    rightHelpBox.getStyleClass().add("rightPane");
    return rightHelpBox;
  }

  @Override
  protected Node setupLeftSide() {
    leftDisplay = new VBox();
    leftDisplay.getStyleClass().add("rightPane");
    return leftDisplay;
  }


  public void displayHelpForElement(String type){
    leftDisplay.getChildren().clear();
    if (type.equals(BOARD_TYPE)){
      leftDisplay.getChildren().add(new Text(ViewResourcesSingleton.getInstance().getString(type + "-" + HELP)));
    }
    else{
      Collection<String> elementPropertyNames = getCallbackDispatcher().call(new GetElementNamesCallback(type)).orElseThrow();
    }
  }
  @Override
  public void loadElements() {

  }
}

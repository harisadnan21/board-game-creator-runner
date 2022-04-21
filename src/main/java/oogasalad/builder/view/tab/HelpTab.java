package oogasalad.builder.view.tab;


import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;

import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetPropertiesCallback;


public class HelpTab extends BasicTab{
  public static String HELP = "help";
  public static String HELP_RESOURCE_PATH = "/elements/help/";
  public static String ELEMENTS_PACKAGE = "ElementsNameList";
  private VBox leftDisplay;
  private static final ResourceBundle elementNames = ResourceBundle.getBundle(HELP_RESOURCE_PATH + ELEMENTS_PACKAGE);

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
    leftDisplay.getChildren().add(new Text(ViewResourcesSingleton.getInstance().getString(type + "-" + HELP)));

    if (!type.equals(BOARD_TYPE)){
      Collection<Property> elementProperties = getCallbackDispatcher().call(new GetPropertiesCallback(type)).orElseThrow();
      for (Property property : elementProperties){
        String propertyName = property.name();
        if (propertyName.contains("required-")){
          propertyName = propertyName.replace("required", type);
        }
        leftDisplay.getChildren().add(new Text(ViewResourcesSingleton.getInstance().getString(propertyName + "-" + HELP)));
      }
    }

  }
  @Override
  public void loadElements() {

  }
}

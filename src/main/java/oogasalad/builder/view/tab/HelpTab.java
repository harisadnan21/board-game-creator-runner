package oogasalad.builder.view.tab;


import static oogasalad.builder.view.tab.AllTabs.DELIMINATOR;
import static oogasalad.builder.view.tab.AllTabs.ORDERED_TABS;
import static oogasalad.builder.view.tab.AllTabs.tabsList;
import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;

import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetPropertiesCallback;

/**
 * Displays help to explain how all the other tabs work
 * @author Mike Keohane
 */
public class HelpTab extends AbstractTab {
  public static String HELP = "help";
  private TextArea leftDisplay;

  public HelpTab(CallbackDispatcher callbackDispatcher){
    super(HELP, callbackDispatcher);
  }
  @Override
  protected Node setupRightSide() {
    VBox rightHelpBox = new VBox();
    for (String name : tabsList.getString(ORDERED_TABS).split(DELIMINATOR)){
      rightHelpBox.getChildren().add(makeButton(name, e -> displayHelpForElement(name)));
    }
    rightHelpBox.getStyleClass().add("rightPane");
    return rightHelpBox;
  }

  @Override
  protected Node setupLeftSide() {
    leftDisplay = new TextArea();
    leftDisplay.getStyleClass().add("helpBox");
    leftDisplay.setId("helpBox");
    leftDisplay.setWrapText(true);
    leftDisplay.setEditable(false);
    return leftDisplay;
  }

  //Displays the help for each Elemet
  private void displayHelpForElement(String type){
    leftDisplay.clear();
    leftDisplay.setText(ViewResourcesSingleton.getInstance().getString(type) + " Tab");
    leftDisplay.setText(leftDisplay.getText() + "\n" + ViewResourcesSingleton.getInstance().getString(type + "-" + HELP) +"\n");

    boolean hasRequiredType = false;
    StringBuilder textToDisplay = new StringBuilder();
    textToDisplay.append(leftDisplay.getText());
    if (!(type.equals(BOARD_TYPE) || type.equals(HELP))){
      Collection<Property> elementProperties = getCallbackDispatcher().call(new GetPropertiesCallback(type)).orElseThrow();
      for (Property property : elementProperties){
        String propertyName = property.name();
        if (propertyName.contains("required-")){
          propertyName = propertyName.replace("required", type);
        }
        if (propertyName.contains("-type")){
          String[] typeOptions = property.valueAsString().split("-");
          leftDisplay.setText(leftDisplay.getText() + "\n" + ViewResourcesSingleton.getInstance().getString(propertyName + "-" + HELP));
          for (String propType : typeOptions){
            displayCorrespondingPropertiesOfType(propType, type);
            hasRequiredType = true;
          }
        }
        textToDisplay.append("\n")
            .append(ViewResourcesSingleton.getInstance().getString(propertyName + "-" + HELP));
      }
      if (!hasRequiredType){
        leftDisplay.setText(String.valueOf(textToDisplay));
      }
    }
  }

  //Displays the help for the properties of a type
  private void displayCorrespondingPropertiesOfType(String propType, String type){
    leftDisplay.setText(leftDisplay.getText() + "\n \n" + ViewResourcesSingleton.getInstance().getString(propType + "-" + HELP));
    Collection<Property> elementProperties = getCallbackDispatcher().call(new GetPropertiesCallback(type)).orElseThrow();
    for (Property prop : elementProperties){
      if (prop.name().contains(propType + "-")){
        leftDisplay.setText(leftDisplay.getText() + "\n" +  ViewResourcesSingleton.getInstance().getString(prop.name() + "-" + HELP));
      }
    }
  }

  /**
   * Need to implement abstract method but nothing needs to be loaded
   */
  @Override
  public void loadElements() {}
}

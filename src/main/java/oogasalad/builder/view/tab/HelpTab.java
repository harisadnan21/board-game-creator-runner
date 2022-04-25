package oogasalad.builder.view.tab;


import static oogasalad.builder.view.tab.AllTabs.DELIMINATOR;
import static oogasalad.builder.view.tab.AllTabs.ORDERED_TABS;
import static oogasalad.builder.view.tab.AllTabs.tabsList;
import static oogasalad.builder.view.tab.boardTab.BoardTab.BOARD_TYPE;

import java.util.Collection;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import oogasalad.builder.model.property.Property;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Displays help to explain how all the other tabs work
 *
 * @author Mike Keohane
 */
public class HelpTab extends AbstractTab {

  public static String NEW_LINE = "\n";
  public static String HELP = "help";
  public static String TYPE = "type";
  public static String REQUIRED = "required";
  private TextArea leftDisplay;

  /**
   * Initializes the HelpTab by calling the Abstract Tab superclass
   *
   * @param callbackDispatcher - callbackDispatcher to communicate with controller.
   */
  public HelpTab(CallbackDispatcher callbackDispatcher) {
    super(HELP, callbackDispatcher);
  }

  /**
   * Sets up right side of pane to box with buttons for each tab
   *
   * @return - node to add to right SplitPane
   */
  @Override
  protected Node setupRightSide() {
    VBox rightHelpBox = new VBox();
    for (String name : tabsList.getString(ORDERED_TABS).split(DELIMINATOR)) {
      rightHelpBox.getChildren().add(makeButton(name, e -> displayHelpForElement(name)));
    }
    rightHelpBox.getStyleClass().add("rightPane");
    return rightHelpBox;
  }

  /**
   * sets up left side of pane to a TextArea to display the help
   *
   * @return - TextArea leftDisplay
   */
  @Override
  protected Node setupLeftSide() {
    leftDisplay = new TextArea();
    leftDisplay.getStyleClass().add("helpBox");
    leftDisplay.setId("helpBox");
    leftDisplay.setWrapText(true);
    leftDisplay.setEditable(false);
    return leftDisplay;
  }

  //Displays the help for each Element and checks for specific types to preserve order
  private void displayHelpForElement(String type) {
    leftDisplay.clear();
    leftDisplay.setText(getViewResourceString(type) + " Tab");
    leftDisplay.setText(
        leftDisplay.getText() + NEW_LINE + getViewResourceString(type + DELIMINATOR + HELP)
            + NEW_LINE);

    boolean hasRequiredType = false;
    StringBuilder textToDisplay = new StringBuilder();
    textToDisplay.append(leftDisplay.getText());
    if (!(type.equals(BOARD_TYPE) || type.equals(HELP))) {
      Collection<Property> elementProperties = getCallbackDispatcher().call(
          new GetPropertiesCallback(type)).orElseThrow();
      for (Property property : elementProperties) {
        String propertyName = property.name();
        if (propertyName.contains(REQUIRED + DELIMINATOR)) {
          propertyName = propertyName.replace(REQUIRED, type);
        }
        if (propertyName.contains(DELIMINATOR + TYPE)) {
          String[] typeOptions = property.valueAsString().split(DELIMINATOR);
          leftDisplay.setText(leftDisplay.getText() + NEW_LINE + getViewResourceString(
              propertyName + DELIMINATOR + HELP));
          for (String propType : typeOptions) {
            displayCorrespondingPropertiesOfType(propType, type);
            hasRequiredType = true;
          }
        }
        textToDisplay.append(NEW_LINE)
            .append(getViewResourceString(propertyName + DELIMINATOR + HELP));
      }
      if (!hasRequiredType) {
        leftDisplay.setText(String.valueOf(textToDisplay));
      }
    }
  }

  //Displays the help for the properties of a type
  private void displayCorrespondingPropertiesOfType(String propType, String type) {
    leftDisplay.setText(leftDisplay.getText() + NEW_LINE + NEW_LINE + getViewResourceString(
        propType + DELIMINATOR + HELP));
    Collection<Property> elementProperties = getCallbackDispatcher().call(
        new GetPropertiesCallback(type)).orElseThrow();
    for (Property prop : elementProperties) {
      if (prop.name().contains(propType + DELIMINATOR)) {
        leftDisplay.setText(leftDisplay.getText() + NEW_LINE + getViewResourceString(
            prop.name() + DELIMINATOR + HELP));
      }
    }
  }

  private String getViewResourceString(String key) {
    try {
      return ViewResourcesSingleton.getInstance().getString(key);
    } catch (Exception e) {
      Logger log = LogManager.getLogger();
      log.log(Level.ERROR, e.getMessage());
    }
    return null;
  }


  /**
   * implements abstract method for loading elements, but nothing needs to be loaded
   */
  @Override
  public void loadElements() {
  }
}

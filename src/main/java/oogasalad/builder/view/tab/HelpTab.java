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
import oogasalad.builder.view.property.PropertyNameAnalyzer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Displays help to explain how all the other tabs work
 *
 * @author Mike Keohane
 */
public class HelpTab extends AbstractTab {

  public static final String HELP = "help";
  private static final String CANT_HELP_MESSAGE = ": Couldn't Find Help";
  private final PropertyNameAnalyzer propertyNameAnalyzer = new PropertyNameAnalyzer();
  public static String NEW_LINE = "\n";
  private TextArea leftDisplay;
  private boolean hasRequiredType;

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
    getPropertiesHelp(type);

  }

  private void getPropertiesHelp(String type) {
    hasRequiredType = false;
    StringBuilder textToDisplay = new StringBuilder();
    textToDisplay.append(leftDisplay.getText());
    if (!type.equals(BOARD_TYPE) && !type.equals(HELP)) {
      Collection<Property> elementProperties = getCallbackDispatcher().call(
          new GetPropertiesCallback(type)).orElseThrow();
      for (Property property : elementProperties) {
        addHelpForNonTypeProperties(property, type, textToDisplay);

        checkAndGetTypeProperties(property, type);
      }
      if (!hasRequiredType) {
        leftDisplay.setText(String.valueOf(textToDisplay));
      }
    }
  }

  private void addHelpForNonTypeProperties(Property property, String type, StringBuilder textToDisplay){
    String propertyName = property.name();
    if (propertyNameAnalyzer.isRequiredProperty(property)) {
      propertyName = propertyName.replace(PropertyNameAnalyzer.REQUIRED, type);
    }
    textToDisplay.append(NEW_LINE)
        .append(getViewResourceString(propertyName + DELIMINATOR + HELP));
  }

  private void checkAndGetTypeProperties(Property property, String type) {
    if (propertyNameAnalyzer.isTypeProperty(property)) {
      String[] typeOptions = property.valueAsString().split(PropertyNameAnalyzer.DELIMITER);
      leftDisplay.setText(leftDisplay.getText() + NEW_LINE + getViewResourceString(
          property.name() + PropertyNameAnalyzer.DELIMITER + HELP));
      for (String propType : typeOptions) {
        displayCorrespondingPropertiesOfType(propType, type);
        hasRequiredType = true;
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
      if (propertyNameAnalyzer.getPropertyNamespace(prop).equals(propType)) {
        leftDisplay.setText(leftDisplay.getText() + NEW_LINE + getViewResourceString(
            prop.name() + PropertyNameAnalyzer.DELIMITER + HELP));
      }
    }
  }

  private String getViewResourceString(String key) {
    try {
      return ViewResourcesSingleton.getInstance().getString(key);
    } catch (Exception e) {
      LogManager.getLogger().log(Level.ERROR, e.getMessage());
    }
    return key + CANT_HELP_MESSAGE;
  }


  /**
   * implements abstract method for loading elements, but nothing needs to be loaded
   */
  @Override
  public void loadElements() {
  }
}

package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import oogasalad.builder.view.ViewResourcesSingleton;
import oogasalad.builder.view.callback.CallbackDispatcher;

/**
 * The Basic Tab which extends BorderPane is the basis for all of the tabs
 *
 * @author Mike Keohane
 */
public abstract class AbstractTab extends BorderPane implements BasicTab {

  private static final double BASIC_TAB_DIVIDER_POSITION = 0.7;
  private String type;
  private SplitPane splitPane;
  private CallbackDispatcher callbackDispatcher;

  /**
   * Initializes the tab by setting up the title and SplitPane
   *
   * @param type       - type of the tab being created, correlated to model
   * @param dispatcher - callback dispatcher to communicate to controller.
   */
  public AbstractTab(String type, CallbackDispatcher dispatcher) {
    this.type = type;
    this.callbackDispatcher = dispatcher;
    setupTitle();
    setupSplitPane();
  }

  private void setupTitle() {
    setTop(new TitlePane(type + "Title"));
  }

  private void setupSplitPane() {
    splitPane = new SplitPane(setupLeftSide(), setupRightSide());
    splitPane.setDividerPositions(BASIC_TAB_DIVIDER_POSITION);
    setCenter(splitPane);
  }

  /**
   * Creates a button with specified name and event on press
   *
   * @param property - key which correlates to text in the resources file
   * @param handler  - action which the button performs
   * @return - the created button
   */
  protected Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(ViewResourcesSingleton.getInstance().getString(property));
    result.setOnAction(handler);
    changeFontsButton(result);
    return result;
  }

//  private void changeFontsButton(Button changeButton) {
//    if(FormatTab.FANCY == 1) { changeButton.setFont(Font.font("Papyrus")); }
//    if(FormatTab.PRESENTATION == 1) { changeButton.setFont(Font.font("Lucida Sans")); }
//    if(FormatTab.NORMAL == 1) { changeButton.setFont(Font.font("Comic Sans")); }
//  }

  protected SplitPane getSplitPane(){
=======
  /**
   * returns the SplitPane used in the tab
   *
   * @return SplitPane splitPane
   */
  protected SplitPane getSplitPane() {
    return splitPane;
  }

  /**
   * returns the type of the tab
   *
   * @return String type
   */
  protected String getType() {
    return type;
  }

  /**
   * returns the callbackDispatcher
   *
   * @return CallbackDispatcher callbackDispatcher
   */
  protected CallbackDispatcher getCallbackDispatcher() {
    return callbackDispatcher;
  }

  /**
   * returns a node to set the right side of the SplitPane
   *
   * @return Node to set right pane
   */
  protected abstract Node setupRightSide();

  /**
   * returns a node to set the left side of the SplitPane
   *
   * @return Node to set right pane
   */
  protected abstract Node setupLeftSide();

  /**
   * Loads the elements of a tab based on the model
   */
  public abstract void loadElements();
}

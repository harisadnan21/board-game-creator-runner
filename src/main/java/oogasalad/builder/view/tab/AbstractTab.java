package oogasalad.builder.view.tab;

// Commit bf3358f6, 3f995f71, 30a2d94f
// This code is designed well because it abstracts all of the differnet tabs into
// one superclass that extends borderpane. Extending borderpane helps each of the
// tabs use the same pane set up and also have the properties of Nodes to be added to
// the tabs in AllTabs. The abstraction lets us create all of the tabs using reflection because
// they are all part of the same super class. The GameElementTab class is done really well,
// but adding this next level of abstraction helps eliminate duplicate code and lets us use
// Liskov-substitution between the different types of tab such as board and element.

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

  //Creates the title based on the type of the tab
  private void setupTitle() {
    setTop(new TitlePane(type + "Title"));
  }

  //Sets up the splitPane and sets it to the center of the boarderpane
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
    result.setId(property + "Button");
    result.setOnAction(handler);
    return result;
  }

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

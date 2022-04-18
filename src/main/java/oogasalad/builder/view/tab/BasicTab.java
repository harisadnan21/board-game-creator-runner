package oogasalad.builder.view.tab;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import oogasalad.builder.view.ViewResourcesSingleton;

/**
 * @author Mike Keohane
 */
public abstract class BasicTab extends BorderPane {

  private String type;
  private SplitPane splitPane;


  public BasicTab(String type) {
    this.type = type;
    setupTitle();
    setupSplitPane();
  }

  private void setupTitle() {
    setTop(new TitlePane(type + "Title").toNode());
  }

  private void setupSplitPane() {
    splitPane = new SplitPane(setupLeftSide(), setupRightSide());
    splitPane.setDividerPositions(0.7f);
    setCenter(splitPane);
  }
  protected Button makeButton(String property, EventHandler<ActionEvent> handler) {
    Button result = new Button();
    result.setText(ViewResourcesSingleton.getInstance().getString(property));
    result.setOnAction(handler);
    return result;
  }
  protected SplitPane getSplitPane(){
    return splitPane;
  }
  protected String getType(){
    return type;
  }

  protected abstract Node setupRightSide();

  protected abstract Node setupLeftSide();
}

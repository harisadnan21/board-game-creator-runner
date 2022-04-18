package oogasalad.builder.view.tab;

import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * @author Mike Keohane
 */
public abstract class BasicTab extends BorderPane {

  protected String type;
  public BasicTab(String type) {
    this.type = type;
    setupTitle();
    setupSplitPane();
  }

  private void setupTitle() {
    setTop(new TitlePane(type + "Title").toNode());
  }

  private void setupSplitPane() {
    SplitPane splitPane = new SplitPane(setupLeftSide(), setupRightSide());
    splitPane.setDividerPositions(0.7f);
    setCenter(splitPane);
  }

  protected abstract Node setupRightSide();

  protected abstract Node setupLeftSide();
}

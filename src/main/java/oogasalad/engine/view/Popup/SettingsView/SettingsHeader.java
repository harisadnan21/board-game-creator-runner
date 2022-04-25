package oogasalad.engine.view.Popup.SettingsView;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SettingsHeader {

  VBox headerLayout;
  Text header;

  public SettingsHeader(String headerText) {
    createLayout();
    createHeaderText(headerText);
  }

  public VBox getHeaderLayout() {
    return headerLayout;
  }

  private void createLayout() {
    headerLayout = new VBox();
    headerLayout.setId("header-layout");
  }
  private void createHeaderText(String headerText) {
    header = new Text(headerText);
    header.setId("settings-header");
    headerLayout.getChildren().add(header);
  }
}

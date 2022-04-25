package oogasalad.engine.view.Popup.SettingsView;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * header for the settings menu
 *
 * @author Cynthia France
 */
public class SettingsHeader {

  VBox headerLayout;
  Text header;

  /**
   *
   * creates a text header for the settings menu
   *
   * @param headerText the text that the header will display
   */
  public SettingsHeader(String headerText) {
    createLayout();
    createHeaderText(headerText);
  }

  /**
   *
   * the header
   *
   * @return the header
   */
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

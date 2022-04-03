package oogasalad.engine.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SettingsControlPanel extends ControlPanel {
  public static String INFO_IMAGE = IMAGES_FOLDER + "info.png";
  public static String SETTINGS_IMAGE = IMAGES_FOLDER + "gear.png";
  
  Button info;
  Button settings;

  public SettingsControlPanel() {
    root = new VBox();
    root.setSpacing(5);
    createButtons();
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    info = createButton(INFO_IMAGE);
    settings = createButton(SETTINGS_IMAGE);
    root.getChildren().addAll(info, settings);
  }
}

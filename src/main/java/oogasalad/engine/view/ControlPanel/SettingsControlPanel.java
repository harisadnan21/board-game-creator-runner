package oogasalad.engine.view.ControlPanel;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import oogasalad.engine.view.ControlPanel.ControlPanel;

public class SettingsControlPanel extends ControlPanel {
  public static String INFO_IMAGE = IMAGES_FOLDER + imBundle.getString("Info");
  public static String SETTINGS_IMAGE = IMAGES_FOLDER + imBundle.getString("Gear");
  
  Button info;
  Button settings;

  public SettingsControlPanel() {
    super();
  }

  public Node getRoot() {
    return root;
  }

  protected void createButtons() {
    info = createButton(INFO_IMAGE);
    //info.setOnAction(e -> myController.startGame());
    settings = createButton(SETTINGS_IMAGE);
    //settings.setOnAction(e -> dhfkjrsngk);
    root.getChildren().addAll(info, settings);
  }
  public Button getInfoButton(){
    return info;
  }
}

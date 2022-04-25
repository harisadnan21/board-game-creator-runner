package oogasalad.engine.view.setup.dashboard;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.function.BiConsumer;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.engine.model.parser.GameParser;
import oogasalad.engine.view.ApplicationAlert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GameIcon extends VBox {
  public static final int ICON_WIDTH = 150;
  public static final int ICON_HEIGHT = 150;
  public static final String CONFIG_FILE = "config.json";
  private ImageView image;
  private String myName;
  private File myGameFolder;
  private BiConsumer<Map<String, String>, File> myUpdateInfo;

  public static final FileFilter getConfigFile = file -> file.getName().equals(CONFIG_FILE);

  private static final Logger LOG = LogManager.getLogger(GameIcon.class);

  public GameIcon(
      BiConsumer<Map<String, String>, File> updateInfo,
      File gameFolder, String imagePath, String name) {
    image = new ImageView(new Image(imagePath, ICON_WIDTH, ICON_HEIGHT, true, true));
    this.getStyleClass().add("gameIcon");
    myName = name;
    myGameFolder = gameFolder;
    myUpdateInfo = updateInfo;
    createIcon();
    addMouseClick();
  }

  private void createIcon() {
    this.getChildren().add(image);
    Label name = new Label(myName);
    name.getStyleClass().add("iconName");
    this.getChildren().add(new Text(myName));
  }

  private void addMouseClick(){
    this.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
      iconClicked();
    });
  }

  private void iconClicked() {
    GameParser parser = new GameParser(getConfigFile());
    LOG.info(getConfigFile());
    try{
      LOG.info(parser.readMetadata());
      myUpdateInfo.accept(parser.readMetadata(), myGameFolder);
    }
    catch(FileNotFoundException | NullPointerException e ){
      LOG.error(e);
      new Alert(Alert.AlertType.ERROR, "Error in config file").showAndWait();
    }
  }

  private File getConfigFile() {
    File configFile = null;
    try {
      configFile = myGameFolder.listFiles(getConfigFile)[0];
    }
    catch(NullPointerException  | ArrayIndexOutOfBoundsException e){
      LOG.error(e);
      new Alert(Alert.AlertType.ERROR, "Error in config file").showAndWait();
    }
    return configFile;
  }

}

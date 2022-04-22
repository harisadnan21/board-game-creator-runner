package oogasalad.engine.view.setup.dashboard;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import oogasalad.engine.model.parser.GameParser;
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
    GameParser parser = new GameParser(
        Objects.requireNonNull(myGameFolder.listFiles(getConfigFile))[0]);
    LOG.info((Objects.requireNonNull(myGameFolder.listFiles(getConfigFile)))[0]);

    try{
      LOG.info(parser.readMetadata());
      myUpdateInfo.accept(parser.readMetadata(), myGameFolder);
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public static final FileFilter getConfigFile = file -> file.getName().equals(CONFIG_FILE);



}

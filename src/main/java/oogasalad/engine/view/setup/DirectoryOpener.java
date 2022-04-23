package oogasalad.engine.view.setup;

import java.io.File;
import java.util.ResourceBundle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * allows user to choose a directory
 *
 * @author Cynthia France
 */
public class DirectoryOpener {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";

  private DirectoryChooser myDirectoryChooser;
  private ResourceBundle myResources;

  /**
   * allows user to choose a directory
   */
  public DirectoryOpener(){
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myDirectoryChooser = makeDirectoryChooser();
  }

  /**
   *
   * returns the file/directory that the user chooses
   *
   * @param stage new stage for the chooser window to appear
   * @return the file/directory that the user chooses
   */
  public File fileChoice(Stage stage) {
    File dataFile = myDirectoryChooser.showDialog(stage);
    return dataFile;
  }


  private DirectoryChooser makeDirectoryChooser() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle(myResources.getString("ChooseFiles"));
    directoryChooser.setInitialDirectory(new File("data/games"));
    return directoryChooser;
  }
}


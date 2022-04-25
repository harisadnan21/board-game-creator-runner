package oogasalad.engine.view.setup;

import java.io.File;
import java.util.ResourceBundle;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * @author Cynthia France
 */
public class DirectoryOpener {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/engine-view/languages/";

  private DirectoryChooser myDirectoryChooser;
  private ResourceBundle myResources;

  public DirectoryOpener(){
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myDirectoryChooser = makeDirectoryChooser();
  }


  public File fileChoice(Stage stage) {
    File dataFile = myDirectoryChooser.showDialog(stage);
    return dataFile;
  }


  private DirectoryChooser makeDirectoryChooser() {
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle(myResources.getString("ChooseFiles"));
    directoryChooser.setInitialDirectory(new File("data/games"));
    //directoryChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    return directoryChooser;
  }
}


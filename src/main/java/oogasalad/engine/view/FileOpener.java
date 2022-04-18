package oogasalad.engine.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;

public class FileOpener {
  public static final String DEFAULT_RESOURCE_PACKAGE = "/languages/";

  private FileChooser myFileChooser;
  private ResourceBundle myResources;

  public FileOpener(){
    String language = "English";
    myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
    myFileChooser = makeFileChooser();
  }


  public File fileChoice(Stage stage) {
    File dataFile = myFileChooser.showOpenDialog(stage);
    return dataFile;
  }


  private FileChooser makeFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle(myResources.getString("ChooseFiles"));
    fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
    return fileChooser;
  }

  public JSONObject getRootObject(File file) {
    String fileContent = fileToString(file);
    JSONObject object = new JSONObject(fileContent);
    return object;
  }

  public String fileToString(File file){
    String content = null;
    try {
      content = Files.readString(Paths.get(file.getPath()));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content;
  }

}


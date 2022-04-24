package oogasalad.engine.view;

import java.util.ResourceBundle;
import javafx.scene.control.Alert;

public class ApplicationAlert {

  public ApplicationAlert(String alertType, String str) {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(alertType);
    alert.setHeaderText(null);
    alert.setContentText(str);
    alert.showAndWait();
  }
}

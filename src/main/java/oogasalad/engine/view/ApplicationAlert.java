package oogasalad.engine.view;

import javafx.scene.control.Alert;

/**
 *
 * alert for errors and notifications to the user
 *
 * @author Cynthia France
 */
public class ApplicationAlert {

  /**
   *
   * creates an alert popup
   *
   * @param alertType type of alert
   * @param str the message
   */
  public ApplicationAlert(String alertType, String str) {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(alertType);
    alert.setHeaderText(null);
    alert.setContentText(str);
    alert.showAndWait();
  }
}

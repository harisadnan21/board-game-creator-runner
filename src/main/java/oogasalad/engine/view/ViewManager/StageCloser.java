package oogasalad.engine.view.ViewManager;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageCloser {

  List<Stage> stages;
  public StageCloser() {
    stages = new ArrayList<>();
  }

  public List<Stage> closeStage(List<Stage> gameStages, Scene scene) {
    copyOf(gameStages);
    Stage stage = findClosedStage(scene);
    stages.remove(stage);
    stage.close();
    return stages;
  }

  private Stage findClosedStage(Scene scene) {
    for (Stage stage : stages) {
      if (stage.getScene().equals(scene)) {
        return stage;
      }
    }
    return new Stage();
  }

  private void copyOf(List<Stage> gameStages) {
    for (Stage s : gameStages) {
      stages.add(s);
    }
  }

}

package oogasalad.builder.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


class BuilderViewTest extends DukeApplicationTest {

  private BuilderView builderView;

  @Override
  public void start(Stage stage) {
    builderView =  new BuilderView(stage);
  }

  @Test
  public void clickAddPieceTest() {

    clickOn(0, 0, MouseButton.PRIMARY);
    int[][] boardConfig = builderView.getBoardConfig();

    assertEquals(1, boardConfig[0][0]);

  }

}


package oogasalad.builder.view;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;


class BuilderViewTest extends DukeApplicationTest {

  private BuilderView builderView;
  private BorderPane boardTab;

  @Override
  public void start(Stage stage) {
    builderView =  new BuilderView(stage);

    boardTab = lookup("#boardTab").query();
  }

  @Test
  public void clickAddPieceTest() {

    clickOn(boardTab, 1,1);
    sleep(500);
    int[][] boardConfig = builderView.getBoardConfig();

    assertEquals(1, boardConfig[0][0]);

  }

}


package oogasalad.builder;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Spinner;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oogasalad.builder.controller.BuilderController;
import oogasalad.builder.view.BuilderView;
import oogasalad.builder.view.BuilderViewAccessor;
import oogasalad.builder.view.callback.CallbackDispatcher;
import oogasalad.builder.view.callback.GetHeightCallback;
import oogasalad.builder.view.callback.GetWidthCallback;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class BuilderTest extends DukeApplicationTest {
  private static final int X_DIM = 10;
  private static final int Y_DIM = 14;

  private CallbackDispatcher callbackDispatcher;
  @Override
  public void start(Stage stage) {
    BuilderView builderView = new BuilderView(stage);
    new BuilderController(builderView);
    callbackDispatcher = BuilderViewAccessor.getCallbackDispatcher(builderView);
  }


  @Test
  public void testUnevenBoardShapes(){
    clickOn("#boardTab");
    var xSpinner = lookup("#xDimEntry").queryAs(Spinner.class);
    var ySpinner = lookup("#yDimEntry").queryAs(Spinner.class);
    xSpinner.getEditor().setText("" + X_DIM);
    ySpinner.getEditor().setText("" + Y_DIM);
    xSpinner.commitValue();
    ySpinner.commitValue();
    lookup("#colorPickerA").queryAs(ColorPicker.class).setValue(Color.BLUE);
    clickOn("#drawBoard");
    assertEquals(callbackDispatcher.call(new GetHeightCallback()).orElseThrow(), Y_DIM);
    assertEquals(callbackDispatcher.call(new GetWidthCallback()).orElseThrow(), X_DIM);
  }
}

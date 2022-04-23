package oogasalad.builder.view;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oogasalad.builder.model.exception.MissingRequiredPropertyException;
import oogasalad.builder.model.property.IntegerProperty;
import oogasalad.builder.model.property.StringProperty;
import oogasalad.builder.view.callback.ClearCellCallback;
import oogasalad.builder.view.callback.FindCellBackgroundCallback;
import oogasalad.builder.view.callback.FindPieceAtCallback;
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertiesCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.GetHeightCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.GetWidthCallback;
import oogasalad.builder.view.callback.LoadCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.boardTab.BoardTabAccessor;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.matcher.base.WindowMatchers.isFocused;


class BuilderViewTest extends DukeApplicationTest {

  private static final int X_DIM = 10;
  private static final int Y_DIM = 14;
  private static final String CHECKERS_PATH = "/games/checkers";

  private BuilderView builderView;
  @Override
  public void start(Stage stage) {
    builderView = new BuilderView(stage);
    builderView.registerCallbackHandler(GetHeightCallback.class, cb -> 5);
    builderView.registerCallbackHandler(GetWidthCallback.class, cb -> 5);
    builderView.registerCallbackHandler(FindCellBackgroundCallback.class, cb -> "0xffffffff");
    builderView.registerCallbackHandler(FindPieceAtCallback.class, cb -> "empty");
    builderView.registerCallbackHandler(GetElementNamesCallback.class, cb-> List.of("test"));
    builderView.registerCallbackHandler(GetElementPropertiesCallback.class, cb -> List.of());
    builderView.registerCallbackHandler(
        GetPropertiesCallback.class, cb -> cb.type().equals("piece") ? List.of(
            new IntegerProperty("required-id", 0, "oogasalad.builder.view.property.IntegerSelector")) : List.of(
             new StringProperty("required-author", "me", "oogasalad.builder.view.property.StringField"),
            new StringProperty("required-description", "descriptionTEST", "oogasalad.builder.view.property.TextAreaField" )));
    clickOn("#loginButton");
  }

  @Test
  void testLogin() {
    // We know the board tab will exist after logging in, so just use that to check
    assertTrue(lookup("#boardTab").tryQuery().isPresent());
    assertTrue(lookup("#pieceTab").tryQuery().isPresent());
    assertTrue(lookup("#actionTab").tryQuery().isPresent());

  }


  @Test
  public void testLoading(){
    //clickOn("#loadGameButton");
    builderView.getAllTabs().loadAllTabs();
    clickOn("#pieceTab");
    assertTrue(lookup("#testCell").tryQuery().isPresent());
   // assertEquals(Color.valueOf("0xffffffff"),BoardTabAccessor.getColor(lookup("#boardTabPane").queryAs(BoardTab.class), 1));
  }

  @Test
  public void testMetaDataAndSave() throws InterruptedException {
    clickOn("#saveGameButton");
    assertTrue(lookup("#metaBox").tryQuery().isPresent());
    assertEquals(lookup("#stringField-author").queryTextInputControl().getText(), "me");
    assertEquals(lookup("#textArea-description").queryTextInputControl().getText(), "descriptionTEST");
    assertDoesNotThrow(() -> clickOn("#metaSaveButton"));
  }

}

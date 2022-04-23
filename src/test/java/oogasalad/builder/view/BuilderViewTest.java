package oogasalad.builder.view;

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
import oogasalad.builder.view.callback.GetElementNamesCallback;
import oogasalad.builder.view.callback.GetElementPropertyByKeyCallback;
import oogasalad.builder.view.callback.GetPropertiesCallback;
import oogasalad.builder.view.callback.MakeBoardCallback;
import oogasalad.builder.view.callback.PlacePieceCallback;
import oogasalad.builder.view.tab.boardTab.BoardTab;
import oogasalad.builder.view.tab.boardTab.BoardTabAccessor;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;


class BuilderViewTest extends DukeApplicationTest {

  private static final int X_DIM = 10;
  private static final int Y_DIM = 14;

  private BuilderView builderView;
  private Stage stage;
  private Stack<PlacePieceCallback> piecePlacedCB = new Stack<>();
  private Stack<ClearCellCallback> pieceErasedCB = new Stack<>();
  private Stack<MakeBoardCallback> makeBoardCB = new Stack<>();

  @Override
  public void start(Stage stage) {
    this.stage = stage;
    builderView = new BuilderView(stage);
    builderView.registerCallbackHandler(
        GetPropertiesCallback.class, cb -> List.of(
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

  }

  @Test
  public void testMetaDataAndSave(){
    clickOn("#saveButton");
    //var desriptionText = lookup("#metaScene").lookup("#textArea-description").queryAs(TextArea.class);
  //  assertTrue(lookup("#metaBox").tryQuery().isPresent());
   // assertTrue(lookup("#stringField-author").tryQuery().isPresent());
   // interact(lookup("#metaScene"));
  }

}
